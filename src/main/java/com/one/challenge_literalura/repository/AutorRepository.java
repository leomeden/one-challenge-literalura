package com.one.challenge_literalura.repository;

import com.one.challenge_literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findFirstByNombre(String nombre);
    List<Autor> findAll();

    List<Autor> findAllByFechaDeNacimientoLessThanAndFechaDeMuerteGreaterThan(int anio, int anio2);

}
