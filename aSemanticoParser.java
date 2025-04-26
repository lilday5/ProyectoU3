// $ANTLR 3.5.3 aSemantico.g 2025-04-25 20:39:34

    import java.util.HashMap;
    import java.util.ArrayList;
    import javax.swing.JTextArea;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class aSemanticoParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CDOUBLE", "CINT", "COMMA", "DOUBLE", 
		"ID", "INT", "PRIVATE", "PROTECTED", "PUBLIC", "RETURN", "SEMICOLON", 
		"VOID", "WS", "'('", "')'", "'*'", "'+'", "'-'", "'='", "'class'", "'{'", 
		"'}'"
	};
	public static final int EOF=-1;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int CDOUBLE=4;
	public static final int CINT=5;
	public static final int COMMA=6;
	public static final int DOUBLE=7;
	public static final int ID=8;
	public static final int INT=9;
	public static final int PRIVATE=10;
	public static final int PROTECTED=11;
	public static final int PUBLIC=12;
	public static final int RETURN=13;
	public static final int SEMICOLON=14;
	public static final int VOID=15;
	public static final int WS=16;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public aSemanticoParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public aSemanticoParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return aSemanticoParser.tokenNames; }
	@Override public String getGrammarFileName() { return "aSemantico.g"; }


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
			
			try {
				generateOptimizationReport();
			} catch (Exception e) {
				printError("Error al generar el reporte de optimización: " + e.getMessage());
			}	
	    } 



	// $ANTLR start "program"
	// aSemantico.g:133:1: program : ( clase )+ ;
	public final void program() throws RecognitionException {
		try {
			// aSemantico.g:133:8: ( ( clase )+ )
			// aSemantico.g:133:10: ( clase )+
			{
			// aSemantico.g:133:10: ( clase )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= PRIVATE && LA1_0 <= PUBLIC)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// aSemantico.g:133:10: clase
					{
					pushFollow(FOLLOW_clase_in_program21);
					clase();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "clase"
	// aSemantico.g:135:1: clase : modificAcceso 'class' ID '{' ( miembro )* '}' ;
	public final void clase() throws RecognitionException {
		Token ID1=null;

		try {
			// aSemantico.g:135:6: ( modificAcceso 'class' ID '{' ( miembro )* '}' )
			// aSemantico.g:136:5: modificAcceso 'class' ID '{' ( miembro )* '}'
			{
			pushFollow(FOLLOW_modificAcceso_in_clase33);
			modificAcceso();
			state._fsp--;

			match(input,23,FOLLOW_23_in_clase35); 
			ID1=(Token)match(input,ID,FOLLOW_ID_in_clase37); 

			        if(findTSG((ID1!=null?ID1.getText():null))) {
			            printError("Error: " + (ID1!=null?ID1.getText():null) + " ya declarado", ID1);
			        } else {
			            pushTSG((ID1!=null?ID1.getText():null), "class", ID1);
			        }
			    
			match(input,24,FOLLOW_24_in_clase41); 
			// aSemantico.g:142:11: ( miembro )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( (LA2_0==DOUBLE||(LA2_0 >= INT && LA2_0 <= PUBLIC)||LA2_0==VOID) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// aSemantico.g:142:11: miembro
					{
					pushFollow(FOLLOW_miembro_in_clase43);
					miembro();
					state._fsp--;

					}
					break;

				default :
					break loop2;
				}
			}

			match(input,25,FOLLOW_25_in_clase46); 

			        metodos.clear();
			        atributos.clear();
			        checkMethodsToDelete();
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "clase"



	// $ANTLR start "miembro"
	// aSemantico.g:148:1: miembro : ( metodo | atributo );
	public final void miembro() throws RecognitionException {
		try {
			// aSemantico.g:148:8: ( metodo | atributo )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( ((LA3_0 >= PRIVATE && LA3_0 <= PUBLIC)) ) {
				int LA3_1 = input.LA(2);
				if ( (LA3_1==DOUBLE||LA3_1==INT||LA3_1==VOID) ) {
					int LA3_2 = input.LA(3);
					if ( (LA3_2==ID) ) {
						int LA3_3 = input.LA(4);
						if ( (LA3_3==17) ) {
							alt3=1;
						}
						else if ( (LA3_3==COMMA||LA3_3==SEMICOLON) ) {
							alt3=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 3, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 3, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA3_0==DOUBLE||LA3_0==INT||LA3_0==VOID) ) {
				int LA3_2 = input.LA(2);
				if ( (LA3_2==ID) ) {
					int LA3_3 = input.LA(3);
					if ( (LA3_3==17) ) {
						alt3=1;
					}
					else if ( (LA3_3==COMMA||LA3_3==SEMICOLON) ) {
						alt3=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 3, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// aSemantico.g:148:10: metodo
					{
					pushFollow(FOLLOW_metodo_in_miembro55);
					metodo();
					state._fsp--;

					}
					break;
				case 2 :
					// aSemantico.g:148:19: atributo
					{
					pushFollow(FOLLOW_atributo_in_miembro59);
					atributo();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "miembro"



	// $ANTLR start "atributo"
	// aSemantico.g:150:1: atributo : ( modificAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON ;
	public final void atributo() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo2 =null;

		try {
			// aSemantico.g:150:9: ( ( modificAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON )
			// aSemantico.g:150:11: ( modificAcceso )? tipo id1= ID ( COMMA id2= ID )* SEMICOLON
			{
			// aSemantico.g:150:11: ( modificAcceso )?
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= PRIVATE && LA4_0 <= PUBLIC)) ) {
				alt4=1;
			}
			switch (alt4) {
				case 1 :
					// aSemantico.g:150:11: modificAcceso
					{
					pushFollow(FOLLOW_modificAcceso_in_atributo66);
					modificAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_atributo69);
			tipo2=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_atributo73); 

			        if(atributos.contains((id1!=null?id1.getText():null))) {
			            printError("Error redeclarando: " + (id1!=null?id1.getText():null), id1);
			        } else {
			            atributos.add((id1!=null?id1.getText():null));
			            pushTSG((id1!=null?id1.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null), id1);
			        }
			    
			// aSemantico.g:157:7: ( COMMA id2= ID )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==COMMA) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// aSemantico.g:157:8: COMMA id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_atributo78); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_atributo82); 

					        if(atributos.contains((id2!=null?id2.getText():null))) {
					            printError("Error redeclarando: " + (id2!=null?id2.getText():null), id2);
					        } else {
					            atributos.add((id2!=null?id2.getText():null));
					            pushTSG((id2!=null?id2.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null), id2);
					        }
					    
					}
					break;

				default :
					break loop5;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_atributo88); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "atributo"



	// $ANTLR start "metodo"
	// aSemantico.g:166:1: metodo : ( modificAcceso )? tipo ID '(' ( decl_arg )? ')' '{' ( instruccion )* '}' ;
	public final void metodo() throws RecognitionException {
		Token ID3=null;

		try {
			// aSemantico.g:166:7: ( ( modificAcceso )? tipo ID '(' ( decl_arg )? ')' '{' ( instruccion )* '}' )
			// aSemantico.g:167:5: ( modificAcceso )? tipo ID '(' ( decl_arg )? ')' '{' ( instruccion )* '}'
			{
			// aSemantico.g:167:5: ( modificAcceso )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( ((LA6_0 >= PRIVATE && LA6_0 <= PUBLIC)) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// aSemantico.g:167:5: modificAcceso
					{
					pushFollow(FOLLOW_modificAcceso_in_metodo99);
					modificAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_metodo102);
			tipo();
			state._fsp--;

			ID3=(Token)match(input,ID,FOLLOW_ID_in_metodo104); 

			        if(metodos.contains((ID3!=null?ID3.getText():null))) {
			            printError("Error: Método " + (ID3!=null?ID3.getText():null) + " ya declarado en esta clase", ID3);
			        } else {
			            metodos.add((ID3!=null?ID3.getText():null));
			            pushTSG((ID3!=null?ID3.getText():null), "method", ID3);
			        }   
			    
			match(input,17,FOLLOW_17_in_metodo108); 
			// aSemantico.g:174:11: ( decl_arg )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==DOUBLE||LA7_0==INT||LA7_0==VOID) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// aSemantico.g:174:11: decl_arg
					{
					pushFollow(FOLLOW_decl_arg_in_metodo110);
					decl_arg();
					state._fsp--;

					}
					break;

			}

			match(input,18,FOLLOW_18_in_metodo113); 
			match(input,24,FOLLOW_24_in_metodo115); 
			// aSemantico.g:174:29: ( instruccion )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( ((LA8_0 >= DOUBLE && LA8_0 <= INT)||LA8_0==RETURN||LA8_0==VOID) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// aSemantico.g:174:29: instruccion
					{
					pushFollow(FOLLOW_instruccion_in_metodo117);
					instruccion();
					state._fsp--;

					}
					break;

				default :
					break loop8;
				}
			}

			match(input,25,FOLLOW_25_in_metodo120); 
			 clearTSL(); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "metodo"



	// $ANTLR start "instruccion"
	// aSemantico.g:176:1: instruccion : ( asignacion | decl_local | llamadaMetodo | retorno );
	public final void instruccion() throws RecognitionException {
		try {
			// aSemantico.g:177:5: ( asignacion | decl_local | llamadaMetodo | retorno )
			int alt9=4;
			switch ( input.LA(1) ) {
			case ID:
				{
				int LA9_1 = input.LA(2);
				if ( (LA9_1==22) ) {
					alt9=1;
				}
				else if ( (LA9_1==17) ) {
					alt9=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 9, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case DOUBLE:
			case INT:
			case VOID:
				{
				alt9=2;
				}
				break;
			case RETURN:
				{
				alt9=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 9, 0, input);
				throw nvae;
			}
			switch (alt9) {
				case 1 :
					// aSemantico.g:177:7: asignacion
					{
					pushFollow(FOLLOW_asignacion_in_instruccion134);
					asignacion();
					state._fsp--;

					}
					break;
				case 2 :
					// aSemantico.g:178:7: decl_local
					{
					pushFollow(FOLLOW_decl_local_in_instruccion142);
					decl_local();
					state._fsp--;

					}
					break;
				case 3 :
					// aSemantico.g:179:7: llamadaMetodo
					{
					pushFollow(FOLLOW_llamadaMetodo_in_instruccion150);
					llamadaMetodo();
					state._fsp--;

					}
					break;
				case 4 :
					// aSemantico.g:180:7: retorno
					{
					pushFollow(FOLLOW_retorno_in_instruccion158);
					retorno();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instruccion"



	// $ANTLR start "llamadaMetodo"
	// aSemantico.g:183:1: llamadaMetodo : ID '(' ( expr ( ',' expr )* )? ')' SEMICOLON ;
	public final void llamadaMetodo() throws RecognitionException {
		Token ID4=null;

		try {
			// aSemantico.g:184:5: ( ID '(' ( expr ( ',' expr )* )? ')' SEMICOLON )
			// aSemantico.g:184:7: ID '(' ( expr ( ',' expr )* )? ')' SEMICOLON
			{
			ID4=(Token)match(input,ID,FOLLOW_ID_in_llamadaMetodo175); 
			match(input,17,FOLLOW_17_in_llamadaMetodo177); 
			// aSemantico.g:184:14: ( expr ( ',' expr )* )?
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( ((LA11_0 >= CDOUBLE && LA11_0 <= CINT)||LA11_0==ID||LA11_0==17) ) {
				alt11=1;
			}
			switch (alt11) {
				case 1 :
					// aSemantico.g:184:15: expr ( ',' expr )*
					{
					pushFollow(FOLLOW_expr_in_llamadaMetodo180);
					expr();
					state._fsp--;

					// aSemantico.g:184:20: ( ',' expr )*
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( (LA10_0==COMMA) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// aSemantico.g:184:21: ',' expr
							{
							match(input,COMMA,FOLLOW_COMMA_in_llamadaMetodo183); 
							pushFollow(FOLLOW_expr_in_llamadaMetodo185);
							expr();
							state._fsp--;

							}
							break;

						default :
							break loop10;
						}
					}

					}
					break;

			}

			match(input,18,FOLLOW_18_in_llamadaMetodo191); 
			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_llamadaMetodo193); 

			            // Validar existencia del método y tipos de argumentos
			            if(!metodos.contains((ID4!=null?ID4.getText():null))) {
			                printError("Error: La función '" + (ID4!=null?ID4.getText():null) + "' no está declarada (línea " + ID4.getLine() + ", columna " + ID4.getCharPositionInLine() + ")", ID4);
			            } else {
			                incrementMethodCallCount((ID4!=null?ID4.getText():null));
			                printError("La función '" + (ID4!=null?ID4.getText():null) + "' fue llamada. Total llamadas: " + methodCallCount.get((ID4!=null?ID4.getText():null)), ID4);
			            }
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "llamadaMetodo"



	// $ANTLR start "retorno"
	// aSemantico.g:196:1: retorno : RETURN ( expr )? SEMICOLON ;
	public final void retorno() throws RecognitionException {
		try {
			// aSemantico.g:197:5: ( RETURN ( expr )? SEMICOLON )
			// aSemantico.g:197:7: RETURN ( expr )? SEMICOLON
			{
			match(input,RETURN,FOLLOW_RETURN_in_retorno220); 
			// aSemantico.g:197:14: ( expr )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( ((LA12_0 >= CDOUBLE && LA12_0 <= CINT)||LA12_0==ID||LA12_0==17) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// aSemantico.g:197:14: expr
					{
					pushFollow(FOLLOW_expr_in_retorno222);
					expr();
					state._fsp--;

					}
					break;

			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_retorno225); 

			            // Aquí podrías validar el tipo de retorno si lo deseas
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "retorno"



	// $ANTLR start "asignacion"
	// aSemantico.g:203:1: asignacion : ID '=' expr SEMICOLON ;
	public final void asignacion() throws RecognitionException {
		Token ID5=null;
		ParserRuleReturnScope expr6 =null;

		try {
			// aSemantico.g:203:11: ( ID '=' expr SEMICOLON )
			// aSemantico.g:203:13: ID '=' expr SEMICOLON
			{
			ID5=(Token)match(input,ID,FOLLOW_ID_in_asignacion247); 
			match(input,22,FOLLOW_22_in_asignacion249); 
			pushFollow(FOLLOW_expr_in_asignacion251);
			expr6=expr();
			state._fsp--;


			    // ← validación de existencia y tipo al asignar
			    if(!findTSL((ID5!=null?ID5.getText():null)) && !findTSGForAtom((ID5!=null?ID5.getText():null))) {
			        printError("Error: " + (ID5!=null?ID5.getText():null) + " no declarado", ID5);
			    } else {
			        if(findTSL((ID5!=null?ID5.getText():null))) {
			            if((Integer)TSL.get((ID5!=null?ID5.getText():null)) == INT_TYPE){
			                if((expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == INT_TYPE) {
			                    // correcto
			                } else {
			                    printError("Error de tipos en asignación: " + (ID5!=null?ID5.getText():null) + " y " + (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0), ID5);
			                }

			            }
			            
			            if((Integer)TSL.get((ID5!=null?ID5.getText():null)) == DOUBLE_TYPE){
			                if((expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == DOUBLE_TYPE || (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == INT_TYPE){
			                    // correcto
			                }else{
			                   printError("Error de tipos en asignación: " + (ID5!=null?ID5.getText():null) + " y " + (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0), ID5);
			                }
			            }
			        } else {
			              if((Integer)TSG.get((ID5!=null?ID5.getText():null)) == INT_TYPE){
			                if((expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == INT_TYPE) {
			                    // correcto
			                } else {
			                    printError("Error de tipos en asignación: " + (ID5!=null?ID5.getText():null) + " y " + (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0), ID5);
			                }

			            }
			            if((Integer)TSG.get((ID5!=null?ID5.getText():null)) == DOUBLE_TYPE){
			                if((expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == DOUBLE_TYPE || (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0) == INT_TYPE){
			                    // correcto
			                }else{
			                   printError("Error de tipos en asignación: " + (ID5!=null?ID5.getText():null) + " y " + (expr6!=null?((aSemanticoParser.expr_return)expr6).tipo:0), ID5);
			                }
			            }
			        }
			    }

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_asignacion255); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "asignacion"



	// $ANTLR start "decl_arg"
	// aSemantico.g:245:1: decl_arg : tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )* ;
	public final void decl_arg() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo1 =null;
		ParserRuleReturnScope tipo2 =null;

		try {
			// aSemantico.g:245:9: (tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )* )
			// aSemantico.g:246:5: tipo1= tipo id1= ID ( COMMA tipo2= tipo id2= ID )*
			{
			pushFollow(FOLLOW_tipo_in_decl_arg270);
			tipo1=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_decl_arg276); 

			        if(findTSL((id1!=null?id1.getText():null))) {
			            printError("Error redeclarando: " + (id1!=null?id1.getText():null), id1);
			        } else {
			            pushTSL((id1!=null?id1.getText():null), (tipo1!=null?input.toString(tipo1.start,tipo1.stop):null), id1);
			        }
			    
			// aSemantico.g:252:7: ( COMMA tipo2= tipo id2= ID )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==COMMA) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// aSemantico.g:252:8: COMMA tipo2= tipo id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_decl_arg281); 
					pushFollow(FOLLOW_tipo_in_decl_arg287);
					tipo2=tipo();
					state._fsp--;

					id2=(Token)match(input,ID,FOLLOW_ID_in_decl_arg293); 

					        if(findTSL((id2!=null?id2.getText():null))) {
					            printError("Error redeclarando: " + (id2!=null?id2.getText():null), id2);
					        } else {
					            pushTSL((id2!=null?id2.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null), id2);
					        }
					    
					}
					break;

				default :
					break loop13;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_arg"



	// $ANTLR start "decl_local"
	// aSemantico.g:260:1: decl_local : tipo id1= ID ( ',' id2= ID )* SEMICOLON ;
	public final void decl_local() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo7 =null;

		try {
			// aSemantico.g:260:11: ( tipo id1= ID ( ',' id2= ID )* SEMICOLON )
			// aSemantico.g:261:5: tipo id1= ID ( ',' id2= ID )* SEMICOLON
			{
			pushFollow(FOLLOW_tipo_in_decl_local308);
			tipo7=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_decl_local314); 

			        if(findTSL((id1!=null?id1.getText():null))) {
			            printError("Error redeclarando: " + (id1!=null?id1.getText():null), id1);
			        } else {
			            pushTSL((id1!=null?id1.getText():null), (tipo7!=null?input.toString(tipo7.start,tipo7.stop):null), id1);
			        }
			    
			// aSemantico.g:267:7: ( ',' id2= ID )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==COMMA) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// aSemantico.g:267:8: ',' id2= ID
					{
					match(input,COMMA,FOLLOW_COMMA_in_decl_local319); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_decl_local325); 

					        if(findTSL((id2!=null?id2.getText():null))) {
					            printError("Error redeclarando: " + (id2!=null?id2.getText():null), id2);
					        } else {
					            pushTSL((id2!=null?id2.getText():null), (tipo7!=null?input.toString(tipo7.start,tipo7.stop):null), id2);
					        }        
					    
					}
					break;

				default :
					break loop14;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_decl_local331); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "decl_local"


	public static class expr_return extends ParserRuleReturnScope {
		public int tipo;
	};


	// $ANTLR start "expr"
	// aSemantico.g:276:1: expr returns [int tipo] : m1= multExpr ( ( '+' | '-' ) m2= multExpr )* ;
	public final aSemanticoParser.expr_return expr() throws RecognitionException {
		aSemanticoParser.expr_return retval = new aSemanticoParser.expr_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope m1 =null;
		ParserRuleReturnScope m2 =null;

		try {
			// aSemantico.g:276:25: (m1= multExpr ( ( '+' | '-' ) m2= multExpr )* )
			// aSemantico.g:277:5: m1= multExpr ( ( '+' | '-' ) m2= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_expr350);
			m1=multExpr();
			state._fsp--;

			retval.tipo = (m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0);
			// aSemantico.g:277:37: ( ( '+' | '-' ) m2= multExpr )*
			loop15:
			while (true) {
				int alt15=2;
				int LA15_0 = input.LA(1);
				if ( ((LA15_0 >= 20 && LA15_0 <= 21)) ) {
					alt15=1;
				}

				switch (alt15) {
				case 1 :
					// aSemantico.g:277:38: ( '+' | '-' ) m2= multExpr
					{
					if ( (input.LA(1) >= 20 && input.LA(1) <= 21) ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_multExpr_in_expr365);
					m2=multExpr();
					state._fsp--;


					        if((m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0) == (m2!=null?((aSemanticoParser.multExpr_return)m2).tipo:0) && (m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0) != ERROR_TIPO) {
					            retval.tipo = (m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0);
					        }else if (((m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0) == DOUBLE_TYPE && (m2!=null?((aSemanticoParser.multExpr_return)m2).tipo:0)==INT_TYPE) || ((m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0) == INT_TYPE && (m2!=null?((aSemanticoParser.multExpr_return)m2).tipo:0)==DOUBLE_TYPE)) {
					                 retval.tipo = DOUBLE_TYPE;
					        } else {
					            retval.tipo = ERROR_TIPO;
					            printError("Error de tipos en suma: " + (m1!=null?((aSemanticoParser.multExpr_return)m1).tipo:0) + " y " + (m2!=null?((aSemanticoParser.multExpr_return)m2).tipo:0), (m1!=null?(m1.start):null));
					        }
					    
					}
					break;

				default :
					break loop15;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class multExpr_return extends ParserRuleReturnScope {
		public int tipo;
	};


	// $ANTLR start "multExpr"
	// aSemantico.g:288:1: multExpr returns [int tipo] : a1= atom ( '*' a2= atom )* ;
	public final aSemanticoParser.multExpr_return multExpr() throws RecognitionException {
		aSemanticoParser.multExpr_return retval = new aSemanticoParser.multExpr_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		try {
			// aSemantico.g:288:29: (a1= atom ( '*' a2= atom )* )
			// aSemantico.g:289:5: a1= atom ( '*' a2= atom )*
			{
			pushFollow(FOLLOW_atom_in_multExpr388);
			a1=atom();
			state._fsp--;

			retval.tipo = (a1!=null?((aSemanticoParser.atom_return)a1).tipo:0);
			// aSemantico.g:289:33: ( '*' a2= atom )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( (LA16_0==19) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// aSemantico.g:289:34: '*' a2= atom
					{
					match(input,19,FOLLOW_19_in_multExpr393); 
					pushFollow(FOLLOW_atom_in_multExpr397);
					a2=atom();
					state._fsp--;


					        if((a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) == (a2!=null?((aSemanticoParser.atom_return)a2).tipo:0) && (a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) != ERROR_TIPO && (a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) != ERROR_NO_DECLARADO) {
					            retval.tipo = (a1!=null?((aSemanticoParser.atom_return)a1).tipo:0);
					        }else if (((a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) == DOUBLE_TYPE && (a2!=null?((aSemanticoParser.atom_return)a2).tipo:0)==INT_TYPE) || ((a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) == INT_TYPE && (a2!=null?((aSemanticoParser.atom_return)a2).tipo:0)==DOUBLE_TYPE)) {
					                 retval.tipo = DOUBLE_TYPE;
					        } else {
					            retval.tipo = ERROR_TIPO;
					            printError("Error de tipos en multiplicación: " + (a1!=null?((aSemanticoParser.atom_return)a1).tipo:0) + " y " + (a2!=null?((aSemanticoParser.atom_return)a2).tipo:0), (a1!=null?((aSemanticoParser.atom_return)a1).start:null));
					        }
					    
					}
					break;

				default :
					break loop16;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		public int tipo;
		public Token start;
	};


	// $ANTLR start "atom"
	// aSemantico.g:300:1: atom returns [int tipo, Token start] : ( CINT | CDOUBLE | ID | '(' expr ')' );
	public final aSemanticoParser.atom_return atom() throws RecognitionException {
		aSemanticoParser.atom_return retval = new aSemanticoParser.atom_return();
		retval.start = input.LT(1);

		Token CINT8=null;
		Token CDOUBLE9=null;
		Token ID10=null;
		ParserRuleReturnScope expr11 =null;

		try {
			// aSemantico.g:300:38: ( CINT | CDOUBLE | ID | '(' expr ')' )
			int alt17=4;
			switch ( input.LA(1) ) {
			case CINT:
				{
				alt17=1;
				}
				break;
			case CDOUBLE:
				{
				alt17=2;
				}
				break;
			case ID:
				{
				alt17=3;
				}
				break;
			case 17:
				{
				alt17=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}
			switch (alt17) {
				case 1 :
					// aSemantico.g:301:5: CINT
					{
					CINT8=(Token)match(input,CINT,FOLLOW_CINT_in_atom421); 
					retval.tipo = INT_TYPE; retval.start = CINT8;
					}
					break;
				case 2 :
					// aSemantico.g:302:5: CDOUBLE
					{
					CDOUBLE9=(Token)match(input,CDOUBLE,FOLLOW_CDOUBLE_in_atom431); 
					retval.tipo = DOUBLE_TYPE; retval.start = CDOUBLE9;
					}
					break;
				case 3 :
					// aSemantico.g:303:5: ID
					{
					ID10=(Token)match(input,ID,FOLLOW_ID_in_atom441); 

					        retval.start = ID10;   

					        Object value = TSG.get((ID10!=null?ID10.getText():null)); 
					        if (Integer.valueOf(METHOD_TYPE).equals(value)) {
					              incrementMethodCallCount((ID10!=null?ID10.getText():null));
					              System.out.println("Se llamo al metodo" + (ID10!=null?ID10.getText():null));
					        }
					    

					        if(findTSL((ID10!=null?ID10.getText():null))) {
					            retval.tipo = (Integer) TSL.get((ID10!=null?ID10.getText():null));
					        } else if(findTSGForAtom((ID10!=null?ID10.getText():null))) {
					            retval.tipo = (Integer) TSG.get((ID10!=null?ID10.getText():null));
					        } else {
					            retval.tipo = ERROR_NO_DECLARADO;
					            printError("Error: " + (ID10!=null?ID10.getText():null) + " no declarado", ID10);
					        }
					    
					}
					break;
				case 4 :
					// aSemantico.g:322:5: '(' expr ')'
					{
					match(input,17,FOLLOW_17_in_atom452); 
					pushFollow(FOLLOW_expr_in_atom454);
					expr11=expr();
					state._fsp--;

					retval.tipo = (expr11!=null?((aSemanticoParser.expr_return)expr11).tipo:0); retval.start = (expr11!=null?(expr11.start):null);
					match(input,18,FOLLOW_18_in_atom458); 
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"



	// $ANTLR start "generateOptimizationReport"
	// aSemantico.g:324:1: generateOptimizationReport :;
	public final void generateOptimizationReport() throws RecognitionException {
		try {
			// aSemantico.g:324:27: ()
			// aSemantico.g:324:29: 
			{

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

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "generateOptimizationReport"



	// $ANTLR start "modificAcceso"
	// aSemantico.g:345:1: modificAcceso : ( PUBLIC | PRIVATE | PROTECTED );
	public final void modificAcceso() throws RecognitionException {
		try {
			// aSemantico.g:345:14: ( PUBLIC | PRIVATE | PROTECTED )
			// aSemantico.g:
			{
			if ( (input.LA(1) >= PRIVATE && input.LA(1) <= PUBLIC) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "modificAcceso"


	public static class tipo_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tipo"
	// aSemantico.g:346:1: tipo : ( INT | DOUBLE | VOID );
	public final aSemanticoParser.tipo_return tipo() throws RecognitionException {
		aSemanticoParser.tipo_return retval = new aSemanticoParser.tipo_return();
		retval.start = input.LT(1);

		try {
			// aSemantico.g:346:5: ( INT | DOUBLE | VOID )
			// aSemantico.g:
			{
			if ( input.LA(1)==DOUBLE||input.LA(1)==INT||input.LA(1)==VOID ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tipo"

	// Delegated rules



	public static final BitSet FOLLOW_clase_in_program21 = new BitSet(new long[]{0x0000000000001C02L});
	public static final BitSet FOLLOW_modificAcceso_in_clase33 = new BitSet(new long[]{0x0000000000800000L});
	public static final BitSet FOLLOW_23_in_clase35 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_clase37 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_clase41 = new BitSet(new long[]{0x0000000002009E80L});
	public static final BitSet FOLLOW_miembro_in_clase43 = new BitSet(new long[]{0x0000000002009E80L});
	public static final BitSet FOLLOW_25_in_clase46 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_metodo_in_miembro55 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atributo_in_miembro59 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificAcceso_in_atributo66 = new BitSet(new long[]{0x0000000000008280L});
	public static final BitSet FOLLOW_tipo_in_atributo69 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_atributo73 = new BitSet(new long[]{0x0000000000004040L});
	public static final BitSet FOLLOW_COMMA_in_atributo78 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_atributo82 = new BitSet(new long[]{0x0000000000004040L});
	public static final BitSet FOLLOW_SEMICOLON_in_atributo88 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificAcceso_in_metodo99 = new BitSet(new long[]{0x0000000000008280L});
	public static final BitSet FOLLOW_tipo_in_metodo102 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_metodo104 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_metodo108 = new BitSet(new long[]{0x0000000000048280L});
	public static final BitSet FOLLOW_decl_arg_in_metodo110 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_metodo113 = new BitSet(new long[]{0x0000000001000000L});
	public static final BitSet FOLLOW_24_in_metodo115 = new BitSet(new long[]{0x000000000200A380L});
	public static final BitSet FOLLOW_instruccion_in_metodo117 = new BitSet(new long[]{0x000000000200A380L});
	public static final BitSet FOLLOW_25_in_metodo120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_asignacion_in_instruccion134 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decl_local_in_instruccion142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llamadaMetodo_in_instruccion150 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_retorno_in_instruccion158 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_llamadaMetodo175 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_17_in_llamadaMetodo177 = new BitSet(new long[]{0x0000000000060130L});
	public static final BitSet FOLLOW_expr_in_llamadaMetodo180 = new BitSet(new long[]{0x0000000000040040L});
	public static final BitSet FOLLOW_COMMA_in_llamadaMetodo183 = new BitSet(new long[]{0x0000000000020130L});
	public static final BitSet FOLLOW_expr_in_llamadaMetodo185 = new BitSet(new long[]{0x0000000000040040L});
	public static final BitSet FOLLOW_18_in_llamadaMetodo191 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_SEMICOLON_in_llamadaMetodo193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RETURN_in_retorno220 = new BitSet(new long[]{0x0000000000024130L});
	public static final BitSet FOLLOW_expr_in_retorno222 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_SEMICOLON_in_retorno225 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_asignacion247 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_asignacion249 = new BitSet(new long[]{0x0000000000020130L});
	public static final BitSet FOLLOW_expr_in_asignacion251 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_SEMICOLON_in_asignacion255 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tipo_in_decl_arg270 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_decl_arg276 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_COMMA_in_decl_arg281 = new BitSet(new long[]{0x0000000000008280L});
	public static final BitSet FOLLOW_tipo_in_decl_arg287 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_decl_arg293 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_tipo_in_decl_local308 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_decl_local314 = new BitSet(new long[]{0x0000000000004040L});
	public static final BitSet FOLLOW_COMMA_in_decl_local319 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_ID_in_decl_local325 = new BitSet(new long[]{0x0000000000004040L});
	public static final BitSet FOLLOW_SEMICOLON_in_decl_local331 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr350 = new BitSet(new long[]{0x0000000000300002L});
	public static final BitSet FOLLOW_set_in_expr355 = new BitSet(new long[]{0x0000000000020130L});
	public static final BitSet FOLLOW_multExpr_in_expr365 = new BitSet(new long[]{0x0000000000300002L});
	public static final BitSet FOLLOW_atom_in_multExpr388 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_19_in_multExpr393 = new BitSet(new long[]{0x0000000000020130L});
	public static final BitSet FOLLOW_atom_in_multExpr397 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_CINT_in_atom421 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CDOUBLE_in_atom431 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom441 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_atom452 = new BitSet(new long[]{0x0000000000020130L});
	public static final BitSet FOLLOW_expr_in_atom454 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_atom458 = new BitSet(new long[]{0x0000000000000002L});
}
