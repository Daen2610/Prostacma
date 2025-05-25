/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SeguimientoTemporal {
  
public static boolean retirarTemporalmente(String folio, String pasoActual) {
    try (Connection conn = MyConnection.getConnection()) {
        conn.setAutoCommit(false);
        
        try {
            // 1. Verificar que el folio existe en producción
            if (!existeEnRetiros(conn, folio)) {
                JOptionPane.showMessageDialog(null, "El folio no existe en producción");
                return false;
            }

            // 2. Insertar en Rollos_Retirados con el paso actual
            String sqlInsert = """
                INSERT INTO Rollos_Retirados 
                    (folio, parte, paso_actual, piezas_realizadas, 
                    piezas_restantes, fecha_retiro, reincorporado)
                SELECT 
                    r.folio, r.numero_parte, ?, 
                    s.piezas_completadas,
                    (s.piezas_totales - s.piezas_completadas - COALESCE(s.piezas_ng, 0)),
                    NOW(), FALSE
                FROM retiros r 
                JOIN Seguimiento s ON r.folio = s.folio_rollo
                WHERE r.folio = ?
                ORDER BY s.id_seguimiento DESC LIMIT 1""";
            
            try (PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
                ps.setString(1, pasoActual);
                ps.setString(2, folio);
                ps.executeUpdate();
            }

            // 3. Eliminar de retiros
            String sqlDelete = "DELETE FROM retiros WHERE folio = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlDelete)) {
                ps.setString(1, folio);
                ps.executeUpdate();
            }

            conn.commit();
            return true;
            
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, 
            "Error al retirar temporalmente: " + e.getMessage());
        return false;
    }
}

private static boolean existeEnRetiros(Connection conn, String folio) throws SQLException {
    String sql = "SELECT 1 FROM retiros WHERE folio = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, folio);
        return ps.executeQuery().next();
    }
}
    
public static boolean reincorporarPieza(String folio, JTable tablaRetirados) throws SQLException {
    try (Connection conn = MyConnection.getConnection()) {
        conn.setAutoCommit(false);

        try {
            // 1. Verificar que el folio existe
            String checkSql = "SELECT parte, piezas_realizadas, piezas_restantes " +
                            "FROM Rollos_Retirados " +
                            "WHERE REPLACE(folio, ' ', '_') = ? " +
                            "AND reincorporado = FALSE";
            
            Object[] datosRollo;
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, folio);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        throw new SQLException("El folio " + folio + " no existe en Rollos_Retirados o ya fue reincorporado");
                    }
                    datosRollo = new Object[]{
                        rs.getString("parte"),
                        rs.getInt("piezas_realizadas"),
                        rs.getInt("piezas_restantes")
                    };
                }
            }

            String numeroParte = (String) datosRollo[0];
            int piezasRealizadas = (int) datosRollo[1];
            int piezasRestantes = (int) datosRollo[2];

            // 2. Insertar en retiros (versión mejorada)
            String insertSql = "INSERT INTO retiros (folio, numero_parte, total_piezas, fecha_retiro) " +
                             "VALUES (?, ?, ?, ?) " +
                             "ON DUPLICATE KEY UPDATE " +
                             "numero_parte = VALUES(numero_parte), " +
                             "total_piezas = VALUES(total_piezas), " +
                             "fecha_retiro = VALUES(fecha_retiro)";

            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                ps.setString(1, folio);
                ps.setString(2, numeroParte);
                ps.setInt(3, piezasRealizadas + piezasRestantes);

                // Obtener la fecha de retiro original del rollo retirado
                String fechaSql = "SELECT fecha_retiro FROM Rollos_Retirados WHERE REPLACE(folio, ' ', '_') = ?";
                try (PreparedStatement psFecha = conn.prepareStatement(fechaSql)) {
                    psFecha.setString(1, folio);
                    try (ResultSet rsFecha = psFecha.executeQuery()) {
                        if (rsFecha.next()) {
                            ps.setTimestamp(4, rsFecha.getTimestamp("fecha_retiro"));
                        } else {
                            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Fecha actual si no hay
                        }
                    }
                }
                ps.executeUpdate();
            }

            // 3. Actualizar Rollos_Retirados
            String updateSql = "UPDATE Rollos_Retirados " +
                             "SET reincorporado = TRUE, " +
                             "fecha_reincorporacion = NOW() " +
                             "WHERE REPLACE(folio, ' ', '_') = ?";
            
            try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                ps.setString(1, folio);
                ps.executeUpdate();
            }

            // 4. Registrar en seguimiento
            registrarReincorporacion(conn, folio, piezasRealizadas, piezasRestantes);

            conn.commit();

            // 5. Actualizar tabla visual
            if (tablaRetirados != null) {
                SwingUtilities.invokeLater(() -> {
                    DefaultTableModel model = (DefaultTableModel) tablaRetirados.getModel();
                    for (int i = 0; i < model.getRowCount(); i++) {
                        String folioTabla = ((String) model.getValueAt(i, 0)).replace(" ", "_");
                        if (folio.equals(folioTabla)) {
                            model.removeRow(i);
                            break;
                        }
                    }
                });
            }

            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        }
    }
}



// Métodos auxiliares actualizados
private static boolean marcarComoReincorporado(Connection conn, String folio) throws SQLException {
    String sql = "UPDATE Rollos_Retirados SET reincorporado = TRUE, " +
                "fecha_reincorporacion = NOW() WHERE folio = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, folio);
        return ps.executeUpdate() > 0;
    }
}

private static void actualizarTablaVisual(JTable tablaRetirados, String folio) {
    DefaultTableModel model = (DefaultTableModel) tablaRetirados.getModel();
    
    // Buscar y eliminar la fila con el folio correspondiente
    for (int i = 0; i < model.getRowCount(); i++) {
        if (folio.equals(model.getValueAt(i, 1))) { // Asumiendo que folio está en la columna 1
            model.removeRow(i);
            break;
        }
    }
    
    // Forzar actualización visual
    tablaRetirados.revalidate();
    tablaRetirados.repaint();
}

private static void registrarReincorporacion(Connection conn, String folio, int realizadas, int restantes) throws SQLException {
    String sql = """
        INSERT INTO Seguimiento 
            (folio_rollo, id_proceso, piezas_completadas, 
             piezas_totales, operador, fecha_inicio)
        SELECT ?, p.id, ?, ?, ?, NOW() 
        FROM Procesos p 
        WHERE p.paso = 'REINGRESO'""";
    
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, folio);
        ps.setInt(2, realizadas);
        ps.setInt(3, realizadas + restantes);
        ps.setString(4, System.getProperty("user.name"));
        ps.executeUpdate();
    }
}
// Métodos auxiliares

private static Object[] obtenerDatosRolloRetirado(Connection conn, String folio) throws SQLException {
    String sql = "SELECT parte, piezas_realizadas, piezas_restantes " +
                "FROM Rollos_Retirados WHERE folio = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, folio);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new Object[]{
            rs.getString("parte"),
            rs.getInt("piezas_realizadas"),
            rs.getInt("piezas_restantes")
        } : null;
    }
}

private static boolean insertarEnRetiros(Connection conn, String folio, String numeroParte, double totalPiezas) throws SQLException {
    // USANDO LOS NOMBRES EXACTOS DE COLUMNAS DE TU TABLA retiros
    String sql = "INSERT INTO retiros (folio, numero_parte, total_piezas) " +
                "VALUES (?, ?, ?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, folio);
        ps.setString(2, numeroParte);
        ps.setDouble(3, totalPiezas);
        return ps.executeUpdate() > 0;
    }
}


}

