package cartas;

public class Main {
    
    public static void main(String[] args) {
        Repartidor repartidor = new Repartidor();
        Jugador jugador1 = new Jugador(repartidor, 1, "Leo");
        Jugador jugador2 = new Jugador(repartidor, 2, "Juan");
        Jugador jugador3 = new Jugador(repartidor, 3, "Pedro");
        Jugador jugador4 = new Jugador(repartidor, 4, "Luis");
        repartidor.setJugadores(jugador1, jugador2, jugador3, jugador4);
        
        jugador1.addObserver(repartidor);
        (new Thread(jugador1)).start();
        
        jugador2.addObserver(repartidor);
        (new Thread(jugador2)).start();
        
        jugador3.addObserver(repartidor);
        (new Thread(jugador3)).start();
        
        jugador4.addObserver(repartidor);
        (new Thread(jugador4)).start();
    }
}