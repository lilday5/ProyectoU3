import tkinter as tk
from tkinter import scrolledtext, messagebox, filedialog
from analyzer import analizar_codigo_fuente

# === Variables globales para almacenar funciones detectadas y llamadas ===
funciones_detectadas = set()
funciones_llamadas = {}

# === Colores y estilos para la interfaz ===
COLOR_SUGERENCIA = '#ffb347'  # Naranja suave para sugerencias
COLOR_NORMAL = '#e0e0e0'      # Gris claro para texto normal
COLOR_FONDO = '#23272e'       # Fondo oscuro de la app
COLOR_TEXTO = '#e0e0e0'       # Texto claro para mejor contraste


def analizar():
    """
    Ejecuta el análisis semántico del código fuente ingresado.
    Muestra el reporte en el área de salida y resalta sugerencias.
    Maneja errores de análisis mostrando mensajes amigables.
    """
    global funciones_detectadas, funciones_llamadas
    codigo = entrada_texto.get("1.0", tk.END)
    try:
        resultado, funciones_detectadas, funciones_llamadas = analizar_codigo_fuente(codigo)
        salida_texto.config(state="normal")
        salida_texto.delete("1.0", tk.END)
        # Insertar línea por línea y resaltar sugerencias
        for line in resultado.split('\n'):
            tag = None
            if 'SUGERENCIA' in line:
                tag = 'sugerencia'
            salida_texto.insert(tk.END, line + '\n', tag)
        salida_texto.config(state="disabled")
    except Exception as e:
        salida_texto.config(state="normal")
        salida_texto.delete("1.0", tk.END)
        salida_texto.insert(tk.END, f"[ERROR] {str(e)}")
        salida_texto.config(state="disabled")


def eliminar_funciones():
    """
    Elimina del área de entrada las funciones/métodos que no son llamadas o solo se llaman una vez,
    así como sus llamadas desde el código fuente. Esto ayuda a optimizar el código.
    """
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
                skip = False
        # Eliminar llamadas a funciones eliminadas (ej: self.metodo())
        if any(f"self.{nombre}()" in stripped for nombre in funciones_a_eliminar):
            continue
        nuevas_lineas.append(line)
    entrada_texto.delete("1.0", tk.END)
    entrada_texto.insert(tk.END, "\n".join(nuevas_lineas))


def exportar_reporte():
    """
    Permite al usuario exportar el reporte generado en el área de salida a un archivo .txt.
    """
    contenido = salida_texto.get("1.0", tk.END)
    if not contenido.strip():
        messagebox.showinfo("Exportar reporte", "No hay reporte para exportar.")
        return
    archivo = filedialog.asksaveasfilename(defaultextension=".txt", filetypes=[("Archivos de texto", "*.txt")])
    if archivo:
        with open(archivo, "w", encoding="utf-8") as f:
            f.write(contenido)
        messagebox.showinfo("Exportar reporte", f"Reporte guardado en:\n{archivo}")

# === Configuración de la ventana principal ===
ventana = tk.Tk()
ventana.title("Detector de funciones optimizables (Python ANTLR)")
ventana.geometry("900x650")
ventana.configure(bg=COLOR_FONDO)

# === Área de entrada de código fuente ===
entrada_texto = scrolledtext.ScrolledText(ventana, height=18, bg=COLOR_FONDO, fg=COLOR_TEXTO, insertbackground=COLOR_TEXTO, font=("Consolas", 12))
entrada_texto.pack(fill="both", expand=True, padx=10, pady=(10,0))

# === Botones de acción ===
frame_botones = tk.Frame(ventana, bg=COLOR_FONDO)
frame_botones.pack(fill="x", padx=10, pady=5)

boton_analizar = tk.Button(frame_botones, text="Analizar", command=analizar, bg="#3a7ca5", fg="white", font=("Arial", 11, "bold"))
boton_analizar.pack(side="left", padx=5)

boton_borrar = tk.Button(frame_botones, text="Eliminar funciones optimizables", command=eliminar_funciones, bg="#ff6363", fg="white", font=("Arial", 11, "bold"))
boton_borrar.pack(side="left", padx=5)

boton_exportar = tk.Button(frame_botones, text="Exportar reporte", command=exportar_reporte, bg="#2ecc71", fg="white", font=("Arial", 11, "bold"))
boton_exportar.pack(side="right", padx=5)

# === Área de salida del reporte ===
salida_texto = scrolledtext.ScrolledText(ventana, height=12, state="normal", bg=COLOR_FONDO, fg=COLOR_TEXTO, font=("Consolas", 12))
salida_texto.pack(fill="both", expand=True, padx=10, pady=(0,10))
salida_texto.tag_configure('sugerencia', foreground=COLOR_SUGERENCIA, font=("Consolas", 12, "bold"))
salida_texto.config(state="disabled")

ventana.mainloop()
