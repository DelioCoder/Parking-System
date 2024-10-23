package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Piso_estacionamiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class Piso_EstacionamientoDAO {

    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;

    // Método para listar todos los pisos de estacionamiento
    public ArrayList<Piso_estacionamiento> listarPisos() {
        ArrayList<Piso_estacionamiento> list = new ArrayList<>();
        Piso_estacionamiento piso;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "SELECT * FROM Piso_estacionamiento";
                pst = connection.prepareCall(sql);
                rs = pst.executeQuery();
                
                while (rs.next()) {
                    piso = new Piso_estacionamiento();
                    
                    piso.setId_piso_est(rs.getInt("id_piso_est"));
                    piso.setNumero_piso(rs.getInt("numero_piso"));
                    piso.setCapacidad(rs.getInt("capacidad"));
                    
                    list.add(piso);
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

    // Método para agregar un piso de estacionamiento
    public boolean agregarPiso(Piso_estacionamiento piso) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "INSERT INTO Piso_estacionamiento (id_piso_est, numero_piso, capacidad) VALUES (?, ?, ?)";
                pst = connection.prepareStatement(sql);
                
                pst.setInt(1, piso.getId_piso_est());
                pst.setInt(2, piso.getNumero_piso());
                pst.setInt(3, piso.getCapacidad());
                
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

    // Método para actualizar un piso de estacionamiento
    public boolean actualizarPiso(Piso_estacionamiento piso) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "UPDATE Piso_estacionamiento SET numero_piso = ?, capacidad = ? WHERE id_piso_est = ?";
                pst = connection.prepareStatement(sql);
                
                pst.setInt(1, piso.getNumero_piso());
                pst.setInt(2, piso.getCapacidad());
                pst.setInt(3, piso.getId_piso_est());
                
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

    // Método para eliminar un piso de estacionamiento
    public boolean eliminarPiso(int id) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if (connection != null) {
                String sql = "DELETE FROM Piso_estacionamiento WHERE id_piso_est = ?";
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
