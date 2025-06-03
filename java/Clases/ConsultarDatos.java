/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author familiar
 */
public class ConsultarDatos {
    
    public static List<String[]> obtenerRegistros() {
        List<String[]> registros = new ArrayList<>();
        Connection cnx = MyConnection.getConnection(); // Obtiene la conexión a la base de datos

        if (cnx != null) {
            try {
                // Consulta SQL para obtener los datos de la tabla Registros
                String sql = "SELECT Numero_Parte, Nombre, Espesor, Ancho, Especificaciones, Peso_Blank FROM Registros";
                PreparedStatement statement = cnx.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                // Recorre los resultados y los agrega a la lista
                while (resultSet.next()) {
                   
                    String numeroParte = resultSet.getString("Numero_Parte");
                    String nombre = resultSet.getString("Nombre");
                    String espesor = resultSet.getString("Espesor");
                    String ancho = resultSet.getString("Ancho");
                    String especificaciones = resultSet.getString("Especificaciones");
                    String pesoblank = resultSet.getString("Peso_Blank");

                    // Agrega los datos a la lista como un arreglo de Strings
                    registros.add(new String[]{numeroParte, nombre, espesor, ancho, especificaciones, pesoblank});
                    
                    // Generar código de barras para el número de parte
                
                }
            } catch (SQLException ex) {
                System.out.println("Error al obtener datos de la tabla Registros: " + ex.getMessage());
            } finally {
                // Cierra la conexión
                try {
                    if (cnx != null) {
                        cnx.close();
                    }
                } catch (SQLException ex) {
                    System.out.println("Error al cerrar la conexión: " + ex.getMessage());
                }
            }
        }
        return registros;
    }
    
public static List<String[]> consultarPorNumeroParteONombre(String busqueda) {
    List<String[]> registros = new ArrayList<>();
    Connection cnx = MyConnection.getConnection();

    if (cnx != null) {
        try {
            // Consulta SQL que busca por número de parte o nombre, permitiendo búsquedas parciales
            String sql = "SELECT Numero_Parte, Nombre, Espesor, Ancho, Especificaciones, Peso_Blank " +
                         "FROM Registros " +
                         "WHERE Numero_Parte LIKE ? OR Nombre LIKE ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            
            // Agregamos el carácter % para permitir búsquedas parciales
            statement.setString(1, busqueda + "%"); // Busca números de parte que comiencen con "busqueda"
            statement.setString(2, busqueda + "%"); // Busca nombres que comiencen con "busqueda"
            
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String numeroParte = resultSet.getString("Numero_Parte");
                String nombre = resultSet.getString("Nombre");
                String espesor = resultSet.getString("Espesor");
                String ancho = resultSet.getString("Ancho");
                String especificaciones = resultSet.getString("Especificaciones");
                String pesoblank = resultSet.getString("Peso_Blank");
                
                // Agregamos el registro a la lista
                registros.add(new String[]{numeroParte, nombre, espesor, ancho, especificaciones, pesoblank});
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar por número de parte o nombre: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return registros;
}
    // En ConsultarDatos.java
public static List<String[]> obtenerAlmacen() {
    List<String[]> registros = new ArrayList<>();
    Connection cnx = MyConnection.getConnection();
    if (cnx != null) {
        try {
            String sql = "SELECT Numero_Parte, Fecha, Folio, Peso_Bobina, TotalPiezas FROM Almacen_Inv";
            PreparedStatement statement = cnx.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String numeroParte = resultSet.getString("Numero_Parte");
                String fecha = resultSet.getString("Fecha");
                String folio = resultSet.getString("Folio");
                String PesoBobina = resultSet.getString("Peso_Bobina");
                String totalpiezas = resultSet.getString("TotalPiezas");
                
                registros.add(new String[]{numeroParte, fecha, folio, PesoBobina, totalpiezas});
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener datos de la tabla Almacen_Inv: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) cnx.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return registros;
}

public static List<String[]> consultarPorNumeroParteAlmacen(String numeroParte) {
    List<String[]> registros = new ArrayList<>();
    Connection cnx = MyConnection.getConnection();

    if (cnx != null) {
        try {
            // Usar LIKE para búsquedas parciales
            String sql = "SELECT Numero_Parte, Fecha, Folio, Peso_Bobina, TotalPiezas FROM Almacen_Inv WHERE Numero_Parte LIKE ?";
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.setString(1, numeroParte + "%"); // Busca números de parte que comiencen con "numeroParte"
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("Numero_Parte");
                String fecha = resultSet.getString("Fecha");
                String folio = resultSet.getString("Folio");
                String PesoBobina = resultSet.getString("Peso_Bobina");
                String totalpiezas = resultSet.getString("TotalPiezas");

                registros.add(new String[]{nombre, fecha, folio, PesoBobina, totalpiezas});
            }
        } catch (SQLException ex) {
            System.out.println("Error al consultar por número de parte: " + ex.getMessage());
        } finally {
            try {
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }
    }
    return registros;
}

public static int obtenerCantidadActual(String numeroParte) {
    String sql = "SELECT Cantidad FROM Almacen_Inv WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, numeroParte);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("Cantidad"); // Retorna la cantidad actual
            }
        }
    } catch (SQLException e) {
        return -1; // Retorna -1 si hay un error
    }
    return -1; // Retorna -1 si no se encuentra la pieza
}
    public static String obtenerFolioOriginal(String numeroParte) {
    String sql = "SELECT Folio FROM Almacen_Inv WHERE Numero_Parte = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, numeroParte);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Folio"); // Retorna el folio original
            }
        }
    } catch (SQLException e) {
        return null; // Retorna null si hay un error
    }
    return null; // Retorna null si no se encuentra la pieza
}
public static List<String[]> obtenerRetiros() {
    List<String[]> retiros = new ArrayList<>();
    String sql = "SELECT Numero_Parte, Folio, Fecha_Retiro FROM Retiros" ;
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        while (rs.next()) {
           
            String numeroParte = rs.getString("Numero_Parte");
            String folio = rs.getString("Folio");
            String fechaRetiro = rs.getString("Fecha_Retiro");
            retiros.add(new String[]{numeroParte, folio, fechaRetiro});
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener los retiros: " + e.getMessage());
    }
    return retiros;
}

public static List<String[]> consultarRetirosPorNumeroParte(String numeroParte) {
    List<String[]> retiros = new ArrayList<>();
    String sql = "SELECT Numero_Parte, Folio, Fecha_Retiro FROM Retiros WHERE Numero_Parte LIKE ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, numeroParte + "%"); // Busca números de parte que comiencen con "numeroParte"
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
        
                String folio = rs.getString("Folio");
                String fechaRetiro = rs.getString("Fecha_Retiro");
                retiros.add(new String[]{numeroParte, folio, fechaRetiro});
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar retiros por número de parte: " + e.getMessage());
    }
    return retiros;
}

public static boolean existeFolioEnAlmacen(String folio) {
    String sql = "SELECT COUNT(*) FROM Almacen_Inv WHERE Folio = ?";
    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, folio);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si el folio existe
            }
        }
    } catch (SQLException e) {
        System.out.println("Error al verificar existencia del folio: " + e.getMessage());
    }
    return false;
}
}
