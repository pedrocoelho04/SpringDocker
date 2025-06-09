package com.lancer.compcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompconApplication {

	public static void main(String[] args) {
	    SpringApplication app = new SpringApplication(CompconApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);
	}

}
