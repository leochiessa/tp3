package cartas;

public class Carta {

    private final int numero;
    private final String palo;

    public Carta(int numero, String palo) {
        this.numero = numero;
        this.palo = palo;
    }

    @Override
    public String toString() {
        return numero + palo;
    }
}