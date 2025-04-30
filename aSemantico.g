grammar aSemantico;

@header {
    import java.util.HashMap;
    import java.util.ArrayList;
    import java.util.Map;
    import java.util.List;
    import java.util.TreeMap;
    import javax.swing.JTextArea;
}

@members {
    HashMap<String, Integer> TSG = new HashMap<>(); // Tabla de símbolos global (para clases, atributos, métodos)
    HashMap<String, Integer> TSL = new HashMap<>(); // Tabla de símbolos local (para variables locales y argumentos)

    HashMap<String, Integer> methodCallCount = new HashMap<>(); // Para rastrear el número de llamadas a cada método
    HashMap<String, Integer> methodReturnTypes = new HashMap<>(); // Para tipos de retorno
    HashMap<String, Integer> methodLineNumbers = new HashMap<>(); // Para almacenar líneas donde se declaran métodos

    // Tipos definidos
    static final int INT_TYPE = 1;
    static final int DOUBLE_TYPE = 2;
    static final int CLASS_TYPE = 3;
    static final int METHOD_TYPE = 4;
    static final int ERROR_TIPO = 5;
    static final int ERROR_NO_DECLARADO = 6;
    static final int VOID_TYPE = 7;

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
        
        // Mensaje formateado para UI
        String formattedMsg = errorLocation + msg;
        
        if (salida != null) {
            salida.append(formattedMsg + "\n");
        } else {
            System.err.println(formattedMsg);
        }
    }

    public void printInfo(String msg, Token token) {
        String infoLocation = "";
        if (token != null) {
            infoLocation = "[línea " + token.getLine() + ":" + token.getCharPositionInLine() + "] ";
        }
        
        if (salida != null) {
            salida.append(infoLocation + msg + "\n");
        } else {
            System.out.println(infoLocation + msg);
        }
    }
    
    public void printInfo(String msg) {
        printInfo(msg, null);
    }
    
    public void printWarning(String msg) {
        if (salida != null) {
            salida.append(msg + "\n");
        } else {
            System.out.println(msg);
        }
    }
    
    public void printHeader(String msg) {
        if (salida != null) {
            salida.append("\n" + msg + "\n");
            salida.append("-----------------------------------------------------\n");
        } else {
            System.out.println("\n" + msg);
            System.out.println("-----------------------------------------------------");
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
            else if(tipo.equals("void")) TSG.put(id, VOID_TYPE);

            if (tipo.equals("method")) {
                methodCallCount.put(id, 0); // Inicializar el contador de llamadas en 0
                if (token != null) {
                    methodLineNumbers.put(id, token.getLine()); // Guardar la línea donde se declara el método
                }
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
                    else if(tipo.equals("void")) TSL.put(id, VOID_TYPE);
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
        if(TSG.containsKey(id)) {
            Integer tipo = (Integer)TSG.get(id);
            return (tipo == INT_TYPE || tipo == DOUBLE_TYPE || tipo == METHOD_TYPE);
        }
        return false;
    }

    public boolean findTSL(String id){
        return TSL.containsKey(id);
    }

    public void clearTSL(){
        TSL.clear();
    }   

    public void incrementMethodCallCount(String methodName) {
        Integer count = methodCallCount.get(methodName);
        if (count != null) {
            methodCallCount.put(methodName, count + 1);
        } else {
            methodCallCount.put(methodName, 1);
        }
    }

    public void checkMethodsToDelete() {
        try {
            for (String methodName : methodCallCount.keySet()) {
                Integer callCount = methodCallCount.get(methodName);
                if (callCount == 0 || callCount == 1) {
                    methodsToDelete.add(methodName);
                }
            }

            // Verificar los métodos que están en la lista pero no en el contador
            for (String methodName : metodos) {
                if(!methodCallCount.containsKey(methodName)){
                    methodsToDelete.add(methodName);
                }
            }
            
            // Llamar directamente al método de generación de reporte
            generarReporteDeOptimizacion();
        } catch (Exception e) {
            printError("Error en checkMethodsToDelete: " + e.getMessage());
        }
    }
    
    // Método para generar el reporte de optimización
    public void generarReporteDeOptimizacion() {
        try {
            printHeader("INFORME DE OPTIMIZACIÓN");
            
            // Ordenar los métodos por número de llamadas (TreeMap)
            Map<String, Integer> sortedMethods = new TreeMap<>(methodCallCount);
            
            // Mostrar métodos que no se utilizan o se utilizan solo una vez
            if(methodsToDelete.isEmpty()) {
                printInfo("No se encontraron métodos para eliminar del programa.");
            } else {
                printWarning("Se pueden eliminar los siguientes métodos para optimizar el programa:");
                for(String methodName : methodsToDelete){
                    int count = methodCallCount.containsKey(methodName) ? methodCallCount.get(methodName) : 0;
                    int lineNum = methodLineNumbers.containsKey(methodName) ? methodLineNumbers.get(methodName) : 0;
                    
                    String lineInfo = lineNum > 0 ? " (línea " + lineNum + ")" : "";
                    printWarning("- " + methodName + lineInfo + ": llamado " + count + " veces");
                }
            }
            
            // Resumen de todas las llamadas a métodos
            printHeader("RESUMEN DE LLAMADAS A MÉTODOS");
            for(String methodName : sortedMethods.keySet()){
                int count = methodCallCount.get(methodName);
                if (count > 1) {
                    printInfo(methodName + ": " + count + " veces");
                } else if (count == 1) {
                    printWarning(methodName + ": " + count + " vez");
                } else {
                    printWarning(methodName + ": nunca llamado");
                }
            }
        } catch (Exception e) {
            printError("Error al generar el reporte de optimización: " + e.getMessage());
        }
    }
    
    // Verificar si un método existe
    public boolean methodExists(String methodName) {
        return metodos.contains(methodName);
    }
    
    // Registrar tipo de retorno
    public void registerMethodReturnType(String methodName, String returnType) {
        if(returnType.equals("int")) methodReturnTypes.put(methodName, INT_TYPE);
        else if(returnType.equals("double")) methodReturnTypes.put(methodName, DOUBLE_TYPE);
        else if(returnType.equals("void")) methodReturnTypes.put(methodName, VOID_TYPE);
        else methodReturnTypes.put(methodName, INT_TYPE); // Por defecto
    }
    
    // Obtener tipo de retorno
    public int getMethodReturnType(String methodName) {
        Integer returnType = methodReturnTypes.get(methodName);
        return (returnType != null) ? returnType : ERROR_NO_DECLARADO;
    }

    public String tipoToString(int tipo) {
        switch(tipo) {
            case INT_TYPE: return "int";
            case DOUBLE_TYPE: return "double";
            case VOID_TYPE: return "void";
            case ERROR_TIPO: return "error de tipo";
            case ERROR_NO_DECLARADO: return "no declarado";
            default: return "desconocido";
        }
    }
}

program: clase+;

clase:
    modificAcceso 'class' ID {
        if(findTSG($ID.text)) {
            printError("Error: " + $ID.text + " ya declarado", $ID);
        } else {
            pushTSG($ID.text, "class", $ID);
            printInfo("Clase " + $ID.text + " declarada correctamente");
        }
    } '{' miembro* '}' {
        checkMethodsToDelete();
        metodos.clear();
        atributos.clear();
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
    modificAcceso? tipoRetorno=tipo ID {
        if(metodos.contains($ID.text)) {
            printError("Error: Método " + $ID.text + " ya declarado en esta clase", $ID);
        } else {
            metodos.add($ID.text);
            pushTSG($ID.text, "method", $ID);
            registerMethodReturnType($ID.text, $tipoRetorno.text);
            methodCallCount.put($ID.text, 0);
            printInfo("Método " + $ID.text + " declarado correctamente (tipo de retorno: " + $tipoRetorno.text + ")");
        }   
    } '(' decl_arg? ')' '{' instruccion* '}' { clearTSL(); };

instruccion
    : asignacion
    | decl_local
    | metodCall
    | retorno
    ;

// Llamada a método como instrucción independiente
metodCall
    : ID '(' args=arglist? ')' SEMICOLON
        {
            String methodName = $ID.text;
            if(methodExists(methodName)) {
                incrementMethodCallCount(methodName);
                printInfo("La función '" + methodName + "' fue llamada. Total llamadas: " + methodCallCount.get(methodName), $ID);
            } else {
                printError("Error: La función '" + methodName + "' no está declarada", $ID);
            }
        }
    ;

// Lista de argumentos para llamadas a métodos
arglist
    : expr (',' expr)*
    ;

retorno
    : RETURN expr? SEMICOLON
    ;

asignacion
    : ID '=' expr SEMICOLON
        {
            if(!findTSL($ID.text) && !findTSGForAtom($ID.text)) {
                printError("Error: " + $ID.text + " no declarado", $ID);
            } else {
                int idTipo = -1;
                if(findTSL($ID.text)) {
                    idTipo = (Integer)TSL.get($ID.text);
                } else {
                    idTipo = (Integer)TSG.get($ID.text);
                }
                
                if(idTipo == INT_TYPE) {
                    if($expr.tipo == INT_TYPE) {
                        // correcto
                    } else {
                        printError("Error de tipos en asignación: " + $ID.text + " (int) y expresión de tipo " + 
                                  tipoToString($expr.tipo), $ID);
                    }
                } else if(idTipo == DOUBLE_TYPE) {
                    if($expr.tipo == DOUBLE_TYPE || $expr.tipo == INT_TYPE) {
                        // correcto
                    } else {
                        printError("Error de tipos en asignación: " + $ID.text + " (double) y expresión de tipo " + 
                                  tipoToString($expr.tipo), $ID);
                    }
                }
            }
        }
    ;

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

expr returns [int tipo] :
    m1=multExpr {$tipo = $m1.tipo;} (('+' | '-') m2=multExpr {
        if($m1.tipo == $m2.tipo && $m1.tipo != ERROR_TIPO) {
            $tipo = $m1.tipo;
        }else if (($m1.tipo == DOUBLE_TYPE && $m2.tipo==INT_TYPE) || ($m1.tipo == INT_TYPE && $m2.tipo==DOUBLE_TYPE)) {
                 $tipo = DOUBLE_TYPE;
        } else {
            $tipo = ERROR_TIPO;
            printError("Error de tipos en suma: " + tipoToString($m1.tipo) + " y " + tipoToString($m2.tipo), $m1.start);
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
            printError("Error de tipos en multiplicación: " + tipoToString($a1.tipo) + " y " + tipoToString($a2.tipo), $a1.start);
        }
    })* ;

atom returns [int tipo, Token start] :   
    CINT {$tipo = INT_TYPE; $start = $CINT;} |
    CDOUBLE {$tipo = DOUBLE_TYPE; $start = $CDOUBLE;} |
    ID '(' args=arglist? ')' {
        $start = $ID;
        String methodName = $ID.text;
        if(methodExists(methodName)) {
            incrementMethodCallCount(methodName);
            $tipo = getMethodReturnType(methodName);
        } else {
            $tipo = ERROR_NO_DECLARADO;
            printError("Error: La función '" + methodName + "' no está declarada", $ID);
        }
    } |
    ID {
        $start = $ID;   
        if(findTSL($ID.text)) {
            $tipo = (Integer) TSL.get($ID.text);
        } else if(findTSGForAtom($ID.text)) {
            $tipo = (Integer) TSG.get($ID.text);
        } else {
            $tipo = ERROR_NO_DECLARADO;
            printError("Error: " + $ID.text + " no declarado", $ID);
        }
    } |
    '(' expr ')' {$tipo = $expr.tipo; $start = $expr.start;};

modificAcceso: PUBLIC | PRIVATE | PROTECTED;
tipo returns [String text]: 
    INT { $text = "int"; } | 
    DOUBLE { $text = "double"; } | 
    VOID { $text = "void"; };

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