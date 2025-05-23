/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Ventanas;

import Clases.ConsultarDatos;
import Clases.DisenoTabla;
import Clases.GenerarPDF;
import Clases.GenerarPDFConCodigoBarras;
import Clases.Ingresar;
import Clases.SeguimientoPiezas;
import java.awt.HeadlessException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class IngresarMP extends javax.swing.JFrame {

    public IngresarMP() {
        initComponents();
        DisenoTabla.aplicarDisenoTabla(jTable1);
        DisenoTabla.aplicarDisenoTabla(jTableRetiros);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNumeroParte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFolios = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        fecharetiro = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        spinnerPesoBobina = new javax.swing.JSpinner();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableRetiros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(77, 130, 188));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Almacen MP");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Numero de Parte:");

        txtNumeroParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroParteActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Peso de Rollo:");

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Salir");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha de ingreso:");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\consultant.png")); // NOI18N
        jButton3.setText(" Consultar");
        jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cantidad a Agregar o Retirar");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\ballot.png")); // NOI18N
        jButton4.setText(" Generar reporte");
        jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\priority-arrows.png")); // NOI18N
        jButton5.setText("Enviar a produccion");
        jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("-------------------------------------------------------");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Folio a retirar:");

        txtFolios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFoliosActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("---------------------------------------------------------");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Fecha de retirada:");

        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 1000.0, 0.1);
        spinnerPesoBobina.setModel(model);

        jButton6.setBackground(new java.awt.Color(153, 255, 153));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon("C:\\Users\\razie\\Downloads\\add.png")); // NOI18N
        jButton6.setText(" Agregar");
        jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(61, 61, 61))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(spinnerPesoBobina, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                                .addComponent(txtNumeroParte, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(txtFolios)
                                .addComponent(fecharetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumeroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spinnerPesoBobina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFolios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecharetiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Consulta de Piezas");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Consulta de Piezas retiradas");

        jTableRetiros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTableRetiros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(282, 282, 282)
                        .addComponent(jLabel7)
                        .addGap(0, 290, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(244, 244, 244))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        PanelPrincipal menu = new PanelPrincipal();
        menu.setVisible(true);
        this.dispose(); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
 // Obtener el número de parte ingresado
    String numeroParte = txtNumeroParte.getText().trim();

    // Validar que el número de parte sea válido (puede contener letras, números, espacios o guiones)
    if (!numeroParte.isEmpty() && !numeroParte.matches("[a-zA-Z0-9\\s\\-]+")) {
        JOptionPane.showMessageDialog(this, "Ingrese un número de parte válido (solo letras, números, espacios o guiones).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Si el número de parte no está vacío, verificar si existe en la tabla Almacen_Inv
    if (!numeroParte.isEmpty() && !Ingresar.existePieza(numeroParte)) {
        JOptionPane.showMessageDialog(this, "El número de parte no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Si todo está bien, cargar las tablas
    cargarTablaAlmacen(numeroParte); // Cargar la tabla de Almacen_Inv
    cargarTablaRetiros(numeroParte); // Cargar la tabla de Retiros
    }//GEN-LAST:event_jButton3ActionPerformed

private void cargarTablaAlmacen(String numeroParte) {
    List<String[]> almacen;

    // Si el número de parte está vacío, consultar todos los registros de Almacen_Inv
    if (numeroParte.isEmpty()) {
        almacen = ConsultarDatos.obtenerAlmacen();
    } else {
        // Si no, consultar solo los registros asociados al número de parte (búsqueda parcial)
        almacen = ConsultarDatos.consultarPorNumeroParteAlmacen(numeroParte);
    }

    // Crear las columnas para la tabla
    String[] columnNames = {"Numero_Parte", "Fecha", "Folio", "Peso_Bobina", "Total de Piezas"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    // Agregar los datos al modelo de la tabla
    if (almacen != null && !almacen.isEmpty()) {
        for (String[] registro : almacen) {
            model.addRow(registro);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No se encontraron registros en el almacén.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Asignar el modelo a la tabla
    jTable1.setModel(model);
}
  
  private void cargarTablaRetiros(String numeroParte) {
    List<String[]> retiros;

    // Si el número de parte está vacío, consultar todos los retiros
    if (numeroParte.isEmpty()) {
        retiros = ConsultarDatos.obtenerRetiros();
    } else {
        // Si no, consultar solo los retiros asociados al número de parte
        retiros = ConsultarDatos.consultarRetirosPorNumeroParte(numeroParte);
    }

    // Crear las columnas para la tabla
    String[] columnNames = {"Numero_Parte", "Folio", "Fecha_Retiro"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

    // Agregar los datos al modelo de la tabla
    if (retiros != null && !retiros.isEmpty()) {
        for (String[] retiro : retiros) {
            model.addRow(retiro);
        }
        
    } else {
        JOptionPane.showMessageDialog(this, "No se encontraron retiros.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    // Asignar el modelo a la tabla
    jTableRetiros.setModel(model);
}
  
    private void txtNumeroParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroParteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroParteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    // Ruta donde se guardará el PDF
    String rutaCarpeta = "C:\\Users\\razie\\Documents\\NetBeansProjects\\procemec\\Reportes\\";
    String nombreArchivo = generarNombreUnico("ReporteAlmacenYRetiros");        
    String rutaArchivo = rutaCarpeta + nombreArchivo; // Ruta completa del archivo

    // Crear la carpeta si no existe
    File carpeta = new File(rutaCarpeta);
    if (!carpeta.exists()) {
        carpeta.mkdirs(); // Crear la carpeta y todas las subcarpetas necesarias
    }

    // Llamar al método para generar el PDF con las dos tablas
    try {
        GenerarPDF.generarPDFDesdeTablas(
            jTable1, "Tabla de Almacén", // Primera tabla y su título
            jTableRetiros, "Tabla de Retiros", // Segunda tabla y su título
            rutaArchivo // Ruta donde se guardará el PDF
        );
        JOptionPane.showMessageDialog(this, "PDF generado correctamente en: " + rutaArchivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    String folio = txtFolios.getText().trim();
    java.util.Date fechaSeleccionada = fecharetiro.getDate();
     

    // Validación
    if (folio.isEmpty() || fechaSeleccionada == null) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verifica que el folio exista en Almacen_Inv
    if (!ConsultarDatos.existeFolioEnAlmacen(folio)) {
        JOptionPane.showMessageDialog(this, "El folio no existe en el almacén.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmación
    int confirmacion = JOptionPane.showConfirmDialog(
        this,
        "¿Estás seguro de que quieres retirar el rollo con folio " + folio + "?",
        "Confirmar retiro",
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        boolean exito = SeguimientoPiezas.retirarPiezaPorFolio(
            folio, new java.sql.Date(fechaSeleccionada.getTime())
        );

        if (exito) {
            JOptionPane.showMessageDialog(this, "Rollo retirado correctamente y seguimiento iniciado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            
        } else {
            JOptionPane.showMessageDialog(this, "Error al retirar el rollo.", "Error", JOptionPane.ERROR_MESSAGE);
        }   
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtFoliosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFoliosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFoliosActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
  // Obtener los valores de los campos
    String numeroParte = txtNumeroParte.getText().trim();
    double pesoBobina = ((Number) spinnerPesoBobina.getValue()).doubleValue(); // Obtener el peso de la bobina
    java.util.Date fechaSeleccionada = jDateChooser.getDate();

    // Validar que los campos no estén vacíos
    if (numeroParte.isEmpty() || fechaSeleccionada == null) {
        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validar que el peso sea un número válido
    if (pesoBobina <= 0) {
        JOptionPane.showMessageDialog(this, "El peso debe ser mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Verificar si la pieza existe en la tabla Registros
    if (!Ingresar.existePieza(numeroParte)) {
        JOptionPane.showMessageDialog(this, "La pieza no existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Convertir la fecha a java.sql.Date
    java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());

    // Insertar el movimiento en la base de datos
    if (Ingresar.insertarMovimiento(numeroParte, pesoBobina, fechaSQL)) {
        JOptionPane.showMessageDialog(this, "Pieza agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        try {
            // Generar el archivo PDF con el código de barras y los datos asociados
            GenerarPDFConCodigoBarras.generarPDFConCodigoBarras(numeroParte);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Error al agregar la pieza.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    limpiarCampos(); // Limpiar los campos después de un registro exitoso
    }//GEN-LAST:event_jButton6ActionPerformed
 
    public static String generarNombreUnico(String prefijo) {
        // Formato de fecha y hora para el nombre del archivo
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = formatoFecha.format(new Date()); // Obtener la fecha y hora actual
        return prefijo + "_" + timestamp + ".pdf"; // Ejemplo: Reporte_20231025_143022.pdf
    }
    
    private void limpiarCampos() {
    txtNumeroParte.setText(""); // Limpiar el campo de número de parte
    spinnerPesoBobina.setValue(0.0);    // Limpiar el campo de cantidad
    jDateChooser.setDate(null); // Restablecer el JDateChooser
    fecharetiro.setDate(null);
    txtFolios.setText("");
    }
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(IngresarMP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresarMP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresarMP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresarMP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new IngresarMP().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser fecharetiro;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableRetiros;
    private javax.swing.JSpinner spinnerPesoBobina;
    private javax.swing.JTextField txtFolios;
    private javax.swing.JTextField txtNumeroParte;
    // End of variables declaration//GEN-END:variables
}
