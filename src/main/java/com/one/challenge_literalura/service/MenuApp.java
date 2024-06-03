package com.one.challenge_literalura.service;

import com.one.challenge_literalura.principal.BusquedaAutorTitulo;

public class MenuApp {



    private String saludoInicial = """
            
            ######################################################################
                    Bienvenido a nuestra aplicación de busqueda de libros
            ###################################################################### 
            """;

    private String menuPrincipal= """
        
        ################ MENÚ ################
        
        Elija su opción:
            
            1- Buscar Libro por Titulo o Autor
            2- Listar Libros registrados
            3- Listar Autores registrados
            4- Listar Autores vivos según año
            5- Listar Libros por idioma
            0- Salir de la aplicación
            
        ######################################
        """;

    public void mostrarSaludoInicial(){
        System.out.println(saludoInicial);
    }

    public void mostrarMenuPrincipal(){
        System.out.println(menuPrincipal);
    }




}
