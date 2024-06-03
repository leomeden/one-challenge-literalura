package com.one.challenge_literalura.excepciones;

public class FormatoNoValido extends Exception{
    public FormatoNoValido() {
    }

    public FormatoNoValido(String message) {
        super(message);
    }
}
