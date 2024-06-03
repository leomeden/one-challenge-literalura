package com.one.challenge_literalura.repository;

import com.one.challenge_literalura.model.Idioma;
import com.one.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findAll();

    Libro findFirstByTitulo(String titulo);

    List<Libro> findAllByIdiomas(Idioma idioma);

//    @Query("SELECT l FROM libros l JOIN l.idiomas i WHERE l.i INCLUDE i")
//    List<Libro> findLibrosByIdioma(Idioma idioma);
}
