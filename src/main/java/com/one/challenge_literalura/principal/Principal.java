package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.excepciones.OpcionIncorrectaMenu;

import com.one.challenge_literalura.model.Autor;
import com.one.challenge_literalura.model.DatosLibros;
import com.one.challenge_literalura.model.Idioma;
import com.one.challenge_literalura.model.Libro;
import com.one.challenge_literalura.repository.AutorRepository;
import com.one.challenge_literalura.repository.IdiomaRepository;
import com.one.challenge_literalura.repository.LibroRepository;
import com.one.challenge_literalura.service.MenuApp;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public MenuApp menuApp = new MenuApp();
    private Scanner teclado = new Scanner(System.in);
    private boolean salir = false;
    int opcion;
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private IdiomaRepository idiomaRepositorio;
    public BusquedaAutorTitulo busqueda = new BusquedaAutorTitulo();
    private DatosLibros libroBuscado;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository, IdiomaRepository idiomaRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
        this.idiomaRepositorio = idiomaRepository;
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
                List<Libro> libroList = libroRepositorio.findAll();
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
        Set<Autor> autores = libroBuscado.autor().stream()
                        .map(a -> new Autor(a))
                                .collect(Collectors.toSet());
        autores.forEach(a-> libro.addAutor(a));

        Set<Idioma> idiomas = libroBuscado.idiomas().stream()
                .map(i -> new Idioma(i))
                .collect(Collectors.toSet());
        idiomas.forEach(i-> libro.addIdioma(i));


        try{
            libroRepositorio.save(libro);
            //preventDuplicates(libro);

        }catch(DataIntegrityViolationException e){
            System.out.println("No se pudo guardar\n");
            System.out.println(e.getMessage());
            libro.getAutores().forEach(a -> {
                        Set<Autor> autoresDB = autorRepositorio.findByNombre(a.getNombre());
                        autoresDB.forEach(b-> {
                            System.out.println("id del elemento: " + b.getId());
                            System.out.println(a.getNombre());
                            System.out.println(b.getNombre());
                            if(a.getNombre().equals(b.getNombre())){
                                a.setId(b.getId());
                                System.out.println("Alcoyana alcoyana");
                                System.out.println(a.getId());
                                //libroRepositorio.save(libro);
                            }
                        });
                    }
            );

            libro.getIdiomas().forEach(a -> {
                        Set<Idioma> idiomasDB = idiomaRepositorio.findByIdioma(a.getIdioma());
                        idiomasDB.forEach(b-> {
                            System.out.println("id del elemento: " + b.getId());
                            System.out.println(a.getIdioma());
                            System.out.println(b.getIdioma());
                            if(a.getIdioma().equals(b.getIdioma())){
                                a.setId(b.getId());
                                System.out.println("Alcoyana alcoyana");
                                System.out.println(a.getId());
                                //libroRepositorio.save(libro);
                            }
                        });
                    }
            );

            //System.out.println(libro);

            libroRepositorio.save(libro);

        }



    }

    //private void preventDuplicates(Libro libro){




}

