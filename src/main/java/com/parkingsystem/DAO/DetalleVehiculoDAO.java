package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.DetalleVehiculo;
import com.parkingsystem.model.Piso_estacionamiento;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.model.Zona_Estacionamiento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author john
 */
public class DetalleVehiculoDAO {
    
    private Cconnection connectionManager = new Cconnection();
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private PreparedStatement pst;
    private ResultSet rs;
    
    
    public DetalleVehiculo listarDetalleVehiculo(int idVehiculo) {
        DetalleVehiculo detalleVehiculo = new DetalleVehiculo();
        detalleVehiculo.setVehiculo(new Vehiculo());
        detalleVehiculo.setConductor(new Conductor());
        detalleVehiculo.setAbonado(new Abonado());
        detalleVehiculo.setTicket(new Ticket_Estacionamiento());
        detalleVehiculo.setZona(new Zona_Estacionamiento());
        detalleVehiculo.setPiso(new Piso_estacionamiento());

        
        try {
            connection = connectionManager.connect();
            if (connection != null) {

                String sql = "{CALL sp_consulta_placa(?)}";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, idVehiculo); // Pasar la placa como parámetro

                rs = pst.executeQuery();
                
                /*


		bp.fecha_pago AS FechaPago,  
		bp.monto_pago AS MontoPago,  
		bp.metodo_pago AS MetodoPago,  
		bp.hora_salida AS HoraSalida  
                
                */

                while (rs.next()) {
                    detalleVehiculo.getConductor().setNombre_cond(rs.getString("NombreConductor"));
                    detalleVehiculo.getConductor().setApellido_cond(rs.getString("ApellidoConductor"));
                    detalleVehiculo.getConductor().setDni_cond(rs.getString("DNICoche"));
                    detalleVehiculo.getConductor().setTelefono_cond(rs.getString("TelefonoConductor"));

                    
                    detalleVehiculo.getVehiculo().setPlaca_veh(rs.getString("PlacaVehiculo"));
                    detalleVehiculo.getVehiculo().setColor_veh(rs.getString("ColorVehiculo"));
                    detalleVehiculo.getVehiculo().setMarca_veh(rs.getString("MarcaVehiculo"));
                    detalleVehiculo.getVehiculo().setModelo_veh(rs.getString("ModeloVehiculo"));
                    detalleVehiculo.getVehiculo().setAño_veh(rs.getString("AñoVehiculo"));
                    
                    detalleVehiculo.getPiso().setNumero_piso(rs.getInt("NumeroPisoEstacionamiento"));
                    detalleVehiculo.getPiso().setCapacidad(rs.getInt("CapacidadPisoEstacionamiento"));
                    
                    
                    detalleVehiculo.getZona().setNombre_zona_est(rs.getString("NombreEspacio"));
                    detalleVehiculo.getZona().setEstado_espacio(rs.getString("EstadoEspacio"));
                    
                    detalleVehiculo.getTicket().setFecha_entrada(rs.getString("FechaEntrada"));
                    detalleVehiculo.getTicket().setHora_entrada(rs.getString("HoraEntrada"));
                    detalleVehiculo.getTicket().setEstado_ticket(rs.getString("EstadoTicket"));

                    detalleVehiculo.getAbonado().setFecha_inicio_abo(rs.getString("FechaEntrada"));
                    detalleVehiculo.getAbonado().set(rs.getString("HoraEntrada"));
                    detalleVehiculo.getAbonado().set(rs.getString("EstadoTicket"));
                    detalleVehiculo.getAbonado().setFecha_entrada(rs.getString("FechaEntrada"));
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
        return vehiculo;
    }
    
}