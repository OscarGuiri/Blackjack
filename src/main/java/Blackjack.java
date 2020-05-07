import utils.Lib;

import java.util.Scanner;

public class Blackjack {
    private Scanner lector;
   public Blackjack(){
       int opcion = 0;
       Carta carta = new Carta(Carta.PALO.TREBOLE, 2, Carta.COLOR.RED);
       System.out.printf(carta.toString());

        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 0:

                    break;



            }
        } while (opcion != 0);
    }

    /**
     * Imprime el menu principal
     * @return El opcion seleccionado
     */
    public int menuPrincipal() {
        lector = new Scanner(System.in);

        int opcion = -1;
        do {
            System.out.println("***********************\n" +
                    "***** BLACKJACK *****\n" +
                    "***********************\n" +
                    "1. Nueva partida\n" +
                    "2. Mostrar estadísticas\n" +
                    "0. Salir");

            try {
                opcion = Integer.parseInt(Lib.lector.nextLine());
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Elija una opción del menú [0-2]");
                    Lib.pausa();
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Solo números por favor");
                Lib.pausa();
            }
        } while (opcion < 0 || opcion > 2);
        return opcion;

    }



}
