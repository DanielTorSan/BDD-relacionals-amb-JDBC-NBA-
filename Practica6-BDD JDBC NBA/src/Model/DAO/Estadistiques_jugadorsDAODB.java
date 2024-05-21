package Model.DAO;

import Model.Taulas.Estadistiques_jugadors;

import java.sql.*;
public class Estadistiques_jugadorsDAODB {

    String taula = "estadistiques_jugadors";


    //CRUD
    public void create(Estadistiques_jugadors c, Connection conn) throws SQLException {

        String query = "INSERT INTO " + taula +
        " (partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_anotats," +
                "tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,assistencies,robades,bloqueigs)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getPartit_id());
        statement.setFloat(2,c.getMinuts_jugats());
        statement.setInt(3, c.getPunts());
        statement.setInt(4, c.getTirs_anotats());
        statement.setInt(5, c.getTirs_tirats());
        statement.setInt(6, c.getTirs_triples_anotats());
        statement.setInt(7, c.getTirs_triples_tirats());
        statement.setInt(8, c.getTirs_lliures_anotats());
        statement.setInt(9, c.getTirs_lliures_tirats());
        statement.setInt(10, c.getRebots_ofensius());
        statement.setInt(11, c.getAssistencies());
        statement.setInt(12, c.getRobades());
        statement.setInt(13, c.getBloqueigs());
        statement.execute();
        statement.close();
    }
    public boolean read(Estadistiques_jugadors c, Connection conn) throws SQLException {
        Estadistiques_jugadors er = read(c.getJugador_id(), conn);
        if (er == null) return false;
        c.set(er.getPartit_id(), er.getMinuts_jugats(), er.getPunts(), er.getTirs_anotats(),er.getTirs_tirats(),
        er.getTirs_triples_anotats(),er.getTirs_triples_tirats(),er.getTirs_lliures_anotats(),er.getTirs_lliures_tirats(),
        er.getRebots_ofensius(),er.getAssistencies(),er.getRobades(),er.getBloqueigs());
        return true;
    }

    public Estadistiques_jugadors read(int id, Connection conn) throws SQLException {
        String query = "SELECT * FROM " + taula + " WHERE jugador_id=?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultat = statement.executeQuery();
        if (resultat == null) {
            statement.close();
            return null;
        }
        resultat.next();
        int partit_id;
        float minuts_jugats;
        int punts;
        int tirs_anotats;
        int tirs_tirats;
        int tirs_triples_anotats;
        int tirs_triples_tirats;
        int tirs_lliures_anotats;
        int tirs_lliures_tirats;
        int rebots_ofensius;
        int assistencies;
        int robades;
        int bloqueigs;
        try {
            partit_id = resultat.getInt("partit_id");
            minuts_jugats = resultat.getFloat("minuts_jugats");
            punts = resultat.getInt("punts");
            tirs_anotats = resultat.getInt("tirs_anotats");
            tirs_tirats = resultat.getInt("tirs_tirats");
            tirs_triples_anotats = resultat.getInt("tirs_triples_anotats");
            tirs_triples_tirats = resultat.getInt("tirs_triples_tirats");
            tirs_lliures_anotats = resultat.getInt("tirs_lliures_anotats");
            tirs_lliures_tirats = resultat.getInt("tirs_lliures_tirats");
            rebots_ofensius = resultat.getInt("rebots_ofensius");
            assistencies = resultat.getInt("assistencies");
            robades = resultat.getInt("robades");
            bloqueigs = resultat.getInt("bloqueigs");
        } catch (Exception e) {
            return null;
        }
        resultat.close();
        statement.close();
        return new Estadistiques_jugadors(id, partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_anotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,assistencies,robades,bloqueigs);
    }
    public void update(Estadistiques_jugadors c, Connection conn) throws SQLException {
        String query = "UPDATE " + taula + " SET minuts_jugats= ? ,punts= ?,tirs_anotats= ?,tirs_tirats= ?,tirs_triples_anotats= ?,tirs_triples_tirats= ?" +
                ",tirs_lliures_anotats= ?,tirs_lliures_tirats= ?,rebots_ofensius= ?,assistencies= ?,robades= ?,bloqueigs= ? WHERE jugador_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getPartit_id());
        statement.setFloat(2,c.getMinuts_jugats());
        statement.setInt(3, c.getPunts());
        statement.setInt(4, c.getTirs_anotats());
        statement.setInt(5, c.getTirs_tirats());
        statement.setInt(6, c.getTirs_triples_anotats());
        statement.setInt(7, c.getTirs_triples_tirats());
        statement.setInt(8, c.getTirs_lliures_anotats());
        statement.setInt(9, c.getTirs_lliures_tirats());
        statement.setInt(10, c.getRebots_ofensius());
        statement.setInt(11, c.getAssistencies());
        statement.setInt(12, c.getRobades());
        statement.setInt(13, c.getBloqueigs());
        statement.execute();
        statement.close();
    }

    public void delete(Estadistiques_jugadors c, Connection conn) throws SQLException {
        String query = "DELETE FROM " + taula + " WHERE jugador_id= ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, c.getPartit_id());
        statement.execute();
        statement.close();
    }

    public boolean exists(Estadistiques_jugadors c, Connection conn) throws SQLException {
        return read(c, conn);
    }
}
