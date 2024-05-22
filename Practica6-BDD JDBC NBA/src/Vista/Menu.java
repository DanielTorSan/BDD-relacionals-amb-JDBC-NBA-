package Vista;

import Controlador.Conexio;
import Controlador.Importacio;
import Model.Taulas.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {
    //Colors
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";
    //--------------------------------------------------------------------

    static HashMap<Integer, Jugadors> jugadors = Importacio.importJugador();

    static HashMap<Integer, Equips> equips = Importacio.importEquips();

    static HashMap<Integer, Estadistiques_jugadors> estadistiques_jugadors = Importacio.importEstadistiques_jugadors();

    static HashMap<Integer, Partits> partits = Importacio.importPartits();
    static Scanner scan = new Scanner(System.in);

    public static void menu(){
        int opcio;
        do {
            System.out.println("---------------------------------------------------------------");
            System.out.println("1.- Llistar tots els jugadors d'un equip");
            System.out.println("2.- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador");
            System.out.println("3.- Llistar tots els partits jugats per un equip amb el seu resultat");
            System.out.println("4.- Inserir un nou jugador a un equip");
            System.out.println("5.- Traspassar un judador a un altra equip");
            System.out.println("6.- Actualitzar les dades de jugadors o equips després d'un partit");
            System.out.println("7.- Modificar les estadístiques d’un jugador");
            System.out.println("8.- Retirar (Eliminar) un jugador");
            System.out.println("9.- Canviar nom franquícia d’un equip");
            System.out.println("0.-Acabar");
            System.out.println("---------------------------------------------------------------");
            System.out.print("Selecciona l'opcio:");
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    llistarJugadors();
                    break;
                case 2:
                    mitjanaJugador();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    System.out.println(ANSI_RED + "Agafa una opció valida!!!" + ANSI_RESET);
                    break;
            }
        } while (opcio != 0);
    }

    public static void llistarJugadors(){
        Scanner scanner = new Scanner(System.in);
        Connection conn = Conexio.conectar();
        Statement stmt = null;

        try {
            System.out.println("Escriu el nom de l'equip:");
            String nomEquip = scanner.nextLine();

            //Execució consulta SQL
            stmt = conn.createStatement();

            // Consulta SQL
            String sql = "SELECT * FROM jugadors WHERE equip_id IN (SELECT equip_id FROM equips WHERE nom = '" + nomEquip + "')";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Jugadors de l'equip: " + ANSI_GREEN + nomEquip + ANSI_RESET);
            while (rs.next()) {
                String nomJugador = rs.getString("nom");
                String cognomJugador = rs.getString("cognom");
                String dorsalJugador = rs.getString("dorsal");
                System.out.println("Jugador " + ANSI_YELLOW + dorsalJugador + ANSI_RESET + ": " + nomJugador + " " + cognomJugador);
            }
            rs.close();
            stmt.close();
            Conexio.desconectar(conn);
        } catch (SQLException se) {
            // errors DBC
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                System.out.println(se2);
            }
        }
    }
    public static void mitjanaJugador(){
        Scanner scanner = new Scanner(System.in);
        Connection conn = Conexio.conectar();
        Statement stmt = null;

    }
}
