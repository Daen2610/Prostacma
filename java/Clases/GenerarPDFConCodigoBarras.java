/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author familiar
 */
public class GenerarPDFConCodigoBarras {
        // Rutas de las carpetas
    private static final String RUTA_CODIGOS_BARRAS = "C:\\Users\\razie\\Documents\\NetBeansProjects\\procemec\\codigos_barras";
    private static final String RUTA_DATOS_FOLIOS = "C:\\Users\\razie\\Documents\\NetBeansProjects\\procemec\\Datos_Folios";

    /**
     * Método para generar un archivo PDF con el código de barras y los datos asociados.
     *
     * @param numeroParte El número de parte para el cual se generará el archivo.
     */
public static void generarPDFConCodigoBarras(String numeroParte) {
    // Obtener los datos de la base de datos
    List<String[]> datos = obtenerDatosDeBaseDeDatos(numeroParte);

    if (datos.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No se encontraron datos para el número de parte: " + numeroParte);
        return;
    }

    // Generar un PDF por cada registro
    for (String[] registro : datos) {
        String folio = registro[6]; // Folio del registro
        String rutaPDF = RUTA_DATOS_FOLIOS + "\\" + folio + ".pdf"; // Ruta del PDF

        // Generar el código de barras
        String rutaCodigoBarras = RUTA_CODIGOS_BARRAS + "\\" + folio + "_barcode.png";
        try {
            generateBarcode(folio, rutaCodigoBarras);
        } catch (WriterException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el código de barras: " + e.getMessage());
            continue;
        }

        // Generar el PDF
        try {
            generarPDF(registro, rutaCodigoBarras, rutaPDF);
        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }

    JOptionPane.showMessageDialog(null, "Archivos PDF generados en: " + RUTA_DATOS_FOLIOS);
}

    /**
     * Método para generar un código de barras y guardarlo como imagen.
     *
     * @param barcodeText Texto para generar el código de barras.
     * @param filePath    Ruta donde se guardará la imagen del código de barras.
     * @throws WriterException Si hay un error al generar el código de barras.
     * @throws IOException     Si hay un error al guardar la imagen.
     */
    private static void generateBarcode(String barcodeText, String filePath) throws WriterException, IOException {
        Code128Writer barcodeWriter = new Code128Writer();
        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.CODE_128, 200, 100);

        Path path = Paths.get(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    /**
     * Método para generar un PDF con los datos y el código de barras.
     *
     * @param registro          Datos del registro.
     * @param rutaCodigoBarras  Ruta de la imagen del código de barras.
     * @param rutaPDF           Ruta donde se guardará el PDF.
     * @throws DocumentException Si hay un error al crear el PDF.
     * @throws IOException       Si hay un error al leer la imagen del código de barras.
     */
private static void generarPDF(String[] registro, String rutaCodigoBarras, String rutaPDF) throws DocumentException, IOException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(rutaPDF));
    document.open();

    // Agregar los datos al PDF
    document.add(new Paragraph("Número de Parte: " + registro[0]));
    document.add(new Paragraph("Nombre: " + registro[1]));
    document.add(new Paragraph("Espesor: " + registro[2]));
    document.add(new Paragraph("Ancho: " + registro[3]));
    document.add(new Paragraph("Peso Bobina: " + registro[4]));
    document.add(new Paragraph("Peso Blank: " + registro[5]));
    document.add(new Paragraph("Total Piezas: " + registro[8])); // Agregar TotalPiezas
    document.add(new Paragraph("Folio: " + registro[6]));
    document.add(new Paragraph("Fecha de Llegada: " + registro[7]));

    // Agregar la imagen del código de barras al PDF
    Image imagenCodigoBarras = Image.getInstance(rutaCodigoBarras);
    document.add(imagenCodigoBarras);

    document.close();
}

    /**
     * Método para obtener los datos de la base de datos.
     *
     * @param numeroParte El número de parte a buscar.
     * @return Una lista con los datos obtenidos.
     */
private static List<String[]> obtenerDatosDeBaseDeDatos(String numeroParte) {
    List<String[]> datos = new ArrayList<>();

    // Consulta SQL para obtener los datos
    String sql = "SELECT R.Numero_Parte, R.Nombre, R.Espesor, R.Ancho, A.Peso_Bobina, R.Peso_Blank, A.Folio, A.Fecha, A.TotalPiezas " +
                 "FROM Registros R JOIN Almacen_Inv A ON R.Numero_Parte = A.Numero_Parte " +
                 "WHERE R.Numero_Parte = ?";

    try (Connection conn = MyConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, numeroParte);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String[] registro = {
                rs.getString("Numero_Parte"),
                rs.getString("Nombre"),
                rs.getString("Espesor"),
                rs.getString("Ancho"),
                rs.getString("Peso_Bobina"),
                rs.getString("Peso_Blank"),
                rs.getString("Folio"),
                rs.getString("Fecha"),
                rs.getString("TotalPiezas") // Agregar TotalPiezas al arreglo
            };
            datos.add(registro);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al consultar la base de datos: " + ex.getMessage());
    }
    return datos;
}
}