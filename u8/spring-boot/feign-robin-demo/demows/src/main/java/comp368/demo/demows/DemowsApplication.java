package comp368.demo.demows;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemowsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemowsApplication.class, args);
	}

}
