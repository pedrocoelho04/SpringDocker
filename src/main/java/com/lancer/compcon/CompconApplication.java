package com.lancer.compcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompconApplication {

	public static void main(String[] args) {
		// Por algum motivo a aplicação começou a desligar apos terminar de executar o codigo,
		// como se não tivesse a dependencia de ser uma API.
		// então procurei uma forma de forçar:
	    SpringApplication app = new SpringApplication(CompconApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET); // com isso
        app.run(args); // e isso
	}

}
