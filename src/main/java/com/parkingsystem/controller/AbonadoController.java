package com.parkingsystem.controller;

import com.parkingsystem.DAO.AbonadoDAO;
import com.parkingsystem.model.Abonado;
import com.parkingsystem.view.abonado.RegistrarAbonado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class AbonadoController implements ActionListener {
    private AbonadoDAO abonadoDao;
    private RegistrarAbonado vista;
    private Abonado abonado;
    
    public AbonadoController(RegistrarAbonado v) {
        this.abonadoDao = new AbonadoDAO();
        this.vista = v;
        this.abonado = new Abonado();
        
        this.vista.btnGrabarAbono.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGrabarAbono) {
            agregarAbonado(abonado);
        }
    }
    
    public ArrayList<Abonado> listarAbonados(String filter, ArrayList<String> data)
    {
        return this.abonadoDao.listarAbonados(filter, data);
    }
    
    public void agregarAbonado(Abonado abonado)
    {
        try {
            abonado.setFecha_inicio_abo(vista.txtFechaInicio.getText());
            abonado.setFecha_fin_abo(vista.txtFechaFin.getText());
            abonado.setMonto_abo(Float.parseFloat(vista.txtMonto.getText()));
            abonado.setTipo_abo(vista.TipoBX.getSelectedItem().toString());
            
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
