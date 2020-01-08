package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author David Esquivel Mendoza
 * @version 1.0
 * */

public class Conexion {
    private String urlBD = "jdbc:mysql://localhost:3306/sirius?serverTimezone=UTC";
    private String usrBD = "root";
    private String pwdBD = "System_17";
    private Connection conn = null;

    public boolean conectar(){
        boolean conectado = Boolean.FALSE;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(urlBD, usrBD, pwdBD);
            if (conn != null){
                conectado = Boolean.TRUE;
            }
        } catch (ClassNotFoundException | SQLException ex){
            System.out.println("Error en la función conectar: " + ex);
        }
        return conectado;
    }
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error en la función desconectar: " + ex);
        }
    }
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
