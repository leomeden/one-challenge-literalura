package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.excepciones.OpcionIncorrectaMenu;

import com.one.challenge_literalura.model.DatosLibros;
import com.one.challenge_literalura.model.Libro;
import com.one.challenge_literalura.repository.LibroRepository;
import com.one.challenge_literalura.service.MenuApp;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public MenuApp menuApp = new MenuApp();
    private Scanner teclado = new Scanner(System.in);
    private boolean salir = false;
    int opcion;
    private LibroRepository repositorio;
    public BusquedaAutorTitulo busqueda = new BusquedaAutorTitulo();
    private DatosLibros libroBuscado;

    public Principal(LibroRepository repository) {
        this.repositorio = repository;
    }

    public void arrancar() {
        menuApp.mostrarSaludoInicial();


        while(!salir){
            menuApp.mostrarMenuPrincipal();
            try{
                opcion = teclado.nextInt();
                if(opcion > 5 || opcion < 0) {
                    throw new OpcionIncorrectaMenu("Elija una opción correcta!!");
                } else {
                    seleccionDeOpcion(opcion);
                    if (opcion == 0){
                        salir = true;
                    }
                }

            } catch(InputMismatchException e){
                System.out.println("\nDebe ingresar un valor valido!!\n");
                teclado.nextLine();
            } catch(OpcionIncorrectaMenu e) {
                System.out.println(e.getMessage());
                teclado.nextLine();
            }

        }

    }

    private void seleccionDeOpcion(int opcion){
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nombre de Autor o Titulo a buscar: ");
                opcionBuscar();
                break;

            case 2:
                System.out.println("Listado de Libros registrados: ");
                List<Libro> libroList = repositorio.findAll();
                System.out.println(libroList);
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

    private void opcionBuscar(){
        libroBuscado = busqueda.buscar();
        System.out.println("Retornó el libro: " + libroBuscado);
        Libro libro = new Libro(libroBuscado);
        repositorio.save(libro);
    }


}

