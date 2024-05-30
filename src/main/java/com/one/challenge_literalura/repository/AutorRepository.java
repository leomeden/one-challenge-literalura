package com.one.challenge_literalura.repository;

import com.one.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Set<Autor> findByNombre(String nombre);
    //Autor findByNombre(String nombre);

    Autor getIdBynombre(String nombre);
}
