package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Conductor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class ConductorDAO {

    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ArrayList<Conductor> listarConductores(String filter, ArrayList<String> data)
    {
        ArrayList<Conductor> list = new ArrayList<>();
        Conductor conductor;
        try {
            connection = connectionManager.connect();
            if(connection != null){
                String sql = "";
                switch (filter) {
                case "nombre":
                    sql = "SELECT * FROM conductor WHERE nombre_cond LIKE ? OR apellido_cond LIKE ?";
                    pst = connection.prepareStatement(sql);
                    pst.setString(1, "%" + data.get(0) + "%");
                    pst.setString(2, "%" + data.get(0) + "%");
                    break;
                        
                    case "dni":
                        sql = "SELECT * FROM conductor WHERE dni_cond REGEXP ?";
                        pst = connection.prepareStatement(sql);
                        pst.setString(1, data.get(0));
                        break;
                    default:
                        sql = "SELECT * FROM conductor";
                        pst = connection.prepareStatement(sql);
                        break;
                }
                
                rs = pst.executeQuery();
                
                while(rs.next()){
                    conductor = new Conductor();
                    
                    conductor.setId_conductor(rs.getInt("id_cond"));
                    conductor.setNombre_cond(rs.getString("nombre_cond"));
                    conductor.setApellido_cond(rs.getString("apellido_cond"));
                    conductor.setDni_cond(rs.getInt("dni_cond"));
                    conductor.setTelefono_cond(rs.getInt("telefono_cond"));
                    
                    list.add(conductor);

                }
                
            }else {
                System.out.println("Conexión fallida");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.toString());
            }
        }
        return list;
    }
    
    public boolean agregarConductor(Conductor conductor){
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            if(connection != null){
                String sql = "INSERT INTO conductor (nombre_cond, apellido_cond, dni_cond, telefono_cond) VALUES (?,?,?,?)";
                
                pst = connection.prepareStatement(sql);
                
                pst.setString(1, conductor.getNombre_cond());
                pst.setString(2, conductor.getApellido_cond());
                pst.setInt(3, conductor.getDni_cond());
                pst.setInt(4, conductor.getTelefono_cond());
                
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
    
    public boolean actualizarConductor (Conductor conductor)
    {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            if (connection != null) {
                // Preparar la llamada al procedimiento almacenado
                String sql = "{CALL spActualizarConductor(?, ?, ?, ?, ?)}";
                callableStatement = connection.prepareCall(sql);

                
                // Establecer los parámetros
                callableStatement.setInt(1, conductor.getId_conductor()); // ID del conductor
                callableStatement.setString(2, conductor.getNombre_cond()); // Nombre
                callableStatement.setString(3, conductor.getApellido_cond()); // Apellido
                callableStatement.setInt(4, conductor.getDni_cond()); // DNI
                callableStatement.setLong(5, conductor.getTelefono_cond()); // Teléfono

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
    
    public boolean eliminarConductor(int id)
    {
        boolean state = false;
        
        try {
            connection = connectionManager.connect();
            
            if(connection != null)
            {
                String sql = "DELETE FROM conductor WHERE id_cond = ?";
                
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
