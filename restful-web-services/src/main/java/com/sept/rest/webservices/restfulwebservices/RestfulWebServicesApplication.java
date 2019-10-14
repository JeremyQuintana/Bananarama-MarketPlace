package com.sept.rest.webservices.restfulwebservices;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RestController
public class RestfulWebServicesApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
}
//save gijhkhukhsdfsdfsdfsdg