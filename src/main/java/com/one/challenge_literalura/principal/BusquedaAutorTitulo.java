package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.model.Datos;
import com.one.challenge_literalura.model.DatosClase;
import com.one.challenge_literalura.service.ConsumoApi;
import com.one.challenge_literalura.service.ConvierteDatos;

import java.util.Scanner;

public class BusquedaAutorTitulo {

    private static String URL_BASE = "https://gutendex.com/books/?search=";


    public static void buscar(){

        ConsumoApi consumoApi = new ConsumoApi();
        ConvierteDatos conversor = new ConvierteDatos();
        DatosClase datosBusquedaTotal = new DatosClase();
        boolean proxima = true;

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

        }

        if(datos.cantidad() == 0) {
            System.out.println("No se encontró ningún Autor o Titulo - Intente con otra busqueda!");
        }


        //System.out.println(datosBusquedaTotal.getLibros());

        while (proxima){
            //System.out.println(datos.proximaPagina());
            System.out.println("Aguarde por favor que la lista es extensa y esta demorando!\n");
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
                    System.out.println("busqueda total ------");
                    System.out.println("Cantidad de libros: " + datosBusquedaTotal.getLibros().size());
                }
            }

        }catch(NullPointerException e) {
            System.out.println("Se cancelo la busqueda!");
        }


    }
}
