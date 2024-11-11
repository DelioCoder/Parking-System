package com.parkingsystem.controller;

import com.parkingsystem.DAO.ConsultaVehiculoDAO;
import com.parkingsystem.DAO.TicketDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.DAO.Zona_EstacionamientoDAO;
import com.parkingsystem.input.ComboBoxItem;
import com.parkingsystem.input.ComboBoxItemVeh;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.model.Zona_Estacionamiento;
import com.parkingsystem.view.vehiculo.ConsultaVehiculo;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ConsultaVehiculoController {
    
    private ConsultaVehiculoDAO consultaVehiculoDAO;
    private ConsultaVehiculo vista;

    public ConsultaVehiculoController(ConsultaVehiculo v) {
        this.consultaVehiculoDAO = new ConsultaVehiculoDAO();
        this.vista = v;
        
        cargarConsultaPorPlaca();
        
        //this.vista.btnGrabar.addActionListener(this);
    }
    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGrabar) {
            registrarTicket(ticket);
        }
    }
    */
    
    private void cargarConsultaPorPlaca()
    {
        /*
        ArrayList<Vehiculo> vehiculos = ConsultaVehiculoDAO.listarVehiculos("", null);
        for(Vehiculo vehiculo : vehiculos)
        {
            this.vista.BxPlacaVeh.addItem(new ComboBoxItemVeh(vehiculo.getId(), vehiculo.getPlaca_veh()));
        }
    */
    }
    
}
