package comps368.u7.advsetting.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService users) throws Exception {
        http
            .logout(withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/logout", "/resources/**").permitAll()
                .requestMatchers("/user").hasAnyRole("ROLE_USER","ROLE_ADMIN")
                .requestMatchers("/admin").hasRole("ROLE_ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .sessionManagement(sessions -> sessions
                .sessionConcurrency(concurrency -> concurrency
                    .maximumSessions(1)
                    .expiredUrl("/login?expired")
                )
            )
            .rememberMe(rememberMe -> rememberMe.userDetailsService(users))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/login", "/logout", "/resources/**"));
		return http.build();
	}

    //In memory hardcode user store
    @Bean
    @SuppressWarnings("deprecation")
	public InMemoryUserDetailsManager userDetailsService() {
        UserDetails visitor = User.withDefaultPasswordEncoder()
                .username("visitor")
                .password("visitor")
                .roles("VISITOR")
                .build();      

		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("user")
				.roles("USER")
				.build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();       
		return new InMemoryUserDetailsManager(visitor, user, admin);
	}
}