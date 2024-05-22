package com.ricardo.literatura.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ricardo.literatura.app.models.Person;

// Repositorio que gestiona las operaciones CRUD para la entidad Person.
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	//Busca a la clase Person por nombre
	List<Person> findByName(String name);
	
	//Busca detro de Person por un numero mayor o igual a la fecha de nacimienro y menor o igual a la fecha de muerte
	@Query("SELECT p FROM Person p WHERE p.birthYear <= :year AND p.deahtYear >= :year")
	List<Person>findYear(Integer year);
	
}
