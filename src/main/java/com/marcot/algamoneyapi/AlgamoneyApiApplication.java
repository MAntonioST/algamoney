package com.marcot.algamoneyapi;

import com.marcot.algamoneyapi.config.security.property.AlgamoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}

}
