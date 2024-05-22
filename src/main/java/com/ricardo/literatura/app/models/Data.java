package com.ricardo.literatura.app.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
//Clase que representa los datos recibidos de la API gutendex.com.
@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(@JsonAlias("results") List<DataBook> resultados) {
}
