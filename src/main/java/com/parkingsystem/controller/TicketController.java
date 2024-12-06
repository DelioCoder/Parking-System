package com.parkingsystem.controller;

import com.parkingsystem.DAO.Boleta_PagoDAO;
import com.parkingsystem.DAO.TicketDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.DAO.Zona_EstacionamientoDAO;
import com.parkingsystem.model.Ticket_Estacionamiento;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.model.Zona_Estacionamiento;
import com.parkingsystem.input.ComboBoxItem;
import com.parkingsystem.input.ComboBoxItemVeh;
import com.parkingsystem.model.Boleta_Pago;
import com.parkingsystem.view.ticket.GenerarTicket;
import com.parkingsystem.view.ticket.TablaTickets;
import com.parkingsystem.view.ticket.Voucher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TicketController implements ActionListener
{
    private TicketDAO ticketDao;
    private Zona_EstacionamientoDAO zonaDao;
    private VehiculoDAO vehiculoDao;
    private Boleta_PagoDAO boletaDao;
    private Ticket_Estacionamiento ticket;
    private TablaTickets vista_principal;
    private GenerarTicket vista;

    public TicketController(GenerarTicket v, TablaTickets home) {
        this.zonaDao = new Zona_EstacionamientoDAO();
        this.vehiculoDao = new VehiculoDAO();
        this.ticketDao = new TicketDAO();
        this.boletaDao = new Boleta_PagoDAO();
        this.ticket = new Ticket_Estacionamiento();
        this.vista = v;
        this.vista_principal = home;
        
        if (this.vista != null) {
            cargarVehiculos();
            cargarZonas();
            this.vista.btnGrabar.addActionListener(this);
        }
        
        if (this.vista_principal != null){
            this.vista_principal.btnPagar.addActionListener(this);
            agregarEventos();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.vista != null && e.getSource() == vista.btnGrabar) {
            registrarTicket(ticket);
        } else if(this.vista_principal != null && e.getSource() == vista_principal.btnPagar){
            pagarTicket();
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


    public void rellenarTablaTickets(String filter)
    {
        List<Ticket_Estacionamiento> tickets = this.ticketDao.listarTickets(filter, 0);
        
        if (tickets == null) {
            System.out.println("No se han podido recuperar tickets.");
            return;
        }
        
        String[] columnas = {"id", "Fecha_Entrada", "Hora_Entrada", "Estado"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        
        for(Ticket_Estacionamiento ticket : tickets){
            
            Object[] fila = {
                ticket.getId_ticket(),
                ticket.getFecha_entrada(),
                ticket.getHora_entrada(),
                ticket.getEstado_ticket(),
            };
            
            modeloTabla.addRow(fila);
            
        }
        this.vista_principal.JTableTickets.setModel(modeloTabla);
        
    }
    
    private void agregarEventos() {
        vista_principal.txtBuscarTicket.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String texto = vista_principal.txtBuscarTicket.getText().trim();

                    if (!texto.isEmpty()) {
                        int id = Integer.parseInt(texto);
                        buscarPorCodigo(id);
                    } else {
                        rellenarTablaTickets("");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Ingrese un código válido.");
                }
            }
        });
    }
    
    private void buscarPorCodigo(int id) {
        ArrayList<Ticket_Estacionamiento> tickets = this.ticketDao.listarTickets("codigo", id);
        DefaultTableModel modelo = (DefaultTableModel) vista_principal.JTableTickets.getModel();
        modelo.setRowCount(0);

        for (Ticket_Estacionamiento ticket : tickets) {
            modelo.addRow(new Object[]{
                ticket.getId_ticket(),
                ticket.getFecha_entrada(),
                ticket.getHora_entrada(),
                ticket.getEstado_ticket()
            });
        }
    }
    
    public void registrarTicket(Ticket_Estacionamiento ticket)
    {
        try {
            ComboBoxItemVeh selectedPlaca = (ComboBoxItemVeh) this.vista.BxPlacaVeh.getSelectedItem();
            if (selectedPlaca != null) {
                ticket.setId_veh(selectedPlaca.getId());
            }
            
            ComboBoxItem selectedZona = (ComboBoxItem) this.vista.BxUbi.getSelectedItem();

            if(selectedZona.toString().contains("Ocupado")){
                JOptionPane.showMessageDialog(vista, "Este espacio esta ocupado.");
            }
            
            if (selectedZona != null) {
                ticket.setId_zona_est(selectedZona.getId());
            }
            ticket.setFecha_entrada(vista.txtEntrada.getText());
            ticket.setHora_entrada(vista.txtHoraEntrada.getText());
            ticket.setEstado_ticket("ACTIVO");
            
            if (ticketDao.agregarTicket(ticket) && !selectedZona.toString().contains("Ocupado")) {
                JOptionPane.showMessageDialog(vista, "Ticket registrado con éxito.");
                rellenarTablaTickets("");
            } else {
                JOptionPane.showMessageDialog(vista, "Error al registrar el ticket.");
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    
    public void pagarTicket(){
        try {
            Boleta_Pago boleta = new Boleta_Pago();
            
            int filaSeleccionada = vista_principal.JTableTickets.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(vista_principal, "Por favor, seleccione un ticket.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idTicket = (int) vista_principal.JTableTickets.getValueAt(filaSeleccionada, 0);
            String horaEntradaStr = (String) vista_principal.JTableTickets.getValueAt(filaSeleccionada, 2);
            
            LocalDateTime ahora = LocalDateTime.now();
            String fechaPago = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String horaSalida = ahora.format(DateTimeFormatter.ofPattern("HH:mm"));
     
            double montoPago = calcularMonto(horaEntradaStr, horaSalida);
            
            boleta.setFecha_pago(fechaPago);
            boleta.setMonto_pago((float) montoPago);
            boleta.setMetodo_pago("efectivo");
            boleta.setHora_salida(horaSalida);
            boleta.setId_ticket(idTicket);
            
            if (this.ticketDao.actualizarEstadoTicket(idTicket, "PAGADO") && this.boletaDao.agregarBoleta(boleta)) {
                JOptionPane.showMessageDialog(vista_principal, "El estado del ticket se actualizó a 'Pagado'.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Voucher voucherForm = new Voucher(boleta);
                voucherForm.setVisible(true);
                rellenarTablaTickets("");
            } else {
                JOptionPane.showMessageDialog(vista_principal, "Error al actualizar el estado del ticket.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista_principal, "Ocurrió un error al intentar actualizar el estado del ticket.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private double calcularMonto(String horaEntradaStr, String horaSalidaStr) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalTime horaEntrada = LocalTime.parse(horaEntradaStr, formatter);
            LocalTime horaSalida = LocalTime.parse(horaSalidaStr, formatter);

            long duracionEnMinutos = Duration.between(horaEntrada, horaSalida).toMinutes();
            long duracionEnHoras = (duracionEnMinutos / 60) + (duracionEnMinutos % 60 == 0 ? 0 : 1);

            double montoBase = 20.0;
            double montoAdicionalPorHora = 10.0;
            return Math.abs(montoBase + (duracionEnHoras - 1) * montoAdicionalPorHora);
        } catch (Exception e) {
            e.printStackTrace();
            return 20.0;
        }
    }
   
}
