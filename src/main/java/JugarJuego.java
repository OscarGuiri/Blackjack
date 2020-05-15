
import utils.Lib;
import java.util.ArrayList;
import java.util.Scanner;

public class JugarJuego {

    private Mesa mesa;
    private Jugador[] jugadores;
    private Scanner lector;
    private static int nVecesaGanadasCPU = 0;
    private static int nVecesaGanadasJugador = 0;


    public JugarJuego( Mesa mesa, Jugador[] jugadores) {
        this.mesa = mesa;
        this.jugadores = jugadores;


    }
    public void empezarPartida() {
        boolean seguirJugando = true;

    do {
        mesa.mezclarCartas();
        pedirApuestas(); // Pido las apuestas
        generarCartasPrincipales(); // Genero las cartas para empezar la partida

        for (int i = 0; i < jugadores.length; i++) {
            System.out.println(jugadores[i].getNombre());
            mesa.imprimirCartas(jugadores[i]);
        }
        System.out.println("BANCO:");
        mesa.imprimirCartas(mesa.getBanco());

        if (isBlackjack(mesa.getBanco().getCartas())) {
            System.out.println("LA MESA TIENE BLACKJACK!");
            nVecesaGanadasCPU += jugadores.length;

            mesa.getBanco().getCartas().get(1).setOculto(false);
            mesa.imprimirCartas(mesa.getBanco());
            // Todos pierden.
            for (int i = 0; i < jugadores.length; i++) {
                mesa.getBanco().setDinero(jugadores[i].getApuesta() + mesa.getBanco().getDinero());
                jugadores[i].setDinero(jugadores[i].getDinero() - jugadores[i].getApuesta());
                jugadores[i].setApuesta(0); // Vuelvo a poner la apuesta a 0
            }

        } else {
            midGame();
            endGame();
            volverPredeterminado();
        }
        if(!preguntarOtraRonda()){
            seguirJugando = false;
        }

        }while (seguirJugando);
    }

    /**
     * CONTINUE HERE!!!! FIX ACE
     */
    public void midGame() {
        int opcion = 0;

        boolean doblar;


        for (int i = 0; i < jugadores.length; i++) { // Recorro los jugadores para ver si uno tiene blackjack
            if (isBlackjack(jugadores[i].getCartas())) {
                System.out.println("BACKJACK PARA " + jugadores[i].getNombre() + "!");
                jugadores[i].setGanado(true);
                jugadores[i].setDinero(jugadores[i].getDinero() + (jugadores[i].getApuesta() * 1.5));
                System.out.println("Has ganado " + jugadores[i].getApuesta() * 1.5);
                mesa.getBanco().setDinero(mesa.getBanco().getDinero() - (jugadores[i].getApuesta() * 1.5));

                jugadores[i].setTieneBlackjack(true);
            }

            if (!jugadores[i].isTieneBlackjack()) { // Si no tiene blackjack, voy jugador por jugador pidiendo que quiere hacer


                System.out.println(jugadores[i].getNombre() + ", que quieres hacer?");
                System.out.println("Tienes un todal de " + jugadores[i].getTotalValorCartasJugador());
                doblar = preguntarDoblarApuesta();

                // Compuebo si el jugador quiere doblar y si tiene el dinero suficiente para hacerlo
                if (doblar) {
                    jugadores[i].setApuesta(jugadores[i].getApuesta() * 2);
                    System.out.println("APUESTA DOBLADA");
                }



                do {
                    opcion = opcionJugador(jugadores[i]); //Pido si quiere una carta mas o si quiere plantarse
                    if (opcion == 1) {
                        jugadores[i].getCartas().add(mesa.sacarCarta());

                        if (jugadores[i].getCartas().get(jugadores[i].getCartas().size() - 1).isAce()) { // Si la carta que le acaba de sacar es un as, pongo que el jugador tiene ace
                            jugadores[i].setHasAce(true);

                        }

                        if (jugadores[i].isHasAce() && jugadores[i].getTotalValorCartasJugador() > 21) {
                            jugadores[i].restarTotalValorCartasJugador(10);

                            for (int j = 0; j < jugadores[i].getCartas().size(); j++) {
                                if (jugadores[i].getCartas().get(j).isAce()) {
                                    jugadores[i].getCartas().get(j).setValorAceToOne(true);
                                }
                            }
                        }


                        System.out.println("Te a salido la carta\n " + jugadores[i].getCartas().get(jugadores[i].getCartas().size() - 1));
                        System.out.println("Tu valor totas es " + jugadores[i].getTotalValorCartasJugador());
                        if (jugadores[i].getTotalValorCartasJugador() == 21) {
                            nVecesaGanadasJugador++;
                            System.out.println("BLACKJACK!");
                            jugadores[i].setTieneBlackjack(true);
                            jugadores[i].setDinero(jugadores[i].getDinero() + jugadores[i].getApuesta());

                        }

                        if (jugadores[i].getTotalValorCartasJugador() > 21) {

                            System.out.println("BUST");
                            jugadores[i].setGanado(false);
                            Lib.pausa();
                            // Resto el dinero del jugador.
                            mesa.getBanco().setDinero(mesa.getBanco().getDinero() - jugadores[i].getApuesta());
                        }
                    }
                } while (jugadores[i].getTotalValorCartasJugador() < 21 && opcion != 2);

            }


        }

    }

    /**
     * todo NOTFINISHED.
     */
    public void endGame() {
        boolean salir = false;
        //Imprimo las cartas del banco
        mesa.getBanco().getCartas().get(1).setOculto(false); // Revelo la carta oculta
        mesa.imprimirCartas(mesa.getBanco());
        System.out.println("La mesa tiene " + mesa.getBanco().getTotalValorCartasJugador());

        do {
            if (!isBlackjack(mesa.getBanco().getCartas()) && mesa.getBanco().getTotalValorCartasJugador() < 16) {
                System.out.println("El banco saca una carta: ");
                mesa.getBanco().getCartas().add(mesa.sacarCarta());
                System.out.println(mesa.getBanco().getCartas().get(mesa.getBanco().getCartas().size() - 1));
                System.out.println("El banco tiene un valor de " + mesa.getBanco().getTotalValorCartasJugador());
                if (mesa.getBanco().getTotalValorCartasJugador() == 21) {
                    salir = true;
                    System.out.println("BLACKJACK PARA EL BANCO");
                    for (int i = 0; i < jugadores.length; i++) {
                        if (!jugadores[i].isTieneBlackjack()) {
                            mesa.getBanco().setDinero(mesa.getBanco().getDinero() + jugadores[i].getApuesta());
                            System.out.println("Jugador " + (i + 1) + " pierde " + jugadores[i].getApuesta());
                            jugadores[i].setDinero(jugadores[i].getDinero() - jugadores[i].getApuesta());
                        }
                    }

                }

                if (mesa.getBanco().getTotalValorCartasJugador() > 21) {
                    salir = true;
                    System.out.println("BUST PARA EL BANCO");
                    for (int i = 0; i < jugadores.length; i++) {
                        if (!jugadores[i].isTieneBlackjack() || !jugadores[i].isGanado()) {
                            jugadores[i].setDinero(jugadores[i].getDinero() + jugadores[i].getApuesta());
                            mesa.getBanco().setDinero(mesa.getBanco().getDinero() - jugadores[i].getApuesta());
                            System.out.println("Jugador " + (i + 1) + " Gana " + jugadores[i].getApuesta());
                            if(jugadores[i].getTotalValorCartasJugador() < 21) {
                                jugadores[i].setDinero(jugadores[i].getDinero() + jugadores[i].getApuesta());
                            }
                        }else{
                            System.out.println("Jugador " + (i + 1) + " pierda " + jugadores[i].getApuesta());
                            jugadores[i].setDinero(jugadores[i].getDinero() - jugadores[i].getApuesta());
                        }
                    }
                }


            } else {
                for (int i = 0; i < jugadores.length; i++) {
                    if (jugadores[i].getTotalValorCartasJugador() > mesa.getBanco().getTotalValorCartasJugador() || jugadores[i].isGanado())  {
                        System.out.println(jugadores[i].getNombre() + " a ganado!");
                        if(!jugadores[i].isTieneBlackjack()) {
                            jugadores[i].setDinero(jugadores[i].getDinero() + jugadores[i].getApuesta());
                        }
                        nVecesaGanadasJugador++;
                    } else{
                        System.out.println(jugadores[i].getNombre() + " a perdido!");
                        nVecesaGanadasCPU++;
                        jugadores[i].setDinero(jugadores[i].getDinero() - jugadores[i].getApuesta());
                        mesa.getBanco().setDinero(mesa.getBanco().getDinero() + jugadores[i].getApuesta());
                    }
                }
                salir = true;
            }
        } while (!salir);




    }

    /**
     * Pone todos los elementos por defecto. (Apuestas a 0, no tiene blackjack, no tiene cartas)
     */
    private void volverPredeterminado() {
        for (int i = 0; i < jugadores.length; i++) {

            jugadores[i].setHasAce(false);
            jugadores[i].setApuesta(0);
            jugadores[i].setTieneBlackjack(false);
            jugadores[i].setCartas(new ArrayList < > ());
            jugadores[i].setGanado(false);
            mesa.getBanco().setHasAce(false);

        }
    }

    /**
     * Pregunta al jugador si quiere doblar la apuesta.
     * @return
     */
    public boolean preguntarDoblarApuesta() {
        lector = new Scanner(System.in);
        boolean doblar = false;
        boolean valido = false;
        char respuesta = ' ';
        do {
            System.out.println("Quieres doblar la apuesta? Si o no?");
            respuesta = lector.next().charAt(0);
            if (respuesta == 's' || respuesta == 'n') {
                valido = true;
                if (respuesta == 's') {
                    doblar = true;

                } else {
                    doblar = false;
                }
            } else {
                System.out.println("Por favor dir si o no");
            }
        } while (!valido);
        return doblar;
    }

    /**
     *
     * Pregunta al usuario si quiere realizar otra ronda
     * @return
     */
    public boolean preguntarOtraRonda() {
        lector = new Scanner(System.in);
        boolean opcion = false;
        boolean valido = false;
        char respuesta = ' ';
        do {
            System.out.println("Otra ronda? Si o no?");
            respuesta = lector.next().charAt(0);
            if (respuesta == 's' || respuesta == 'n') {
                valido = true;
                if (respuesta == 's') {
                    opcion = true;

                } else {
                    opcion = false;
                }
            } else {
                System.out.println("Por favor dir si o no");
            }
        } while (!valido);
        return opcion;
    }

    /**
     * Da los opciones si quiere una carta mas o no
     * @param jugador el jugador la cual estamos preguntando
     * @return 1 para HIT, 2 para STAY
     */
    public int opcionJugador(Jugador jugador) {
        boolean hasAce = false;
        boolean validado = false;
        int opcion = -1;


        do {
            System.out.println("1. HIT");
            System.out.println("2. STAY");

            opcion = Lib.pedirInt("opcion", false);
            validado = true;
            if (opcion > 2 || opcion < 1) {
                System.out.println("Elige un numero entre el 1 y el 2:");
            }


        } while (!validado);


        return opcion;
    }






    /**
     * Comprueba si las cartas dan blackjack
     * @return
     */
    public boolean isBlackjack(ArrayList < Carta > cartas) {
        int sumaCartas = 0;
        boolean isBlackjack = false;

        for (int i = 0; i < cartas.size(); i++) {
            sumaCartas += cartas.get(i).getValorId(cartas.get(i).getId());
            if (sumaCartas == 21) {
                isBlackjack = true;
            }
        }
        return isBlackjack;
    }

    /**
     * Pide las apuestas de los jugadores. Tambien compruba que el banco puede retribuir los ganadores si hace falta
     */
    public void pedirApuestas() {
        double apuesta = 0;
        boolean validado = false;
        double dineroBando = mesa.getBanco().getDinero();
        double aux = 0;
        // Recorro los jugadores para pedir apuesta
        for (int i = 0; i < jugadores.length; i++) {
            do {
                System.out.println(jugadores[i].getNombre() + ", cuanto quieres apostar?");
                System.out.println("Tienes " + jugadores[i].getDinero());
                apuesta = Lib.pedirInt("Apuesta", false);

                aux = dineroBando - (apuesta * 1.5); // Si el banco no tiene los creditos suficientes por si gana el jugador, vuelve a pedir la apuesta
                if (aux >= 0) {
                    if(jugadores[i].getDinero() >= apuesta) {
                        validado = true;

                    }else{
                        System.out.println("No tienes dinero para realizar esta apuesta.");
                    }

                } else {
                    System.out.println("El banco no tiene los creditos para apostar. " + apuesta);
                }

            } while (!validado);
            jugadores[i].setApuesta(apuesta);

        }

    }

    /**
     * Genera las cartas principales de la partida
     */
    private void generarCartasPrincipales() {

        ArrayList < Carta > cartas = new ArrayList < > ();
        ArrayList < Carta > cartasBanco = new ArrayList < > ();
        //Doy las 2 cartas a los jugadores cartas a los jugadores

        for (int i = 0; i < jugadores.length; i++) {
            for (int j = 0; j < 2; j++) {
                cartas.add(mesa.sacarCarta());
                if (cartas.get(cartas.size() - 1).isAce()) {
                    jugadores[i].setHasAce(true);
                }

            }
            jugadores[i].setCartas(cartas);
            cartas = new ArrayList < > ();

        }

        cartasBanco.add(mesa.sacarCarta());
        cartasBanco.add(mesa.sacarCarta());
        cartasBanco.get(1).setOculto(true);

        // Si la mesa recibe 2 hace, resto 10 puntos de valor para que no tenga un valor de 22 y que tenga un valor de 11
        if(cartasBanco.get(0).isAce() || cartasBanco.get(0).isAce()){
            mesa.getBanco().setHasAce(true);
        }
        mesa.getBanco().setCartas(cartasBanco);
        if(mesa.getBanco().isHasAce() && mesa.getBanco().getTotalValorCartasJugador() > 21){
            mesa.getBanco().restarTotalValorCartasJugador(10);
        }
    }
    //GETTERS AND SETTERS


    public static int getnVecesaGanadasCPU() {
        return nVecesaGanadasCPU;
    }

    public static int getnVecesaGanadasJugador() {
        return nVecesaGanadasJugador;
    }

}