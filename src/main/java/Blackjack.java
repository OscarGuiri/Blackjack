import utils.Lib;

import java.util.Scanner;

public class Blackjack {
    private Scanner lector;
    private JugarJuego jugarJuego;
    private static int nVecesGadadasJugador;
    private static int getnVecesGadadasCPU;

    public Blackjack(double saldoJugadores, double saldoMesa) {

        int opcion = 0;


        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1:
                   nuevaPartida(saldoMesa,saldoJugadores);

                    break;
                case 2:
                    System.out.println("Ganadas CPU: " + JugarJuego.getnVecesaGanadasCPU());
                    System.out.println("Ganadas jugadores: " + JugarJuego.getnVecesaGanadasJugador());

                    break;
                case 3:

                    break;
                case 0:

                    break;



            }
        } while (opcion != 0);
    }

    /**
     * Jugar una partida nueva
     * @param saldoMesa
     */
   public void nuevaPartida(double saldoMesa, double saldoJugador){
       int nJugadores;
       String nombre = "";
       //Pido cuantos jugadores hay
       do {
           System.out.println("Cualtos jugadores habran?");
           nJugadores = Lib.pedirInt("Jugadores", false);
           if(nJugadores > 7){
               System.out.println("Este juego solo permite hasta 7 jugadores");
           }
       }while (nJugadores > 7);
       Jugador[] jugadores = new Jugador[nJugadores];

       for(int i = 0; i < jugadores.length; i++){
           System.out.println("Como se llama el jugador " + (i+1) + "?");
           nombre = Lib.pedirString("Jugador");
           jugadores[i] = new Jugador(saldoJugador, nombre);
       }

       // Creo la mesa
       Mesa mesa = new Mesa(saldoMesa);
       //Empiezo la partida
       jugarJuego = new JugarJuego( mesa, jugadores);
       jugarJuego.empezarPartida();

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