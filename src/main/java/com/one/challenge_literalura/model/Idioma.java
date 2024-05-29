package com.one.challenge_literalura.model;

public class Idioma {
    private Long id;
    private String idioma;

    public Idioma() {}

    public Idioma(DatosLibros datosLibros) {
        this.idioma = datosLibros.idiomas().get(0);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}
