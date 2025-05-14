/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author familiar
 */
public class Ingresar {

public static boolean insertarMovimiento(String numeroParte, double pesoBobina, Date fecha) {
    Connection cnx = MyConnection.getConnection();
    if (cnx != null) {
        try {
            // Obtener el Peso_Blank de la tabla Registros
            String sqlPesoBlank = "SELECT Peso_Blank FROM Registros WHERE Numero_Parte = ?";
            PreparedStatement pstmtPesoBlank = cnx.prepareStatement(sqlPesoBlank);
            pstmtPesoBlank.setString(1, numeroParte);
            ResultSet rs = pstmtPesoBlank.executeQuery();

            double pesoBlank = 0.0;
            if (rs.next()) {
                pesoBlank = rs.getDouble("Peso_Blank");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el Peso_Blank para el número de parte: " + numeroParte);
                return false;
            }

            // Calcular el TotalPiezas
            double totalPiezas = pesoBobina / pesoBlank;

            // Insertar el registro en la tabla Almacen_Inv
            String sqlInsert = "INSERT INTO Almacen_Inv (Numero_Parte, Folio, Fecha, Peso_Bobina, TotalPiezas) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmtInsert = cnx.prepareStatement(sqlInsert);

            // Generar un folio único
            String folioUnico = GeneralFolio.generarFolioBase();

            // Insertar los datos
            pstmtInsert.setString(1, numeroParte);
            pstmtInsert.setString(2, folioUnico);
            pstmtInsert.setDate(3, new java.sql.Date(fecha.getTime()));
            pstmtInsert.setDouble(4, pesoBobina);
            pstmtInsert.setDouble(5, totalPiezas); // Insertar el TotalPiezas

            int filasAfectadas = pstmtInsert.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se insertó correctamente
        } catch (SQLException e) {
            System.out.println("Error al insertar movimiento: " + e.getMessage());
            return false;
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    return false;
}
    

// Método para verificar si la pieza ya existe en la tabla Almacen_Inv
public static boolean existeEnAlmacen(String numeroParte) {
    String sql = "SELECT COUNT(*) FROM Almacen_Inv WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, numeroParte);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si la pieza existe
            }
        }
    } catch (SQLException e) {
        return false;
    }
    return false;
}

// Método para actualizar la cantidad de una pieza existente
public static boolean actualizarCantidad(String numeroParte, int cantidad, Date fecha) {
    String sql = "UPDATE Almacen_Inv SET Cantidad = Cantidad + ?, Fecha = ? WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, cantidad);
        pstmt.setDate(2, fecha);
        pstmt.setString(3, numeroParte);
        int filasAfectadas = pstmt.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        return false;
    }
}

    public static boolean existePieza(String numeroParte) {
        String sql = "SELECT Numero_Parte FROM Registros WHERE Numero_Parte = ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, numeroParte);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Retorna true si la pieza existe
            }
        } catch (SQLException e) {
            return false;
        }
    }
    
    public static boolean actualizarOInsertarMovimiento(String numeroParte, int cantidad, String fecha) {
       // Consulta para actualizar o insertar
        String sql = "INSERT INTO Almacen_Inv (Numero_Parte, Cantidad, Fecha) VALUES (?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE Cantidad = Cantidad + ?, Fecha = ?";

        try (Connection conn = MyConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Parámetros para la inserción o actualización
            pstmt.setString(1, numeroParte);
            pstmt.setInt(2, cantidad);
            pstmt.setString(3, fecha);
            pstmt.setInt(4, cantidad); // Cantidad a sumar en caso de actualización
            pstmt.setString(5, fecha); // Nueva fecha en caso de actualización

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si se insertó o actualizó correctamente
        } catch (SQLException e) {
            return false;
        }
    }
    
 public static boolean retirarPieza(String numeroParte, int cantidad, Date fecha, String folioRetiro) {
    // Verificar que la cantidad a retirar no sea mayor que la disponible
    String sqlVerificar = "SELECT Cantidad FROM Almacen_Inv WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar)) {
        pstmtVerificar.setString(1, numeroParte);
        try (ResultSet rs = pstmtVerificar.executeQuery()) {
            if (rs.next()) {
                int cantidadDisponible = rs.getInt("Cantidad");
                if (cantidadDisponible < cantidad) {
                    System.out.println("No hay suficiente cantidad para retirar.");
                    return false;
                }
            } else {
                System.out.println("La pieza no existe en el almacén.");
                return false;
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar cantidad disponible: " + e.getMessage());
        return false;
    }

    // Retirar la cantidad
    String sqlRetirar = "UPDATE Almacen_Inv SET Cantidad = Cantidad - ?, Fecha = ? WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmtRetirar = conn.prepareStatement(sqlRetirar)) {
        pstmtRetirar.setInt(1, cantidad);
        pstmtRetirar.setDate(2, fecha);
        pstmtRetirar.setString(3, numeroParte);
        int filasAfectadas = pstmtRetirar.executeUpdate();
        return filasAfectadas > 0;
    } catch (SQLException e) {
        System.out.println("Error al retirar pieza: " + e.getMessage());
        return false;
    }
}
    
}
