package br.com.firstsoft.backendstateless;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendStatelessApplication {
	public static void main(String[] args) {
		System.out.println("Teste");
		SpringApplication.run(BackendStatelessApplication.class, args);
	}
}
