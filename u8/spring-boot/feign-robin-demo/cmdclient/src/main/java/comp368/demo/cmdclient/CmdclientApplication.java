package comp368.demo.cmdclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("comp368.demo.cmdclient.*")
public class CmdclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmdclientApplication.class, args).close();
	}

}