package Controlador;
import Model.Taulas.*;

import java.sql.*;
import java.util.HashMap;

public class Importacio {

    public static HashMap<Integer, Jugadors> importJugador() {
        HashMap<Integer, Jugadors> jugadors = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM jugadors ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("jugador_id");
                String nom = resultat.getString("nom");
                String cog = resultat.getString("cognom");
                String data_naixement = resultat.getString("data_naixement");
                Float alcada = resultat.getFloat("alcada");
                Float pes = resultat.getFloat("pes");
                String dorsal = resultat.getString("dorsal");
                String posicio = resultat.getString("posicio");
                int equip_id = resultat.getInt("equip_id");
                Jugadors jugador = new Jugadors(id, nom, cog, data_naixement, alcada, pes, dorsal, posicio, equip_id);
                jugadors.put(i, jugador);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jugadors;
    }

    public static HashMap<Integer, Equips> importEquips() {
        HashMap<Integer, Equips> equips = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM equips ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);

            while (resultat.next()) {
                int id = resultat.getInt("equip_id");
                String nom = resultat.getString("nom");
                String acronim = resultat.getString("acronim");
                String ciutat = resultat.getString("ciutat");
                String divisio = resultat.getString("divisio");
                int guanyades = resultat.getInt("guanyades");
                int perdudes = resultat.getInt("perdudes");
                Equips equip = new Equips(id, nom, acronim, ciutat, divisio, divisio, guanyades, perdudes);
                equips.put(i, equip);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return equips;
    }

    public static HashMap<Integer, Estadistiques_jugadors> importEstadistiques_jugadors() {
        HashMap<Integer, Estadistiques_jugadors> estadistiques_jugadors = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM estadistiques_jugadors ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);
            while (resultat.next()) {
                int jugador_id = resultat.getInt("jugador_id");
                int partit_id = resultat.getInt("partit_id");
                Float minuts_jugats = resultat.getFloat("minuts_jugats");
                int punts = resultat.getInt("punts");
                int tirs_anotats = resultat.getInt("tirs_anotats");
                int tirs_tirats = resultat.getInt("tirs_tirats");
                int tirs_triples_anotats = resultat.getInt("tirs_triples_anotats");
                int tirs_triples_tirats = resultat.getInt("tirs_triples_tirats");
                int tirs_lliures_anotats = resultat.getInt("tirs_lliures_anotats");
                int tirs_lliures_tirats = resultat.getInt("tirs_lliures_tirats");
                int rebots_ofensius = resultat.getInt("rebots_ofensius");
                int assistencies = resultat.getInt("assistencies");
                int robades = resultat.getInt("robades");
                int bloqueigs = resultat.getInt("bloqueigs");
                Estadistiques_jugadors estadistica_jugador = new Estadistiques_jugadors(jugador_id, partit_id, minuts_jugats,
                        punts, tirs_anotats, tirs_tirats, tirs_triples_anotats, tirs_triples_tirats, tirs_lliures_anotats, tirs_lliures_tirats, rebots_ofensius, assistencies, robades, bloqueigs);
                estadistiques_jugadors.put(i, estadistica_jugador);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estadistiques_jugadors;
    }

    public static HashMap<Integer, Partits> importPartits() {
        HashMap<Integer, Partits> partits = new HashMap<>();
        try {
            int i = 1;
            Connection conn = Conexio.conectar();
            String sql = "SELECT * FROM partits ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultat = stmt.executeQuery(sql);
            while (resultat.next()) {
                int partit_id = resultat.getInt("partit_id");
                int equip_id = resultat.getInt("equip_id");
                String data_partit = resultat.getString("data_partit");
                String matx = resultat.getString("matx");
                String resultat_p = resultat.getString("resultat");
                Partits partit = new Partits(partit_id, equip_id, data_partit, matx, resultat_p);
                partits.put(i, partit);
                i++;
            }
            resultat.close();
            stmt.close();
            Conexio.desconectar(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return partits;
    }
}
