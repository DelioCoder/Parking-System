/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.parkingsystem.view.conductor;

import com.parkingsystem.controller.ConductorController;
import com.parkingsystem.model.Conductor;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 *
 * @author John
 */
public class ModalConductor extends javax.swing.JFrame {

    private Conductor conductor;
    private Boolean nuevoConductor;
    private ConductorController conductorController;


    private TablaC tablaConductor;


    /**
     * Creates new form EditarC
     */
    public ModalConductor(TablaC tablaConductor, Conductor conductor, Boolean nuevoConductor) {
        this.conductor = conductor;
        this.nuevoConductor = nuevoConductor;
        this.tablaConductor = tablaConductor;
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Esto cierra solo esta ventana
        
        initComponents();
        inicializarControlador();
        this.inicializarVista();

    }
   
    private void inicializarVista() {
        if (this.nuevoConductor == true) {
            txtTitulo.setText("Registrar Conductor");
            btnAction.setText("Registrar");
        } else {
            txtTitulo.setText("Editar conductor");
            btnAction.setText("Editar");
        }
        estilizarBotones();
        // Asignar los valores del conductor a los campos de texto
        txtNombre.setText(conductor.getNombre_cond());
        txtApellido.setText(conductor.getApellido_cond());
        int dni = conductor.getDni_cond();
        int telefono = conductor.getTelefono_cond();
        txtDNI.setText(conductor.getDni_cond()+"") ;
        txtTelefono.setText(conductor.getTelefono_cond()+"");
        if (dni == 0) {
            txtDNI.setText("") ;
        }
        if (telefono == 0) {
            txtTelefono.setText("");
        }
    }
    

    private void inicializarControlador() {
        conductorController = new ConductorController(this);
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

        txtTelefono = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnAction = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 263, 347, -1));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 69, 59, -1));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel3.setText("Apellidos");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 123, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel4.setText("DNI");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 183, 37, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 10)); // NOI18N
        jLabel5.setText("Telefono");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 243, -1, -1));

        txtTitulo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtTitulo.setText("Conductor");
        getContentPane().add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 34, -1, -1));

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 89, 347, -1));

        txtApellido.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtApellidoKeyReleased(evt);
            }
        });
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 143, 347, -1));

        txtDNI.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDNIKeyReleased(evt);
            }
        });
        getContentPane().add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 203, 347, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnAction.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        btnAction.setText("Editar");
        btnAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActionMouseClicked(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAction, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelMouseClicked
        cerrarVentana();
    }//GEN-LAST:event_btnCancelMouseClicked

    private void btnActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActionMouseClicked
        conductorController.setTablaConductor(tablaConductor);
        if (this.nuevoConductor == true) {
            conductorController.agregarConductor(conductor);
        } else {
            conductorController.editarConductor(conductor);

        }
        
    }//GEN-LAST:event_btnActionMouseClicked

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        String text = getInputText(txtNombre);
        conductor.setNombre_cond(text);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyReleased
        String text = getInputText(txtApellido);
        conductor.setApellido_cond(text);
    }//GEN-LAST:event_txtApellidoKeyReleased

    private void txtDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyReleased
        String text = getInputText(txtDNI);
        conductor.setDni_cond(Integer.parseInt(text));
    }//GEN-LAST:event_txtDNIKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        String text = getInputText(txtTelefono);
        conductor.setTelefono_cond(Integer.parseInt(text));
    }//GEN-LAST:event_txtTelefonoKeyReleased

    
    public String getInputText(JTextField field) {
        return field.getText();
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablaC tablaConductor = new TablaC();
                ModalConductor frame = new ModalConductor(tablaConductor, new Conductor(), true); // Asegúrate de pasar un objeto Conductor válido
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAction;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    public javax.swing.JLabel txtTitulo;
    // End of variables declaration//GEN-END:variables
}
