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
        System.out.println("xd"+idVehiculo);
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
        Tipo_Abonado tipoAbonado = detalleVehiculo.getTipoAbonado();

        
        detalleVehiculoVista.txtPlaca.setText(vehiculo.getPlaca_veh());
        detalleVehiculoVista.txtMarca.setText(vehiculo.getMarca_veh());
        detalleVehiculoVista.txtColor.setText(vehiculo.getColor_veh());

        detalleVehiculoVista.txtConductorNombre.setText(conductor.getNombre_cond()+ " "+ conductor.getApellido_cond());
        detalleVehiculoVista.txtDNI.setText(conductor.getDni_cond());
        detalleVehiculoVista.txtTelefono.setText(conductor.getTelefono_cond());
        
        
        if (ticket != null) {
            detalleVehiculoVista.txtFechaEntrada.setText(ticket.getFecha_entrada()+ " " +ticket.getHora_entrada());
            String estadoTicket = ticket.getEstado_ticket();
            if ("ACTIVO".equals(estadoTicket)) {
                estadoTicket = "ESTACIONADO";
                detalleVehiculoVista.txtTicketEstatus.setForeground(new Color(19,183, 30));
            } else {
                estadoTicket = estadoTicket.toUpperCase();
            }
            detalleVehiculoVista.txtTicketEstatus.setText(estadoTicket);
            detalleVehiculoVista.txtPiso.setText(piso.getNumero_piso()+"");
            detalleVehiculoVista.txtZona.setText(zona.getNombre_zona_est());
        } else {
            System.out.println("sin ticket");
            detalleVehiculoVista.txtTicketEstatus.setText("NO ESTACIONADO");
            detalleVehiculoVista.txtTicketEstatus.setForeground(Color.GRAY);

            detalleVehiculoVista.txtPiso.setVisible(false);
            detalleVehiculoVista.txtZona.setVisible(false);
            detalleVehiculoVista.txtTicket2.setVisible(false);
            detalleVehiculoVista.txtTicket3.setVisible(false);
            detalleVehiculoVista.txtTicket4.setVisible(false);
            detalleVehiculoVista.txtTicket5.setVisible(false);
        }
        
        if (abonado != null) {
            detalleVehiculoVista.txtFechaInicioAbonado.setText(abonado.getFecha_inicio_abo());
            detalleVehiculoVista.txtFechaFinAbonado.setText(abonado.getFecha_fin_abo());
            detalleVehiculoVista.txtTipoAbono.setText(tipoAbonado.getNombre());
            detalleVehiculoVista.txtMontoAbonado.setText(tipoAbonado.getMonto()+"");
        } else {
            System.out.println("sin abonado");
            detalleVehiculoVista.txtFechaInicioAbonado.setVisible(false);
            detalleVehiculoVista.txtFechaFinAbonado.setVisible(false);
            detalleVehiculoVista.txtTipoAbono.setVisible(false);
            detalleVehiculoVista.txtMontoAbonado.setVisible(false);
            
            detalleVehiculoVista.txtAbo1.setVisible(false);
            detalleVehiculoVista.txtAbo2.setVisible(false);
            detalleVehiculoVista.txtAbo3.setVisible(false);
            detalleVehiculoVista.txtAbo4.setVisible(false);
            detalleVehiculoVista.txtAbo5.setVisible(false);
            detalleVehiculoVista.abonadoContainer.setVisible(false);
        }

        
        
 

    }
    

}
