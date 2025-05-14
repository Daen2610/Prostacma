/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CrudUsuarios {
    
        public static String obtenerPermisosSeleccionados(boolean checkConsultar, boolean checkAlmacenMP, boolean checkRegistroProd, boolean checkSeguimientoProd) {
        StringBuilder permisos = new StringBuilder();
        if (checkConsultar) permisos.append("Consultar,");
        if (checkAlmacenMP) permisos.append("IngresarMP,");
        if (checkRegistroProd) permisos.append("Registros,");
        if (checkSeguimientoProd) permisos.append("ConsultaPiezas,");
        if (permisos.length() > 0) {
            permisos.deleteCharAt(permisos.length() - 1);
        }
        return permisos.toString();
    }

    public static void limpiarCampos(javax.swing.JTextField txtNombreUsuario, javax.swing.JPasswordField txtContrasena, javax.swing.JPasswordField txtConfirmarContrasena, javax.swing.JTextField txtNombreCompleto, javax.swing.JCheckBox checkConsultar, javax.swing.JCheckBox checkAlmacenMP, javax.swing.JCheckBox checkRegistroProd, javax.swing.JCheckBox checkSeguimientoProd, javax.swing.JTable tablaUsuarios) {
        if (txtNombreUsuario != null) txtNombreUsuario.setText("");
        if (txtContrasena != null) txtContrasena.setText("");
        if (txtConfirmarContrasena != null) txtConfirmarContrasena.setText("");
        if (txtNombreCompleto != null) txtNombreCompleto.setText("");
        if (checkConsultar != null) checkConsultar.setSelected(false);
        if (checkAlmacenMP != null) checkAlmacenMP.setSelected(false);
        if (checkRegistroProd != null) checkRegistroProd.setSelected(false);
        if (checkSeguimientoProd != null) checkSeguimientoProd.setSelected(false);
        if (tablaUsuarios != null) tablaUsuarios.clearSelection();
    }

    public static void cargarUsuarios(DefaultTableModel modeloTabla, javax.swing.JFrame parentComponent) {
        modeloTabla.setRowCount(0);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT usuario_id, nombre_usuario, nombre_completo, permisos FROM Usuarios";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{rs.getInt("usuario_id"), rs.getString("nombre_usuario"), rs.getString("nombre_completo"), rs.getString("permisos")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error al cargar usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos(rs, pstmt, conn, parentComponent);
        }
    }

    private static void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn, javax.swing.JFrame parentComponent) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error al cerrar recursos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void agregarUsuario(String nuevoUsuario, String nuevaContrasenia, String confirmarContrasenia, String nombreCompleto, String permisos, DefaultTableModel modeloTabla, javax.swing.JFrame parentComponent) {
        if (nuevoUsuario == null || nuevoUsuario.isEmpty() || nuevaContrasenia == null || nuevaContrasenia.isEmpty() || confirmarContrasenia == null || confirmarContrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(parentComponent, "Nombre de usuario, contraseña y confirmación son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!nuevaContrasenia.equals(confirmarContrasenia)) {
            JOptionPane.showMessageDialog(parentComponent, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String contraseniaHasheada = SesionUsuario.hashContrasena(nuevaContrasenia);
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "INSERT INTO Usuarios (nombre_usuario, contrasena, nombre_completo, permisos) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoUsuario);
            pstmt.setString(2, contraseniaHasheada);
            pstmt.setString(3, nombreCompleto);
            pstmt.setString(4, permisos);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(parentComponent, "Usuario agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarUsuarios(modeloTabla, parentComponent);
                limpiarCampos(null, null, null, null, null, null, null, null, null); // Limpiar campos en la interfaz
            } else {
                JOptionPane.showMessageDialog(parentComponent, "No se pudo agregar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error al agregar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos(null, pstmt, conn, parentComponent);
        }
    }

    public static void eliminarUsuario(int idUsuarioAEliminar, DefaultTableModel modeloTabla, javax.swing.JFrame parentComponent) {
        if (idUsuarioAEliminar == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione un usuario de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(parentComponent, "¿Está seguro de que desea eliminar este usuario?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = MyConnection.getConnection();
                String sql = "DELETE FROM Usuarios WHERE usuario_id = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, idUsuarioAEliminar);
                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Usuario eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarUsuarios(modeloTabla, parentComponent);
                    limpiarCampos(null, null, null, null, null, null, null, null, null);
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "No se encontró el usuario para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(parentComponent, "Error al eliminar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                cerrarRecursos(null, pstmt, conn, parentComponent);
            }
        }
    }

    public static void buscarUsuarios(String nombreUsuarioBuscar, DefaultTableModel modeloTabla, javax.swing.JFrame parentComponent) {
        modeloTabla.setRowCount(0);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT usuario_id, nombre_usuario, nombre_completo, permisos FROM Usuarios WHERE nombre_usuario LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nombreUsuarioBuscar + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{rs.getInt("usuario_id"), rs.getString("nombre_usuario"), rs.getString("nombre_completo"), rs.getString("permisos")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error al buscar usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos(rs, pstmt, conn, parentComponent);
        }
    }

    public static void actualizarUsuario(int idUsuarioAActualizar, String nuevoUsuario, String nuevaContrasenia, String confirmarContrasenia, String nombreCompleto, String permisos, DefaultTableModel modeloTabla, javax.swing.JFrame parentComponent) {
        if (idUsuarioAActualizar == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione un usuario de la tabla para actualizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (nuevoUsuario == null || nuevoUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(parentComponent, "El nombre de usuario es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = MyConnection.getConnection();
            String sql = "UPDATE Usuarios SET nombre_usuario = ?, nombre_completo = ?, permisos = ?";
            if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty() && nuevaContrasenia.equals(confirmarContrasenia)) {
                sql += ", contrasena = ?";
            }
            sql += " WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoUsuario);
            pstmt.setString(2, nombreCompleto);
            pstmt.setString(3, permisos);
            int parametroIndex = 4;
            if (nuevaContrasenia != null && !nuevaContrasenia.isEmpty() && nuevaContrasenia.equals(confirmarContrasenia)) {
                String contraseniaHasheada = SesionUsuario.hashContrasena(nuevaContrasenia);
                pstmt.setString(parametroIndex++, contraseniaHasheada);
            }
            pstmt.setInt(parametroIndex, idUsuarioAActualizar);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(parentComponent, "Usuario actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarUsuarios(modeloTabla, parentComponent);
                limpiarCampos(null, null, null, null, null, null, null, null, null);
            } else {
                JOptionPane.showMessageDialog(parentComponent, "No se pudo actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error al actualizar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos(null, pstmt, conn, parentComponent);
        }
    }

    public static int obtenerIdUsuarioSeleccionado(javax.swing.JTable tablaUsuarios, DefaultTableModel modeloTabla) {
        int filaSeleccionada = (tablaUsuarios != null) ? tablaUsuarios.getSelectedRow() : -1;
        if (filaSeleccionada >= 0) {
            return (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        }
        return -1;
    }
}
