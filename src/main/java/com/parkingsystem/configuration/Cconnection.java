package com.parkingsystem.configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author david
 */
public class Cconnection {
    
    Connection connect = null;
    
    String user = "user_el"; // user_el
    String password = "T@@st456789"; // T@@st456789
    String database = "dbparqueo";
    String ip = "localhost";
    String puerto = "1433";
    
    public Connection connect() {
        try {
            String cadena = "jdbc:sqlserver://localhost:"+puerto+";"+"databaseName="+database+";encrypt=false";
            
            connect = DriverManager.getConnection(cadena, this.user, this.password);
            connect = DriverManager.getConnection(cadena);
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return connect;
    }
    
        public void close() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (Exception e) {
            System.out.println("Error closing connection: " + e.toString());
        }
    }
}
