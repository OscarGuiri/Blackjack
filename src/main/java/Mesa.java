
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mesa {
    private Carta[] cartas;
    private double saldoMesa;
    private static int posicionArrayCarta;

    private Jugador banco;

    public Mesa(double saldoMesa){
        this.cartas = new Carta[52];
        this.saldoMesa = saldoMesa;
        cartas = generarCartas(52);
        banco = new Jugador(saldoMesa,"BANCO");

    }



    /**
     * Saca una carta de la mesa. Cuando llegamos al final del array de cartas, pongo la posicion a 0 y lo vuelvo a mezclar
     * @return
     */
    public Carta sacarCarta(){
        Carta carta =  cartas[posicionArrayCarta];
        posicionArrayCarta++;
        if(posicionArrayCarta > 52){

            posicionArrayCarta = 0;
            mezclarCartas();
        }
        return carta;



    }

    /**
     * Genera cartas aleatoriamente
     * @return El array de cartas
     */
    public  Carta[] generarCartas(int cantidadCartas){
        Carta[] cartas = new Carta[cantidadCartas];
        int id = 1;
        int auxColor = 0;
        int auxPalo = 0;

        Carta.PALO palo = Carta.PALO.ERROR;

        Carta.COLOR color = Carta.COLOR.ERROR;

        for(int i = 0; i < cartas.length; i++){ // Recorro todas las castas
            if(auxColor == 0){ // Las cartas se crean azul y rojo 50/50
                color = Carta.COLOR.BLUE;
                auxColor = 1;
            }else{
                color = Carta.COLOR.RED;
                auxColor = 0;
            }
            switch (auxPalo){
                case 0:
                    palo = Carta.PALO.CORAZONE;
                    auxPalo++;
                    break;
                case 1:
                    palo = Carta.PALO.DIAMANTE;
                    auxPalo++;
                    break;
                case 2:
                    palo = Carta.PALO.ESPADA;
                    auxPalo++;
                    break;
                case 3:
                    palo = Carta.PALO.TREBOLE;
                    auxPalo = 0;
                    break;
                default:
                    System.out.println("ERROR AUX PALO");
                    break;

            }

            cartas[i] = new Carta(palo, id, color);


            // Cuando llega al id 13 (REY) vuelve al id 1 (ACE)
           id += 1;
           if(id > 13){
               id = 1;
           }

        }

        return  cartas;
        }

    /**
     * Mezclan las cartas de la mesa
     * @return Las cartas mezcladas
     */
   public void mezclarCartas() {
       System.out.println("Mezclando cartas...");
       Random rnd = ThreadLocalRandom.current();
       for (int i = cartas.length - 1; i > 0; i--) {
           int index = rnd.nextInt(i + 1);

           Carta a = cartas[index];
           cartas[index] = cartas[i];
          this.cartas[i] = a;
       }

   }


    public void imprimirCartas(Jugador jugador){
      for(int i = 0; i < jugador.getCartas().size(); i++){
          System.out.println(jugador.getCartas().get(i).toString());
      }

    }



    public Jugador getBanco() {
        return banco;
    }

    public void setBanco(Jugador banco) {
        this.banco = banco;
    }

    public Carta[] getCartas() {
        return cartas;
    }
}
