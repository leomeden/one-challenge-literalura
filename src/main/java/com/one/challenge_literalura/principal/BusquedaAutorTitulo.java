package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.excepciones.OpcionIncorrectaMenu;
import com.one.challenge_literalura.model.Datos;
import com.one.challenge_literalura.model.DatosLibros;
import com.one.challenge_literalura.service.ConsumoApi;
import com.one.challenge_literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class BusquedaAutorTitulo {

    private static String URL_BASE = "https://gutendex.com/books/?search=";


    public DatosLibros buscar(){

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        //DatosClase datosBusquedaTotal = new DatosClase();
        List<DatosLibros> datosBusquedaTotal = new ArrayList<>();
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
                    datosBusquedaTotal.addAll(datos.resultados());
                    //System.out.println("elegi seguir");
                }

            }

        }else{
            if(datos.cantidad() < 1) {
                System.out.println("No se encontró ningún Autor o Titulo - Intente con otra busqueda!");
            } else {
                datosBusquedaTotal.addAll(datos.resultados());
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

            datosBusquedaTotal.addAll(datos.resultados());


        }

        try{
            if(datos.cantidad() > 0){
                if(datosBusquedaTotal.size() > 0){
                    System.out.println("\nbusqueda total ------");
                    System.out.println("Cantidad de libros: " + datosBusquedaTotal.size());
                    AtomicInteger num = new AtomicInteger(1);
                    datosBusquedaTotal.forEach(l -> {
                                String autores = l.autor().stream().map(a -> a.nombre()).collect(Collectors.joining(" / "));
                                System.out.println((num.getAndIncrement()) + ") " + l.titulo() + " - " + autores);
                            });


                    while(!salir){
                        System.out.print("\nIngrese el número del libro que desea guardar (0 para salir): ");
                        try{
                            opcion = teclado.nextInt();
                            if(opcion > datosBusquedaTotal.size() || opcion < 0){
                                throw new OpcionIncorrectaMenu();
                            }else {
                                salir = true;
                                if(opcion == 0){
                                    System.out.println("Se canceló la opción de guardar libro");
                                    return null;
                                } else {
                                    System.out.println("Se eligio guardar el libro: " + opcion);
                                    //System.out.println(datosBusquedaTotal.get(opcion-1));
                                    return datosBusquedaTotal.get(opcion-1);
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

        return null;
    }


}
