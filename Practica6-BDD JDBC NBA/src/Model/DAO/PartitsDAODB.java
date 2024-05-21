package Model.DAO;
import Model.Taulas.Partits;

import java.sql.*;

public class PartitsDAODB {

    String taula = "partits";


    //CRUD
    public void create(Partits c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula + " (equip_id,data_partit,matx,resultat) VALUES (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEquip_id());
        statement.setString(2,c.getData_partit());
        statement.setString(3,c.getMatx());
        statement.setString(4,c.getResultat());
        statement.execute();
        statement.close();
    }
    public boolean read(Partits c, Connection conn) throws SQLException {
        Partits er = read(c.getPartit_id(), conn);
        if (er == null) return false;
        c.set(er.getEquip_id(), er.getData_partit(), er.getMatx(), er.getResultat());
        return true;
    }

    public Partits read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE partit_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resul = statement.executeQuery();
        if (resul == null) {
            statement.close();
            return null;
        }
        resul.next();
        int equip_id;
        String data_partit;
        String matx;
        String resultat;
        try {
            equip_id = resul.getInt("equip_id");
            data_partit = resul.getString("data_partit");
            matx = resul.getString("matx");
            resultat= resul.getString("resultat");
        } catch (Exception e) {
            return null;
        }
        resul.close();
        statement.close();
        return new Partits(id, equip_id,data_partit,matx,resultat);
    }
    public void update(Partits c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET equip_id= ? ,data_partit= ?,matx= ?,resultat= ? WHERE partit_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getEquip_id());
        statement.setString(2,c.getData_partit());
        statement.setString(3,c.getMatx());
        statement.setString(4,c.getResultat());
        statement.execute();
        statement.close();
    }

    public void delete(Partits c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE partit_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getPartit_id());
        statement.execute();
        statement.close();
    }

    public boolean exists(Partits c, Connection conn) throws SQLException {
        return read(c, conn);
    }
}
