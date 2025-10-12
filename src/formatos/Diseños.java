/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package formatos;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Clase para personalizar tablas en Swing.
 */
public class Diseños {

    /**
     * Aplica el diseño personalizado a una o más tablas.
     *
     * @param tablas Uno o más JTable a los que se les aplicará el diseño.
     */
    public static void personalizarTabla(JTable... tablas) {
        for (JTable tabla : tablas) {
            // Configurar las líneas de la tabla
            configurarLineasDeTabla(tabla);

            // Personalizar el encabezado
            JTableHeader encabezado = tabla.getTableHeader();
            encabezado.setDefaultRenderer(new EncabezadoRenderer());

            // Ajustar la altura del encabezado
            encabezado.setPreferredSize(new Dimension(encabezado.getPreferredSize().width, 40)); // Cambia 40 por la altura deseada

            // Personalizar las celdas
            tabla.setDefaultRenderer(Object.class, new CeldaRenderer());
        }
    }

    /**
     * Configura las líneas de la tabla.
     *
     * @param tabla JTable a configurar.
     */
    private static void configurarLineasDeTabla(JTable tabla) {
        tabla.setShowGrid(true); // Mostrar líneas
        tabla.setGridColor(Color.LIGHT_GRAY); // Color de las líneas
    }
}

/**
 * Renderizador personalizado para el encabezado de la tabla.
 */
class EncabezadoRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(new Color(70, 130, 180)); // Azul oscuro
        c.setForeground(Color.WHITE); // Texto blanco
        setFont(new Font("Arial", Font.BOLD, 14)); // Fuente del encabezado
        setHorizontalAlignment(CENTER); // Centrar texto

        return c;
    }
}

/**
 * Renderizador personalizado para las celdas de la tabla.
 */
class CeldaRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected) {
            c.setBackground(new Color(0, 0, 0, 200)); // Negro semi-transparente
            c.setForeground(Color.WHITE); // Texto blanco
        } else {
            c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : Color.WHITE); // Alternar colores
            c.setForeground(Color.BLACK); // Texto negro
        }
        return c;
    }
}
