/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Clases.MyConnection;
import java.awt.Color;
import Clases.SesionUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        usertxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        passtxt = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        BotonIngresar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\Recurso-1.png")); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 190, 160));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("INICIAR SESIÓN");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\a1 (2).png")); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\porque_procemec.jpg")); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 420, 420));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("INICIAR SESIÓN");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Usuario");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 135, -1, 40));

        usertxt.setForeground(new java.awt.Color(153, 153, 153));
        usertxt.setText("Ingrese su nombre de usuario");
        usertxt.setBorder(null);
        usertxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                usertxtMousePressed(evt);
            }
        });
        usertxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usertxtActionPerformed(evt);
            }
        });
        jPanel2.add(usertxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 176, 490, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 210, 490, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Contraseña");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 240, -1, -1));

        passtxt.setForeground(new java.awt.Color(153, 153, 153));
        passtxt.setBorder(null);
        passtxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passtxtMousePressed(evt);
            }
        });
        passtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passtxtActionPerformed(evt);
            }
        });
        jPanel2.add(passtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 490, 30));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 490, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 30, 40));

        BotonIngresar.setBackground(new java.awt.Color(51, 153, 255));
        BotonIngresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BotonIngresar.setForeground(new java.awt.Color(255, 255, 255));
        BotonIngresar.setText("ENTRAR");
        BotonIngresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotonIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BotonIngresarMouseExited(evt);
            }
        });
        BotonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIngresarActionPerformed(evt);
            }
        });
        jPanel2.add(BotonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, 120, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\user.png")); // NOI18N
        jLabel8.setText(" BIENVENIDO ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 979, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usertxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usertxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usertxtActionPerformed

    private void passtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passtxtActionPerformed

    private void BotonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonIngresarActionPerformed
     String nombreUsuarioIngresado = usertxt.getText().trim();
     String contraseniaIngresada = new String(passtxt.getPassword()); // getPassword() devuelve un char[], es mejor convertirlo a String inmediatamente para su uso
     
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = MyConnection.getConnection(); // Asegúrate de tener la clase MyConnection
            String sql = "SELECT usuario_id, contrasena, permisos FROM Usuarios WHERE nombre_usuario = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreUsuarioIngresado);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int usuarioId = rs.getInt("usuario_id");
                String contrasenaAlmacenada = rs.getString("contrasena");
                String permisosStr = rs.getString("permisos");

                if (SesionUsuario.verificarContrasena(contraseniaIngresada, contrasenaAlmacenada)) {
                    // Autenticación exitosa
                    SesionUsuario.iniciarSesion(usuarioId, nombreUsuarioIngresado, permisosStr);

                    // Abrir la ventana principal (reemplaza PanelPrincipalJava con el nombre real)
                    Ventanas.PanelPrincipal panelPrincipal = new Ventanas.PanelPrincipal();
                    panelPrincipal.setVisible(true);
                    this.dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no encontrado.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar o consultar la base de datos: " + ex.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al cerrar la conexión: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BotonIngresarActionPerformed

    private void BotonIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonIngresarMouseEntered
        BotonIngresar.setBackground(new Color(0, 156, 223));
    }//GEN-LAST:event_BotonIngresarMouseEntered

    private void BotonIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonIngresarMouseExited
        BotonIngresar.setBackground(new Color(0, 134, 190));
    }//GEN-LAST:event_BotonIngresarMouseExited

    private void usertxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usertxtMousePressed
        if (usertxt.getText().equals("Ingrese su nombre de usuario"))  {  
            usertxt.setText("");
            usertxt.setForeground(Color.black);
        }
        if (String.valueOf(passtxt.getPassword()).isEmpty()) {
            passtxt.setText("");
            passtxt.setForeground(Color.gray);
         }
    }//GEN-LAST:event_usertxtMousePressed

    private void passtxtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passtxtMousePressed
        if (String.valueOf(passtxt.getPassword()).equals("")) {
        passtxt.setText("");
        passtxt.setForeground(Color.black);
        }
        if (usertxt.getText().isEmpty()) {
        usertxt.setText("Ingrese su nombre de usuario");
        usertxt.setForeground (Color.gray);
        }
    }//GEN-LAST:event_passtxtMousePressed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JTextField usertxt;
    // End of variables declaration//GEN-END:variables
}
