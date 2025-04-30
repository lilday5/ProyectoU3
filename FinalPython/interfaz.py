import tkinter as tk
from tkinter import scrolledtext
from analyzer import analizar_codigo_fuente

funciones_detectadas = set()
funciones_llamadas = {}

def analizar():
    global funciones_detectadas, funciones_llamadas
    codigo = entrada_texto.get("1.0", tk.END)
    resultado, funciones_detectadas, funciones_llamadas = analizar_codigo_fuente(codigo)
    salida_texto.config(state="normal")
    salida_texto.delete("1.0", tk.END)
    salida_texto.insert(tk.END, resultado)
    salida_texto.config(state="disabled")

def eliminar_funciones():
    codigo = entrada_texto.get("1.0", tk.END).splitlines()
    nuevas_lineas = []
    skip = False
    indentacion_actual = None

    # construir conjunto de nombres a eliminar
    funciones_a_eliminar = [nombre for nombre in funciones_detectadas if funciones_llamadas[nombre] <= 1]

    for line in codigo:
        stripped = line.strip()

        # Eliminar definiciones de funciones optimizables
        if any(stripped.startswith(f"def {nombre}") for nombre in funciones_a_eliminar):
            skip = True
            indentacion_actual = len(line) - len(stripped)
            continue

        # Saltar líneas dentro del cuerpo de la función eliminada
        if skip:
            if line.strip() == "":
                continue
            elif (len(line) - len(line.lstrip())) > indentacion_actual:
                continue
            else:
                skip = False  # ya salimos del bloque de la función

        # Eliminar llamadas a funciones eliminadas (ej: self.metodo())
        if any(f"self.{nombre}()" in stripped for nombre in funciones_a_eliminar):
            continue  # omitir esta línea también

        nuevas_lineas.append(line)

    entrada_texto.delete("1.0", tk.END)
    entrada_texto.insert(tk.END, "\n".join(nuevas_lineas))


# Ventana
ventana = tk.Tk()
ventana.title("Detector de funciones optimizables (Python ANTLR)")
ventana.geometry("800x600")

# Entrada
entrada_texto = scrolledtext.ScrolledText(ventana, height=20)
entrada_texto.pack(fill="both", expand=True, padx=10, pady=10)

# Botones
boton_analizar = tk.Button(ventana, text="Analizar", command=analizar)
boton_analizar.pack(pady=5)

boton_borrar = tk.Button(ventana, text="Eliminar funciones optimizables", command=eliminar_funciones)
boton_borrar.pack(pady=5)

# Salida
salida_texto = scrolledtext.ScrolledText(ventana, height=10, state="disabled", bg="#0a0a0a")
salida_texto.pack(fill="both", expand=True, padx=10, pady=10)

ventana.mainloop()
