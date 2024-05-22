package com.ricardo.literatura.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ricardo.literatura.app.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	List<Person> findByName(String name);
	
	@Query("SELECT p FROM Person p WHERE p.birthYear <= :year AND p.deahtYear >= :year")
	List<Person>findYear(Integer year);
	
}
