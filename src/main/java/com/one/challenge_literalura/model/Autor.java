package com.one.challenge_literalura.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private int fechaDeNacimiento;
    private int fechaDeMuerte;
    /*@ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<Libro>();*/

    public Autor(){};

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();

        try {
            this.fechaDeNacimiento = Integer.parseInt(datosAutor.fechaDeNacimiento());
        }
        catch (NumberFormatException e){
            this.fechaDeNacimiento = 0;
        }

        try {
            this.fechaDeMuerte = Integer.parseInt(datosAutor.fechaDeMuerte());
        }
        catch (NumberFormatException e){
            this.fechaDeMuerte = 0;
        }


    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", fechaDeMuerte=" + fechaDeMuerte +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        /*if(!(o instanceof Autor)) return false;
        return id != null && id.equals(((Autor) o).getId());*/
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Autor autor = (Autor) o;
        return Objects.equals( id, autor.id );
    }

    @Override
    public int hashCode() {
        //return getClass().hashCode();
        return Objects.hash( id );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(int fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }
/*
    public Set<Libro> getLibros() {
        return libros;
    }
*/
}
