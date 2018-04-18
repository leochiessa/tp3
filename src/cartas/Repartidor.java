package cartas;

import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class Repartidor implements Observer {

    private boolean busy;
    private final Stack mazo;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;

    public Repartidor() {
        mazo = new Stack<>();
        for (int i = 0; i < 12; i++) {
            mazo.push(new Carta(i + 1, " de Espadas"));
            mazo.push(new Carta(i + 1, " de Bastos"));
            mazo.push(new Carta(i + 1, " de Copas"));
            mazo.push(new Carta(i + 1, " de Oros"));
        }
    }

    public synchronized Carta recibirCarta() {
        while (mazo.empty()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        notifyAll();
        return (Carta) mazo.pop();
    }

    public void setJugadores(Jugador jugador1, Jugador jugador2, Jugador jugador3, Jugador jugador4) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador3 = jugador3;
        this.jugador4 = jugador4;
    }

    public Carta repartir() throws InterruptedException {
        while (busy) {
            wait();
        }
        busy = true;
        try {
            Thread.sleep(250);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        busy = false;
        notifyAll();
        return (Carta) mazo.pop();
    }

    @Override
    public void update(Observable o, Object o1) {
        Jugador jugador = (Jugador) o;
        Carta carta = (Carta) o1;
        System.out.println(carta + " para " + jugador.getNombre());
    }

    public Carta sacarCarta() {
        return (Carta) mazo.pop();
    }

    public void cargarCarta(Carta l) {
        mazo.push(l);
    }

    public boolean estadoMazo() {
        return mazo.empty();
    }

    public void Gameover() {
        if (mazo.empty()) {

            System.out.println("\nFIN DEL JUEGO\n");
            System.out.println(jugador1.getNombre() + ": " + jugador1.getMazoJugador().size() + " cartas.");
            System.out.println(jugador2.getNombre() + ": " + jugador2.getMazoJugador().size() + " cartas.");
            System.out.println(jugador3.getNombre() + ": " + jugador3.getMazoJugador().size() + " cartas.");
            System.out.println(jugador4.getNombre() + ": " + jugador4.getMazoJugador().size() + " cartas.");

            avisarGanador();
        }
    }

    public void avisarGanador() {
        Jugador J;
        if (jugador1.getMazoJugador().size() >= jugador2.getMazoJugador().size() && jugador1.getMazoJugador().size() >= jugador3.getMazoJugador().size() && jugador1.getMazoJugador().size() >= jugador4.getMazoJugador().size()) {
            System.out.println("\nEl ganador es: " + jugador1.getNombre() + ", con " + jugador1.getMazoJugador().size() + " cartas.");
            J = jugador1;
            guardarGanador(J);
        }
        if (jugador2.getMazoJugador().size() >= jugador1.getMazoJugador().size() && jugador2.getMazoJugador().size() >= jugador3.getMazoJugador().size() && jugador2.getMazoJugador().size() >= jugador4.getMazoJugador().size()) {
            System.out.println("\nEl ganador es: " + jugador2.getNombre() + ", con " + jugador2.getMazoJugador().size() + " cartas.");
            J = jugador2;
            guardarGanador(J);
        }
        if (jugador3.getMazoJugador().size() >= jugador1.getMazoJugador().size() && jugador3.getMazoJugador().size() >= jugador2.getMazoJugador().size() && jugador3.getMazoJugador().size() >= jugador4.getMazoJugador().size()) {
            System.out.println("\nEl ganador es: " + jugador3.getNombre() + ", con " + jugador3.getMazoJugador().size() + " cartas.");
            J = jugador3;
            guardarGanador(J);
        }
        if (jugador4.getMazoJugador().size() >= jugador1.getMazoJugador().size() && jugador4.getMazoJugador().size() >= jugador2.getMazoJugador().size() && jugador4.getMazoJugador().size() >= jugador3.getMazoJugador().size()) {
            System.out.println("\nEl ganador es: " + jugador4.getNombre() + ", con " + jugador4.getMazoJugador().size() + " cartas.");
            J = jugador4;
            guardarGanador(J);
        }
    }

    private void guardarGanador(Jugador j) {
        Conexion conexion = new Conexion();
        conexion.insert(j.getNombre(), j.getMazoJugador().size());
    }
}