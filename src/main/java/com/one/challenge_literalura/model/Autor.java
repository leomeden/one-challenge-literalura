package com.one.challenge_literalura.model;

import java.time.LocalDate;

public class Autor {

    private Long id;
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaDeMuerte;

    public Autor(){};

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();

        try{
            this.fechaDeNacimiento = LocalDate.parse(datosAutor.fechaDeNacimiento());
        }catch (NumberFormatException e){
            this.fechaDeNacimiento = null;
        }

        try{
            this.fechaDeMuerte = LocalDate.parse(datosAutor.fechaDeMuerte());
        }catch (NumberFormatException e){
            this.fechaDeMuerte = null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public LocalDate getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(LocalDate fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }
}
