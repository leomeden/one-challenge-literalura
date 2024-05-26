package com.one.challenge_literalura.model;

import java.util.List;

public class DatosClase {

    private List<DatosLibros> libros;

    public List<DatosLibros> getLibros() {
        return libros;
    }

    public void setLibros(List<DatosLibros> libros) {
        this.libros = libros;
    }



    public void agregarLibros(List<DatosLibros> listaLibros){

            this.libros.addAll(listaLibros);

        //System.out.println(this.libros);

    }


}
