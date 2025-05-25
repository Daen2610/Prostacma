/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class GeneradorEtiquetas {
    private JTextField txtNumeroParte;
    private JTextField txtNombre;
    private JTextField txtSNP;
    private JTextField txtFecha;
    private JTextField txtTarjetaViajera;
    private JTextField txtCantidad;
    private final JButton btnBuscar;
    private JButton btnImprimir;
    
    private Connection conexion;

    public GeneradorEtiquetas(JTextField txtNumeroParte, JTextField txtNombre, JTextField txtSNP, 
                            JTextField txtFecha, JTextField txtTarjetaViajera, JTextField txtCantidad,
                            JButton btnBuscar, JButton btnImprimir) {
        this.txtNumeroParte = txtNumeroParte;
        this.txtNombre = txtNombre;
        this.txtSNP = txtSNP;
        this.txtFecha = txtFecha;
        this.txtTarjetaViajera = txtTarjetaViajera;
        this.txtCantidad = txtCantidad;
        this.btnBuscar = btnBuscar;
        this.btnImprimir = btnImprimir;
        
        // Configurar la conexión a la base de datos
        MyConnection.getConnection();
        
        // Configurar el evento del botón Buscar
        configurarEventoBuscar();
    }
private void configurarEventoBuscar() {
        btnBuscar.addActionListener((ActionEvent e) -> {
            buscarProducto();
        });
    }

public void buscarProducto() {
        String numeroParte = txtNumeroParte.getText().trim();
        
        if (numeroParte.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número de parte", 
                                      "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String sql = "SELECT descripcion, snp FROM productos WHERE numero_parte = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
                stmt.setString(1, numeroParte);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Encontramos el producto - llenamos los campos
                        txtNombre.setText(rs.getString("descripcion"));
                        txtSNP.setText(String.valueOf(rs.getInt("snp")));
                    } else {
                        // No se encontró el producto
                        JOptionPane.showMessageDialog(null, "No se encontró un producto con el número de parte: " + numeroParte,
                                "Producto no encontrado", JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar en la base de datos: " + ex.getMessage(), 
                                      "Error de consulta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtSNP.setText("");
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
}
