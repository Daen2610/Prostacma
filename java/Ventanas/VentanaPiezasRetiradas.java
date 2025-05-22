/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;
import Clases.MyConnection;
import Clases.SeguimientoTemporal;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

public class VentanaPiezasRetiradas extends javax.swing.JFrame {

    public VentanaPiezasRetiradas() {
        initComponents();
        inicializarModeloTabla();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRetirados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(220, 220, 220));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableRetirados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableRetirados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 780, 410));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Almacen de piezas retiradas temporalmente");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Reincorporar a Producción");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 490, 240, 50));

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Regresar");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 510, 130, 50));

        jButtonBuscar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 490, 130, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    int filaSeleccionada = jTableRetirados.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this,
            "Seleccione un folio de la tabla",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE);
        return;
    }

    String folio = (String) jTableRetirados.getValueAt(filaSeleccionada, 1);
    
    int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Reincorporar el folio " + folio + " a producción?",
        "Confirmar",
        JOptionPane.YES_NO_OPTION);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        // Llamada al método modificado (pasando la tabla como parámetro)
        if (SeguimientoTemporal.reincorporarPieza(folio, jTableRetirados)) {
            JOptionPane.showMessageDialog(this,
                "Rollo reincorporado exitosamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed
private void inicializarModeloTabla() {
    String[] columnNames = {
        "Folio", 
        "Número de Parte", 
        "Paso Actual", 
        "Piezas Realizadas", 
        "Piezas Restantes", 
        "Fecha de Retiro"
    };
  
    DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 3: // Piezas Realizadas
                case 4: // Piezas Restantes
                    return Integer.class;
                case 5: // Fecha de Retiro
                    return Timestamp.class;
                default:
                    return String.class;
            }
        }
    };
    
    jTableRetirados.setModel(model);
}
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    jButtonBuscar.setEnabled(false);
    
    try {
        DefaultTableModel model = (DefaultTableModel) jTableRetirados.getModel();
        model.setRowCount(0);
        
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT folio, parte, paso_actual, piezas_realizadas, " +
                 "piezas_restantes, fecha_retiro FROM Rollos_Retirados " +
                 "ORDER BY fecha_retiro DESC");
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("folio"),
                    rs.getString("parte"),
                    rs.getString("paso_actual"),
                    rs.getInt("piezas_realizadas"),
                    rs.getInt("piezas_restantes"),
                    rs.getTimestamp("fecha_retiro")
                });
            }
            
            // Depuración corregida
            if (model.getRowCount() > 0) {
                Vector firstRow = (Vector)model.getDataVector().elementAt(0);
                System.out.println("Primer registro: " + firstRow);
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
            "Error al consultar: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    } finally {
        setCursor(Cursor.getDefaultCursor());
        jButtonBuscar.setEnabled(true);
        jTableRetirados.revalidate();
        jTableRetirados.repaint();
    }
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ConsultaPiezas menu = new ConsultaPiezas();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {
      
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPiezasRetiradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPiezasRetiradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPiezasRetiradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPiezasRetiradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPiezasRetiradas().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRetirados;
    // End of variables declaration//GEN-END:variables
}
