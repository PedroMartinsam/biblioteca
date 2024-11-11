package com.atividade.Livro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.atividade.Livro")
@EntityScan(basePackages ={ "com.atividade.Livro.domains", "com.atividade.Livro.domains.enums"})
@EnableJpaRepositories(basePackages = "com.atividade.Livro.repositories")
@SpringBootApplication
public class LivroApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivroApplication.class, args);
	}

}
