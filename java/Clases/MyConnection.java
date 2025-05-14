/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author familiar
 */
public class MyConnection {
    // Método estático para obtener una conexión a la base de datos
    public static Connection getConnection() {
Connection connection = null;
        try {
            // 1. Registrar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establecer la conexión
        String url = "jdbc:mysql://localhost:3306/procemecbd";
        String user = "root"; // Usuario de MySQL
        String password = "Admin"; // Contraseña de MySQL

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC.");
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos.");
        }
        return connection;
    }
}