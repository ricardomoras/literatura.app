package com.ricardo.literatura.app.repositories;

import com.ricardo.literatura.app.models.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//Repositorio que gestiona las operaciones CRUD para la entidad Book.
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	// Busca libros por título, ignorando mayúsculas y minúsculas.
	List<Book> findByTitleContainsIgnoreCase(String title);

	// Busca libros por idioma utilizando una consulta nativa de SQL
	@Query(value = "SELECT * FROM book b WHERE b.languages LIKE %:language%", nativeQuery = true)
	List<Book> findByLanguages(String language);
}
