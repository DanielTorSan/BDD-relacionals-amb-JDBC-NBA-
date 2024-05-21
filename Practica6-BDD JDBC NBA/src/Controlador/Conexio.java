package Controlador;

import java.sql.*;

public class Conexio {
    public static Connection conectar(){
        String bdd = "jdbc:mysql://localhost/nba";
        String user = "root";
        String password = "";
        try{
            Connection conn= DriverManager.getConnection(bdd,user,password);
            return conn;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public static void desconectar(Connection con){
        try {
            if (con != null){
                con.close();
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}

