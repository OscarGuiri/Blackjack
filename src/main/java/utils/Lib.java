package utils;

import java.util.Random;
import java.util.Scanner;

public class Lib {
    public static Scanner lector = new Scanner(System.in);
    /**
     * Pido un string
     * @return El el string introducido por el usuario
     */
    public static String pedirString(String string){
        boolean validado;

        do {
            System.out.println(string + ":");
            string = lector.nextLine();
            validado = string.length() > 2;
            if(!validado) {
                System.out.println(string + " debe tener almenos 2 caracteres");
                Lib.pausa();
            }
        } while (!validado);

        return  string;
    }


    /**
     * Pide un numero entero por teclado
     * @param nombreInt El nombre del entero
     * @param posibleNegativo Si el numero puede ser negativo o no.
     * @return
     */
    public static int pedirInt(String nombreInt, boolean posibleNegativo){
        int numero = -1;
        boolean validado = false;
        System.out.println(nombreInt + ":");

        do {
            try {
                numero = Integer.parseInt(lector.nextLine());
                validado = true;
                if(numero < 0 && !posibleNegativo){
                    System.out.println(nombreInt +  "no puede ser negativo");
                    validado = false;
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Tienes que introducir un numero");

            }
        }while (!validado);
        return  numero;
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausa() {
        System.out.println("Pulsa INTRO para continuar...");
        lector.nextLine();
    }

    public static int aleatorio() {
        Random r = new Random();
        return r.nextInt();
    }

    public static int aleatorio(int min, int max) {
        Random r = new Random();
        return r.nextInt(max - min +1 ) + min;
    }

    public static double aleatorio(double min, double max) {
        Random r = new Random();
        return min + r.nextDouble() * (max - min);
    }

    public static float aleatorio(float min, float max) {
        Random r = new Random();
        return min + r.nextFloat() * (max - min);
    }

}
