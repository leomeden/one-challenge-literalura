package com.one.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String idioma;

    /*@ManyToMany(mappedBy = "idiomas", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<Libro>();*/

    public Idioma() {}

    public Idioma(String idioma) {
        this.idioma =idioma;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        /*if(!(o instanceof Idioma)) return false;
        return id != null && id.equals(((Idioma) o).getId());*/
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Idioma idioma = (Idioma) o;
        return Objects.equals( id, idioma.id );
    }

    @Override
    public int hashCode() {

        //return getClass().hashCode();
        return Objects.hash( id );
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "id=" + id +
                ", idioma='" + idioma + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
/*
    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }

 */
}
