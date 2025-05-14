/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author familiar
 */
public class Eliminar {
    public static void eliminarAlmacen(int id) {
        Connection cnx = MyConnection.getConnection();
        if (cnx != null) {
            try {
                // Consulta SQL para eliminar un registro por Folio
                String sql = "DELETE FROM Almacen_Inv WHERE Numero_Parte = ?";
                PreparedStatement statement = cnx.prepareStatement(sql);
                statement.setInt(1, id); // Asignar el valor del folio al parámetro

                // Ejecutar la consulta
                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Registro eliminado correctamente.");
                } else {
                    System.out.println("No se encontró ningún registro con el Numero de Parte: " + id);
                }

            } catch (SQLException ex) {
                System.out.println("Error al eliminar datos: " + ex.getMessage());
            } finally {
                try {
                    cnx.close(); // Cerrar la conexión
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
    }
  
     public boolean eliminarMateriaPrima(String numeroParteAEliminar) {
        Connection conn = null;
        PreparedStatement psRegistros = null;
        PreparedStatement psProcesos = null;
        boolean eliminacionExitosa = false;

        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);

            // Eliminar de la tabla Registros
            String sqlRegistros = "DELETE FROM Registros WHERE numero_parte = ?";
            psRegistros = conn.prepareStatement(sqlRegistros);
            psRegistros.setString(1, numeroParteAEliminar);
            int registrosEliminados = psRegistros.executeUpdate();

            // Eliminar de la tabla Procesos
            String sqlProcesos = "DELETE FROM Procesos WHERE numero_parte = ?";
            psProcesos = conn.prepareStatement(sqlProcesos);
            psProcesos.setString(1, numeroParteAEliminar);
            int procesosEliminados = psProcesos.executeUpdate();

            conn.commit();
            eliminacionExitosa = (registrosEliminados > 0 && procesosEliminados >= 0);

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, "Error al hacer rollback en DAO: " + rollbackEx.getMessage(), "Error de Rollback", JOptionPane.ERROR_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro en DAO: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (psRegistros != null) psRegistros.close();
                if (psProcesos != null) psProcesos.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión en DAO: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            }
        }
        return eliminacionExitosa;
    }
    
}
