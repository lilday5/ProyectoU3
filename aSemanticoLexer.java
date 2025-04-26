// $ANTLR 3.5.3 aSemantico.g 2025-04-25 20:39:34

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class aSemanticoLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public aSemanticoLexer() {} 
	public aSemanticoLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public aSemanticoLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "aSemantico.g"; }

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:2:7: ( '(' )
			// aSemantico.g:2:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:3:7: ( ')' )
			// aSemantico.g:3:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:4:7: ( '*' )
			// aSemantico.g:4:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:5:7: ( '+' )
			// aSemantico.g:5:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:6:7: ( '-' )
			// aSemantico.g:6:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:7:7: ( '=' )
			// aSemantico.g:7:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:8:7: ( 'class' )
			// aSemantico.g:8:9: 'class'
			{
			match("class"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:9:7: ( '{' )
			// aSemantico.g:9:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:10:7: ( '}' )
			// aSemantico.g:10:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:348:4: ( 'int' )
			// aSemantico.g:348:6: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:349:7: ( 'double' )
			// aSemantico.g:349:9: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:350:5: ( 'void' )
			// aSemantico.g:350:7: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "PUBLIC"
	public final void mPUBLIC() throws RecognitionException {
		try {
			int _type = PUBLIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:351:7: ( 'public' )
			// aSemantico.g:351:9: 'public'
			{
			match("public"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUBLIC"

	// $ANTLR start "PRIVATE"
	public final void mPRIVATE() throws RecognitionException {
		try {
			int _type = PRIVATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:352:8: ( 'private' )
			// aSemantico.g:352:10: 'private'
			{
			match("private"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIVATE"

	// $ANTLR start "PROTECTED"
	public final void mPROTECTED() throws RecognitionException {
		try {
			int _type = PROTECTED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:353:10: ( 'protected' )
			// aSemantico.g:353:12: 'protected'
			{
			match("protected"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROTECTED"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:354:6: ( ',' )
			// aSemantico.g:354:8: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:355:10: ( ';' )
			// aSemantico.g:355:12: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "CDOUBLE"
	public final void mCDOUBLE() throws RecognitionException {
		try {
			int _type = CDOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:357:8: ( CINT '.' CINT )
			// aSemantico.g:357:10: CINT '.' CINT
			{
			mCINT(); 

			match('.'); 
			mCINT(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CDOUBLE"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:358:7: ( 'return' )
			// aSemantico.g:358:9: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:359:3: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ ( '0' .. '9' )* )
			// aSemantico.g:359:5: ( 'a' .. 'z' | 'A' .. 'Z' )+ ( '0' .. '9' )*
			{
			// aSemantico.g:359:5: ( 'a' .. 'z' | 'A' .. 'Z' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= 'A' && LA1_0 <= 'Z')||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// aSemantico.g:
					{
					if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			// aSemantico.g:359:29: ( '0' .. '9' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// aSemantico.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "CINT"
	public final void mCINT() throws RecognitionException {
		try {
			int _type = CINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:360:5: ( ( '0' .. '9' )+ )
			// aSemantico.g:360:7: ( '0' .. '9' )+
			{
			// aSemantico.g:360:7: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// aSemantico.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CINT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// aSemantico.g:362:3: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// aSemantico.g:362:5: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// aSemantico.g:362:5: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// aSemantico.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// aSemantico.g:1:8: ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | INT | DOUBLE | VOID | PUBLIC | PRIVATE | PROTECTED | COMMA | SEMICOLON | CDOUBLE | RETURN | ID | CINT | WS )
		int alt5=22;
		alt5 = dfa5.predict(input);
		switch (alt5) {
			case 1 :
				// aSemantico.g:1:10: T__17
				{
				mT__17(); 

				}
				break;
			case 2 :
				// aSemantico.g:1:16: T__18
				{
				mT__18(); 

				}
				break;
			case 3 :
				// aSemantico.g:1:22: T__19
				{
				mT__19(); 

				}
				break;
			case 4 :
				// aSemantico.g:1:28: T__20
				{
				mT__20(); 

				}
				break;
			case 5 :
				// aSemantico.g:1:34: T__21
				{
				mT__21(); 

				}
				break;
			case 6 :
				// aSemantico.g:1:40: T__22
				{
				mT__22(); 

				}
				break;
			case 7 :
				// aSemantico.g:1:46: T__23
				{
				mT__23(); 

				}
				break;
			case 8 :
				// aSemantico.g:1:52: T__24
				{
				mT__24(); 

				}
				break;
			case 9 :
				// aSemantico.g:1:58: T__25
				{
				mT__25(); 

				}
				break;
			case 10 :
				// aSemantico.g:1:64: INT
				{
				mINT(); 

				}
				break;
			case 11 :
				// aSemantico.g:1:68: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 12 :
				// aSemantico.g:1:75: VOID
				{
				mVOID(); 

				}
				break;
			case 13 :
				// aSemantico.g:1:80: PUBLIC
				{
				mPUBLIC(); 

				}
				break;
			case 14 :
				// aSemantico.g:1:87: PRIVATE
				{
				mPRIVATE(); 

				}
				break;
			case 15 :
				// aSemantico.g:1:95: PROTECTED
				{
				mPROTECTED(); 

				}
				break;
			case 16 :
				// aSemantico.g:1:105: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 17 :
				// aSemantico.g:1:111: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 18 :
				// aSemantico.g:1:121: CDOUBLE
				{
				mCDOUBLE(); 

				}
				break;
			case 19 :
				// aSemantico.g:1:129: RETURN
				{
				mRETURN(); 

				}
				break;
			case 20 :
				// aSemantico.g:1:136: ID
				{
				mID(); 

				}
				break;
			case 21 :
				// aSemantico.g:1:139: CINT
				{
				mCINT(); 

				}
				break;
			case 22 :
				// aSemantico.g:1:144: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA5 dfa5 = new DFA5(this);
	static final String DFA5_eotS =
		"\7\uffff\1\22\2\uffff\4\22\2\uffff\1\32\1\22\2\uffff\6\22\2\uffff\2\22"+
		"\1\46\7\22\1\uffff\1\22\1\57\4\22\1\64\1\22\1\uffff\4\22\1\uffff\1\72"+
		"\1\73\2\22\1\76\2\uffff\1\77\1\22\2\uffff\1\22\1\102\1\uffff";
	static final String DFA5_eofS =
		"\103\uffff";
	static final String DFA5_minS =
		"\1\11\6\uffff\1\154\2\uffff\1\156\2\157\1\162\2\uffff\1\56\1\145\2\uffff"+
		"\1\141\1\164\1\165\1\151\1\142\1\151\2\uffff\1\164\1\163\1\60\1\142\1"+
		"\144\1\154\1\166\1\164\1\165\1\163\1\uffff\1\154\1\60\1\151\1\141\1\145"+
		"\1\162\1\60\1\145\1\uffff\1\143\1\164\1\143\1\156\1\uffff\2\60\1\145\1"+
		"\164\1\60\2\uffff\1\60\1\145\2\uffff\1\144\1\60\1\uffff";
	static final String DFA5_maxS =
		"\1\175\6\uffff\1\154\2\uffff\1\156\2\157\1\165\2\uffff\1\71\1\145\2\uffff"+
		"\1\141\1\164\1\165\1\151\1\142\1\157\2\uffff\1\164\1\163\1\172\1\142\1"+
		"\144\1\154\1\166\1\164\1\165\1\163\1\uffff\1\154\1\172\1\151\1\141\1\145"+
		"\1\162\1\172\1\145\1\uffff\1\143\1\164\1\143\1\156\1\uffff\2\172\1\145"+
		"\1\164\1\172\2\uffff\1\172\1\145\2\uffff\1\144\1\172\1\uffff";
	static final String DFA5_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\11\4\uffff\1\20\1\21\2"+
		"\uffff\1\24\1\26\6\uffff\1\25\1\22\12\uffff\1\12\10\uffff\1\14\4\uffff"+
		"\1\7\5\uffff\1\13\1\15\2\uffff\1\23\1\16\2\uffff\1\17";
	static final String DFA5_specialS =
		"\103\uffff}>";
	static final String[] DFA5_transitionS = {
			"\2\23\2\uffff\1\23\22\uffff\1\23\7\uffff\1\1\1\2\1\3\1\4\1\16\1\5\2\uffff"+
			"\12\20\1\uffff\1\17\1\uffff\1\6\3\uffff\32\22\6\uffff\2\22\1\7\1\13\4"+
			"\22\1\12\6\22\1\15\1\22\1\21\3\22\1\14\4\22\1\10\1\uffff\1\11",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\24",
			"",
			"",
			"\1\25",
			"\1\26",
			"\1\27",
			"\1\31\2\uffff\1\30",
			"",
			"",
			"\1\33\1\uffff\12\20",
			"\1\34",
			"",
			"",
			"\1\35",
			"\1\36",
			"\1\37",
			"\1\40",
			"\1\41",
			"\1\42\5\uffff\1\43",
			"",
			"",
			"\1\44",
			"\1\45",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\1\47",
			"\1\50",
			"\1\51",
			"\1\52",
			"\1\53",
			"\1\54",
			"\1\55",
			"",
			"\1\56",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\1\65",
			"",
			"\1\66",
			"\1\67",
			"\1\70",
			"\1\71",
			"",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\1\74",
			"\1\75",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"",
			"",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			"\1\100",
			"",
			"",
			"\1\101",
			"\12\22\7\uffff\32\22\6\uffff\32\22",
			""
	};

	static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
	static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
	static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
	static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
	static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
	static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
	static final short[][] DFA5_transition;

	static {
		int numStates = DFA5_transitionS.length;
		DFA5_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
		}
	}

	protected class DFA5 extends DFA {

		public DFA5(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 5;
			this.eot = DFA5_eot;
			this.eof = DFA5_eof;
			this.min = DFA5_min;
			this.max = DFA5_max;
			this.accept = DFA5_accept;
			this.special = DFA5_special;
			this.transition = DFA5_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | INT | DOUBLE | VOID | PUBLIC | PRIVATE | PROTECTED | COMMA | SEMICOLON | CDOUBLE | RETURN | ID | CINT | WS );";
		}
	}

}
