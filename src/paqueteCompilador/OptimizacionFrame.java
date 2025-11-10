package paqueteCompilador;

import javax.swing.*;
import java.awt.*;

public class OptimizacionFrame extends JFrame {

    private JTextArea areaCodigo;
    private JLabel lblTiempo, lblTamanos;

    public OptimizacionFrame(String codigoOptimizado, long tiempo, long tamOriginal, long tamOptimizado) {
        setTitle("Resultados de Optimización");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        areaCodigo = new JTextArea(codigoOptimizado);
        areaCodigo.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaCodigo.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaCodigo);

        lblTiempo = new JLabel("⏱ Tiempo de optimización: " + tiempo + " ms");
        lblTamanos = new JLabel("📦 Tamaño original: " + tamOriginal + " bytes | Optimizado: " + tamOptimizado + " bytes");

        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.add(lblTiempo);
        panelInfo.add(lblTamanos);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(panelInfo, BorderLayout.SOUTH);
    }
}
