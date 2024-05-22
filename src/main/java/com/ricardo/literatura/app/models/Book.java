package com.ricardo.literatura.app.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "book_person", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> persons = new ArrayList<>();
	
	private List<String> languages;
	
	private Double downloadCount;

	public Book() {
	}

	public Book(String title, List<Person> persons, List<String> languages, Double downloadCount) {
		this.title = title;
		this.persons = persons;
		this.languages = languages;
		this.downloadCount = downloadCount;
	}

	public Book(String title, Double downloadCount) {
		this.title = title;
		this.downloadCount = downloadCount;
	}

	public Book(DataBook data) {
		this.title = data.title();
		this.persons = data.author().stream().map(a -> new Person(a.name(), a.birthYear(), a.deathYear()))
				.collect(Collectors.toList());
		this.languages = data.languages();
		this.downloadCount = data.downloadCount();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
		for (Person person : persons) {
			person.getBooks().add(this);
		}
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public Double getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Double downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Override
	public String toString() {
		return "title=" + title + ", downloadCount=" + downloadCount;
	}

}
