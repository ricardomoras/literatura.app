package com.ricardo.literatura.app.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Clase que representa los datos recibidos de las personas(autores) de la API gutendex.com.
@JsonIgnoreProperties(ignoreUnknown = true)
public record DataPerson(@JsonAlias("name") String name, @JsonAlias("birth_year") Integer birthYear,
		@JsonAlias("death_year") Integer deathYear) {
}
