/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    
}
