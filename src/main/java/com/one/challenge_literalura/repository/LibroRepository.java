package com.one.challenge_literalura.repository;

import com.one.challenge_literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findAll();

    Libro findFirstByTitulo(String titulo);
}
