/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author familiar
 */
public class LectorCodigoBarras {
    public static String leerCodigoBarras(String rutaArchivo) {
        try {
            // Cargar la imagen del código de barras
            BinaryBitmap binaryBitmap = new BinaryBitmap(
                new HybridBinarizer(
                    new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(rutaArchivo))
                    )
                )
            );

            // Leer el código de barras
            Result resultado = new MultiFormatReader().decode(binaryBitmap);
            return resultado.getText(); // Devuelve el texto del código de barras
        } catch (NotFoundException | IOException e) {
            System.err.println("Error al leer el código de barras: " + e.getMessage());
            return null;
        }
    }

    // Método para simular la lectura de un código de barras
    public String leerCodigo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Simulación de escaneo: Ingresa el código de barras: ");
        String codigoBarras = scanner.nextLine();
        return codigoBarras;
    }
    
   
}
