package com.ricardo.literatura.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ricardo.literatura.app.princial.Principal;
import com.ricardo.literatura.app.repositories.BookRepository;
import com.ricardo.literatura.app.repositories.PersonRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private BookRepository repository;

	@Autowired 
	PersonRepository personRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,personRepository);
		principal.showMenu();
	}

}
