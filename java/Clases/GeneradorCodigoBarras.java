/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import java.io.File;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author familiar
 */
public class GeneradorCodigoBarras {

    // Método para generar códigos de barras para una lista de folios
    public static void generarCodigosDeBarras(List<String> folios, String rutaCarpeta) {
        // Verificar si la carpeta existe, si no, crearla
        Path carpeta = FileSystems.getDefault().getPath(rutaCarpeta);
        if (!java.nio.file.Files.exists(carpeta)) {
            try {
                java.nio.file.Files.createDirectories(carpeta);
            } catch (IOException e) {
                System.err.println("Error al crear la carpeta: " + e.getMessage());
                return;
            }
        }

        // Generar un código de barras para cada folio
        for (String folio : folios) {
            String rutaArchivo = rutaCarpeta + "/" + folio + ".png"; // Ruta completa del archivo
            generarCodigoBarras(folio, rutaArchivo);
        }
    }

    // Método para generar un código de barras individual
    private static void generarCodigoBarras(String texto, String rutaArchivo) {
        try {
            // Configurar el tamaño del código de barras
            int ancho = 300;
            int alto = 100;

            // Generar la matriz del código de barras
            BitMatrix bitMatrix = new Code128Writer().encode(texto, BarcodeFormat.CODE_128, ancho, alto);

            // Guardar la imagen del código de barras en un archivo PNG
            Path path = FileSystems.getDefault().getPath(rutaArchivo);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            System.out.println("Código de barras generado y guardado en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el código de barras: " + e.getMessage());
        }
    
    }

    public static String CodigoBarrasUnico (String numeroParte, int idProceso) {
        // Formato: NumeroParte_IdProceso_Timestamp
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = formatoFecha.format(new Date());
        return numeroParte + "_" + idProceso + "_" + timestamp;
    }
    
public static void generarCodigoBarrasParaParte(String numeroParte, String folio) {
    // Obtener detalles de la pieza desde la tabla Registros
    List<String[]> detalles = ConsultarDatos.consultarPorNumeroParteONombre(numeroParte);
    if (detalles != null && !detalles.isEmpty()) {
        String[] registro = detalles.get(0);
        String nombre = registro[1];
        String espesor = registro[2];
        String ancho = registro[3];
        String especificaciones = registro[4];

        // Crear el texto para el código de barras (solo número de parte y folio)
        String textoCodigoBarras = String.format("Numero_Parte: %s, Folio: %s", numeroParte, folio);

        // Ruta donde se guardará el código de barras
        String rutaCarpeta = "C:\\Users\\razie\\Documents\\NetBeansProjects\\procemec\\codigos_barras\\";
        String rutaArchivo = rutaCarpeta + folio + ".png";

        // Verificar si la carpeta existe, si no, crearla
        Path carpeta = Paths.get(rutaCarpeta);
        if (!Files.exists(carpeta)) {
            try {
                Files.createDirectories(carpeta); // Crear la carpeta y todas las subcarpetas necesarias
            } catch (IOException e) {
                System.err.println("Error al crear la carpeta: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Error al crear la carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        // Generar el código de barras
        try {
            GeneradorCodigoBarras.generarCodigoBarras(textoCodigoBarras, rutaArchivo);
            System.out.println("Código de barras generado y guardado en: " + rutaArchivo);
        } catch (Exception e) {
            System.err.println("Error al generar el código de barras: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al generar el código de barras.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    public static void generarCodigoDeBarrass(String texto, String carpetaDestino) {
        int width = 300;
        int height = 100;

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(texto, BarcodeFormat.CODE_128, width, height);
            String nombreArchivo = carpetaDestino + File.separator + texto + ".png";
            Path path = FileSystems.getDefault().getPath(nombreArchivo);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
        }
    }
    }

    
