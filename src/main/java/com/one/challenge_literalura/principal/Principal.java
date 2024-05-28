package com.one.challenge_literalura.principal;

import com.one.challenge_literalura.excepciones.OpcionIncorrectaMenu;

import com.one.challenge_literalura.service.MenuApp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public MenuApp menuApp = new MenuApp();
    private Scanner teclado = new Scanner(System.in);
    private boolean salir = false;
    int opcion;

    public void arrancar() {
        menuApp.mostrarSaludoInicial();


        while(!salir){
            menuApp.mostrarMenuPrincipal();
            try{
                opcion = teclado.nextInt();
                if(opcion > 5 || opcion < 0) {
                    throw new OpcionIncorrectaMenu("Elija una opción correcta!!");
                } else {
                    menuApp.seleccionDeOpcion(opcion);
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


}

