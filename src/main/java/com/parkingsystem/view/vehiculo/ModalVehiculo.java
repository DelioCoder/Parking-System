/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.parkingsystem.view.vehiculo;
import com.parkingsystem.DAO.ConductorDAO;
import com.parkingsystem.DAO.VehiculoDAO;
import com.parkingsystem.controller.VehiculoController;
import com.parkingsystem.input.ComboBoxItem;
import com.parkingsystem.input.ComboBoxItemVeh;
import com.parkingsystem.model.Conductor;
import com.parkingsystem.model.Vehiculo;
import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author John
 */
public class ModalVehiculo extends javax.swing.JFrame {

    private Vehiculo vehiculo;
    private Boolean nuevoVehiculo;
    private VehiculoController vehiculoController;
    private TablaVehiculo tablaVehiculo;
    private ConductorDAO conductorDAO;
    private ArrayList<Conductor> conductoresLista = new ArrayList<Conductor>();


    /**
     * Creates new form EditarC
     */
    public ModalVehiculo(TablaVehiculo tablaVehiculo, Vehiculo vehiculo, Boolean nuevoVehiculo) {
        this.vehiculo = vehiculo;
        this.nuevoVehiculo = nuevoVehiculo;
        this.tablaVehiculo = tablaVehiculo;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Esto cierra solo esta ventana
        
        initComponents();
        inicializarControlador();
        this.cargarConductores();

        this.inicializarVista();
    }
   
    private void inicializarVista() {
        if (this.nuevoVehiculo == true) {
            txtTitulo.setText("Registrar Conductor");
            btnAction.setText("Registrar");
        } else {
            txtTitulo.setText("Editar conductor");
            btnAction.setText("Editar");
            // Asignar los valores del conductor a los campos de texto
            txtPlaca.setText(vehiculo.getPlaca_veh());
            txtColor.setText(vehiculo.getColor_veh());
            txtMarca.setText(vehiculo.getMarca_veh());
            txtModelo.setText(vehiculo.getModelo_veh());
            txtAño.setText(vehiculo.getAño_veh());
            
            int idConductor = vehiculo.getId_conductor();
            int index = -1;
            // Iterar sobre la lista y comparar el atributo id
            for (int i = 0; i < conductoresLista.size(); i++) {
                if (conductoresLista.get(i).getId_conductor()== idConductor) {
                    index = i;
                    break; // Salir del bucle cuando encontramos la coincidencia
                }
            }

            comboConductor.setSelectedIndex(index);
            
        }
        estilizarBotones();

    }
    
    private void cargarConductores() {
        ArrayList<String> keyList = new ArrayList<>();
        keyList.add(0, "");
        this.conductorDAO = new ConductorDAO();

        conductoresLista = conductorDAO.listarConductores("", keyList);
        for(Conductor conductor : conductoresLista)
        {
            String name = "DNI: "+conductor.getDni_cond()+" - "+conductor.getNombre_cond()+" "+conductor.getApellido_cond();
            this.comboConductor.addItem(new ComboBoxItem(conductor.getId_conductor(), name));
        }
        
        if (this.nuevoVehiculo == true) {
            comboConductor.setSelectedIndex(-1);
        }
        
    }
    

    private void inicializarControlador() {
        vehiculoController = new VehiculoController(this);
    }
    
    public void estilizarBotones() {
        estilizarBoton(btnAction);
        estilizarBoton(btnCancel);
    }
    
    public void estilizarBoton(JButton boton) {
        boton.setBackground(Color.WHITE); // Fondo verde
        boton.setForeground(Color.BLACK); // Texto negro (si hay texto)
        boton.setFocusPainted(false); // Elimina el borde de foco predeterminado
        boton.setRolloverEnabled(false); // No cambia el color al pasar el mouse
        boton.setBorderPainted(false); // Elimina el borde del botón
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Opcionalmente, si la imagen tiene un fondo transparente y quieres que se vea bien:
        boton.setOpaque(false);
    }
    
    private void cerrarVentana() {
        // Cierra la ventana modal
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        txtAño = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboConductor = new javax.swing.JComboBox<ComboBoxItem>();
        btnAction = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setText("Placa");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 69, 59, -1));

        txtAño.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtAño.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAñoKeyReleased(evt);
            }
        });
        getContentPane().add(txtAño, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 347, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel3.setText("Color");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 123, -1, -1));

        txtTitulo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtTitulo.setText("Vehículo");
        getContentPane().add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 34, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel4.setText("Marca");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 183, 37, -1));

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });
        getContentPane().add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 89, 347, -1));

        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMarcaKeyReleased(evt);
            }
        });
        getContentPane().add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 203, 347, -1));

        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtColorActionPerformed(evt);
            }
        });
        txtColor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColorKeyReleased(evt);
            }
        });
        getContentPane().add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 143, 347, -1));

        txtTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefono1KeyReleased(evt);
            }
        });
        getContentPane().add(txtTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 347, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel6.setText("Año");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel7.setText("Conductor");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        comboConductor.setModel(new javax.swing.DefaultComboBoxModel<>());
        comboConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboConductorActionPerformed(evt);
            }
        });
        getContentPane().add(comboConductor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 350, -1));

        btnAction.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAction.setText("Editar");
        btnAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActionMouseClicked(evt);
            }
        });
        getContentPane().add(btnAction, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 130, 30));

        btnCancel.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnCancel.setText("Cancelar");
        btnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelMouseClicked(evt);
            }
        });
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 450, 120, 30));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel5.setText("Modelo");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 90, -1));

        txtModelo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtModeloKeyReleased(evt);
            }
        });
        getContentPane().add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 347, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void txtColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtColorActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        String text = getInputText(txtPlaca);
        vehiculo.setPlaca_veh(text);
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtColorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColorKeyReleased
        String text = getInputText(txtColor);
        vehiculo.setColor_veh(text);
    }//GEN-LAST:event_txtColorKeyReleased

    private void txtMarcaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyReleased
        String text = getInputText(txtMarca);
        vehiculo.setMarca_veh((text));
    }//GEN-LAST:event_txtMarcaKeyReleased

    private void txtAñoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAñoKeyReleased
        String text = getInputText(txtAño);
        vehiculo.setAño_veh((text));
    }//GEN-LAST:event_txtAñoKeyReleased

    private void txtTelefono1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefono1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono1KeyReleased

    private void comboConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboConductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboConductorActionPerformed

    private void btnActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseClicked
        ComboBoxItem cbxConductor = (ComboBoxItem) comboConductor.getSelectedItem();

        if (cbxConductor == null ||
            getInputText(txtPlaca).trim().isEmpty() ||
            getInputText(txtColor).trim().isEmpty() ||
            getInputText(txtMarca).trim().isEmpty() ||
            getInputText(txtModelo).trim().isEmpty() ||
            getInputText(txtAño).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int id = cbxConductor.getId();
            vehiculo.setId_conductor(id);

            vehiculoController.setTablaVehiculo(tablaVehiculo);
            if (this.nuevoVehiculo == true) {
                vehiculoController.agregarConductor(vehiculo);
            } else {
                vehiculoController.editarConductor(vehiculo);
            }

        }

    }//GEN-LAST:event_btnActionMouseClicked

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        cerrarVentana();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtModeloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyReleased
        String text = getInputText(txtModelo);
        vehiculo.setModelo_veh((text));
    }//GEN-LAST:event_txtModeloKeyReleased

    
    public String getInputText(JTextField field) {
        return field.getText();
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablaVehiculo tablaVehiculo = new TablaVehiculo();
                ModalVehiculo frame = new ModalVehiculo(tablaVehiculo, new Vehiculo(), true); // Asegúrate de pasar un objeto Conductor válido
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    private javax.swing.JButton btnCancel;
    public javax.swing.JComboBox<ComboBoxItem> comboConductor;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTelefono1;
    public javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
