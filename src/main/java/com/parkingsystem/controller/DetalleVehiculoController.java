package com.parkingsystem.controller;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.view.vehiculo.DetalleVehiculoVista;
import java.util.List;
import com.parkingsystem.DAO.DetalleVehiculoDAO;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.model.Boleta_Pago;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.DetalleVehiculo;
import com.parkingsystem.model.Piso_estacionamiento;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Tipo_Abonado;
import com.parkingsystem.model.Zona_Estacionamiento;
import java.awt.Color;

/**
 *
 * @author John
 */
public class DetalleVehiculoController {
    
    private DetalleVehiculoDAO detalleVehiculoDAO = new DetalleVehiculoDAO();
    private DetalleVehiculoVista detalleVehiculoVista;
    public DetalleVehiculo detalleVehiculo;
    
    public DetalleVehiculoController(DetalleVehiculoVista detalleVehiculoVista) {
        this.detalleVehiculoVista = detalleVehiculoVista;
    }

    public DetalleVehiculoDAO getDetalleVehiculoDAO() {
        return detalleVehiculoDAO;
    }

    public void setDetalleVehiculoDAO(DetalleVehiculoDAO detalleVehiculoDAO) {
        this.detalleVehiculoDAO = detalleVehiculoDAO;
    }

    public DetalleVehiculoVista getDetalleVehiculoVista() {
        return detalleVehiculoVista;
    }

    public void setDetalleVehiculoVista(DetalleVehiculoVista detalleVehiculoVista) {
        this.detalleVehiculoVista = detalleVehiculoVista;
    }
    

    public void listarDetalleVehiculo(int idVehiculo) {
        detalleVehiculo = detalleVehiculoDAO.listarDetalleVehiculo(idVehiculo);

        if (detalleVehiculo == null) {
            System.out.println("No se han podido recuperar detalleVehiculo.");
            return;
        };
        
        Conductor conductor = detalleVehiculo.getConductor();
        Vehiculo vehiculo = detalleVehiculo.getVehiculo();
        Abonado abonado = detalleVehiculo.getAbonado();
        Ticket_Estacionamiento ticket = detalleVehiculo.getTicket();
        Zona_Estacionamiento zona = detalleVehiculo.getZona();
        Piso_estacionamiento piso = detalleVehiculo.getPiso();
        Boleta_Pago boletaPago = detalleVehiculo.getBoletaPago();
        Tipo_Abonado tipoAbonado = detalleVehiculo.getTipoAbonado();

        
        detalleVehiculoVista.txtPlaca.setText(vehiculo.getPlaca_veh());
        detalleVehiculoVista.txtMarca.setText(vehiculo.getMarca_veh());
        detalleVehiculoVista.txtColor.setText(vehiculo.getColor_veh());

        detalleVehiculoVista.txtConductorNombre.setText(conductor.getNombre_cond()+ " "+ conductor.getApellido_cond());
        detalleVehiculoVista.txtDNI.setText(conductor.getDni_cond());
        detalleVehiculoVista.txtTelefono.setText(conductor.getTelefono_cond());
        
        detalleVehiculoVista.txtFechaEntrada.setText(ticket.getFecha_entrada()+ " " +ticket.getHora_entrada());
        
        String estadoTicket = ticket.getEstado_ticket();
        
        if ("ACTIVO".equals(estadoTicket)) {
            estadoTicket = "ESTACIONADO";
            detalleVehiculoVista.txtTicketEstatus.setForeground(Color.GREEN);
        } else {
            estadoTicket = estadoTicket.toUpperCase();
        }
        
        detalleVehiculoVista.txtTicketEstatus.setText(estadoTicket);
        
        detalleVehiculoVista.txtFechaInicioAbonado.setText(abonado.getFecha_inicio_abo());
        detalleVehiculoVista.txtFechaFinAbonado.setText(abonado.getFecha_fin_abo());

        detalleVehiculoVista.txtPiso.setText(piso.getNumero_piso()+"");
        
        detalleVehiculoVista.txtZona.setText(zona.getNombre_zona_est());
        
        
        detalleVehiculoVista.txtTipoAbono.setText(tipoAbonado.getNombre());
        detalleVehiculoVista.txtMontoAbonado.setText(tipoAbonado.getMonto()+"");

    }
    

}
