package com.ricardo.literatura.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DataPerson> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double downloadCount
) {

	
}
