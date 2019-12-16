package com.dei.xpand.planets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.dei.xpand.planets.repository", "com.dei.xpand.planets.security"})
@EntityScan({"com.dei.xpand.planets.domain", "com.dei.xpand.planets.security"})
public class PlanetsApplication
{
	public static void main(String[] args) {
		SpringApplication.run(PlanetsApplication.class, args);
	}
}
