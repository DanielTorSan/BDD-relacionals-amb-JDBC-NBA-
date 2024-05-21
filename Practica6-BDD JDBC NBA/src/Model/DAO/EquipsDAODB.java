package Model.DAO;

import Model.Taulas.Equips;

import java.sql.*;

public class EquipsDAODB {
    String taula = "equips";


    // CRUD
    public void create(Equips c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (nom,acronim,ciutat,estat,divisio,guanyades,perdues) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getAcronim());
        statement.setString(3, c.getCiutat());
        statement.setString(4, c.getEstat());
        statement.setString(5, c.getDivisio());
        statement.setInt(6, c.getGuanyades());
        statement.setInt(7, c.getPerdues());
        statement.execute();
        statement.close();
    }

    public boolean read(Equips c, Connection conn) throws SQLException {
        Equips er = read(c.getEquip_id(), conn);
        if (er == null) return false;
        c.set(er.getNom(), er.getAcronim(), er.getCiutat(), er.getEstat(), er.getDivisio(), er.getGuanyades(), er.getPerdues());
        return true;
    }

    public Equips read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE equip_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        String nom;
        String acronim;
        String ciutat;
        String estat;
        String divisio;
        int guanyades;
        int perdues;
        try {
            nom = resultat.getString("nom");
            acronim = resultat.getString("acronim");
            ciutat = resultat.getString("ciutat");
            estat = resultat.getString("estat");
            divisio = resultat.getString("divisio");
            guanyades = resultat.getInt("guanyades");
            perdues = resultat.getInt("perdues");
        } catch (Exception e) {
            return null;
        }
        resultat.close();
        statement.close();
        return new Equips(id, nom, acronim, ciutat, estat, divisio, guanyades, perdues);
    }

    public void update(Equips c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET nom= ? ,acronim= ?,ciutat= ?,estat= ?,divisio= ?,guanyades= ?,perdues= ? WHERE equip_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, c.getNom());
        statement.setString(2, c.getAcronim());
        statement.setString(3, c.getCiutat());
        statement.setString(4, c.getEstat());
        statement.setString(5, c.getDivisio());
        statement.setInt(6, c.getGuanyades());
        statement.setInt(7, c.getPerdues());
        statement.execute();
        statement.close();
    }

    public void delete(Equips c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE equip_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEquip_id());
        statement.execute();
        statement.close();
    }

    public boolean exists(Equips c, Connection conn) throws SQLException {
        return read(c, conn);
    }

}
