package one.digitalinnovation.santander_dev_week_2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
