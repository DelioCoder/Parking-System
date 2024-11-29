package com.parkingsystem.controller;

import com.parkingsystem.DAO.TicketDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.DAO.Zona_EstacionamientoDAO;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.model.Zona_Estacionamiento;
import com.parkingsystem.input.ComboBoxItem;
import com.parkingsystem.input.ComboBoxItemVeh;
import com.parkingsystem.view.ticket.GenerarTicket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TicketController implements ActionListener
{
    private TicketDAO ticketDao;
    private Zona_EstacionamientoDAO zonaDao;
    private VehiculoDAO vehiculoDao;
    private Ticket_Estacionamiento ticket;
    private GenerarTicket vista;

    public TicketController(GenerarTicket v) {
        this.zonaDao = new Zona_EstacionamientoDAO();
        this.vehiculoDao = new VehiculoDAO();
        this.ticketDao = new TicketDAO();
        this.ticket = new Ticket_Estacionamiento();
        this.vista = v;
        
        cargarVehiculos();
        cargarZonas();
        
        this.vista.btnGrabar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGrabar) {
            registrarTicket(ticket);
        }
    }
    
    private void cargarVehiculos()
    {
        ArrayList<Vehiculo> vehiculos = vehiculoDao.listarVehiculos("");
        for(Vehiculo vehiculo : vehiculos)
        {
            this.vista.BxPlacaVeh.addItem(new ComboBoxItemVeh(vehiculo.getId(), vehiculo.getPlaca_veh()));
        }
        
    }
    
    private void cargarZonas() {
        ArrayList<Zona_Estacionamiento> zonas = zonaDao.listarZonas();
        for (Zona_Estacionamiento zona : zonas) {
            String displayText = "Zona " + zona.getId_zona_est() + " - " + zona.getEstado_espacio();
            this.vista.BxUbi.addItem(new ComboBoxItem(zona.getId_zona_est(), displayText));
        }
    }


    public ArrayList<Ticket_Estacionamiento> listarTickets(String filter)
    {
        return this.ticketDao.listarTickets(filter, 0);
    }
    
    public void registrarTicket(Ticket_Estacionamiento ticket)
    {
        try {
            ComboBoxItemVeh selectedPlaca = (ComboBoxItemVeh) this.vista.BxPlacaVeh.getSelectedItem();
            if (selectedPlaca != null) {
                ticket.setId_veh(selectedPlaca.getId());
            }
            
            ComboBoxItem selectedZona = (ComboBoxItem) this.vista.BxUbi.getSelectedItem();

            if (selectedZona != null) {
                ticket.setId_zona_est(selectedZona.getId());
            }
            ticket.setFecha_entrada(vista.txtEntrada.getText());
            ticket.setHora_entrada(vista.txtHoraEntrada.getText());
            ticket.setEstado_ticket("Activo");
            
            if (ticketDao.agregarTicket(ticket)) {
                JOptionPane.showMessageDialog(vista, "Ticket registrado con éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar el ticket.");
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public boolean actualizarTicket(Ticket_Estacionamiento ticket)
    {
        return this.ticketDao.actualizarTicket(ticket);
    }
    
    public boolean eliminarTicket(int id)
    {
        return this.ticketDao.eliminarTicket(id);
    }
    
}
