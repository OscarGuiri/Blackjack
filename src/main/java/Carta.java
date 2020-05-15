import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Carta {
    protected   enum PALO {TREBOLE, DIAMANTE, CORAZONE, ESPADA, ERROR};
    protected  enum COLOR{ RED, BLUE, ERROR};
    protected COLOR color;
    protected  PALO palo;
    protected  String simbolo;

    protected boolean oculto;
    protected  int id;
    protected static HashMap<Integer, Integer> idValores = setIdValores(); // KEY: id, VALOR: valor


    public Carta(PALO palo, int id, COLOR color) {

        if(id <= 13) {

            this.id = id;
            this.palo = palo;
            this.simbolo = paloToChar(palo);
            this.color = color;
            this.oculto = false;
        }else{
            System.out.println("No hay carta con id " + id);
        }

    }
    public static HashMap<Integer, Integer> setIdValores(){
        HashMap<Integer, Integer> valores = new HashMap<>();
        valores.put(1,11);
        valores.put(2,2);
        valores.put(3,3);
        valores.put(4,4);
        valores.put(5,5);
        valores.put(6,6);
        valores.put(7,7);
        valores.put(8,8);
        valores.put(9,9);
        valores.put(10,10);
        // J, Q y K
        valores.put(11,10);
        valores.put(12,10);
        valores.put(13,10);


        return  valores;

    }

    /**
     * Conviete el enum del palo a un caracter acci
     * @param palo el palo en formato ENUM
     * @return el palo en formato acci
     */
    public String paloToChar(PALO palo){
        switch (palo){
            case TREBOLE:
                return "♣";
            case DIAMANTE:
                return "♦";
            case CORAZONE:
                return "♥";
            case ESPADA:
                return  "♠";
            default:
                System.out.println("ERROR PALO TO STRING");
                return "?";

        }


    }


    @Override
    /**
     * Devuelve los atributos del objeto en formato string
     */
    public String toString() {
        String carta;
        StringBuilder sb = new StringBuilder();
        String valor = "";


            switch (this.id){
                case 11:
                    valor = "J";
                    break;
                case 12:
                    valor = "Q";
                    break;
                case 13:
                    valor = "K";
                    break;
                default:
                   valor = String.valueOf(this.id);
                    break;

            }


        if(!oculto) {

            if (color.toString().equalsIgnoreCase("red")) {
                sb.append(".-------.").append(System.lineSeparator());
                sb.append("|\u001B[31m" + valor + "\u001B[0m      |").append(System.lineSeparator());
                sb.append("|   \u001B[31m" + simbolo + "\u001B[0m   |").append(System.lineSeparator());
                sb.append("|      \u001B[31m" + valor + "\u001B[0m|").append(System.lineSeparator());
                sb.append(".-------.").append(System.lineSeparator());

            } else {
                sb.append(".-------.").append(System.lineSeparator());
                sb.append("|\u001B[34m" + valor + "\u001B[0m      |").append(System.lineSeparator());
                sb.append("|   \u001B[34m" + simbolo + "\u001B[0m   |").append(System.lineSeparator());
                sb.append("|      \u001B[34m" + valor + "\u001B[0m|").append(System.lineSeparator());
                sb.append(".-------.").append(System.lineSeparator());
            }
        }else{
            if (color.toString().equalsIgnoreCase("red")) {
                sb.append(".-------.").append(System.lineSeparator());
                sb.append("|\u001B[31m" + "?" + "\u001B[0m      |").append(System.lineSeparator());
                sb.append("|   \u001B[31m" + "?" + "\u001B[0m   |").append(System.lineSeparator());
                sb.append("|      \u001B[31m" + "?" + "\u001B[0m|").append(System.lineSeparator());
                sb.append(".-------.").append(System.lineSeparator());

            } else {
                sb.append(".-------.").append(System.lineSeparator());
                sb.append("|\u001B[34m" + "?" + "\u001B[0m      |").append(System.lineSeparator());
                sb.append("|   \u001B[34m" + "?" + "\u001B[0m   |").append(System.lineSeparator());
                sb.append("|      \u001B[34m" + "?" + "\u001B[0m|").append(System.lineSeparator());
                sb.append(".-------.").append(System.lineSeparator());
            }
        }


       return carta = sb.toString();

    }

    /**
     * Para configurar que el ace vale 11 o 1
     * @param set True para que sea 1, false para que sea 11
     */
    public void setValorAceToOne(boolean set){
        if(set){
            this.idValores.put(1,1);
        }else{
            this.idValores.put(1,11);
        }

    }
    // GETTERS AND SETTERS


    public int getId() {
        return id;
    }

    public int getValorId(int id){
        return this.idValores.get(id);

    }
    public boolean isAce(){
        if(this.id == 1){
            return  true;
        }else{
            return  false;
        }
    }

    public void setOculto(boolean oculto) {
        this.oculto = oculto;
    }
}
