package com.one.challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("count") int cantidad,
        @JsonAlias("next") String proximaPagina,
        @JsonAlias("results") Set<DatosLibros> resultados
) {
}