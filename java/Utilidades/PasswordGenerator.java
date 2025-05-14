/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Scanner;
/**
 *
 * @author familiar
 */
public class PasswordGenerator {
    public static String hashContrasena(String contrasena) {
        return BCrypt.hashpw(contrasena, BCrypt.gensalt());
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingrese la contraseña que desea hashear: ");
            String contraseñaPlana = scanner.nextLine();
            
            String contraseñaHasheada = hashContrasena(contraseñaPlana);
            System.out.println("Contraseña hasheada (para copiar en la base de datos):");
            System.out.println(contraseñaHasheada);
        }
    }
}
