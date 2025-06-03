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
import java.time.LocalDate;

/**
 *
 * @author familiar
 */
public class SeguimientoPiezas {
    
public static boolean retirarPieza(String numeroParte, int cantidad, Date fecha, String folioBase) {
    Connection conn = null;
    try {
        conn = MyConnection.getConnection();
        conn.setAutoCommit(false); // Iniciar transacción

        // 1️⃣ Verificar si la pieza existe en la tabla Registros antes de continuar
        String sqlVerificar = "SELECT * FROM Registros WHERE Numero_Parte = ?";
        try (PreparedStatement pstmtVerificar = conn.prepareStatement(sqlVerificar)) {
            pstmtVerificar.setString(1, numeroParte);
            ResultSet rs = pstmtVerificar.executeQuery();
            if (!rs.next()) {
                System.err.println("❌ ERROR: La pieza con Numero_Parte " + numeroParte + " no existe en Registros.");
                return false;
            }
        }

        // 2️⃣ Actualizar la cantidad en la tabla Almacen_Inv
        String sqlAlmacen = "UPDATE Almacen_Inv SET Cantidad = Cantidad - ?, Fecha = ? WHERE Numero_Parte = ? AND Cantidad >= ?";
        try (PreparedStatement pstmtAlmacen = conn.prepareStatement(sqlAlmacen)) {
            pstmtAlmacen.setInt(1, cantidad);
            pstmtAlmacen.setDate(2, new java.sql.Date(fecha.getTime()));
            pstmtAlmacen.setString(3, numeroParte);
            pstmtAlmacen.setInt(4, cantidad);

            int filasAfectadas = pstmtAlmacen.executeUpdate();
            System.out.println("🔄 Filas afectadas en Almacen_Inv: " + filasAfectadas);

            if (filasAfectadas == 0) {
                System.err.println("❌ ERROR: No se pudo actualizar Almacen_Inv. ¿Hay suficiente cantidad?");
                conn.rollback();
                return false;
            }
        }

        // 3️⃣ Insertar en la tabla Retiros
        String sqlRetiros = "INSERT INTO Retiros (Numero_Parte, Folio) VALUES (?, ?)";
        try (PreparedStatement pstmtRetiros = conn.prepareStatement(sqlRetiros)) {
            for (int i = 1; i <= cantidad; i++) {
                String folioRetiro = folioBase;

                // Verificar si el folio ya existe antes de insertar
                String sqlVerificarFolio = "SELECT * FROM Retiros WHERE Folio = ?";
                try (PreparedStatement pstmtVerificarFolio = conn.prepareStatement(sqlVerificarFolio)) {
                    pstmtVerificarFolio.setString(1, folioRetiro);
                    ResultSet rsFolio = pstmtVerificarFolio.executeQuery();
                    if (rsFolio.next()) {
                        System.err.println("⚠️ ERROR: El folio " + folioRetiro + " ya existe en Retiros. Se generará otro.");
                        continue; // Saltar esta iteración y continuar con otro folio
                    }
                }

                pstmtRetiros.setString(1, numeroParte);
                pstmtRetiros.setString(2, folioRetiro);
                pstmtRetiros.addBatch();
                System.out.println("✅ Insertando retiro: " + numeroParte + " - " + folioRetiro);
            }
            int[] resultadoBatch = pstmtRetiros.executeBatch();
            System.out.println("🟢 Registros insertados en Retiros: " + resultadoBatch.length);
        }

        // 4️⃣ Confirmar la transacción
        conn.commit();
        System.out.println("🚀 Transacción confirmada con éxito.");
        return true;
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback();
                System.err.println("🔴 Transacción revertida.");
            } catch (SQLException ex) {
                System.err.println("🔴 Error al hacer rollback: " + ex.getMessage());
            }
        }
        System.err.println("🔴 Error SQL: " + e.getMessage());
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException e) {
                System.err.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
    return false;
}

    public static String obtenerNumeroParte(String folio) {
    String sql = "SELECT Numero_Parte FROM Seguimiento WHERE Folio = ? LIMIT 1";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, folio);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Numero_Parte"); // Retorna el número de parte
            }
        }
    } catch (SQLException e) {
        return null; // Retorna null si hay un error
    }
    return null; // Retorna null si no se encuentra el folio
}
    
public static boolean retirarPiezaPorFolio(String folio, java.sql.Date fecha) {
    Connection conn = null;
    try {
        conn = MyConnection.getConnection();
        conn.setAutoCommit(false); // Desactivar el modo de autocommit

        // 1. Obtener el número de parte asociado al folio
        String numeroParte = obtenerNumeroPartePorFolio(folio);
        if (numeroParte == null) {
            conn.rollback(); // Revertir la transacción si no se encuentra el folio
            return false;
        }

        // 2. Insertar el registro en la tabla Retiros
        String sqlInsertarRetiro = "INSERT INTO Retiros (Numero_Parte, Folio, Fecha_Retiro) VALUES (?, ?, ?)";
        try (PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertarRetiro)) {
            pstmtInsertar.setString(1, numeroParte);
            pstmtInsertar.setString(2, folio);
            pstmtInsertar.setDate(3, fecha);

            int filasAfectadas = pstmtInsertar.executeUpdate();
            if (filasAfectadas <= 0) {
                conn.rollback(); // Revertir la transacción si no se insertó el retiro
                return false;
            }
        }

        // 3. Eliminar el registro de la tabla Almacen_Inv
        String sqlEliminarAlmacen = "DELETE FROM Almacen_Inv WHERE Folio = ?";
        try (PreparedStatement pstmtEliminar = conn.prepareStatement(sqlEliminarAlmacen)) {
            pstmtEliminar.setString(1, folio);

            int filasAfectadas = pstmtEliminar.executeUpdate();
            if (filasAfectadas <= 0) {
                conn.rollback(); // Revertir la transacción si no se eliminó la pieza
                return false;
            }
        }

        conn.commit(); // Confirmar la transacción
        return true;
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException ex) {
                System.out.println("Error al revertir la transacción: " + ex.getMessage());
            }
        }
        System.out.println("Error al retirar la pieza: " + e.getMessage());
        return false;
    } finally {
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Restaurar el modo de autocommit
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

// Método para obtener el número de parte asociado a un folio
public static String obtenerNumeroPartePorFolio(String folio) {
    String sql = "SELECT Numero_Parte FROM Almacen_Inv WHERE Folio = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, folio);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Numero_Parte"); // Retorna el número de parte
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el número de parte: " + e.getMessage());
    }
    return null; // Retorna null si no se encuentra el folio
}
    
}
