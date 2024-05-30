package com.one.challenge_literalura.repository;

import com.one.challenge_literalura.model.Autor;
import com.one.challenge_literalura.model.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IdiomaRepository extends JpaRepository<Idioma, Long> {

    Set<Idioma> findByIdioma(String idioma);
}
