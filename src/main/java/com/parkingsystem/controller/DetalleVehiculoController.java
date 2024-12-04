package com.parkingsystem.controller;


import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.DAO.DetalleVehiculoDAO;

import com.parkingsystem.view.vehiculo.DetalleVehiculoVista;

import java.awt.Color;
import java.awt.Component;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author John
 */
public class DetalleVehiculoController {
    
    private DetalleVehiculoDAO detalleVehiculoDAO = new DetalleVehiculoDAO();
    private DetalleVehiculoVista detalleVehiculoVista;

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
    

    public void rellenarTabla(String value) {
        List<Vehiculo> vehiculos = vehiculoDao.listarVehiculos(value);

        if (vehiculos == null) {
            System.out.println("No se han podido recuperar vehiculos.");
            return;
        }

        String[] columnas = {"Placa", "Color", "Marca", "Modelo", "Año", "Nombre de conductor", "DNI de conductor", "Acciones"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Solo la columna de "Acciones" será editable
            }
        };

        for (Vehiculo vehiculo : vehiculos) {

            Object[] fila = {
                vehiculo.getPlaca_veh(),
                vehiculo.getColor_veh(),
                vehiculo.getMarca_veh(),
                vehiculo.getModelo_veh(),
                vehiculo.getAño_veh(),
                vehiculo.getNombre_cond()+" "+vehiculo.getApellido_cond(),
                vehiculo.getDni_cond(),
                vehiculo // Pasamos el objeto Conductor como referencia en la última columna
            };
            modeloTabla.addRow(fila);
        }

        // Asignar el modelo a la tabla
        this.tablaVehiculo.jTableVehiculo.setModel(modeloTabla);
        
        // Habilitar edición con un solo clic en la columna de acciones
        this.tablaVehiculo.jTableVehiculo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);

                // Verificar si se hizo clic en la columna de acciones
                if (column == 7) {
                    table.editCellAt(row, column);
                    Component editor = table.getEditorComponent();
                    if (editor != null) {
                        editor.requestFocus();
                    }
                }
            }
        });

        // Renderizador para fondo blanco y borde inferior por fila
        DefaultTableCellRenderer renderizadorConBordeInferior = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Fondo blanco fijo para todas las celdas
                componente.setBackground(Color.WHITE);

                // Bordes: solo línea inferior negra (border-bottom)
                setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));
                return componente;
            }
        };

        // Aplicar el renderizador a todas las columnas (incluida "Acciones")
        for (int i = 0; i < this.tablaVehiculo.jTableVehiculo.getColumnCount(); i++) {
            this.tablaVehiculo.jTableVehiculo.getColumnModel().getColumn(i).setCellRenderer(renderizadorConBordeInferior);
        }

        // Renderizador y editor para la columna de acciones
        this.tablaVehiculo.jTableVehiculo.getColumnModel().getColumn(7).setCellRenderer(new AccionesRenderer());
        this.tablaVehiculo.jTableVehiculo.getColumnModel().getColumn(7).setCellEditor(new AccionesEditor());

        // Configuración de la tabla
        this.tablaVehiculo.jTableVehiculo.setRowHeight(30); // Altura de fila
        this.tablaVehiculo.jTableVehiculo.setShowGrid(false); // Desactivar cuadrícula general
        this.tablaVehiculo.jTableVehiculo.setBackground(Color.WHITE); // Fondo de tabla blanco
        this.tablaVehiculo.jTableVehiculo.setSelectionBackground(Color.WHITE); // Fondo blanco para selección
        this.tablaVehiculo.jTableVehiculo.setSelectionForeground(Color.BLACK); // Texto negro para selección

        // Habilitar edición con un solo clic en la columna de acciones
        this.tablaVehiculo.jTableVehiculo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);

                // Verificar si se hizo clic en la columna de acciones
                if (column == 4) {
                    table.editCellAt(row, column);
                    Component editor = table.getEditorComponent();
                    if (editor != null) {
                        editor.requestFocus();
                    }
                }
            }
        });
    }
    

}
