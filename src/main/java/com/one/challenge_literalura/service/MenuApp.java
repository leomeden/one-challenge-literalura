package com.one.challenge_literalura.service;

import com.one.challenge_literalura.principal.BusquedaAutorTitulo;

public class MenuApp {

    private String saludoInicial = """
            
            ######################################################################
                    Bienvenido a nuestra aplicación de busqueda de libros
            ######################################################################
            
            """;

    private String menuPrincipal= """
            
        Elija su opción:
            
            1- Buscar Libro por Titulo o Autor
            2- Listar Libros registrados
            3- Listar Autores registrados
            4- Listar Autores vivos según año
            5- Listar Libros por idioma
            0- Salir de la aplicación
        """;

    public void mostrarSaludoInicial(){
        System.out.println(saludoInicial);
    }

    public void mostrarMenuPrincipal(){
        System.out.println(menuPrincipal);
    }

    public void seleccionDeOpcion(int opcion){
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre de Autor o Titulo a buscar: ");
                BusquedaAutorTitulo.buscar();
                break;

            case 2:
                System.out.println("Listado de Libros registrados: ");
                break;

            case 3:
                System.out.println("Listado de Autores registrados: ");
                break;

            case 4:
                System.out.println("Autores vivos según año: ");
                break;
            case 5:
                System.out.println("Libros por idioma: ");
                break;

            case 0:
                System.out.println("Listado de libros registrados: ");
                break;

            default:
                break;
        }
    }


}
