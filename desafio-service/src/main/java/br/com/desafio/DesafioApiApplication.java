package br.com.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "br.com.desafio.models" })
@EnableJpaRepositories(basePackages = { "br.com.desafio.repositories" })
@ComponentScan(basePackages = { "br.com.desafio.business", "br.com.desafio.controllers", "br.com.desafio.repositories",
		"br.com.desafio.configs" })
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class DesafioApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApiApplication.class, args);
	}

	
}
