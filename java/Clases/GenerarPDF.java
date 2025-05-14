/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JTable;
/**
 *
 * @author familiar
 */
public class GenerarPDF {
public static void generarPDFDesdeTablas(JTable tabla1, String tituloTabla1, JTable tabla2, String tituloTabla2, String rutaArchivo) {
        Document documento = new Document();

        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            PdfWriter.getInstance(documento, fos);
            documento.open();

            // Fuente personalizada para los títulos
            Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.BOLD, BaseColor.RED);
            Font fuenteSubtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, Font.BOLD, BaseColor.BLUE);

            // Título principal
            Paragraph tituloPrincipal = new Paragraph("Reporte de Almacén y Retiros", fuenteTitulo);
            tituloPrincipal.setAlignment(Element.ALIGN_CENTER);
            tituloPrincipal.setSpacingAfter(20);
            documento.add(tituloPrincipal);

            // Agregar la primera tabla
            agregarTablaAlPDF(documento, tabla1, tituloTabla1, fuenteSubtitulo);

            // Espacio entre tablas
            documento.add(new Paragraph(" "));

            // Agregar la segunda tabla
            agregarTablaAlPDF(documento, tabla2, tituloTabla2, fuenteSubtitulo);

            documento.close();
            System.out.println("PDF generado correctamente en: " + rutaArchivo);
        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar el PDF: " + e.getMessage());
        }
    }

    private static void agregarTablaAlPDF(Document documento, JTable tabla, String tituloTabla, Font fuenteSubtitulo) throws DocumentException {
        Paragraph titulo = new Paragraph(tituloTabla, fuenteSubtitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(10);
        documento.add(titulo);

        PdfPTable pdfTable = new PdfPTable(tabla.getColumnCount());
        pdfTable.setWidthPercentage(100);
        pdfTable.setSpacingBefore(10);
        pdfTable.setSpacingAfter(10);

        Font fuenteCeldas = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

        // Agregar nombres de columnas
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            PdfPCell celda = new PdfPCell(new Phrase(tabla.getColumnName(i), fuenteCeldas));
            celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            celda.setPadding(5);
            pdfTable.addCell(celda);
        }

        // Agregar datos de la tabla
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
                Object valorCelda = tabla.getValueAt(fila, columna);
                String textoCelda = (valorCelda == null) ? "" : valorCelda.toString();
                PdfPCell celda = new PdfPCell(new Phrase(textoCelda, fuenteCeldas));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(5);
                pdfTable.addCell(celda);
            }
        }

        documento.add(pdfTable);
    }
    
}
