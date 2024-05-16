package com.ricardo.literatura.app.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DataPerson> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double numeroDeDescargas
) {
}
