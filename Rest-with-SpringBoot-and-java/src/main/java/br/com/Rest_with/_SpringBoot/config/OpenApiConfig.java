package br.com.Rest_with._SpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTfull API with Java 21 and Spring Boot")
						.version("v1")
						.description("Some description about your API")
						.termsOfService("https://github.com/EdersomBarros/Rest-with-SpringBoot-and-java")
						.license(
								new License()
									.name("Apache 2.0")
									.url("https://github.com/EdersomBarros/Rest-with-SpringBoot-and-java")));
	}
	
	

}
