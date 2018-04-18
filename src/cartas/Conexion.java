package cartas;

import java.sql.*;
import java.util.Calendar;

public class Conexion {

    private Connection conn = null;

    public Conexion() {
        try {
            String host = "localhost";
            int port = 3306;
            String db = "tp3";
            String user = "root";
            String password = "";

            String url = String.format("jdbc:mysql://%s:%d/%s?useSSL=false", host, port, db);

            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException ex) {
            System.out.println("\nERROR: no se pudo conectar a la base de datos...");
            ex.printStackTrace();
        }
    }

    public void insert(String nombre, int cantidadCartas) {

        try {
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            String query = "insert into resultado (ganador, cantCartas, fecha) values (?, ?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nombre);
            preparedStmt.setInt(2, cantidadCartas);
            preparedStmt.setDate(3, startDate);

            preparedStmt.execute();

            conn.close();

            System.out.printf("\nDatos Guardados!");
        } catch (Exception e) {
            System.err.println("\nERROR: no se guardaron los datos...");
            System.err.println(e.getMessage());
        }
    }
}