package com.one.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String titulo;
    private Double descargas;
    @Transient
    private List<Autor> autores;
    @Transient
    private List<String> idiomas;

    public Libro(){};

    public Libro(DatosLibros datosLibros) {

        this.titulo = datosLibros.titulo();
        this.descargas = datosLibros.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descargas=" + descargas +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }
}
