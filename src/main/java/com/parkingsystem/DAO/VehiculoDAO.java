package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class VehiculoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ArrayList<Vehiculo> listarVehiculos(String filter, ArrayList<String> data) {
        ArrayList<Vehiculo> list = new ArrayList<>();
        Vehiculo vehiculo;
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                String sql = "";

                switch (filter) {
                    case "codigo":
                        sql = "SELECT * FROM Vehiculo WHERE id_veh = ?";
                        pst = connection.prepareStatement(sql);
                        pst.setString(1, data.get(0));
                        break;
                    case "matricula":
                        sql = "SELECT * FROM Vehiculo WHERE placa_veh = ?";
                        pst = connection.prepareStatement(sql);
                        pst.setString(1, data.get(1));
                        break;

                    default:
                        sql = "SELECT * FROM Vehiculo";
                        pst = connection.prepareStatement(sql);
                        break;
                }

                rs = pst.executeQuery();

                while (rs.next()) {
                    vehiculo = new Vehiculo();

                    vehiculo.setId(rs.getInt("id_veh"));
                    vehiculo.setPlaca_veh(rs.getString("placa_veh"));
                    vehiculo.setColor_veh(rs.getString("color_veh"));
                    vehiculo.setMarca_veh(rs.getString("marca_veh"));
                    vehiculo.setAño_veh(rs.getString("año_veh"));
                    vehiculo.setId_conductor(rs.getInt("id_cond"));

                    list.add(vehiculo);
                }

            } else {
                System.out.println("Conexión fallida");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error si ocurre
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

    
    public boolean agregarVehiculo(Vehiculo vehiculo)
    {
        boolean state = false;
        
        try {
            if(connection != null){
                String sql = "INSERT INTO Vehiculo (id_veh, placa_veh, color_veh, marca_veh, modelo_veh, año_deh, id_cond) VALUES (?,?,?,?,?,?,?)";
                
                pst = connection.prepareStatement(sql);
                
                pst.setInt(1, vehiculo.getId());
                pst.setString(2, vehiculo.getPlaca_veh());
                pst.setString(3, vehiculo.getColor_veh());
                pst.setString(4, vehiculo.getMarca_veh());
                pst.setString(5, vehiculo.getModelo_veh());
                pst.setString(6, vehiculo.getAño_veh());
                pst.setInt(7, vehiculo.getId_conductor());
                
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
    
    public boolean actualizarVehiculo(Vehiculo vehiculo)
    {
        boolean state = false;
        
        try {
            
            connection = connectionManager.connect();
            
            if(connection != null){
                
                String sql = "UPDATE Vehiculo SET placa_veh = ?, color_veh = ?, marca_veh = ?, año_veh = ?, id_conductor = ? WHERE id_veh = ?";
                
                pst = connection.prepareStatement(sql);
                pst.setString(1, vehiculo.getPlaca_veh());
                pst.setString(2, vehiculo.getColor_veh());
                pst.setString(3, vehiculo.getMarca_veh());
                pst.setString(4, vehiculo.getAño_veh());
                pst.setInt(5, vehiculo.getId_conductor());
                pst.setInt(6, vehiculo.getId());
                
                int res = pst.executeUpdate();
                
                state = res > 0;
                
            }else {
                System.out.println("Error al conectar");
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
    
    public boolean eliminarVehiculo(int id)
    {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if(connection != null)
            {
                String sql = "DELETE FROM Vehiculo WHERE id_ticket = ?";
                
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