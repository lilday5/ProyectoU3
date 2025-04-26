grammar aSemantico;

@header {
    import java.util.HashMap;
    import java.util.ArrayList;
    import javax.swing.JTextArea;
}

@members {
    HashMap TSG = new HashMap(); // Tabla de símbolos global (para clases, atributos, métodos)
    HashMap TSL = new HashMap(); // Tabla de símbolos local (para variables locales y argumentos)

    HashMap<String, Integer> methodCallCount = new HashMap<>(); // Para rastrear el número de llamadas a cada método

    // Tipos definidos
    static final int INT_TYPE = 1;
    static final int DOUBLE_TYPE = 2;
    static final int CLASS_TYPE = 3;
    static final int METHOD_TYPE = 4;
    static final int ERROR_TIPO = 5; // ← agregado para errores de tipos
    static final int ERROR_NO_DECLARADO = 6; // ← agregado para variables no declaradas

    ArrayList<String> methodsToDelete = new ArrayList<>();
    ArrayList<String> metodos = new ArrayList<>();
    ArrayList<String> atributos = new ArrayList<>();
    private JTextArea salida;

    public void setSalida(JTextArea salida) {
        this.salida = salida;
    }

    public void printError(String msg, Token token) {
        String errorLocation = "";
        if (token != null) {
            errorLocation = "[línea " + token.getLine() + ":" + token.getCharPositionInLine() + "] ";
        }
        if (salida != null) {
            salida.append(errorLocation + msg + "\n");
        } else {
            System.err.println("\033[31m" + errorLocation + msg + "\033[0m");
        }
    }

    public void printError(String msg) {
        printError(msg, null);
    }

    public void pushTSG(String id, String tipo, Token token){
        Integer se_encuentra = (Integer) TSG.get(id);
        if(se_encuentra == null){
            if(tipo.equals("int")) TSG.put(id, INT_TYPE);
            else if(tipo.equals("double")) TSG.put(id, DOUBLE_TYPE);
            else if(tipo.equals("class")) TSG.put(id, CLASS_TYPE);
            else if(tipo.equals("method")) TSG.put(id, METHOD_TYPE);

            if (tipo.equals("method")) {
                methodCallCount.put(id, 0); // Inicializar el contador de llamadas en 0
            }

        } else {
            printError("Error redeclarando: " + id, token);
        }
    }

    public void pushTSG(String id, String tipo){
        pushTSG(id, tipo, null);
    }
    public void pushTSL(String id, String tipo, Token token){
        Integer se_encuentra_local = (Integer) TSL.get(id);
        Integer se_encuentra_global = (Integer) TSG.get(id);

        if(se_encuentra_local == null){
            if(se_encuentra_global != null){
                 printError("Error redeclarando: " + id, token);
            }else {
                    if(tipo.equals("int")) TSL.put(id, INT_TYPE);
                    else if(tipo.equals("double")) TSL.put(id, DOUBLE_TYPE);
            }

        } else {
            printError("Error redeclarando: " + id, token);
        }
    }

    public void pushTSL(String id, String tipo){
        pushTSL(id, tipo, null);
    }

    public boolean findTSG(String id){
        return TSG.containsKey(id);
    }

    public boolean findTSGForAtom(String id){
        if(TSG.containsKey(id) && (((Integer)TSG.get(id) == INT_TYPE) || ((Integer)TSG.get(id) == DOUBLE_TYPE))){
            return true;
        }
        return false;
    }

    public boolean findTSL(String id){
        return TSL.containsKey(id);
    }

    public void clearTSL(){
        TSL.clear();
    }   

    // Metodos para la deteccion de metodos seguros para eliminar
    public void incrementMethodCallCount(String methodName) {
        Integer count = methodCallCount.get(methodName);
        if (count != null) {
            methodCallCount.put(methodName, count + 1);
        }
    }

    public void checkMethodsToDelete() {
        for (String methodName : methodCallCount.keySet()) {
            Integer callCount = methodCallCount.get(methodName);
            if (callCount == 0 || callCount == 1) {
                methodsToDelete.add(methodName);
            }
        }

         for (String methodName : metodos) {
                if(!methodCallCount.containsKey(methodName)){
                   methodsToDelete.add(methodName);
                }
            }
        generateOptimizationReport();
    } 
}

program: clase+;

clase:
    modificAcceso 'class' ID {
        if(findTSG($ID.text)) {
            printError("Error: " + $ID.text + " ya declarado", $ID);
        } else {
            pushTSG($ID.text, "class", $ID);
        }
    } '{' miembro* '}' {
        metodos.clear();
        atributos.clear();
        checkMethodsToDelete();
    };

miembro: metodo | atributo;

atributo: modificAcceso? tipo id1=ID {
        if(atributos.contains($id1.text)) {
            printError("Error redeclarando: " + $id1.text, $id1);
        } else {
            atributos.add($id1.text);
            pushTSG($id1.text, $tipo.text, $id1);
        }
    } (COMMA id2=ID {
        if(atributos.contains($id2.text)) {
            printError("Error redeclarando: " + $id2.text, $id2);
        } else {
            atributos.add($id2.text);
            pushTSG($id2.text, $tipo.text, $id2);
        }
    })* SEMICOLON;

metodo:
    modificAcceso? tipo ID {
        if(metodos.contains($ID.text)) {
            printError("Error: Método " + $ID.text + " ya declarado en esta clase", $ID);
        } else {
            metodos.add($ID.text);
            pushTSG($ID.text, "method", $ID);
        }   
    } '(' decl_arg? ')' '{' instruccion* '}' { clearTSL(); };

instruccion
    : asignacion
    | decl_local
    | llamadaMetodo
    | retorno
    ;

llamadaMetodo
    : ID '(' (expr (',' expr)*)? ')' SEMICOLON
        {
            // Validar existencia del método y tipos de argumentos
            if(!metodos.contains($ID.text)) {
                printError("Error: La función '" + $ID.text + "' no está declarada (línea " + $ID.getLine() + ", columna " + $ID.getCharPositionInLine() + ")", $ID);
            } else {
                incrementMethodCallCount($ID.text);
                printError("La función '" + $ID.text + "' fue llamada. Total llamadas: " + methodCallCount.get($ID.text), $ID);
            }
        }
    ;

retorno
    : RETURN expr? SEMICOLON
        {
            // Aquí podrías validar el tipo de retorno si lo deseas
        }
    ;

asignacion: ID '=' expr {
    // ← validación de existencia y tipo al asignar
    if(!findTSL($ID.text) && !findTSGForAtom($ID.text)) {
        printError("Error: " + $ID.text + " no declarado", $ID);
    } else {
        if(findTSL($ID.text)) {
            if((Integer)TSL.get($ID.text) == INT_TYPE){
                if($expr.tipo == INT_TYPE) {
                    // correcto
                } else {
                    printError("Error de tipos en asignación: " + $ID.text + " y " + $expr.tipo, $ID);
                }

            }
            
            if((Integer)TSL.get($ID.text) == DOUBLE_TYPE){
                if($expr.tipo == DOUBLE_TYPE || $expr.tipo == INT_TYPE){
                    // correcto
                }else{
                   printError("Error de tipos en asignación: " + $ID.text + " y " + $expr.tipo, $ID);
                }
            }
        } else {
              if((Integer)TSG.get($ID.text) == INT_TYPE){
                if($expr.tipo == INT_TYPE) {
                    // correcto
                } else {
                    printError("Error de tipos en asignación: " + $ID.text + " y " + $expr.tipo, $ID);
                }

            }
            if((Integer)TSG.get($ID.text) == DOUBLE_TYPE){
                if($expr.tipo == DOUBLE_TYPE || $expr.tipo == INT_TYPE){
                    // correcto
                }else{
                   printError("Error de tipos en asignación: " + $ID.text + " y " + $expr.tipo, $ID);
                }
            }
        }
    }
} SEMICOLON;

decl_arg:
    tipo1 = tipo id1 = ID {
        if(findTSL($id1.text)) {
            printError("Error redeclarando: " + $id1.text, $id1);
        } else {
            pushTSL($id1.text, $tipo1.text, $id1);
        }
    } (COMMA tipo2 = tipo id2 = ID {
        if(findTSL($id2.text)) {
            printError("Error redeclarando: " + $id2.text, $id2);
        } else {
            pushTSL($id2.text, $tipo2.text, $id2);
        }
    })*;

decl_local:
    tipo id1 = ID {
        if(findTSL($id1.text)) {
            printError("Error redeclarando: " + $id1.text, $id1);
        } else {
            pushTSL($id1.text, $tipo.text, $id1);
        }
    } (',' id2 = ID {
        if(findTSL($id2.text)) {
            printError("Error redeclarando: " + $id2.text, $id2);
        } else {
            pushTSL($id2.text, $tipo.text, $id2);
        }        
    })* SEMICOLON;

// ← expr, multExpr y atom devuelven el tipo de dato para validación semántica
expr returns [int tipo] :
    m1=multExpr {$tipo = $m1.tipo;} (('+' | '-') m2=multExpr {
        if($m1.tipo == $m2.tipo && $m1.tipo != ERROR_TIPO) {
            $tipo = $m1.tipo;
        }else if (($m1.tipo == DOUBLE_TYPE && $m2.tipo==INT_TYPE) || ($m1.tipo == INT_TYPE && $m2.tipo==DOUBLE_TYPE)) {
                 $tipo = DOUBLE_TYPE;
        } else {
            $tipo = ERROR_TIPO;
            printError("Error de tipos en suma: " + $m1.tipo + " y " + $m2.tipo, $m1.start);
        }
    })* ;

multExpr returns [int tipo] :
    a1=atom {$tipo = $a1.tipo;} ('*' a2=atom {
        if($a1.tipo == $a2.tipo && $a1.tipo != ERROR_TIPO && $a1.tipo != ERROR_NO_DECLARADO) {
            $tipo = $a1.tipo;
        }else if (($a1.tipo == DOUBLE_TYPE && $a2.tipo==INT_TYPE) || ($a1.tipo == INT_TYPE && $a2.tipo==DOUBLE_TYPE)) {
                 $tipo = DOUBLE_TYPE;
        } else {
            $tipo = ERROR_TIPO;
            printError("Error de tipos en multiplicación: " + $a1.tipo + " y " + $a2.tipo, $a1.start);
        }
    })* ;

atom returns [int tipo, Token start] :   
    CINT {$tipo = INT_TYPE; $start = $CINT;} |
    CDOUBLE {$tipo = DOUBLE_TYPE; $start = $CDOUBLE;} |
    ID  {
        $start = $ID;   

        Object value = TSG.get($ID.text); 
        if (Integer.valueOf(METHOD_TYPE).equals(value)) {
              incrementMethodCallCount($ID.text);
              System.out.println("Se llamo al metodo" + $ID.text);
        }
    

        if(findTSL($ID.text)) {
            $tipo = (Integer) TSL.get($ID.text);
        } else if(findTSGForAtom($ID.text)) {
            $tipo = (Integer) TSG.get($ID.text);
        } else {
            $tipo = ERROR_NO_DECLARADO;
            printError("Error: " + $ID.text + " no declarado", $ID);
        }
    } |
    '(' expr {$tipo = $expr.tipo; $start = $expr.start;} ')' ;

generateOptimizationReport: {
    try {
        if(methodsToDelete.isEmpty()) {
            printError("No se encontraron métodos para eliminar del programa.");
        } else {
            printError("Se pueden eliminar los siguientes métodos para optimizar el programa:");
            for(String methodName : methodsToDelete){
                int count = methodCallCount.containsKey(methodName) ? methodCallCount.get(methodName) : 0;
                printError(methodName + " (llamado " + count + " veces)");
            }
        }
        // Resumen de todas las llamadas a métodos
        printError("Resumen de llamadas a métodos:");
        for(String methodName : methodCallCount.keySet()){
            printError(methodName + ": " + methodCallCount.get(methodName) + " veces");
        }
    } catch (Exception e) {
        printError("Error al generar el reporte de optimización: " + e.getMessage());
    }
};

modificAcceso: PUBLIC | PRIVATE | PROTECTED;
tipo: INT | DOUBLE | VOID;

INT: 'int';
DOUBLE: 'double';
VOID: 'void';
PUBLIC: 'public';
PRIVATE: 'private';
PROTECTED: 'protected';
COMMA: ',';
SEMICOLON: ';';

CDOUBLE: CINT '.' CINT;
RETURN: 'return';
ID: ('a' ..'z' | 'A' ..'Z')+('0' ..'9')*;
CINT: ('0' ..'9')+;

WS: (' ' | '\n' | '\t' | '\r')+ {$channel=HIDDEN;};