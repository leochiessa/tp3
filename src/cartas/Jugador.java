package cartas;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;
import static java.lang.Thread.sleep;

public class Jugador extends Observable implements Runnable {

    private int id;
    private String nombre;
    private Repartidor repartidor;
    private Carta ultimaCarta;
    private Stack<Carta> mazoJugador;

    public Jugador(Repartidor repartidor, int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.repartidor = repartidor;
        this.mazoJugador = new Stack<>();
    }

    @Override
    public void run() {
        try {
            for (;;) {
                Carta c = getRepartidor().recibirCarta();
                this.getMazoJugador().push(c);
                setChanged();
                notifyObservers(c);
                repartidor.Gameover();
                sleep((long) aleatorio());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int aleatorio() {
        Random ran = new Random();
        int val = ran.nextInt() % 1500;
        if (val < 0) {
            val = val * (-1);
        }
        return val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public Carta getUltimaCarta() {
        return ultimaCarta;
    }

    public void setUltimaCarta(Carta ultimaCarta) {
        this.ultimaCarta = ultimaCarta;
    }

    public Stack<Carta> getMazoJugador() {
        return mazoJugador;
    }

    public void setMazoJugador(Stack<Carta> mazoJugador) {
        this.mazoJugador = mazoJugador;
    }
}