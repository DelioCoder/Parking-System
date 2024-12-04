package com.parkingsystem.controller;

import com.parkingsystem.DAO.AbonadoDAO;
import com.parkingsystem.DAO.Tipo_AbonadoDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.input.ComboBoxItemVeh;
import com.parkingsystem.input.ComboBoxTipoAbonado;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.model.Tipo_Abonado;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.view.abonado.RegistrarAbonado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class AbonadoController implements ActionListener {
    private AbonadoDAO abonadoDao;
    private VehiculoDAO vehiculoDao;
    private Tipo_AbonadoDAO tipoAbonadoDao;
    private RegistrarAbonado vista;
    private Abonado abonado;
    
    public AbonadoController(RegistrarAbonado v) {
        this.abonadoDao = new AbonadoDAO();
        this.vehiculoDao = new VehiculoDAO();
        this.tipoAbonadoDao = new Tipo_AbonadoDAO();
        this.vista = v;
        this.abonado = new Abonado();
        
        this.vista.btnGrabarAbono.addActionListener(this);
        cargarVehiculos();
        cargarTipoAbonado();
        
        this.vista.TipoBX.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                actualizarCamposSegunTipo();
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGrabarAbono) {
            agregarAbonado(abonado);
        }
    }
    
    private void cargarVehiculos()
    {
        ArrayList<Vehiculo> vehiculos = vehiculoDao.listarVehiculos("");
        for(Vehiculo vehiculo : vehiculos)
        {
            this.vista.VehiculosCBX.addItem(new ComboBoxItemVeh(vehiculo.getId(), vehiculo.getPlaca_veh()));
        }
        
    }
    
    private void cargarTipoAbonado()
    {
        this.vista.TipoBX.removeAllItems();
        this.vista.TipoBX.addItem(new ComboBoxTipoAbonado(0, "Seleccionar tipo de abonado"));
        ArrayList<Tipo_Abonado> tipo_abonados = tipoAbonadoDao.listarTipoAbonados();
        System.out.println(tipo_abonados);
        for(Tipo_Abonado tipo_abonado : tipo_abonados)
        {
            this.vista.TipoBX.addItem(new ComboBoxTipoAbonado(tipo_abonado.getId_tipo_abo(), tipo_abonado.getNombre()));
        }
        
    }
    
    private void actualizarCamposSegunTipo() {
        ComboBoxTipoAbonado tipoSeleccionado = (ComboBoxTipoAbonado) vista.TipoBX.getSelectedItem();

        if (tipoSeleccionado != null) {
            String tipoNombre = tipoSeleccionado.getNombre();

            switch (tipoNombre.toLowerCase()) {
                case "mensual":
                    vista.txtMonto.setText("15.00");
                    vista.txtFechaInicio.setText(obtenerFechaActual());
                    vista.txtFechaFin.setText(obtenerFechaConIncremento(1));
                    break;
                case "trimestral":
                    vista.txtMonto.setText("30.00");
                    vista.txtFechaInicio.setText(obtenerFechaActual());
                    vista.txtFechaFin.setText(obtenerFechaConIncremento(3));
                    break;
        
                default:
                    break;
            }
        }
    }

    private String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaActual.format(formato);
    }

    private String obtenerFechaConIncremento(int meses) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaFin = fechaActual.plusMonths(meses);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaFin.format(formato);
    }

    
    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data)
    {
        return this.abonadoDao.listarAbonados(filter, data);
    }
    
    public void agregarAbonado(Abonado abonado)
    {
        try {
            
            ComboBoxItemVeh selectedPlaca = (ComboBoxItemVeh) this.vista.VehiculosCBX.getSelectedItem();
            if (selectedPlaca != null) {
                abonado.setId_vehiculo(selectedPlaca.getId());
            }
            ComboBoxTipoAbonado tipoSeleccionado = (ComboBoxTipoAbonado) vista.TipoBX.getSelectedItem();

            if (tipoSeleccionado != null) {
                abonado.setId_tipo_abonado(tipoSeleccionado.getId());
            }
            
            abonado.setFecha_inicio_abo(vista.txtFechaInicio.getText());
            abonado.setFecha_fin_abo(vista.txtFechaFin.getText());
            
            if(abonadoDao.agregarAbonado(abonado)){
                JOptionPane.showMessageDialog(vista, "Abonado registrado correctamente");
            }else {
                JOptionPane.showMessageDialog(vista, "Error al registrar abonado");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public boolean actualizarAbonado(Abonado abonado)
    {
        return this.abonadoDao.actualizarAbonado(abonado);
    }
    
    public boolean eliminarAbonado(int id)
    {
        return this.abonadoDao.eliminarAbonado(id);
    }
}
