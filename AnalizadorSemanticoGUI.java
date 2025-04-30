import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.antlr.runtime.*;

public class AnalizadorSemanticoGUI extends JFrame {
    private JTextArea taEntrada;
    private JTextArea taSalida;
    private JButton btnAnalizar, btnCargar, btnLimpiar;
    private LineNumberComponent lineNumbers;

    public AnalizadorSemanticoGUI() {
        setTitle("Analizador Semántico");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel de entrada con números de línea
        taEntrada = new JTextArea();
        taEntrada.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Componente para mostrar números de línea
        lineNumbers = new LineNumberComponent(taEntrada);
        
        // Panel para los números de línea + editor
        JPanel editorPanel = new JPanel(new BorderLayout());
        editorPanel.add(lineNumbers, BorderLayout.WEST);
        editorPanel.add(new JScrollPane(taEntrada), BorderLayout.CENTER);
        
        // Panel de salida 
        taSalida = new JTextArea();
        taSalida.setEditable(false);
        taSalida.setBackground(new Color(250, 250, 250));
        taSalida.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Botones
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
                taSalida.setText("");
            }
        });

        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                  editorPanel, new JScrollPane(taSalida));
        splitPane.setDividerLocation(300);
        splitPane.setOneTouchExpandable(true);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnCargar);
        panelBotones.add(btnLimpiar);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(splitPane, BorderLayout.CENTER);

        add(panel);
        
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            taSalida.append("Error de reconocimiento: " + ex.getMessage() + "\n");
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
                taSalida.setText(""); 
            } catch (IOException ex) {
                taSalida.append("Error al leer el archivo: " + ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AnalizadorSemanticoGUI().setVisible(true);
        });
    }
    
    private class LineNumberComponent extends JPanel {
        private final JTextArea textArea;
        private final FontMetrics fontMetrics;
        private final int charHeight;
        private final int charWidth;
        
        public LineNumberComponent(JTextArea textArea) {
            this.textArea = textArea;
            setBackground(new Color(235, 235, 235));
            setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY));
            
            fontMetrics = getFontMetrics(textArea.getFont());
            charHeight = fontMetrics.getHeight();
            charWidth = fontMetrics.charWidth('0');
            
            textArea.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) { repaint(); }
                @Override
                public void removeUpdate(DocumentEvent e) { repaint(); }
                @Override
                public void changedUpdate(DocumentEvent e) { repaint(); }
            });
            
            textArea.addCaretListener(e -> repaint());
        }
        
        @Override
        public Dimension getPreferredSize() {
            int lineCount = textArea.getLineCount();
            int digits = Math.max(2, String.valueOf(lineCount).length());
            int width = 2 * charWidth * digits;
            return new Dimension(width, textArea.getHeight());
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setFont(textArea.getFont());
            g.setColor(Color.GRAY);
            
            Rectangle clip = g.getClipBounds();
            int lineHeight = fontMetrics.getHeight();
            int startLine = Math.max(0, clip.y / lineHeight);
            int endLine = Math.min(textArea.getLineCount(), (clip.y + clip.height) / lineHeight + 1);
            
            int width = getWidth();
            for (int i = startLine; i < endLine; i++) {
                String lineNum = String.valueOf(i + 1);
                int y = i * lineHeight + fontMetrics.getAscent();
                int x = width - fontMetrics.stringWidth(lineNum) - 5;
                g.drawString(lineNum, x, y);
            }
        }
    }
}