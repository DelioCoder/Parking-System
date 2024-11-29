package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Vehiculo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class VehiculoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public ArrayList<Vehiculo> listarVehiculos(String placa) {
        ArrayList<Vehiculo> list = new ArrayList<>();
        Vehiculo vehiculo;
        try {
            connection = connectionManager.connect();
            if (connection != null) {

                String sql = "{CALL spObtenerVehiculosPorPlaca(?)}";
                pst = connection.prepareStatement(sql);
                pst.setString(1, placa); // Pasar la placa como parámetro

                rs = pst.executeQuery();

                while (rs.next()) {
                    vehiculo = new Vehiculo();

                    vehiculo.setId(rs.getInt("id_veh"));
                    vehiculo.setPlaca_veh(rs.getString("placa_veh"));
                    vehiculo.setColor_veh(rs.getString("color_veh"));
                    vehiculo.setMarca_veh(rs.getString("marca_veh"));
                    vehiculo.setModelo_veh(rs.getString("modelo_veh"));
                    vehiculo.setAño_veh(rs.getString("año_veh"));
                    vehiculo.setId_conductor(rs.getInt("id_cond"));
                    vehiculo.setNombre_cond(rs.getString("nombre_cond"));
                    vehiculo.setApellido_cond(rs.getString("apellido_cond"));
                    vehiculo.setDni_cond(rs.getString("dni_cond"));
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

    
    public boolean agregarVehiculo(Vehiculo vehiculo){
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            if(connection != null){
                
            // 2. Preparar la llamada al procedimiento almacenado
            String sql = "{CALL spCrearVehiculo(?, ?, ?, ?, ?, ?, ?)}"; // Llamada al procedimiento
            callableStatement = connection.prepareCall(sql);

            // 3. Establecer los parámetros
            callableStatement.setString(1, vehiculo.getPlaca_veh());
            callableStatement.setString(2, vehiculo.getColor_veh());
            callableStatement.setString(3, vehiculo.getMarca_veh());
            callableStatement.setString(4, vehiculo.getModelo_veh());
            callableStatement.setString(5, vehiculo.getAño_veh());
            callableStatement.setInt(6, vehiculo.getId_conductor());
            callableStatement.setBoolean(7, true);

            state = callableStatement.execute();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        
        return state;
    }
    
    public boolean actualizarVehiculo (Vehiculo vehiculo) {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                // Preparar la llamada al procedimiento almacenado
                String sql = "{CALL spActualizarVehiculo(?, ?, ?, ?, ?, ?, ?)}";
                callableStatement = connection.prepareCall(sql);

                // 3. Establecer los parámetros
                callableStatement.setInt(1, vehiculo.getId());
                callableStatement.setString(2, vehiculo.getPlaca_veh());
                callableStatement.setString(3, vehiculo.getColor_veh());
                callableStatement.setString(4, vehiculo.getMarca_veh());
                callableStatement.setString(5, vehiculo.getModelo_veh());
                callableStatement.setString(6, vehiculo.getAño_veh());
                callableStatement.setInt(7, vehiculo.getId_conductor());
                // Ejecutar el procedimiento
                callableStatement.execute();

                System.out.println("Procedimiento ejecutado correctamente.");
            } else {
                System.out.println("Error al conectar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return state;
        
    }
    
    public boolean cambiarEstadoVehiculo(int id, boolean nuevoEstado) {
        boolean state = false;

        try {
            connection = connectionManager.connect();
            
            if(connection != null)
            {
                
                String sql = "{CALL ActualizarEstadoConductor(?, ?)}"; // Llamada al procedimiento
                callableStatement = connection.prepareCall(sql);

                // 3. Asignar valores a los parámetros del procedimiento
                callableStatement.setInt(1, id); // Parámetro @id_cond
                callableStatement.setBoolean(2, nuevoEstado); // Parámetro @nuevo_estado

                // 4. Ejecutar el procedimiento almacenado
                int filasAfectadas = callableStatement.executeUpdate();

                // 5. Verificar si se realizó la actualización
                if (filasAfectadas > 0) {
                    System.out.println("Estado del conductor actualizado correctamente.");
                } else {
                    System.out.println("No se encontró un conductor con el ID especificado.");
                }
                
                state = filasAfectadas > 0;
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