package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.excepciones.OpcionIncorrectaMenu;
import com.one.challenge_literalura.model.Datos;
import com.one.challenge_literalura.model.DatosClase;
import com.one.challenge_literalura.service.ConsumoApi;
import com.one.challenge_literalura.service.ConvierteDatos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BusquedaAutorTitulo {

    private static String URL_BASE = "https://gutendex.com/books/?search=";


    public static void buscar(){

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        DatosClase datosBusquedaTotal = new DatosClase();
        boolean proxima = true;
        boolean salir = false;
        int opcion = 0;

        int contadorPaginas = 1;

        Scanner teclado = new Scanner(System.in);
        String entrada = teclado.nextLine();
        entrada = entrada.replace(" ", "%20");

        String respuestaApi = consumoApi.obtenerDatos(URL_BASE + entrada);
        //System.out.println(respuestaApi);


        var datos = conversor.obtenerDatos(respuestaApi, Datos.class);
        if (datos.proximaPagina() == null){
            proxima = false;
        }

        if(datos.cantidad()>50){
            System.out.println("La busqueda arrojo " + datos.cantidad() + " resultados y puede demorar una considerable cantidad de tiempo\n");
            System.out.println("Continúa o desea reiniciar la busqueda siendo mas específico? (s/n)");
            while(!entrada.toLowerCase().equals("n") && !entrada.toLowerCase().equals("s")){
                entrada = teclado.nextLine();
                if(entrada.toLowerCase().equals("n")){
                    proxima = false;
                    //System.out.println("elegi cancelar");
                }
                if(entrada.toLowerCase().equals("s")){
                    datosBusquedaTotal.setLibros(datos.resultados());
                    //System.out.println("elegi seguir");
                }

            }

        }else{
            if(datos.cantidad() < 1) {
                System.out.println("No se encontró ningún Autor o Titulo - Intente con otra busqueda!");
            } else {
                datosBusquedaTotal.setLibros(datos.resultados());
            }

        }




        //System.out.println(datosBusquedaTotal.getLibros());

        while (proxima){
            //System.out.println(datos.proximaPagina());
            int porcentaje = (int)((((double)contadorPaginas*32)*100)/(double)datos.cantidad());
            System.out.println("Aguarde por favor que la lista es extensa y esta demorando! (" + porcentaje + "%)\n");
            contadorPaginas++;
            respuestaApi = consumoApi.obtenerDatos(datos.proximaPagina());
            //System.out.println(respuestaApi);

            datos = conversor.obtenerDatos(respuestaApi, Datos.class);

            if (datos.proximaPagina() == null){
                proxima = false;
            }

            datosBusquedaTotal.agregarLibros(datos.resultados());


        }

        try{
            if(datos.cantidad() > 0){
                if(datosBusquedaTotal.getLibros().size() > 0){
                    System.out.println("\nbusqueda total ------");
                    System.out.println("Cantidad de libros: " + datosBusquedaTotal.getLibros().size());
                    AtomicInteger num = new AtomicInteger(1);
                    datosBusquedaTotal.getLibros().forEach(l -> {
                                String autores = l.autor().stream().map(a -> a.nombre()).collect(Collectors.joining(" / "));
                                System.out.println((num.getAndIncrement()) + ") " + l.titulo() + " - " + autores);
                            });


                    while(!salir){
                        System.out.print("\nIngrese el número del libro que desea guardar (0 para salir): ");
                        try{
                            opcion = teclado.nextInt();
                            if(opcion > datosBusquedaTotal.getLibros().size() || opcion < 0){
                                throw new OpcionIncorrectaMenu();
                            }else {
                                salir = true;
                                if(opcion == 0){
                                    System.out.println("Se canceló la opción de guardar libro");
                                } else {
                                    System.out.println("Se eligio guardar el libro: " + opcion);
                                }
                            }


                        }catch(InputMismatchException e){
                            System.out.println("Debe ingresar un numero de opcion");
                            teclado.nextLine();
                        }catch(OpcionIncorrectaMenu e){
                            System.out.println("Debe ingresar un numero válido");
                            teclado.nextLine();
                        }
                    }




                }
            }

        }catch(NullPointerException e) {
            System.out.println("Se cancelo la busqueda!");
        }


    }


}
