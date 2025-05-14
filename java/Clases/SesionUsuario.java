/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Arrays;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;


public class SesionUsuario {
    private static int usuarioId;
    private static String nombreUsuario;
    private static List<String> permisos;

    // Constructor privado
    private SesionUsuario() {
    }

    public static void iniciarSesion(int id, String usuario, String permisosStr) {
        usuarioId = id;
        nombreUsuario = usuario;
        permisos = (permisosStr != null && !permisosStr.isEmpty()) ? Arrays.asList(permisosStr.split(",")) : java.util.Collections.emptyList();
    }

    public static void cerrarSesion() {
        usuarioId = 0;
        nombreUsuario = null;
        permisos = null;
    }

    public static int getUsuarioId() {
        return usuarioId;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static List<String> getPermisos() {
        return permisos;
    }

    public static boolean tienePermiso(String nombreVentana) {
        return permisos != null && permisos.contains(nombreVentana);
    }

    // Métodos para el manejo de contraseñas
    public static String hashContrasena(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public static boolean verificarContrasena(String contrasenaIngresada, String contrasenaAlmacenada) {
        try {
            return BCrypt.checkpw(contrasenaIngresada, contrasenaAlmacenada);
        } catch (IllegalArgumentException e) {
            return false;
        }
    } 
}


