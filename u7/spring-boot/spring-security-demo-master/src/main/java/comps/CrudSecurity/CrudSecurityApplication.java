package comps.CrudSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, ContactsRepository.class})
public class CrudSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSecurityApplication.class, args);
	}

}
