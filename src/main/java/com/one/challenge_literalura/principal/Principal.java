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
                List<Autor> autorList = autorRepositorio.findAll();
                imprimirAutores(autorList);
                break;

            case 4:
                System.out.println("Autores vivos según año: ");
                buscarAutoresPorAnio();
                break;
            case 5:

                buscarLibrosPorIdioma();
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
                Libro libroYaGuardado = libroRepositorio.findFirstByTitulo(libro.getTitulo());
                if (libroYaGuardado != null) {
                    System.out.println("\nEl libro -> " + libro.getTitulo() + " ya se encuentra en nuestra base de datos!! \n");
                    imprimirLibro(libroYaGuardado);
                } else {
                    libroRepositorio.save(libro);

                    libroBuscado.autor().forEach(a -> {
                        Autor autorYaGuardado = autorRepositorio.findFirstByNombre(a.nombre());
                        if (autorYaGuardado != null) {
                            libro.addAutor(autorYaGuardado);
                        } else {
                            libro.addAutor(new Autor(a));
                        }

                    });
                    libroBuscado.idiomas().forEach(i -> {
                        Idioma idiomaYaGuardado = idiomaRepositorio.findFirstByIdioma(i);
                        if (idiomaYaGuardado != null) {
                            libro.addIdioma(idiomaYaGuardado);
                        } else {
                            libro.addIdioma(new Idioma(i));
                        }
                        libro.addIdioma(idiomaRepositorio.findFirstByIdioma(i));
                    });
                    //System.out.println("Libro  con autor e idioma para guardar:" + libro);
                    libroRepositorio.save(libro);
                    System.out.println("\nEl siguiente Libro se guardó de forma exitosa:\n");
                    imprimirLibro(libro);

                }
            }
        }catch(Exception e){
            System.out.println("No se pudo guardar\n");
            if(e.getMessage().contains("Detail: Ya existe la llave (titulo)")){
                System.out.println("El libro elegido ya existe en la base de datos!!");
            }
            //System.out.println(e.getMessage());
        }
    }

    private void imprimirLibros(List<Libro> libros){
        libros.forEach(l-> {
            imprimirLibro(l);
        });
    }

    private void imprimirLibro(Libro libro) {
        System.out.println("\n########################################");
        System.out.println("Titulo: " + libro.getTitulo());
        String autores = libro.getAutores().stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(" / "));
        System.out.println("Autores: " + autores);
        System.out.println("Descargas: " + libro.getDescargas());
        String idiomas = libro.getIdiomas().stream()
                .map(i -> traducirLenguaje(i))
                .collect(Collectors.joining(" / "));
        System.out.println("Idiomas: " + idiomas);
        System.out.println("########################################");
    }
    private void imprimirAutores(List<Autor> autores){
        autores.forEach(a-> {
            imprimirAutor(a);
        });
    }

    private void imprimirAutor(Autor autor) {
        System.out.println("\n########################################");
        System.out.println("Nombre: " + autor.getNombre());
        System.out.println("Año de nacimiento: " + autor.getFechaDeNacimiento());
        System.out.println("Año de muerte: " + autor.getFechaDeMuerte());
        String libros = autor.getLibros().stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(" / "));
        System.out.println("Libros: " + libros);
        System.out.println("########################################");
    }

    private void buscarAutoresPorAnio(){
        System.out.println("Ingrese año del que desea buscar autores vivos (formato AAAA):");
        boolean continuar = true;
        Integer anio = 0;
        while(continuar){
            try{
                 anio = teclado.nextInt();

                if(anio.toString().length() == 4){
                    continuar = false;
                }else{
                    throw new InputMismatchException("Numero debe ser de 4 digitos");
                }

            } catch(InputMismatchException e){
                System.out.println("Debe ingresar un año en formato número de 4 digitos");
                System.out.println(e.getMessage());
            }
            teclado.nextLine();
        }
        List<Autor> autorAnioList = autorRepositorio.findAllByFechaDeNacimientoLessThanAndFechaDeMuerteGreaterThan(anio, anio);
        if (autorAnioList.size() == 0) {
            System.out.println("\nNo se encontraron autores vivos registrados en el año " + anio + "\n");
        } else {
            System.out.println("\nSe encontraron los siguientes autores registrados vivos en el año " + anio + ":");
            imprimirAutores(autorAnioList);
        }


    }

    private void buscarLibrosPorIdioma(){
        System.out.println("Ingrese idioma del que desea que le mostremos libros: (Ejemplo -> portugues)");
        //teclado.reset();
        teclado.nextLine();
        String idiomaIngresado = teclado.nextLine();
        String tagIdioma = idiomaTagDesdeTexto(idiomaIngresado);
        if (tagIdioma == null){
            System.out.println("No se encontró ningún libro con el idioma ingresado!");
        }else {
            System.out.println("\nSe encontraron los siguientes libros para el idioma " + idiomaIngresado);
            Idioma idioma = idiomaRepositorio.findFirstByIdioma(tagIdioma);
            List<Libro> librosIdioma = libroRepositorio.findAllByIdiomas(idioma);
            imprimirLibros(librosIdioma);

        }

    }

        private String traducirLenguaje(Idioma Idioma) {
            String nombre = Idioma.getIdioma();

                Locale nombreIdioma = Locale.forLanguageTag(nombre);
                String traducido = nombreIdioma.getDisplayName();
                return traducido;
        }

        private String idiomaTagDesdeTexto(String idioma){
            List<String> tags = Arrays.stream(Locale.getISOLanguages()).toList();
            List<String> displayLanguages = tags.stream().map(t-> quitarAcentos(Locale.forLanguageTag(t).getDisplayLanguage())).collect(Collectors.toList());
            idioma = quitarAcentos(idioma);
            String tag = "";
            try{
                return tags.get(displayLanguages.indexOf(idioma));
            }catch(ArrayIndexOutOfBoundsException e){
                return null;
            }


            //System.out.println(t + "-" + Locale.forLanguageTag(t).getDisplayLanguage()));

        }

        private String quitarAcentos(String texto){
        return texto.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .toLowerCase();
        }

}

