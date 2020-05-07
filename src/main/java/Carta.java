import java.text.SimpleDateFormat;

public class Carta {
    protected   enum PALO {TREBOLE, DIAMANTE, CORAZONE, ESPADA};
    protected  enum COLOR{ RED, BLUE};
    protected COLOR color;
    protected  PALO palo;
    protected  String simbolo;
    protected int valor;

    public Carta(PALO palo, int valor, COLOR color) {

        if(valor <= 11 && valor > 0) { // Compruebo que el valor de la carta no es mayor que 11  y mayor que 0
            this.palo = palo;
            this.valor = valor;
            this.simbolo = paloToChar(palo);
            this.color = color;
        }else{
            System.out.println("CARTA SOBREPASA VALOR, CARTA NO CREADA");
        }
    }

    public String paloToChar(PALO palo){
        switch (palo){
            case TREBOLE:
                return "♣";
            case DIAMANTE:
                return "D";
            case CORAZONE:
                return "";
            case ESPADA:
                return  "S";
            default:
                System.out.println("ERROR PALO TO CHAR");
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

        if(color.toString().equalsIgnoreCase("red")){
            sb.append(".-------.").append(System.lineSeparator());
            sb.append("|\u001B[31m" + valor + "\u001B[0m      |").append(System.lineSeparator());
            sb.append("|   \u001B[31m" + simbolo + "\u001B[0m   |").append(System.lineSeparator());
            sb.append("|      \u001B[31m" + valor +"\u001B[0m|").append(System.lineSeparator());
            sb.append(".-------.").append(System.lineSeparator());

        }else{
            sb.append(".-------.").append(System.lineSeparator());
            sb.append("|\u001B[34m" + valor + "\u001B[0m      |").append(System.lineSeparator());
            sb.append("|   \u001B[34m" + simbolo + "\u001B[0m   |").append(System.lineSeparator());
            sb.append("|      \u001B[34m" + valor +"\u001B[0m|").append(System.lineSeparator());
            sb.append(".-------.").append(System.lineSeparator());
        }


       return carta = sb.toString();

    }
}
