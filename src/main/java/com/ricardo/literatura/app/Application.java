package com.ricardo.literatura.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ricardo.literatura.app.princial.Principal;
import com.ricardo.literatura.app.repositories.BookRepository;
import com.ricardo.literatura.app.repositories.PersonRepository;

//  Clase principal que inicializa la aplicación de gestión de libros y autores.
@SpringBootApplication
public class Application implements CommandLineRunner {

	// Punto de entrada principal de la aplicación.
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 Repositorios para acceder a la base de datos y se carguen las instancias
	 tanto de libros como de personas
	 */
	 
	@Autowired
	private BookRepository bookRepository;

	@Autowired 
	PersonRepository personRepository;
	
	//Método que se ejecuta al iniciar la aplicación.
	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(bookRepository,personRepository);
		principal.showMenu();
	}

}
