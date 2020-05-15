import java.util.ArrayList;

public class Jugador {
    private double dinero;
    private ArrayList<Carta> cartas;
    private int id;
    private static int nId = 0;
    private double apuesta;
    private boolean tieneBlackjack;
    private int totalValoresCartas;
    private boolean hasAce;
    private boolean ganado;

    private String nombre;

    public Jugador(double dinero,  String nombre) {
        this.dinero = dinero;
        this.cartas = new ArrayList<>();
        this.id = nId++;
        this.nombre = nombre;
        tieneBlackjack = false;
        ganado = false;
        this.totalValoresCartas = 0;

    }
    public String mostrarCartas(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < cartas.size(); i++){
            sb.append(cartas.get(i).toString());

        }
        return  sb.toString();

    }

    @Override
    public String toString() {
        return "Jugador{" +
                "dinero=" + dinero +
                ", cartas=" + cartas +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
    // GETTERS AND SETTERS

    public boolean isGanado() {
        return ganado;
    }

    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    public int getTotalValorCartasJugador(){
        int totalValorCartas = 0;
        for(int i = 0; i < cartas.size(); i++){
            totalValorCartas += cartas.get(i).getValorId(cartas.get(i).getId());

        }
        this.totalValoresCartas = totalValorCartas;
        return  totalValorCartas;

    }
    public void restarTotalValorCartasJugador(int  totalValorCartasJugador){
        this.totalValoresCartas -= this.totalValoresCartas - totalValorCartasJugador;

    }
    public void setHasAce(boolean hasAce){
        this.hasAce = hasAce;

    }

    public boolean isHasAce() {
        return hasAce;
    }

    public boolean isTieneBlackjack() {
        return tieneBlackjack;
    }

    public void setTieneBlackjack(boolean tieneBlackjack) {
        this.tieneBlackjack = tieneBlackjack;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getApuesta() {
        return apuesta;
    }

    public void setApuesta(double apuesta) {
        this.apuesta = apuesta;
    }

    public double getDinero() {
        return dinero;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public int getId() {
        return id;
    }
}
