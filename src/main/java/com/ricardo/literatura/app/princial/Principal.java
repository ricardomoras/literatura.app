package com.ricardo.literatura.app.princial;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.literatura.app.models.Book;
import com.ricardo.literatura.app.models.Data;
import com.ricardo.literatura.app.models.DataBook;
import com.ricardo.literatura.app.models.Person;
import com.ricardo.literatura.app.repositories.BookRepository;
import com.ricardo.literatura.app.repositories.PersonRepository;
import com.ricardo.literatura.app.services.ConvertData;
import com.ricardo.literatura.app.services.GetAPI;

/*
 * En esta clase se generan el menu y los diferentes metodos para cargar los datos de la API gutendex.com,
 * para posteriormente guardarse en una base de datos, se evita tener duplicidades de tanto libros como autores
 * */
@Service
public class Principal {

	// Llama a la clase Scaner para poder buscar la informacion
	Scanner keyBoard = new Scanner(System.in);
	// Se carga la informacion de la API gutendex.com
	private GetAPI getApi = new GetAPI();
	private final String URL_BASE = "https://gutendex.com/books/?search=";

	// Se combierte el json en clases
	private ConvertData convertData = new ConvertData();

	// Se llama a los repositorios para acceder a la base de datos
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	PersonRepository personRepository;

	public Principal(BookRepository bookRepository, PersonRepository personRepository) {
		this.bookRepository = bookRepository;
		this.personRepository = personRepository;
	}

	// Es el menu de opciones para llamar a los metodos
	public void showMenu() {

		var opcion = -1;
		while (opcion != 0) {
			var menu = """
					---------------------------------
					Elija la opcion a través de su numero
					1 - buscar libro por titulo
					2 - buscar libro registrados por titulo
					3 - listar libros registrados
					4 - listar autores registrados
					5 - listar autores vivos en un determinado año
					6 - listar libros por idioma


					0 - Salir
					""";
			System.out.println(menu);

			try {
				opcion = keyBoard.nextInt();
				keyBoard.nextLine();
			} catch (Exception e) {
				System.out.println("Opción inválida");
				keyBoard.nextLine();
				continue;
			}

			switch (opcion) {
			case 1:
				findBookWeb();
				break;
			case 2:
				findByTitle();
				break;
			case 3:
				findAllBooks();
				break;
			case 4:
				findAllPersons();
				break;
			case 5:
				findPersonByYear();
				break;
			case 6:
				findLanguages();
				break;
			}
		}
	}
	/*
	 * Metodos que se encarga de convertir la clase DataBook en las clases Book y
	 * Person ademas de validar que no exista en la base de datos y guardar los
	 * datos
	 */

	private void findBookWeb() {
		DataBook data = getDataBook();

		Book boo2 = new Book();
		List<Person> persons = null;
		try {
			persons = data.author().stream().map(p -> new Person(p.name(), p.birthYear(), p.deathYear()))
					.collect(Collectors.toList());
		} catch (NullPointerException e) {
			System.out.println("Libro no encontrado o nulls");
			showMenu();
		}

		List<Person> person2 = null;
		for (int i = 0; i < persons.size(); i++) {
			String name = persons.get(i).getName();
			person2 = personRepository.findByName(name);
		}
		if (person2.isEmpty()) {
			for (Person person : persons) {
				personRepository.save(person);
			}
		} else {
			for (Person person : person2) {
				personRepository.save(person);
				persons = person2;
			}
		}

		boo2.setTitle(data.title());
		boo2.setPersons(persons);
		boo2.setLanguages(data.languages());
		boo2.setDownloadCount(data.downloadCount());

		List<Book> findBook = bookRepository.findByTitleContainsIgnoreCase(boo2.getTitle());

		if (findBook.isEmpty()) {
			bookRepository.save(boo2);
		} else {
			System.out.println("El libro ya esta en la base de datos");
		}

	}

	// Se obtiene los datos de la API y se convierte a la clase DataBook
	private DataBook getDataBook() {
		System.out.println("Escribe el nombre del libro");
		var nameBook = keyBoard.nextLine();
		var json = getApi.getData(URL_BASE + nameBook.replace(" ", "%20"));
		var data = convertData.getData(json, Data.class);
		Optional<DataBook> datos = data.resultados().stream()
				.filter(l -> l.title().toUpperCase().contains(nameBook.toUpperCase())).findFirst();
		DataBook book2 = null;

		if (datos.isPresent()) {
			System.out.println("Libro encontrado");
			book2 = new DataBook(datos.get().title(), datos.get().author(), datos.get().languages(),
					datos.get().downloadCount());
		} else {
			System.out.println("Libro no encontrado");
		}

		return book2;
	}

	// Se busca dentro de la base datos por nombre de la clase Book
	private void findByTitle() {
		System.out.println("Escriba el nombre del libro que desea buscar");
		String title = keyBoard.nextLine();
		List<Book> book1 = bookRepository.findByTitleContainsIgnoreCase(title);
		System.out.println(book1);

	}

	// Se busca dentro de la base dato la clase Book
	private void findAllBooks() {
		List<Book> allbooks = bookRepository.findAll();
		extractedBook(allbooks);

	}

	// Se busca dentro de la base dato la clase Person
	private void findAllPersons() {
		List<Person> allPersons = personRepository.findAll();
		extractedPerson(allPersons);
	}

	//Busca dentro de la base de datos por  año para determinar si estaba vivo
	private void findPersonByYear() {
		System.out.println("Ingresa el año vivo del autor(es) que decea buscar");
		Integer year = keyBoard.nextInt();
		List<Person> allPersons = personRepository.findYear(year);
		extractedPerson(allPersons);
	}

	//Busca dentro de la base de datos los libros por idioma
	private void findLanguages() {
		System.out.println("""
				Escriba el idioma del libro que desea buscar:
				es- español
				en- ingles
				fr- francés
				pt- portugués
				""");
		String lang = keyBoard.nextLine();
		List<Book> allbooks = bookRepository.findByLanguages(lang);
		if (allbooks.isEmpty()) {
			System.out.println("No hay libros en ese idioma");
		}
		extractedBook(allbooks);
	}

	//Metodo para mostrar la informaion de libros en pantalla
	private void extractedBook(List<Book> allbooks) {
		for (Book b : allbooks) {

			String name = b.getPersons().stream().map(Person::getName).collect(Collectors.joining(", "));
			String lenguaje = b.getLanguages().stream().collect(Collectors.joining(", "));
			String print = String.format("""
					----- LiBRO -----
					Titulo: %s
					Autor: %s
					Idioma: %s
					Numero de descargas: %.0f
					------------------
					""", b.getTitle(), name, lenguaje, b.getDownloadCount());
			System.out.println(print);
		}
	}

	//Metodo para mostrar la informaion de personas en pantalla
	private void extractedPerson(List<Person> allPersons) {
		for (Person p : allPersons) {
			String book = p.getBooks().stream().map(Book::getTitle).collect(Collectors.joining(", "));

			String print = String.format("""
					-----------------
					Autor: %s
					Fecha de nacimiento: %d
					Fecha de fallecimiento: %d
					Libros: %s
					------------------
					""", p.getName(), p.getBirthYear(), p.getDeahtYear(), book);
			System.out.println(print);
		}
	}
}
