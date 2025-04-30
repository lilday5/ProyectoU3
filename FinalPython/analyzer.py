from antlr4 import *
from PythonLexer import PythonLexer
from PythonParser import PythonParser
from MethodUsageVisitor import MethodUsageVisitor

def analizar_codigo_fuente(codigo):
    input_stream = InputStream(codigo)
    lexer = PythonLexer(input_stream)
    tokens = CommonTokenStream(lexer)
    parser = PythonParser(tokens)
    tree = parser.file_input()

    visitor = MethodUsageVisitor()
    visitor.visit(tree)

    # Excluir funciones llamadas desde fuera de clase (nivel global)
    for f in visitor.llamadas_fuera_de_clase:
        visitor.funciones_llamadas[f] = max(visitor.funciones_llamadas.get(f, 0), 2)

    resultado = "=== Funciones llamadas 0 veces ===\n"
    for f in sorted(visitor.funciones_definidas):
        if visitor.funciones_llamadas.get(f, 0) == 0:
            resultado += f"- {f}\n"

    resultado += "\n=== Funciones llamadas 1 vez ===\n"
    for f in sorted(visitor.funciones_definidas):
        if visitor.funciones_llamadas.get(f, 0) == 1:
            resultado += f"- {f}\n"

    return resultado.strip(), visitor.funciones_definidas, visitor.funciones_llamadas
