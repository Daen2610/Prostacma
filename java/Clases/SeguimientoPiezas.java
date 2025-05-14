/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

/**
 *
 * @author familiar
 */
public class SeguimientoPiezas {

    public static boolean retirarPiezaPorFolio(String folio, java.sql.Date fecha) {
        Connection conn = null;

        try {
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Obtener datos del rollo
            String numeroParte;
            double pesoBobina;
            try (PreparedStatement ps = conn.prepareStatement("SELECT Numero_Parte, Peso_Bobina FROM Almacen_Inv WHERE Folio = ?")) {
                ps.setString(1, folio);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        conn.rollback();
                        System.out.println("Folio no existe en inventario.");
                        return false;
                    }
                    numeroParte = rs.getString("Numero_Parte");
                    pesoBobina = rs.getDouble("Peso_Bobina");
                }
            }

            // 2. Verificar si es parte relacionada
            boolean esRelacionada = false;
            String partePrincipal = numeroParte;
            try (PreparedStatement ps = conn.prepareStatement("SELECT parte_principal FROM Partes_Relacionadas WHERE parte_sub = ?")) {
                ps.setString(1, numeroParte);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        esRelacionada = true;
                        partePrincipal = rs.getString("parte_principal");
                    }
                }
            }

            // 3. Obtener los procesos de la parte principal (ordenados por paso)
            List<ProcesoInfo> procesos = new ArrayList<>();
            try (PreparedStatement ps = conn.prepareStatement("""
                SELECT p.id, p.numero_parte, r.Peso_Blank
                FROM Procesos p
                JOIN Registros r ON p.numero_parte = r.Numero_Parte
                WHERE p.numero_parte IN (
                    SELECT parte_sub FROM Partes_Relacionadas WHERE parte_principal = ?
                    UNION SELECT ?
                )
                ORDER BY LENGTH(p.paso), p.paso
            """)) {
                ps.setString(1, partePrincipal);
                ps.setString(2, partePrincipal);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String parte = rs.getString("numero_parte");
                        int idProceso = rs.getInt("id");
                        String pesoBlankStr = rs.getString("Peso_Blank");
                        double pesoBlank = pesoBlankStr == null ? 0 : Double.parseDouble(pesoBlankStr);
                        procesos.add(new ProcesoInfo(idProceso, parte, pesoBlank));
                    }
                }
            }

            if (procesos.isEmpty()) {
                conn.rollback();
                System.out.println("No hay procesos definidos para la parte: " + partePrincipal);
                return false;
            }

            // 4. Calcular la cantidad mínima de piezas posibles según peso_blank (solo para procesos con peso_blank > 0)
            int totalPiezas;
            List<ProcesoInfo> conPeso = new ArrayList<>();
            for (ProcesoInfo pi : procesos) {
                if (pi.pesoBlank > 0) conPeso.add(pi);
            }

            if (esRelacionada && !conPeso.isEmpty()) {
                List<Integer> posiblesPorPaso = new ArrayList<>();
                for (ProcesoInfo pi : conPeso) {
                    int piezas = (int) (pesoBobina / pi.pesoBlank);
                    posiblesPorPaso.add(piezas);
                }
                totalPiezas = Collections.min(posiblesPorPaso);

                // Guardar excedente como WIP
                for (ProcesoInfo pi : conPeso) {
                    int piezasPosibles = (int) (pesoBobina / pi.pesoBlank);
                    int sobrantes = piezasPosibles - totalPiezas;
                    if (sobrantes > 0) {
                        try (PreparedStatement ps = conn.prepareStatement(
                            "INSERT INTO WIP (numero_parte, piezas_sobrantes, folio_origen, peso_blank, fecha) VALUES (?, ?, ?, ?, NOW())"
                        )) {
                            ps.setString(1, pi.numeroParte);
                            ps.setInt(2, sobrantes);
                            ps.setString(3, folio);
                            ps.setDouble(4, pi.pesoBlank);
                            ps.executeUpdate();
                        }
                    }
                }
            } else {
                // Lógica normal (solo dividir por peso_blank del primer paso)
                double pesoBlankPrimero = procesos.get(0).pesoBlank;
                if (pesoBlankPrimero <= 0) pesoBlankPrimero = 1; // evitar división por cero
                totalPiezas = (int) (pesoBobina / pesoBlankPrimero);
            }

            // 5. Insertar en Retiros
            try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO retiros (numero_parte, folio, fecha_retiro, total_piezas) VALUES (?, ?, ?, ?)"
            )) {
                ps.setString(1, numeroParte);
                ps.setString(2, folio);
                ps.setDate(3, fecha);
                ps.setInt(4, totalPiezas);
                ps.executeUpdate();
            }

            // 6. Insertar seguimiento para primer paso
            try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO Seguimiento (folio_rollo, id_proceso, piezas_totales) VALUES (?, ?, ?)"
            )) {
                ps.setString(1, folio);
                ps.setInt(2, procesos.get(0).id);
                ps.setInt(3, totalPiezas);
                ps.executeUpdate();
            }

            // 7. Marcar el rollo como retirado
            try (PreparedStatement ps = conn.prepareStatement("UPDATE Almacen_Inv SET retirado = TRUE WHERE Folio = ?")) {
                ps.setString(1, folio);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Rollo retirado correctamente.");
            return true;

        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    // Clase auxiliar para almacenar info de procesos con su peso_blank
    static class ProcesoInfo {
        int id;
        String numeroParte;
        double pesoBlank;

        ProcesoInfo(int id, String numeroParte, double pesoBlank) {
            this.id = id;
            this.numeroParte = numeroParte;
            this.pesoBlank = pesoBlank;
        }
    }

    public static String obtenerNumeroPartePorFolio(String folio) {
        String sql = "SELECT Numero_Parte FROM Almacen_Inv WHERE Folio = ?";
        try (Connection conn = MyConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, folio);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Numero_Parte");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el número de parte: " + e.getMessage());
        }
        return null;
    }
}
