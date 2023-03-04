package comps.CrudSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsService svc;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(svc).passwordEncoder(GetEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/index").permitAll()
            .anyRequest().fullyAuthenticated()
            .and()
            .logout().permitAll().logoutSuccessUrl("/index")
            .and()
            .formLogin().defaultSuccessUrl("/list", true)
            .and()
            .csrf().disable();
    }

    @Bean
    protected PasswordEncoder GetEncoder(){
        return new BCryptPasswordEncoder();
    }
}