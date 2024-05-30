package com.one.challenge_literalura.model;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Double descargas;
    @ManyToMany(/*targetEntity = Autor.class,*/ cascade = {
            /*CascadeType.PERSIST,
            CascadeType.MERGE*/
            CascadeType.ALL

    })
    @JoinTable(
            name="autor_libro",
            joinColumns = @JoinColumn(name= "libros_id"),
            inverseJoinColumns = @JoinColumn(name="autores_id")
    )
    /*@JoinTable(name = "autor_libro", joinColumns = {
            @JoinColumn(name = "libros_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "autores_id", referencedColumnName = "id")}, uniqueConstraints = @UniqueConstraint(columnNames = {
            "libros_id", "autores_id" }))*/
    private Set<Autor> autores = new HashSet<Autor>();
    //private List<Autor> autores = new ArrayList<Autor>();

    @ManyToMany(targetEntity = Idioma.class, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name="idioma_libro",
            joinColumns = @JoinColumn(name= "libros_id"),
            inverseJoinColumns = @JoinColumn(name="idiomas_id")
    )
    /*@JoinTable(name = "idioma_libro", joinColumns = {
            @JoinColumn(name = "libros_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "idiomas_id", referencedColumnName = "id")}, uniqueConstraints = @UniqueConstraint(columnNames = {
            "libros_id", "idiomas_id" }))*/
    private Set<Idioma> idiomas = new HashSet<Idioma>();

    public Libro(){};

    public Libro(DatosLibros datosLibros) {

        this.titulo = datosLibros.titulo();
        this.descargas = datosLibros.numeroDeDescargas();
        /*this.autores = datosLibros.autor().stream()
                .map(a-> new Autor(a))
                .collect(Collectors.toSet());
        this.idiomas = datosLibros.idiomas().stream()
                .map(i-> new Idioma(i))
                .collect(Collectors.toSet());*/
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

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
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
