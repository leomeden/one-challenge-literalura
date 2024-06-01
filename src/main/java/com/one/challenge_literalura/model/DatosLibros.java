package com.one.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Set<DatosAutor> autor,
        @JsonAlias("languages") Set<String> idiomas,
        @JsonAlias("download_count") Long numeroDeDescargas

) {
}
