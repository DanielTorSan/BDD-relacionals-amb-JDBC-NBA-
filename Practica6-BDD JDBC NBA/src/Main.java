import java.sql.*;
import java.util.Scanner;

public class Main {
    // Configuración de la conexión a la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    static final String DB_URL = "jdbc:mysql://localhost/nba";
    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Paso 1: Registrar el driver JDBC
            Class.forName(JDBC_DRIVER);

            // Paso 2: Abrir la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Paso 3: Solicitar al usuario el nombre del equipo
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el nombre del equipo de la NBA:");
            String nombreEquipo = scanner.nextLine();

            // Paso 4: Ejecutar la consulta SQL
            stmt = conn.createStatement();

            // Consulta SQL para obtener los jugadores de un equipo específico
            String sql = "SELECT * FROM jugadors WHERE equip_id IN (SELECT id FROM equips WHERE nom = '" + nombreEquipo + "')";
            ResultSet rs = stmt.executeQuery(sql);

            // Paso 5: Procesar los resultados de la consulta
            System.out.println("Jugadores del equipo " + nombreEquipo + ":");
            while (rs.next()) {
                // Mostrar los datos de cada jugador
                String nombreJugador = rs.getString("nom");
                String apellidoJugador = rs.getString("cognom");
                System.out.println("Jugador: " + nombreJugador + " " + apellidoJugador);
            }

            // Paso 6: Cerrar recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Manejar errores de JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Manejar otros errores
            e.printStackTrace();
        } finally {
            // Cerrar recursos en un bloque finally
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // Nada que hacer
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
