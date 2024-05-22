package com.ricardo.literatura.app.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

//Clase que representa un persona(autor) en el sistema.	
@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer birthYear;
	private Integer deahtYear;
	@ManyToMany(mappedBy = "persons", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Book> books = new ArrayList<>();

	// Constructor vacío requerido por JPA.
	public Person() {
	}

	// Constructor completo para inicializar todos los atributos de una persona.
	public Person(String name, Integer birthYear, Integer deahtYear) {
		this.name = name;
		this.birthYear = birthYear;
		this.deahtYear = deahtYear;
	}

	// Métodos getters y setters
	public String getName() {
		return name;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getDeahtYear() {
		return deahtYear;
	}

	public void setDeahtYear(Integer deahtYear) {
		this.deahtYear = deahtYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
		for (Book book : books) {
			book.getPersons().add(this);
		}
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//Método toString para obtener una representación en forma de cadena del objeto Person.
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthYear=" + birthYear + ", deahtYear=" + deahtYear + "]";
	}


}
