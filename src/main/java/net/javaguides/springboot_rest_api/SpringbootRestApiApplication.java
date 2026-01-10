package net.javaguides.springboot_rest_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(
		title = "User Management API",
		version = "1.0",
		description = "API for managing users",
		contact = @Contact(
			name = "Asif Jaman",
			email = "",
			url = "https://www.example.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "http://www.apache.org/licenses/LICENSE-2.0.html"
		)
	),
	externalDocs = @ExternalDocumentation(
			description = "Spring Boot User Management Documentation",
			url = "https://www.example.com"
	)
)
public class SpringbootRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
	}
}
