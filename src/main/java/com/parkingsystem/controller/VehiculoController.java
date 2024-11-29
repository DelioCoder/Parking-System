package com.parkingsystem.controller;

import com.parkingsystem.DAO.ConductorDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Vehiculo;
import com.parkingsystem.view.conductor.ModalConductor;
import com.parkingsystem.view.conductor.TablaC;
import com.parkingsystem.view.vehiculo.ModalVehiculo;
import com.parkingsystem.view.vehiculo.TablaVehiculo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author David
 */
public class VehiculoController {
    
    private VehiculoDAO vehiculoDao = new VehiculoDAO();
    private TablaVehiculo tablaVehiculo;
    private ModalVehiculo modalVehiculo;
    
    
    public VehiculoController(TablaVehiculo tablaVehiculo) {
        this.tablaVehiculo = tablaVehiculo;
    }
    
    public VehiculoController(ModalVehiculo modalVehiculo) {
        this.modalVehiculo = modalVehiculo;
    };

    public TablaVehiculo getTablaVehiculo() {
        return tablaVehiculo;
    }

    public void setTablaVehiculo(TablaVehiculo tablaVehiculo) {
        this.tablaVehiculo = tablaVehiculo;
    }


    public void actionPerformed(ActionEvent e) {
        rellenarTabla("");
    }

    public void rellenarTabla(String value) {
        List<Vehiculo> vehiculos = vehiculoDao.listarVehiculos(value);

        if (vehiculos == null) {
            System.out.println("No se han podido recuperar vehiculos.");
            return;
        }

        
        String[] columnas = {"Placa", "Color", "Marca", "Año", "Nombre de conductor", "DNI de conductor", "Acciones"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6; // Solo la columna de "Acciones" será editable
            }
        };

        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("xd: "+vehiculo.getNombre_cond());
            Object[] fila = {
                vehiculo.getPlaca_veh(),
                vehiculo.getColor_veh(),
                vehiculo.getMarca_veh(),
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
                if (column == 6) {
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
        this.tablaVehiculo.jTableVehiculo.getColumnModel().getColumn(6).setCellRenderer(new AccionesRenderer());
        this.tablaVehiculo.jTableVehiculo.getColumnModel().getColumn(6).setCellEditor(new AccionesEditor());

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
    
    public void editarConductor(Vehiculo vehiculo) {
        vehiculoDao.actualizarVehiculo(vehiculo);
        modalVehiculo.dispose();
        reloadTabla();
    }
    
    public void agregarConductor(Vehiculo vehiculo) {
        vehiculoDao.agregarVehiculo(vehiculo);
        modalVehiculo.dispose();
        reloadTabla();
    }
    
    public void reloadTabla() {
        String conductorText = this.tablaVehiculo.txtNombreVehiculo.getText();
        rellenarTabla(conductorText);
    }
    
    private void abrirModalEditarVehiculo(Vehiculo vehiculo) {
        ModalVehiculo form = new ModalVehiculo(tablaVehiculo, vehiculo, false);
        form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Evita que la ventana principal se cierre
        form.setVisible(true);
        form.toFront();  // Asegura que el formulario aparezca en el frente
    };
    

    private void abrirModalEliminarVehiculo(Vehiculo vehiculo) {
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Estás seguro de que deseas eliminar a " + vehiculo.getPlaca_veh()+ "?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            vehiculoDao.cambiarEstadoVehiculo(vehiculo.getId_conductor(), false);
            JOptionPane.showMessageDialog(null, "Conductor eliminado exitosamente.");
            
            String conductorText = tablaVehiculo.txtNombreVehiculo.getText();

            rellenarTabla(conductorText);
        }
    }

    private class AccionesEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private Vehiculo currentVehiculo;

        public AccionesEditor() {
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panel.setBackground(Color.WHITE); // Fondo blanco fijo
            panel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));


            JLabel editar = new JLabel("<html><a style='color: blue;' href='#'>Editar</a></html>");
            JLabel eliminar = new JLabel("<html><a style='color: red;' href='#'>Eliminar</a></html>");

            // Configurar cursor como pointer
            editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            eliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            // Acciones con un solo clic
            editar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentVehiculo != null) {
                        stopCellEditing(); // Finaliza la edición antes de disparar la acción
                        abrirModalEditarVehiculo(currentVehiculo);
                    }
                }
            });

            eliminar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentVehiculo != null) {
                        stopCellEditing(); // Finaliza la edición antes de disparar la acción
                        abrirModalEliminarVehiculo(currentVehiculo);
                    }
                }
            });

            panel.add(editar);
            panel.add(new JLabel(" | "));
            panel.add(eliminar);
        }

        @Override
        public Object getCellEditorValue() {
            return currentVehiculo; // Devolver el conductor actual
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Vehiculo) {
                currentVehiculo = (Vehiculo) value;
            };

            // Asegurar que el editor tenga el mismo estilo que el renderizador
            panel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));

            return panel;
        }
    }
    // Renderizador de acciones
    private static class AccionesRenderer extends JPanel implements TableCellRenderer {
        public AccionesRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER));
            setBackground(Color.WHITE); // Fondo blanco fijo para la columna "Acciones"
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            removeAll(); // Limpiar componentes previos

            if (value instanceof Vehiculo) {
                Vehiculo vehiculo = (Vehiculo) value;

                JLabel editar = new JLabel("<html><a style='color: blue;' href='#'>Editar</a></html>");
                JLabel eliminar = new JLabel("<html><a style='color: red;' href='#'>Eliminar</a></html>");

                // Configurar cursor como pointer
                editar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                eliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                add(editar);
                add(new JLabel(" | "));
                add(eliminar);
            }

            // Fondo blanco fijo, incluso al seleccionar
            setBackground(Color.WHITE);

            // Aplicar borde inferior (similar a otras filas)
            setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 1));

            return this;
        }
    }
    
}
