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
                String estat = resultat.getString("estat");
                String divisio = resultat.getString("divisio");
                int guanyades = resultat.getInt("guanyades");
                int perdues = resultat.getInt("perdues");
                Equips equip = new Equips(id, nom, acronim, ciutat, estat, divisio, guanyades, perdues);
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
}
