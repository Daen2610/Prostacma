/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SeguimientoRollos {
    private Connection getConnection() throws SQLException {
        return MyConnection.getConnection(); // usa tu clase de conexión
    }

    // Llama esto cuando se presione el botón "Buscar"
public void buscarSeguimiento(String entrada, JTable tablaActual, JTable tablaHistorial) {
    try (Connection conn = getConnection()) {
        
        boolean esFolio = entrada.contains("_"); // Si contiene "_", es folio
        String sqlActual;
        PreparedStatement psActual;

        if (esFolio) {
            // Búsqueda por FOLIO
            sqlActual = """
                SELECT r.folio, p.paso, p.proceso, p.area, s.piezas_completadas, s.piezas_ng, s.piezas_totales,
                       CASE WHEN s.piezas_completadas + s.piezas_ng >= s.piezas_totales THEN 'Finalizado' ELSE 'En proceso' END AS estado,
                       r.numero_parte
                FROM Seguimiento s
                JOIN Procesos p ON s.id_proceso = p.id
                JOIN retiros r ON s.folio_rollo = r.folio
                WHERE s.folio_rollo = ?
                ORDER BY s.id_seguimiento DESC
                LIMIT 1
            """;
            psActual = conn.prepareStatement(sqlActual);
            psActual.setString(1, entrada);

        } else {
            // Búsqueda por NÚMERO DE PARTE
            sqlActual = """
                SELECT r.folio, p.paso, p.proceso, p.area, s.piezas_completadas, s.piezas_ng, s.piezas_totales,
                       CASE WHEN s.piezas_completadas + s.piezas_ng >= s.piezas_totales THEN 'Finalizado' ELSE 'En proceso' END AS estado,
                       r.numero_parte
                FROM Seguimiento s
                JOIN Procesos p ON s.id_proceso = p.id
                JOIN retiros r ON s.folio_rollo = r.folio
                WHERE r.numero_parte = ? AND (s.piezas_completadas + s.piezas_ng) < s.piezas_totales
                ORDER BY r.folio
            """;
            psActual = conn.prepareStatement(sqlActual);
            psActual.setString(1, entrada);
        }

        ResultSet rsActual = psActual.executeQuery();
        DefaultTableModel modelActual = new DefaultTableModel(
            new String[]{"Folio", "Número de Parte", "Paso Actual", "Proceso", "Área", "Piezas Completadas", "Piezas NG", "Piezas Totales", "Estado"}, 0
        );

        while (rsActual.next()) {
            modelActual.addRow(new Object[]{
                rsActual.getString("folio"),
                rsActual.getString("numero_parte"),
                rsActual.getString("paso"),
                rsActual.getString("proceso"),
                rsActual.getString("area"),
                rsActual.getInt("piezas_completadas"),
                rsActual.getInt("piezas_ng"),
                rsActual.getInt("piezas_totales"),
                rsActual.getString("estado")
            });
        }

        tablaActual.setModel(modelActual);

        // Solo se muestra historial si se buscó por folio
        DefaultTableModel modelHistorial = new DefaultTableModel(
            new String[]{"Paso", "Proceso", "Área", "Completadas", "NG", "Totales", "Inició", "Finalizó", "Aceptado"}, 0
        );

        if (esFolio) {
            String sqlHistorial = """
                SELECT p.paso, p.proceso, p.area, s.piezas_completadas, s.piezas_ng, s.piezas_totales,
                       s.fecha_inicio, s.fecha_fin, s.aceptado
                FROM Seguimiento s
                JOIN Procesos p ON s.id_proceso = p.id
                WHERE s.folio_rollo = ?
                ORDER BY CAST(p.paso AS UNSIGNED)
            """;

            PreparedStatement psHistorial = conn.prepareStatement(sqlHistorial);
            psHistorial.setString(1, entrada);
            ResultSet rsHistorial = psHistorial.executeQuery();

            while (rsHistorial.next()) {
                modelHistorial.addRow(new Object[]{
                    rsHistorial.getString("paso"),
                    rsHistorial.getString("proceso"),
                    rsHistorial.getString("area"),
                    rsHistorial.getInt("piezas_completadas"),
                    rsHistorial.getInt("piezas_ng"),
                    rsHistorial.getInt("piezas_totales"),
                    rsHistorial.getTimestamp("fecha_inicio"),
                    rsHistorial.getTimestamp("fecha_fin"),
                    rsHistorial.getBoolean("aceptado") ? "Sí" : "No"
                });
            }
        }

        tablaHistorial.setModel(modelHistorial);

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al buscar seguimiento: " + e.getMessage());
    }
}

}
