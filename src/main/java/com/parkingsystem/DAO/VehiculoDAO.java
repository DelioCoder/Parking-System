package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author david
 */
public class VehiculoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private PreparedStatement pst;
    
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
}