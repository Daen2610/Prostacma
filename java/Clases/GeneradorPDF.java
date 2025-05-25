/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.oned.Code128Writer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class GeneradorPDF {
    private static final float CM_A_PT = 28.35f;
    private static final Font FONT_NORMAL = new Font(Font.FontFamily.HELVETICA, 8);
    private static final Font FONT_BOLD = new Font(Font.FontFamily.HELVETICA, 9, Font.BOLD);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void generarEtiquetaCustom(String numeroParte, String nombre, String snp,
                                          Date fecha, String tarjetaViajera, int cantidad) {
        try {
            // Validación de parámetros
            if (numeroParte == null || nombre == null || snp == null || 
                fecha == null || tarjetaViajera == null) {
                throw new IllegalArgumentException("Ningún parámetro puede ser nulo");
            }

            // Configuración de dimensiones
            float margen = 0.5f * CM_A_PT;      // Margen aumentado a 5mm
            float separacion = 0.3f * CM_A_PT;   // 3mm entre etiquetas
            
            // Tamaño de etiqueta (ajustado para 2 columnas)
            float etiquetaAncho = (15f * CM_A_PT - 2*margen - separacion) / 2;
            float etiquetaAlto = 4.7f * CM_A_PT; // 4.7cm de alto

            // Tamaño de página (15cm × 21.7cm)
            Rectangle pageSize = new Rectangle(15f * CM_A_PT, 21.7f * CM_A_PT);

            // Configuración del documento PDF
            Document document = new Document(pageSize, 0, 0, 0, 0);
            String carpeta = System.getProperty("user.home") + File.separator + "Documents" + 
                           File.separator + "EtiquetasCustom";
            new File(carpeta).mkdirs();
            File pdfFile = new File(carpeta + File.separator + "etiquetas_" + 
                                  System.currentTimeMillis() + ".pdf");

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();

            // Configuración de disposición (2 columnas × 3 filas)
            int columnas = 2;
            int filas = 3;
            int total = cantidad;
            int index = 0;

            while (index < total) {
                for (int fila = 0; fila < filas && index < total; fila++) {
                    for (int col = 0; col < columnas && index < total; col++) {
                        float x = margen + col * (etiquetaAncho + separacion);
                        float y = pageSize.getHeight() - margen - etiquetaAlto - 
                                 fila * (etiquetaAlto + separacion);

                        // Crear tabla de 2 columnas para la etiqueta
                        PdfPTable tablaEtiqueta = new PdfPTable(2);
                        tablaEtiqueta.setTotalWidth(etiquetaAncho);
                        float[] anchos = {etiquetaAncho*0.5f, etiquetaAncho*0.5f}; // 60% texto, 40% código
                        tablaEtiqueta.setWidths(anchos);

                        // Celda de texto (izquierda)
                        PdfPCell celdaTexto = new PdfPCell();
                        celdaTexto.setBorder(Rectangle.BOX);
                        celdaTexto.setPadding(5);
                        celdaTexto.setFixedHeight(etiquetaAlto);

                        Phrase contenido = new Phrase();
                        contenido.add(new Chunk("ETIQUETA DE PRODUCTO\n", FONT_BOLD));
                        contenido.add(new Chunk("\nN° Parte: " + numeroParte + "\n", FONT_NORMAL));
                        contenido.add(new Chunk("Descripción: " + nombre + "\n", FONT_NORMAL));
                        contenido.add(new Chunk("SNP: " + snp + "\n", FONT_NORMAL));
                        contenido.add(new Chunk("Fecha: " + DATE_FORMAT.format(fecha) + "\n", FONT_NORMAL));
                        
                        celdaTexto.addElement(contenido);
                        tablaEtiqueta.addCell(celdaTexto);

                        // Celda de código de barras (derecha)
                        PdfPCell celdaCodigo = new PdfPCell();
                        celdaCodigo.setBorder(Rectangle.BOX);
                        celdaCodigo.setPadding(5);
                        celdaCodigo.setFixedHeight(etiquetaAlto);
                        celdaCodigo.setVerticalAlignment(Element.ALIGN_MIDDLE);

                        // Generar código de barras
                        BufferedImage barcodeImage = generarCodigoBarras(tarjetaViajera, 
                                (int)(etiquetaAncho*0.35f), (int)(etiquetaAlto*0.6f));
                        Image pdfBarcode = Image.getInstance(barcodeImage, null);
                        pdfBarcode.setAlignment(Image.ALIGN_CENTER);
                        celdaCodigo.addElement(pdfBarcode);

                        tablaEtiqueta.addCell(celdaCodigo);
                        tablaEtiqueta.writeSelectedRows(0, -1, x, y, writer.getDirectContent());
                        index++;
                    }
                }
                if (index < total) document.newPage();
            }

            document.close();

            // Mostrar mensaje y abrir PDF
            JOptionPane.showMessageDialog(null,
                    "Se generaron " + cantidad + " etiquetas en:\n" + pdfFile.getAbsolutePath());

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }

        } catch (WriterException | DocumentException | HeadlessException | IOException | IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, 
                    "Error al generar etiquetas: " + e.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static BufferedImage generarCodigoBarras(String texto, int width, int height) throws WriterException {
        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(texto, BarcodeFormat.CODE_128, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
