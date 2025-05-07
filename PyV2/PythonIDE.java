import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class PythonIDE extends JFrame {
    private JTextArea codeArea, consoleArea;

    public PythonIDE() {
        setTitle("Python Analyzer IDE");
        setSize(950, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Colores inspirados en Visual Studio Code
        Color vscodeBg = new Color(30, 34, 43);
        Color vscodeFg = new Color(212, 212, 212);
        Color vscodeCaret = new Color(130, 200, 255);
        Color vscodeConsoleBg = new Color(23, 26, 33);
        Color vscodeConsoleFg = new Color(180, 255, 180);
        Color vscodeButton = new Color(51, 153, 255);
        Color vscodeButton2 = new Color(60, 60, 60);
        Color vscodeButton3 = new Color(100, 40, 40);

        codeArea = new JTextArea();
        codeArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        codeArea.setBackground(vscodeBg);
        codeArea.setForeground(vscodeFg);
        codeArea.setCaretColor(vscodeCaret);
        codeArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70,70,90)), "Código Python", 0, 0, new Font(Font.SANS_SERIF, Font.BOLD, 14), vscodeFg));

        consoleArea = new JTextArea();
        consoleArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        consoleArea.setBackground(vscodeConsoleBg);
        consoleArea.setForeground(vscodeConsoleFg);
        consoleArea.setEditable(false);
        consoleArea.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(70,70,90)), "Consola de Análisis", 0, 0, new Font(Font.SANS_SERIF, Font.BOLD, 14), vscodeFg));

        JButton analyzeBtn = new JButton("Analizar");
        JButton loadBtn = new JButton("Cargar archivo");
        JButton clearBtn = new JButton("Limpiar");

        analyzeBtn.setBackground(vscodeButton);
        analyzeBtn.setForeground(Color.WHITE);
        analyzeBtn.setFocusPainted(false);
        loadBtn.setBackground(vscodeButton2);
        loadBtn.setForeground(Color.WHITE);
        loadBtn.setFocusPainted(false);
        clearBtn.setBackground(vscodeButton3);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);

        analyzeBtn.addActionListener(e -> analyzeCode());
        loadBtn.addActionListener(e -> loadFile());
        clearBtn.addActionListener(e -> {
            codeArea.setText("");
            consoleArea.setText("");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 8));
        buttonPanel.setBackground(new Color(40, 44, 60));
        buttonPanel.add(analyzeBtn);
        buttonPanel.add(loadBtn);
        buttonPanel.add(clearBtn);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(codeArea), new JScrollPane(consoleArea));
        splitPane.setDividerLocation(350);

        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }

    private void analyzeCode() {
        try {
            // Escribe el código a un archivo temporal con UTF-8
            File tempFile = File.createTempFile("input", ".py");
            try (Writer fw = new OutputStreamWriter(new FileOutputStream(tempFile), "UTF-8")) {
                fw.write(codeArea.getText());
            }
            // Ejecuta el análisis y captura la salida estándar en UTF-8
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                ".;antlr-4.13.2-complete.jar",
                "MainAnalyzer",
                tempFile.getAbsolutePath()
            );
            pb.redirectErrorStream(true);
            Process proc = pb.start();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(proc.getInputStream(), "UTF-8")
            );
            String line;
            consoleArea.setText("");
            while ((line = reader.readLine()) != null) {
                consoleArea.append(line + "\n");
            }
            tempFile.delete();
        } catch (Exception ex) {
            consoleArea.setText("Error: " + ex.getMessage());
        }
    }

    private void loadFile() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                codeArea.read(br, null);
            } catch (IOException ex) {
                consoleArea.setText("Error al cargar archivo: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PythonIDE().setVisible(true));
    }
}