package Vista;

import Controlador.Conexio;
import Controlador.Importacio;
import Model.Taulas.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    static Connection conn = Conexio.conectar();
    static Statement stmt = null;
    static Date dataNaixement = null;

    public static void menu(){
        int opcio;
        do {
            System.out.println("---------------------------------------------------------------");
            System.out.println(ANSI_PURPLE + "1" + ANSI_RESET +".- Llistar tots els jugadors d'un equip");
            System.out.println(ANSI_PURPLE + "2" + ANSI_RESET +".- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador (per fer)");
            System.out.println(ANSI_PURPLE + "3" + ANSI_RESET +".- Llistar tots els partits jugats per un equip amb el seu resultat(per fer)");
            System.out.println(ANSI_PURPLE + "4" + ANSI_RESET +".- Inserir un nou jugador a un equip");
            System.out.println(ANSI_PURPLE + "5" + ANSI_RESET +".- Traspassar un judador a un altra equip");
            System.out.println(ANSI_PURPLE + "6" + ANSI_RESET +".- Actualitzar les dades de jugadors o equips després d'un partit(per fer)");
            System.out.println(ANSI_PURPLE + "7" + ANSI_RESET +".- Modificar les estadístiques d’un jugador(per fer)");
            System.out.println(ANSI_PURPLE + "8" + ANSI_RESET +".- Retirar (Eliminar) un jugador");
            System.out.println(ANSI_PURPLE + "9" + ANSI_RESET +".- Canviar nom franquícia d’un equip");
            System.out.println(ANSI_PURPLE + "0" + ANSI_RESET +".-Acabar");
            System.out.println("---------------------------------------------------------------");
            System.out.print("Selecciona l'opcio:");
            opcio = scan.nextInt();
            scan.nextLine();
            switch (opcio) {
                case 1:
                    llistarJugadors();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    inserirNouJugador();
                    break;
                case 5:
                    traspasarJugador();
                    break;
                case 6:
                    break;
                case 7:
                    modificarEstadistiquesJugador();
                    break;
                case 8:
                    retirarJugador();
                    break;
                case 9:
                    canviarNomFranquiciaEquip();
                    break;
                default:
                    System.out.println(ANSI_RED + "Agafa una opció valida!!!" + ANSI_RESET);
                    break;
            }
        } while (opcio != 0);
    }

    public static void llistarJugadors(){
        try {
            System.out.println("Escriu el nom de l'equip:");
            String nomEquip = scan.nextLine();

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

    }
    public static void inserirNouJugador(){
        try {
            System.out.println("Introdueix el nom complet del jugador:");
            String nomComplet = scan.nextLine();

            // Asignem el nom i el cognom a les variables corresponents
            String[] parts = nomComplet.split(" ");
            String nomJugador = parts[0];
            String cognomJugador = "";
            if (parts.length > 1) {
                cognomJugador = parts[1];
            }
            Date dataNaixement = demanarDataNaixement();

            System.out.println("Introdueix l'alcada del jugador:");
            float alcada = scan.nextFloat();
            scan.nextLine();

            System.out.println("Introdueix el pes del jugador:");
            float pes = scan.nextFloat();
            scan.nextLine();

            System.out.println("Introdueix el dorsal del jugador:");
            String dorsal = scan.nextLine();


            System.out.println("Introdueix la posició del jugador:");
            String posicio = scan.nextLine();

            System.out.println("Introdueix l'equip on vols inserir el jugador:");
            String nomEquip = scan.nextLine();

            if (existeixJugador(nomJugador, cognomJugador)) {
                System.out.println("El jugador ja existeix en un altre equip. Vols continuar? (S/N)");
                String continuar = scan.nextLine();
                if (!continuar.equalsIgnoreCase("S")) {
                    return;
                }
            }

            // Obtenim l'id de l'equip en qüestió
            int equipId = obtindreEquipID(nomEquip);

            // Nou jugador_id
            int nextJugadorId = nouJugadorID();

            // Inserció de dades a la BDD
            String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, nextJugadorId);
            preparedStatement.setString(2, nomJugador);
            preparedStatement.setString(3, cognomJugador);
            preparedStatement.setDate(4, dataNaixement);
            preparedStatement.setFloat(5, alcada);
            preparedStatement.setFloat(6, pes);
            preparedStatement.setString(7, dorsal);
            preparedStatement.setString(8, posicio);
            preparedStatement.setInt(9, equipId);
            preparedStatement.executeUpdate();
            System.out.println( ANSI_BLUE + "Nou jugador inserit a l'equip "+ ANSI_YELLOW + nomEquip + ANSI_BLUE + " amb èxit!" + ANSI_RESET);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int nouJugadorID() throws SQLException {
        String sql = "SELECT MAX(jugador_id) AS max_id FROM jugadors";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int maxId = rs.getInt("max_id");
        return maxId + 1;
    }

    // Funció per comprobar que el jugador ja existeix en la base de dades
    public static boolean existeixJugador(String nomJugador, String cognomJugador) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM jugadors WHERE nom = ? AND cognom = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nomJugador);
        preparedStatement.setString(2, cognomJugador);
        ResultSet rs = preparedStatement.executeQuery();
        rs.next();
        int count = rs.getInt("count");
        return count > 0;
    }

    // Funció per obtindre l'id d'un equip per el seu nom
    public static int obtindreEquipID(String nomEquip) throws SQLException {
        String sql = "SELECT equip_id FROM equips WHERE nom = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, nomEquip);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return rs.getInt("equip_id");
        } else {
            throw new SQLException("No s'ha trobat l'equip: " + nomEquip);
        }
    }

    // Funcio per traspassar jugadors
    public static void traspasarJugador(){
        try {
            System.out.println("Introdueix el nom complet del jugador:");
            String nomComplet = scan.nextLine();

            // Asignem el nom i el cognom a les variables corresponents
            String[] parts = nomComplet.split(" ");
            String nomJugador = parts[0];
            String cognomJugador = "";
            if (parts.length > 1) {
                cognomJugador = parts[1];
            }
            System.out.println("Introdueix el nou equip del jugador:");
            String nouEquip = scan.nextLine();

            int nouEquipId = obtindreEquipID(nouEquip);

            // Actualitza la BDD
            String sql = "UPDATE jugadors SET equip_id = ? WHERE nom = ? AND cognom = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, nouEquipId);
            preparedStatement.setString(2, nomJugador);
            preparedStatement.setString(3, cognomJugador);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(ANSI_BLUE + "El jugador "+ ANSI_YELLOW + nomComplet + ANSI_BLUE + " ha estat traspasat a " + ANSI_YELLOW + nouEquip + ANSI_BLUE +" amb èxit!" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "No s'ha pogut trobar el jugador o el nou equip." + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Date demanarDataNaixement() {
        boolean dataValida = false;
        while (!dataValida) {
            System.out.println("Introdueix la data de naixement del jugador (format: AAAA-MM-DD):");
            String dataNaixementStr = scan.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                java.util.Date parsedDate = dateFormat.parse(dataNaixementStr);
                dataNaixement = new Date(parsedDate.getTime());
                dataValida = true; // Si no lanza excepción, la fecha es válida
            } catch (ParseException e) {
                System.out.println("Format de data incorrecte. Torna a introduir la data.");
            }
        }
        return dataNaixement;
    }
    // Funció per retirar un jugador
    public static void retirarJugador() {
        try {
            System.out.println("Introdueix el nom complet del jugador que vols retirar:");
            String nomComplet = scan.nextLine();

            // Asignem el nom i el cognom a les variables corresponents
            String[] parts = nomComplet.split(" ");
            String nomJugador = parts[0];
            String cognomJugador = parts[1];

            // Obtenim l'id del jugador en qüestió
            String sqlJugadorId = "SELECT jugador_id FROM jugadors WHERE nom = ? AND cognom = ?";
            PreparedStatement preparedStatementJugadorId = conn.prepareStatement(sqlJugadorId);
            preparedStatementJugadorId.setString(1, nomJugador);
            preparedStatementJugadorId.setString(2, cognomJugador);
            ResultSet rsJugadorId = preparedStatementJugadorId.executeQuery();
            if (!rsJugadorId.next()) {
                System.out.println(ANSI_RED + "No s'ha trobat cap jugador amb el nom " + ANSI_YELLOW + nomComplet + ANSI_RED + "." + ANSI_RESET);
                return;
            }
            int jugadorId = rsJugadorId.getInt("jugador_id");

            // Obtenim les dades del jugador
            String sqlJugador = "SELECT * FROM jugadors WHERE jugador_id = ?";
            PreparedStatement preparedStatementJugador = conn.prepareStatement(sqlJugador);
            preparedStatementJugador.setInt(1, jugadorId);
            ResultSet rsJugador = preparedStatementJugador.executeQuery();
            rsJugador.next();

            // Inserim el jugador a la taula de històrics
            String sqlInsertHistoricJugador = "INSERT INTO historics_jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, dorsal, posicio, equip_id, data_retirat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatementInsertHistoricJugador = conn.prepareStatement(sqlInsertHistoricJugador);
            preparedStatementInsertHistoricJugador.setInt(1, rsJugador.getInt("jugador_id"));
            preparedStatementInsertHistoricJugador.setString(2, rsJugador.getString("nom"));
            preparedStatementInsertHistoricJugador.setString(3, rsJugador.getString("cognom"));
            preparedStatementInsertHistoricJugador.setDate(4, rsJugador.getDate("data_naixement"));
            preparedStatementInsertHistoricJugador.setFloat(5, rsJugador.getFloat("alcada"));
            preparedStatementInsertHistoricJugador.setFloat(6, rsJugador.getFloat("pes"));
            preparedStatementInsertHistoricJugador.setString(7, rsJugador.getString("dorsal"));
            preparedStatementInsertHistoricJugador.setString(8, rsJugador.getString("posicio"));
            preparedStatementInsertHistoricJugador.setInt(9, rsJugador.getInt("equip_id"));
            preparedStatementInsertHistoricJugador.setDate(10, new Date(System.currentTimeMillis())); // La data actual com a data de retir
            preparedStatementInsertHistoricJugador.executeUpdate();

            // Obtenim les estadístiques del jugador
            String sqlEstadistiques = "SELECT * FROM estadistiques_jugadors WHERE jugador_id = ?";
            PreparedStatement preparedStatementEstadistiques = conn.prepareStatement(sqlEstadistiques);
            preparedStatementEstadistiques.setInt(1, jugadorId);
            ResultSet rsEstadistiques = preparedStatementEstadistiques.executeQuery();

            // Inserim les estadístiques del jugador a la taula de històrics
            String sqlInsertHistoricEstadistiques = "INSERT INTO historics_estadistiques_jugadors (jugador_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs) " +
                    "SELECT jugador_id, minuts_jugats, punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, rebots_defensius, assistencies, robades, bloqueigs " +
                    "FROM estadistiques_jugadors WHERE jugador_id = ?";
            PreparedStatement preparedStatementInsertEstadistiques = conn.prepareStatement(sqlInsertHistoricEstadistiques);
            preparedStatementInsertEstadistiques.setInt(1, jugadorId);
            preparedStatementInsertEstadistiques.executeUpdate();

            // Eliminar les estadístiques del jugador de la taula estadistiques_jugadors
            String sqlDeleteEstadistiques = "DELETE FROM estadistiques_jugadors WHERE jugador_id = ?";
            PreparedStatement preparedStatementDeleteEstadistiques = conn.prepareStatement(sqlDeleteEstadistiques);
            preparedStatementDeleteEstadistiques.setInt(1, jugadorId);
            preparedStatementDeleteEstadistiques.executeUpdate();

            // Eliminar el jugador de la taula jugadors
            String sqlDeleteJugador = "DELETE FROM jugadors WHERE jugador_id = ?";
            PreparedStatement preparedStatementDeleteJugador = conn.prepareStatement(sqlDeleteJugador);
            preparedStatementDeleteJugador.setInt(1, jugadorId);
            preparedStatementDeleteJugador.executeUpdate();

            System.out.println(ANSI_BLUE + "El jugador " + ANSI_YELLOW + nomComplet + ANSI_BLUE + " ha estat retirat amb èxit!" + ANSI_RESET);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void canviarNomFranquiciaEquip() {
        try {
            System.out.println("Introdueix el nom de l'equip del qual vols canviar la franquícia:");
            String nomEquip = scan.nextLine();

            // Comprovar si l'equip existeix
            if (!existeixEquip(nomEquip)) {
                System.out.println(ANSI_RED + "L'equip especificat no existeix." + ANSI_RESET);
                return;
            }

            System.out.println("Introdueix el nou nom de la franquícia:");
            String nouNomFranquicia = scan.nextLine();

            // Actualitzar la BDD
            String sql = "UPDATE equips SET ciutat = ? WHERE nom = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nouNomFranquicia);
            preparedStatement.setString(2, nomEquip);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(ANSI_BLUE + "El nom de la franquícia de l'equip " + ANSI_YELLOW + nomEquip + ANSI_BLUE + " s'ha actualitzat a " + ANSI_YELLOW + nouNomFranquicia + ANSI_BLUE + " amb èxit!" + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "No s'ha pogut actualitzar el nom de la franquícia de l'equip." + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean existeixEquip(String nomEquip) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM equips WHERE nom = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nomEquip);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void modificarEstadistiquesJugador() {
        try {
            System.out.println("Introdueix el nom complet del jugador:");
            String nomComplet = scan.nextLine();

            // Dividir el nom en nom y cognom
            String[] parts = nomComplet.split(" ");
            String nomJugador = parts[0];
            String cognomJugador = parts.length > 1 ? parts[1] : "";

            // Consulta SQL para obtener el ID del jugador
            String sqlBuscarJugador = "SELECT jugador_id FROM jugadors WHERE nom = ? AND cognom = ?";
            PreparedStatement psBuscarJugador = conn.prepareStatement(sqlBuscarJugador);
            psBuscarJugador.setString(1, nomJugador);
            psBuscarJugador.setString(2, cognomJugador);
            ResultSet rsBuscarJugador = psBuscarJugador.executeQuery();

            // Verificar si se ha encontrado el jugador
            if (!rsBuscarJugador.next()) {
                System.out.println(ANSI_RED + "No s'ha trobat cap jugador amb el nom especificat." + ANSI_RESET);
                return;
            }

            int idJugador = rsBuscarJugador.getInt("jugador_id");

            // Consulta SQL para obtener el partit_id máximo
            String sqlMaxPartitId = "SELECT MAX(partit_id) AS max_partit_id FROM estadistiques_jugadors WHERE jugador_id = ?";
            PreparedStatement psMaxPartitId = conn.prepareStatement(sqlMaxPartitId);
            psMaxPartitId.setInt(1, idJugador);
            ResultSet rsMaxPartitId = psMaxPartitId.executeQuery();

            int maxPartitId = 0;
            if (rsMaxPartitId.next()) {
                maxPartitId = rsMaxPartitId.getInt("max_partit_id");
            } else {
                System.out.println(ANSI_RED + "No s'han trobat estadístiques per aquest jugador." + ANSI_RESET);
                return;
            }

            // Solicitar las nuevas estadísticas al usuario
            System.out.println("Introdueix les noves estadístiques del jugador per al partit amb partit_id = " + maxPartitId + ":");

            System.out.print("Minuts jugats: ");
            int minutsJugats = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            System.out.print("Punts: ");
            int punts = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            System.out.print("Tirs anotats: ");
            int tirsAnotats = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            System.out.print("Tirs tirats: ");
            int tirsTirats = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            System.out.print("Tirs triples anotats: ");
            int tirsTriplesAnotats = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            System.out.print("Tirs triples tirats: ");
            int tirsTriplesTirats = scan.nextInt();
            scan.nextLine(); // Limpiamos el buffer de entrada

            // Continuar con el resto de estadísticas...

            // Actualizar las estadísticas en la base de datos
            String sqlActualizarEstadistiques = "UPDATE estadistiques_jugadors SET minuts_jugats = ?, punts = ?, tirs_anotats = ?, tirs_tirats = ?, tirs_triples_anotats = ?, tirs_triples_tirats = ? WHERE jugador_id = ? AND partit_id = ?";
            PreparedStatement psActualizarEstadistiques = conn.prepareStatement(sqlActualizarEstadistiques);
            psActualizarEstadistiques.setInt(1, minutsJugats);
            psActualizarEstadistiques.setInt(2, punts);
            psActualizarEstadistiques.setInt(3, tirsAnotats);
            psActualizarEstadistiques.setInt(4, tirsTirats);
            psActualizarEstadistiques.setInt(5, tirsTriplesAnotats);
            psActualizarEstadistiques.setInt(6, tirsTriplesTirats);
            // Continuar con el resto de estadísticas...

            psActualizarEstadistiques.setInt(7, idJugador);
            psActualizarEstadistiques.setInt(8, maxPartitId);
            int filasActualizadas = psActualizarEstadistiques.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println(ANSI_GREEN + "Les estadístiques del jugador per al partit amb partit_id = " + maxPartitId + " s'han actualitzat correctament." + ANSI_RESET);
            } else {
                System.out.println(ANSI_RED + "No s'han pogut actualitzar les estadístiques del jugador per al partit amb partit_id = " + maxPartitId + "." + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
