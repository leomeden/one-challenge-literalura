package com.one.challenge_literalura.model;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "idiomas")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String idioma;
/*
    @ManyToMany(mappedBy = "idiomas", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<Libro>();
*/
    public Idioma() {}

    public Idioma(String idioma) {
        this.idioma =idioma;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Idioma)) return false;
        return id != null && id.equals(((Idioma) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
}
