from PythonParserVisitor import PythonParserVisitor

class MethodUsageVisitor(PythonParserVisitor):
    def __init__(self):
        self.funciones_definidas = set()
        self.funciones_llamadas = {}
        self.llamadas_fuera_de_clase = set()  # para marcar funciones llamadas desde el nivel global

    def visitFuncdef(self, ctx):
        nombre = ctx.NAME().getText()
        self.funciones_definidas.add(nombre)
        self.funciones_llamadas[nombre] = 0
        return self.visitChildren(ctx)

    def visitPower(self, ctx):
        atom = ctx.atom()
        trailers = ctx.trailer()

        if atom and trailers:
            last = trailers[-1]
            if hasattr(last, 'LPAR') and last.LPAR():
                nombre = None

                if len(trailers) >= 2 and hasattr(trailers[-2], 'NAME'):
                    nombre = trailers[-2].NAME().getText()  # obj.metodo()
                else:
                    nombre = atom.getText()  # llamada directa: metodo()

                # detecta si la llamada ocurrió desde fuera de clase
                if nombre in self.funciones_llamadas:
                    self.funciones_llamadas[nombre] += 1

                    padre = ctx.parentCtx
                    dentro_de_clase = False
                    while padre:
                        if hasattr(padre, 'classdef'):
                            dentro_de_clase = True
                            break
                        padre = padre.parentCtx

                    if not dentro_de_clase:
                        self.llamadas_fuera_de_clase.add(nombre)

        return self.visitChildren(ctx)
