package org.bluewhale.webcontent.configuration;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
// @ComponentScan(basePackageClasses = {
// org.bluewhale.webcontent.controllers.GreetingController.class,
// PersonRepository.class })
@ComponentScan("org.bluewhale.webcontent.controllers")
public class Application {

	public static String ROOT = "upload-dir";

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (final String[] args) -> {
			new File(ROOT).mkdir();
		};
	}
}