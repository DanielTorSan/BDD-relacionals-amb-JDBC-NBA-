package Model.DAO;
import Model.Taulas.Jugadors;


import java.sql.*;

public class JugadorsDAODB {

    String taula = "jugadors";


    // CRUD
    public void create(Jugadors c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (nom,cognom,data_naixement,alcada,pes,dorsal,posicio,equip_id) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCognom());
        statement.setString(3, c.getData_naixement());
        statement.setFloat(4, c.getAlcada());
        statement.setFloat(5, c.getPes());
        statement.setString(6, c.getDorsal());
        statement.setString(7, c.getPosicio());
        statement.setInt(8, c.getEquip_id());
        statement.execute();
        statement.close();
    }
    public boolean read(Jugadors c, Connection conn) throws SQLException {
        Jugadors er = read(c.getJugador_id(), conn);
        if (er == null) return false;
        c.set(er.getNom(), er.getCognom(), er.getData_naixement(), er.getAlcada(), er.getPes(), er.getDorsal(), er.getPosicio(), er.getEquip_id());
        return true;
    }

    public Jugadors read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE jugador_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        String nom;
        String cognom;
        String data_naixement;
        float alcada;
        float pes;
        String dorsal;
        String posicio;
        int equip_id;
        try {
            nom = resultat.getString("nom");
            cognom = resultat.getString("cognom");
            data_naixement = resultat.getString("data_neixement");
            alcada = resultat.getFloat("alcada");
            pes = resultat.getFloat("pes");
            dorsal = resultat.getString("dorsal");
            posicio = resultat.getString("posicio");
            equip_id = resultat.getInt("equip_id");
        } catch (Exception e) {
            return null;
        }
        resultat.close();
        statement.close();
        return new Jugadors(id, nom,cognom,data_naixement,alcada,pes,dorsal,posicio,equip_id);
    }
    public void update(Jugadors c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET nom= ? ,cognom= ?,data_naixement= ?,alcada= ?,pes= ?,dorsal= ?,posicio= ?,equip_id= ? WHERE jugador_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getCognom());
        statement.setString(3, c.getData_naixement());
        statement.setFloat(4, c.getAlcada());
        statement.setFloat(5, c.getPes());
        statement.setString(6, c.getDorsal());
        statement.setString(7, c.getPosicio());
        statement.setInt(8, c.getEquip_id());
        statement.execute();
        statement.close();
    }

    public void delete(Jugadors c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE jugador_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getJugador_id());
        statement.execute();
        statement.close();
    }

    public boolean exists(Jugadors c, Connection conn) throws SQLException {
        return read(c, conn);
    }
}
