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
                imprimirLibros(libroList);
                break;

            case 3:
                System.out.println("Listado de Autores registrados: ");
                Idioma idioma = new Idioma("es");
                System.out.println(traducirLenguaje(idioma));
                break;

            case 4:
                System.out.println("Autores vivos según año: ");
                break;
            case 5:
                System.out.println("Libros por idioma: ");
                break;

            case 0:
                System.out.println("Chau chau: ");
                break;

            default:
                break;
        }
    }

    private void opcionBuscar(){
        libroBuscado = busqueda.buscar();
        //System.out.println("Retornó el libro: " + libroBuscado);
        Libro libro;

        try{
            if(libroBuscado != null) {
                libro = new Libro(libroBuscado);
                //System.out.println("Libro sin autor ni idioma para guardar:" + libro);
                libroRepositorio.save(libro);
                libroBuscado.autor().forEach(a -> {
                    Autor autorYaGuardado = autorRepositorio.findFirstByNombre(a.nombre());
                    if(autorYaGuardado != null) {
                        libro.addAutor(autorYaGuardado);
                    } else{
                        libro.addAutor(new Autor(a));
                    }

                });
                libroBuscado.idiomas().forEach(i -> {
                    Idioma idiomaYaGuardado = idiomaRepositorio.findFirstByIdioma(i);
                    if(idiomaYaGuardado != null) {
                        libro.addIdioma(idiomaYaGuardado);
                    }else {
                        libro.addIdioma(new Idioma(i));
                    }
                    libro.addIdioma(idiomaRepositorio.findFirstByIdioma(i));
                });
                //System.out.println("Libro  con autor e idioma para guardar:" + libro);
                libroRepositorio.save(libro);
                System.out.println("Se guardó el libro de forma exitosa!!");
            }
        }catch(Exception e){
            System.out.println("No se pudo guardar\n");
            System.out.println(e.getMessage());
        }
    }

    private void imprimirLibros(List<Libro> libros){
        libros.forEach(l-> {
            System.out.println("\n########################################");
            System.out.println("Titulo: " + l.getTitulo());
            String autores = l.getAutores().stream()
                    .map(Autor::getNombre)
                    .collect(Collectors.joining(" / "));
            System.out.println("Autores: " + autores);
            System.out.println("Descargas: " + l.getDescargas());
            String idiomas = l.getIdiomas().stream()
                    .map(i -> traducirLenguaje(i))
                    .collect(Collectors.joining(" / "));
            System.out.println("Idiomas: " + idiomas);
            System.out.println("########################################");
        });
    }

        private String traducirLenguaje(Idioma Idioma) {
            String nombre = Idioma.getIdioma();

                Locale nombreIdioma = Locale.forLanguageTag(nombre);
                String traducido = nombreIdioma.getDisplayName();
                return traducido;
        }

}

