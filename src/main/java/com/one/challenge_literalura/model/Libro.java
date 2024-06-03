package com.one.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Long descargas;
    @ManyToMany(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="autor_libro",
            joinColumns = @JoinColumn(name= "libros_id"),
            inverseJoinColumns = @JoinColumn(name="autores_id")
    )
    private Set<Autor> autores = new HashSet<Autor>();

    @ManyToMany(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="idioma_libro",
            joinColumns = @JoinColumn(name= "libros_id"),
            inverseJoinColumns = @JoinColumn(name="idiomas_id")
    )

    private Set<Idioma> idiomas = new HashSet<Idioma>();

    public Libro(){};

    public Libro(DatosLibros datosLibros) {

        this.titulo = datosLibros.titulo();
        this.descargas = datosLibros.numeroDeDescargas();
    }

    public void addIdioma(Idioma idioma){
        idiomas.add(idioma);
    }

    public void removeIdioma(Idioma idioma){
        idiomas.remove(idioma);
    }

    public void addAutor(Autor autor){
        autores.add(autor);
    }

    public void removeAutor(Autor autor){
        autores.remove(autor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Libro)) return false;
        return id != null && id.equals(((Libro) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descargas=" + descargas +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
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

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }

    public Set<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Set<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
}
