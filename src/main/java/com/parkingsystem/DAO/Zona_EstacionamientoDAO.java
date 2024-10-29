package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Zona_Estacionamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Zona_EstacionamientoDAO {

    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;

    // Método para listar todas las zonas de estacionamiento
    public ArrayList<Zona_Estacionamiento> listarZonas() {
        ArrayList<Zona_Estacionamiento> list = new ArrayList<>();
        Zona_Estacionamiento zona;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "SELECT * FROM Zona_estacionamiento";
                pst = connection.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    zona = new Zona_Estacionamiento();
                    
                    zona.setId_zona_est(rs.getInt("id_zona_est"));
                    zona.setEstado_espacio(rs.getString("estado_espacio"));
                    zona.setId_piso_est(rs.getInt("id_piso_est"));
                    
                    list.add(zona);
                }
            } else {
                System.out.println("Conexión fallida");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        return list;
    }

    // Método para agregar una zona de estacionamiento
    public boolean agregarZona(Zona_Estacionamiento zona) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "INSERT INTO Zona_estacionamiento (id_zona_est, estado_espacio, id_piso_est) VALUES (?, ?, ?)";
                pst = connection.prepareStatement(sql);
                
                pst.setInt(1, zona.getId_zona_est());
                pst.setString(2, zona.getEstado_espacio());
                pst.setInt(3, zona.getId_piso_est());
                
                int res = pst.executeUpdate();
                
                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        
        return state;
    }

    // Método para actualizar una zona de estacionamiento
    public boolean actualizarZona(Zona_Estacionamiento zona) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "UPDATE Zona_estacionamiento SET estado_espacio = ?, id_piso_est = ? WHERE id_zona_est = ?";
                pst = connection.prepareStatement(sql);
                
                pst.setString(1, zona.getEstado_espacio());
                pst.setInt(2, zona.getId_piso_est());
                pst.setInt(3, zona.getId_zona_est());
                
                int res = pst.executeUpdate();
                
                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        
        return state;
    }

    // Método para eliminar una zona de estacionamiento
    public boolean eliminarZona(int id) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "DELETE FROM Zona_estacionamiento WHERE id_zona_est = ?";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, id);
                
                int res = pst.executeUpdate();
                
                state = res > 0;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        
        return state;
    }
    
}
