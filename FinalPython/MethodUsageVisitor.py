from PythonParserVisitor import PythonParserVisitor

class SymbolTableEntry:
    def __init__(self, name, defined_at):
        # Almacena la información de cada función/método
        self.name = name  # Nombre de la función/método
        self.defined_at = defined_at  # Línea donde se definió
        self.calls = 0  # Número de veces que fue llamada
        self.called_at = []  # Líneas donde fue llamada

class MethodUsageVisitor(PythonParserVisitor):
    def __init__(self):
        # Tabla de símbolos: nombre -> SymbolTableEntry
        self.symbol_table = {}
        self.llamadas_fuera_de_clase = set()  # (Reservado para futuras mejoras)

    def visitFuncdef(self, ctx):
        """
        Intercepta la definición de funciones/métodos.
        Agrega la función a la tabla de símbolos con su línea de definición.
        """
        nombre = ctx.NAME().getText()
        line = ctx.start.line if hasattr(ctx, 'start') else None
        if nombre not in self.symbol_table:
            self.symbol_table[nombre] = SymbolTableEntry(nombre, line)
        return self.visitChildren(ctx)

    def visitPower(self, ctx):
        """
        Intercepta llamadas a funciones/métodos.
        Si el atom es una función definida, incrementa el contador de llamadas y almacena la línea.
        Si no estaba en la tabla de símbolos, la agrega como llamada sin definición.
        """
        atom = ctx.atom()
        trailers = ctx.trailer()
        if atom and trailers:
            for t in trailers:
                if hasattr(t, 'LPAR') and t.LPAR():  # Detecta llamada tipo foo() o self.foo()
                    nombre = atom.getText()
                    if nombre in self.symbol_table:
                        self.symbol_table[nombre].calls += 1
                        if hasattr(ctx, 'start'):
                            self.symbol_table[nombre].called_at.append(ctx.start.line)
                    else:
                        self.symbol_table[nombre] = SymbolTableEntry(nombre, None)
                        self.symbol_table[nombre].calls = 1
                        if hasattr(ctx, 'start'):
                            self.symbol_table[nombre].called_at.append(ctx.start.line)
        return self.visitChildren(ctx)
