/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
/**
 *
 * @author familiar
 */
public class DisenoTabla {
     public static void aplicarDisenoTabla(JTable tabla) {
        // Personalizar la cabecera
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(220, 220, 220));
        header.setFont(new Font("SansSerif", Font.BOLD, 12));

        // Renderer personalizado
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : table.getBackground());
                }

                String columnName = table.getColumnName(column);

                // Coloreado para la columna "Aceptado"
                if ("Aceptado".equals(columnName)) {
                    if ("Sí".equalsIgnoreCase(value.toString())) {
                        c.setBackground(new Color(144, 238, 144)); // Verde claro
                    } else if ("No".equalsIgnoreCase(value.toString())) {
                        c.setBackground(new Color(255, 182, 193)); // Rosa claro
                    }
                }
                // Coloreado para las columnas "Completadas" y "Piezas Completadas" (texto verde)
                else if ("Completadas".equals(columnName) || "Piezas Completadas".equals(columnName)) {
                    c.setForeground(new Color(0, 128, 0)); // Verde oscuro
                }
                // Coloreado para las columnas "NG" y "Piezas NG" (texto rojo)
                else if ("NG".equals(columnName) || "Piezas NG".equals(columnName)) {
                    c.setForeground(Color.RED);
                }
                // Coloreado para la columna "Estado" (fondo verde o amarillo)
                else if ("Estado".equals(columnName)) {
                    if ("Finalizado".equalsIgnoreCase(value.toString())) {
                        c.setBackground(new Color(144, 238, 144)); // Verde claro
                    } else if ("En proceso".equalsIgnoreCase(value.toString())) {
                        c.setBackground(new Color(255, 255, 0));   // Amarillo
                    }
                } else {
                    c.setForeground(table.getForeground());
                    if (!isSelected) {
                        c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : table.getBackground());
                    }
                }
                return c;
            }
        };

        // Aplica el renderer como el renderer PREDETERMINADO
        tabla.setDefaultRenderer(Object.class, renderer);

        // Color de selección personalizado
        tabla.setSelectionBackground(new Color(200, 220, 255));
        tabla.setSelectionForeground(Color.BLACK);

        // Mostrar líneas de la grilla
        tabla.setShowGrid(true);
        tabla.setGridColor(Color.LIGHT_GRAY);

        // Ajustar el modo de redimensionamiento automático
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    // Nuevo método para aplicar un diseño más moderno a los componentes
    public static void aplicarDisenoModerno(JComponent componente) {
        Font fuenteEtiqueta = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteTexto = new Font("Segoe UI", Font.PLAIN, 12);
        Color colorTexto = new Color(51, 51, 51); // Gris oscuro
        Color colorFondoEtiqueta = new Color(230, 230, 230); // Gris claro
        Color colorBotonFondo = new Color(51, 122, 183); // Azul
        Color colorBotonTexto = Color.WHITE;
        javax.swing.border.Border bordeSuave = BorderFactory.createEmptyBorder(8, 15, 8, 15); // Padding suave
        javax.swing.border.Border bordeBoton = BorderFactory.createLineBorder(colorBotonFondo, 2);
        javax.swing.border.Border bordeTexto = BorderFactory.createLineBorder(new Color(204, 204, 204));
        javax.swing.border.Border bordeCompuestoTexto = BorderFactory.createCompoundBorder(bordeTexto, BorderFactory.createEmptyBorder(5, 8, 5, 8));


        componente.setForeground(colorTexto);
        componente.setFont(fuenteTexto);

        if (componente instanceof JLabel) {
            componente.setFont(fuenteEtiqueta);
            componente.setBackground(colorFondoEtiqueta);
            ((JLabel) componente).setOpaque(true);
        } else if (componente instanceof JTextField) {
            componente.setBorder(bordeCompuestoTexto);
            ((JTextField) componente).setCaretColor(colorTexto);
            ((JTextField) componente).setSelectionColor(new Color(153, 204, 255));
        }
    }

    // Método específico para colorear el fondo de los JTextField de Piezas Realizadas y Piezas NG
    public static void colorearCamposPiezas(JTextField txtPiezasRealizadas, JTextField txtPiezasNG) {
        txtPiezasRealizadas.setBackground(new Color(220, 255, 220)); // Verde muy claro
        txtPiezasNG.setBackground(new Color(255, 220, 220));       // Rojo muy claro
    }

    // Método específico para colorear el fondo del JTextField de Estado
    public static void colorearCampoEstado(JTextField txtEstado) {
        String estado = txtEstado.getText().trim();
        txtEstado.setOpaque(true); // Asegurar que el color de fondo se muestre
        if ("En proceso".equalsIgnoreCase(estado)) {
            txtEstado.setBackground(new Color(255, 255, 180)); // Amarillo muy claro
        } else if ("Completo".equalsIgnoreCase(estado) || "Finalizado".equalsIgnoreCase(estado)) {
            txtEstado.setBackground(new Color(200, 255, 200)); // Verde muy claro
        } else {
            txtEstado.setBackground(new Color(245, 245, 245)); // Gris muy claro por defecto
        }
    }
}

