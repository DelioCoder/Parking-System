package com.parkingsystem.controller;

import com.parkingsystem.DAO.Boleta_PagoDAO;
import com.parkingsystem.model.Boleta_Pago;
import com.parkingsystem.view.reportes.REstacionamiento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class ClienteController implements ActionListener
{
    private Boleta_PagoDAO boletaDao = new Boleta_PagoDAO(); ;
    private REstacionamiento vista;

    public ClienteController(REstacionamiento vista) {
        this.vista = vista;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        rellenarTabla();
    }
    
    public void rellenarTabla() {
        List<Boleta_Pago> boletas = boletaDao.listarBoletas();
        
        if (boletas == null) {
            System.out.println("No se han podido recuperar boletas.");
            return;
        }
        
        String[] columnas = {"conductor", "DNI", "telefono", "Color de Vehiculo", "Monto de Pago", "Hora de Entrada", "Hora de Salida", "Zona", "Piso"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);

        for (Boleta_Pago boleta : boletas) {
            Object[] fila = {
                boleta.getConductor(),
                boleta.getDni(),
                boleta.getTelefono(),
                boleta.getColorVehiculo(),
                boleta.getMonto_pago(),
                boleta.getHoraEntrada(),
                boleta.getHora_salida(),
                boleta.getZona(),
                boleta.getPiso(),
            };
            modeloTabla.addRow(fila);
        }

        this.vista.txtTablaReporte.setModel(modeloTabla);
    }
    
    public ArrayList<Boleta_Pago> listarBoletas(String filter)
    {
        return this.boletaDao.listarBoletas();
    }
    
    public boolean registrarBoleta(Boleta_Pago boleta)
    {
        return this.boletaDao.agregarBoleta(boleta);
    }
    
    public boolean actualizarBoleta(Boleta_Pago boleta)
    {
        return this.boletaDao.actualizarBoleta(boleta);
    }
    
    public boolean eliminarBoleta(int id)
    {
        return this.boletaDao.eliminarBoleta(id);
    }
}
