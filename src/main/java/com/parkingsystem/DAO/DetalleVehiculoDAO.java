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

        try {
            connection = connectionManager.connect();
            if (connection != null) {

                String sql = "{CALL sp_consulta_placa(?)}";
                pst = connection.prepareStatement(sql);
                pst.setInt(1, idVehiculo); // Pasar la placa como parámetro

                rs = pst.executeQuery();

                while (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo();
                    Conductor conductor = new Conductor();

                    vehiculo.setId(rs.getInt("id_veh"));
                    vehiculo.setPlaca_veh(rs.getString("placa_veh"));
                    vehiculo.setColor_veh(rs.getString("color_veh"));
                    vehiculo.setMarca_veh(rs.getString("marca_veh"));
                    vehiculo.setModelo_veh(rs.getString("modelo_veh"));
                    vehiculo.setAño_veh(rs.getString("año_veh"));
                    
                    conductor.setNombre_cond(rs.getString("id_cond"));
                    conductor.setNombre_cond(rs.getString("nombre_cond"));
                    conductor.setApellido_cond(rs.getString("apellido_cond"));
                    conductor.setDni_cond(rs.getString("dni_cond"));
                    conductor.setTelefono_cond(rs.getString("telefono_cond"));

                    detalleVehiculo.setVehiculo(vehiculo);
                    detalleVehiculo.setConductor(conductor);
                    
                    int idTicket = rs.getInt("id_ticket");
                    
                    if (idTicket != 0) {
                        Ticket_Estacionamiento ticket = new Ticket_Estacionamiento();
                        Zona_Estacionamiento zona = new Zona_Estacionamiento();
                        Piso_estacionamiento piso = new Piso_estacionamiento();
                        
                        ticket.setId_ticket(rs.getInt("id_ticket"));
                        ticket.setFecha_entrada(rs.getString("fecha_entrada"));
                        ticket.setHora_entrada(rs.getString("hora_entrada"));
                        ticket.setEstado_ticket(rs.getString("estado_ticket"));
                        zona.setNombre_zona_est(rs.getString("nombre_espacio"));
                        zona.setEstado_espacio(rs.getString("estado_espacio"));    
                        piso.setNumero_piso(rs.getInt("numero_piso_est"));
                        piso.setCapacidad(rs.getInt("capacidad_piso_est"));
                        
                        detalleVehiculo.setTicket(ticket);
                        detalleVehiculo.setZona(zona);
                        detalleVehiculo.setPiso(piso);
                    }
                    
                    int idAbonado = rs.getInt("id_abo");

                    if (idAbonado != 0) {
                        Abonado abonado = new Abonado();
                        Tipo_Abonado tipoAbonado = new Tipo_Abonado();
                        abonado.setFecha_inicio_abo(rs.getString("fecha_inicio_abo"));
                        abonado.setFecha_fin_abo(rs.getString("fecha_fin_abo"));
                        tipoAbonado.setNombre(rs.getString("nombre_tipo_abonado"));
                        tipoAbonado.setMonto(rs.getFloat("monto_tipo_abonado"));
                        detalleVehiculo.setAbonado(abonado);
                        detalleVehiculo.setTipoAbonado(tipoAbonado);
                    }
                    
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