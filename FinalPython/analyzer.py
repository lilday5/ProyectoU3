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

    resultado = "=== Reporte de uso de funciones/métodos ===\n"
    funciones_detectadas = set()
    funciones_llamadas = {}
    for entry in visitor.symbol_table.values():
        funciones_detectadas.add(entry.name)
        funciones_llamadas[entry.name] = entry.calls
        resultado += f"- {entry.name} (definida en línea {entry.defined_at}): {entry.calls} llamadas\n"
        if entry.calls == 0:
            resultado += "  -> SUGERENCIA: Esta función/método NO es llamada. Puede optimizarse/eliminarse.\n"
        elif entry.calls == 1:
            resultado += "  -> SUGERENCIA: Solo se llama una vez. Puede optimizarse.\n"
    return resultado.strip(), funciones_detectadas, funciones_llamadas
