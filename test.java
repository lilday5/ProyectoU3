import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.antlr.runtime.*;

public class test extends JFrame {
    private JTextArea taEntrada, taSalida;
    private JButton btnAnalizar, btnCargar, btnLimpiar;

    public test() {
        setTitle("Analizador Semántico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        taEntrada = new JTextArea(10, 50);
        taSalida = new JTextArea(10, 50);
        taSalida.setEditable(false);
        btnAnalizar = new JButton("Analizar Código");
        btnCargar = new JButton("Cargar Programa de Prueba");
        btnLimpiar = new JButton("Limpiar Entrada");

        btnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analizarCodigo();
            }
        });

        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taEntrada.setText("");
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(new JScrollPane(taEntrada), BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnLimpiar);

        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(new JScrollPane(taSalida), BorderLayout.SOUTH);

        add(panel);
    }

    private void analizarCodigo() {
        String codigo = taEntrada.getText();
        taSalida.setText("");

        try {
            ANTLRStringStream input = new ANTLRStringStream(codigo);
            aSemanticoLexer lexer = new aSemanticoLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            aSemanticoParser parser = new aSemanticoParser(tokens);
            parser.setSalida(taSalida);
            parser.program();
        } catch (RecognitionException ex) {
            taSalida.append("\033[31mError de reconocimiento: " + ex.getMessage() + "\033[0m\n");
        }
    }

    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos Java y de Texto", "java", "txt");
        fileChooser.setFileFilter(filter);
        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                taEntrada.read(br, null);
            } catch (IOException ex) {
                taSalida.append("\033[31mError al leer el archivo: " + ex.getMessage() + "\033[0m\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnalizadorSemanticoGUI().setVisible(true);
        });
    }
}

