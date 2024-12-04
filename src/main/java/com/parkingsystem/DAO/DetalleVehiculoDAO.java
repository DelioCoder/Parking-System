package com.parkingsystem.DAO;

import com.parkingsystem.configuration.Cconnection;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.model.Boleta_Pago;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.DetalleVehiculo;
import com.parkingsystem.model.Piso_estacionamiento;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Tipo_Abonado;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.model.Zona_Estacionamiento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        detalleVehiculo.setBoletaPago(new Boleta_Pago());
        detalleVehiculo.setTipoAbonado(new Tipo_Abonado());

        
        try {
            connection = connectionManager.connect();
            if (connection != null) {

                String sql = "{CALL sp_consulta_placa(?)}";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, idVehiculo); // Pasar la placa como parámetro

                rs = pst.executeQuery();

                while (rs.next()) {
                    detalleVehiculo.getConductor().setNombre_cond(rs.getString("nombre_cond"));
                    detalleVehiculo.getConductor().setApellido_cond(rs.getString("apellido_cond"));
                    detalleVehiculo.getConductor().setDni_cond(rs.getString("dni_cond"));
                    detalleVehiculo.getConductor().setTelefono_cond(rs.getString("telefono_cond"));

                    
                    detalleVehiculo.getVehiculo().setPlaca_veh(rs.getString("placa_veh"));
                    detalleVehiculo.getVehiculo().setColor_veh(rs.getString("color_veh"));
                    detalleVehiculo.getVehiculo().setMarca_veh(rs.getString("marca_veh"));
                    detalleVehiculo.getVehiculo().setModelo_veh(rs.getString("modelo_veh"));
                    detalleVehiculo.getVehiculo().setAño_veh(rs.getString("año_veh"));
                    
                    detalleVehiculo.getPiso().setNumero_piso(rs.getInt("numero_piso_est"));
                    detalleVehiculo.getPiso().setCapacidad(rs.getInt("capacidad_piso_est"));
                    
                    
                    detalleVehiculo.getZona().setNombre_zona_est(rs.getString("nombre_espacio"));
                    detalleVehiculo.getZona().setEstado_espacio(rs.getString("estado_espacio"));
                    
                    detalleVehiculo.getTicket().setFecha_entrada(rs.getString("fecha_entrada"));
                    detalleVehiculo.getTicket().setHora_entrada(rs.getString("hora_entrada"));
                    detalleVehiculo.getTicket().setEstado_ticket(rs.getString("estado_ticket"));

                    detalleVehiculo.getBoletaPago().setMonto_pago(rs.getFloat("monto_pago"));
                    detalleVehiculo.getBoletaPago().setHora_salida(rs.getString("hora_salida"));
                    detalleVehiculo.getBoletaPago().setMetodo_pago(rs.getString("metodo_pago"));
                    
                    detalleVehiculo.getAbonado().setFecha_inicio_abo(rs.getString("fecha_inicio_abo"));
                    detalleVehiculo.getAbonado().setFecha_fin_abo(rs.getString("fecha_fin_abo"));
                    
                    detalleVehiculo.getTipoAbonado().setMonto(rs.getFloat("monto_tipo_abonado"));
                    detalleVehiculo.getTipoAbonado().setNombre(rs.getString("nombre_tipo_abonado"));

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
        return detalleVehiculo;
    }
    
}