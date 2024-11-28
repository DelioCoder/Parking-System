package com.parkingsystem.controller;
import com.parkingsystem.DAO.ConductorDAO;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.view.conductor.ModalConductor;
import com.parkingsystem.view.conductor.TablaC;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;


public class ConductorController implements ActionListener {

    private ConductorDAO conductorDao = new ConductorDAO();
    private TablaC tablaConductor;

    public TablaC getTablaConductor() {
        return tablaConductor;
    }

    public void setTablaConductor(TablaC tablaConductor) {
        this.tablaConductor = tablaConductor;
    }
    private ModalConductor modalConductor;

    public ConductorController(TablaC tablaConductor) {
        this.tablaConductor = tablaConductor;
    }
    
    public ConductorController(ModalConductor modalConductor) {
        this.modalConductor = modalConductor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<String> keyList = new ArrayList<>();
        rellenarTabla("", keyList);
    }

    public void rellenarTabla(String filter, ArrayList<String> keyList) {
        List<Conductor> conductores = conductorDao.listarConductores(filter, keyList);

        if (conductores == null) {
            System.out.println("No se han podido recuperar conductores.");
            return;
        }

        String[] columnas = {"Nombre", "Apellidos", "DNI", "Telefono", "Acciones"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4; // Solo la columna de "Acciones" será editable
            }
        };

        for (Conductor conductor : conductores) {
            Object[] fila = {
                conductor.getNombre_cond(),
                conductor.getApellido_cond(),
                conductor.getDni_cond(),
                conductor.getTelefono_cond(),
                conductor // Pasamos el objeto Conductor como referencia en la última columna
            };
            modeloTabla.addRow(fila);
        }

        // Asignar el modelo a la tabla
        this.tablaConductor.jTableConductor.setModel(modeloTabla);
        
        // Habilitar edición con un solo clic en la columna de acciones
        this.tablaConductor.jTableConductor.addMouseListener(new MouseAdapter() {
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
        for (int i = 0; i < this.tablaConductor.jTableConductor.getColumnCount(); i++) {
            this.tablaConductor.jTableConductor.getColumnModel().getColumn(i).setCellRenderer(renderizadorConBordeInferior);
        }

        // Renderizador y editor para la columna de acciones
        this.tablaConductor.jTableConductor.getColumnModel().getColumn(4).setCellRenderer(new AccionesRenderer());
        this.tablaConductor.jTableConductor.getColumnModel().getColumn(4).setCellEditor(new AccionesEditor());

        // Configuración de la tabla
        this.tablaConductor.jTableConductor.setRowHeight(30); // Altura de fila
        this.tablaConductor.jTableConductor.setShowGrid(false); // Desactivar cuadrícula general
        this.tablaConductor.jTableConductor.setBackground(Color.WHITE); // Fondo de tabla blanco
        this.tablaConductor.jTableConductor.setSelectionBackground(Color.WHITE); // Fondo blanco para selección
        this.tablaConductor.jTableConductor.setSelectionForeground(Color.BLACK); // Texto negro para selección

        // Habilitar edición con un solo clic en la columna de acciones
        this.tablaConductor.jTableConductor.addMouseListener(new MouseAdapter() {
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
    
    public void editarConductor(Conductor conductor) {
        conductorDao.actualizarConductor(conductor);
        modalConductor.dispose();
        reloadTabla();
    }
    
    public void agregarConductor(Conductor conductor) {
        conductorDao.agregarConductor(conductor);
        modalConductor.dispose();
        reloadTabla();
    }
    
    public void reloadTabla() {
        String conductorText = this.tablaConductor.txtNombreConductor.getText();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.add(0, conductorText);
        rellenarTabla("nombre", keyList);
    }
    
    private void abrirModalEditarConductor(Conductor conductor) {
        try {
            ModalConductor form = new ModalConductor(tablaConductor, conductor, false);
            form.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Evita que la ventana principal se cierre
            Thread.sleep(300); // 1000 milisegundos = 1 segundo
            form.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace(); // Maneja la excepción si el hilo es interrumpido
        }
    };
    

    private void abrirModalEliminarConductor(Conductor conductor) {
        int respuesta = JOptionPane.showConfirmDialog(null, 
            "¿Estás seguro de que deseas eliminar a " + conductor.getNombre_cond() + "?",
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            // Lógica de eliminación (ej. llamar a conductorDao para borrar)
            //conductorDao.eliminarConductor(conductor);
            JOptionPane.showMessageDialog(null, "Conductor eliminado exitosamente.");
            // Opcional: actualizar la tabla para reflejar los cambios
            ArrayList<String> keyList = new ArrayList<>();
            rellenarTabla("", keyList);
        }
    }

    private class AccionesEditor extends AbstractCellEditor implements TableCellEditor {
        private final JPanel panel;
        private Conductor currentConductor;

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
                    if (currentConductor != null) {
                        stopCellEditing(); // Finaliza la edición antes de disparar la acción
                        abrirModalEditarConductor(currentConductor);
                    }
                }
            });

            eliminar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (currentConductor != null) {
                        stopCellEditing(); // Finaliza la edición antes de disparar la acción
                        abrirModalEliminarConductor(currentConductor);
                    }
                }
            });

            panel.add(editar);
            panel.add(new JLabel(" | "));
            panel.add(eliminar);
        }

        @Override
        public Object getCellEditorValue() {
            return currentConductor; // Devolver el conductor actual
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Conductor) {
                currentConductor = (Conductor) value;
            }

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

            if (value instanceof Conductor) {
                Conductor conductor = (Conductor) value;

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