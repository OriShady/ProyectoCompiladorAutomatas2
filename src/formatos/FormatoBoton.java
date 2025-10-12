package formatos;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class FormatoBoton {

    /**
     * Personaliza un JButton con bordes redondeados y color.
     *
     * @param boton JButton a personalizar.
     * @param color Color de fondo del botón.
     */
    public static void personalizarBoton( Color color,JButton... botones) {
        for (JButton boton : botones) {
            boton.setForeground(Color.WHITE); // Texto blanco
            boton.setFont(new Font("Arial", Font.BOLD, 14)); // Fuente personalizada
            boton.setBorder(new BordeRedondeado(20)); // Bordes redondeados con radio de 20
            boton.setFocusPainted(false); // Quitar borde de enfoque
            boton.setContentAreaFilled(false); // Desactivar área de contenido predeterminada
            boton.setOpaque(false); // Hacer el botón transparente
            boton.setUI(new BotonConFondo(color)); // Asignar la UI personalizada

        }
    }

    /**
     * Borde redondeado personalizado para JButton.
     */
    static class BordeRedondeado extends AbstractBorder {

        private final int radio;

        public BordeRedondeado(int radio) {
            this.radio = radio;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY); // Color del borde
            g2.drawRoundRect(x, y, width - 1, height - 1, radio, radio);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 10, 5, 10);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = 10;
            return insets;
        }
    }

    /**
     * UI personalizada para dibujar el fondo redondeado de un botón.
     */
    static class BotonConFondo extends javax.swing.plaf.basic.BasicButtonUI {

        private final Color fondo;

        public BotonConFondo(Color fondo) {
            this.fondo = fondo;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Dibujar fondo redondeado
            g2.setColor(fondo);
            g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);

            // Dibujar texto y otros componentes del botón
            super.paint(g2, c);
            g2.dispose();
        }
    }
}
