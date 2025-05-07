import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

public class MainAnalyzer {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Uso: java MainAnalyzer archivo.py");
            return;
        }
        CharStream input = CharStreams.fromFileName(args[0]);
        PythonLexer lexer = new PythonLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PythonParser parser = new PythonParser(tokens);
        parser.file_input(); // Esto ejecuta el análisis y el reporte se imprime por System.out
    }
}