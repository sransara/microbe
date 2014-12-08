// Generated from Microbe.g4 by ANTLR 4.4

    import java.util.LinkedList;
    import Nucleus.*;
    import SymbolScope.*;
    import AST.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MicrobeLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ASSIGN=1, SEMICOLON=2, ADD=3, MINUS=4, MULTIPLY=5, DIVIDE=6, LT=7, GT=8, 
		LTE=9, GTE=10, EQUALS=11, NEQUALS=12, OPAREN=13, CPAREN=14, COMMA=15, 
		PROGRAM=16, BEGIN=17, END=18, FUNCTION=19, RETURN=20, IF=21, ELSE=22, 
		ENDIF=23, WHILE=24, CONTINUE=25, BREAK=26, ENDWHILE=27, READ=28, WRITE=29, 
		VOID=30, INT=31, FLOAT=32, STRING=33, FLOATLITERAL=34, INTLITERAL=35, 
		STRINGLITERAL=36, IDENTIFIER=37, COMMENT=38, WHITES=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''"
	};
	public static final String[] ruleNames = {
		"ASSIGN", "SEMICOLON", "ADD", "MINUS", "MULTIPLY", "DIVIDE", "LT", "GT", 
		"LTE", "GTE", "EQUALS", "NEQUALS", "OPAREN", "CPAREN", "COMMA", "PROGRAM", 
		"BEGIN", "END", "FUNCTION", "RETURN", "IF", "ELSE", "ENDIF", "WHILE", 
		"CONTINUE", "BREAK", "ENDWHILE", "READ", "WRITE", "VOID", "INT", "FLOAT", 
		"STRING", "FLOATLITERAL", "INTLITERAL", "STRINGLITERAL", "IDENTIFIER", 
		"COMMENT", "WHITES", "CHAR", "DIGIT"
	};


	    public SymbolScopeTree sst = new SymbolScopeTree();


	public MicrobeLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Microbe.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2)\u0123\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!"+
		"\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\7#\u00e8\n#\f#\16#\u00eb\13#"+
		"\3#\3#\6#\u00ef\n#\r#\16#\u00f0\3$\6$\u00f4\n$\r$\16$\u00f5\3%\3%\7%\u00fa"+
		"\n%\f%\16%\u00fd\13%\3%\3%\3&\3&\3&\7&\u0104\n&\f&\16&\u0107\13&\3\'\3"+
		"\'\3\'\3\'\7\'\u010d\n\'\f\'\16\'\u0110\13\'\3\'\5\'\u0113\n\'\3\'\3\'"+
		"\3\'\3\'\3(\6(\u011a\n(\r(\16(\u011b\3(\3(\3)\3)\3*\3*\4\u00fb\u010e\2"+
		"+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37"+
		"= ?!A\"C#E$G%I&K\'M(O)Q\2S\2\3\2\7\5\2\f\f\17\17$$\4\2\f\f\17\17\5\2\13"+
		"\f\17\17\"\"\4\2C\\c|\3\2\62;\u0129\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2"+
		"\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2"+
		"\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3"+
		"\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2"+
		"\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2"+
		"\2O\3\2\2\2\3U\3\2\2\2\5X\3\2\2\2\7Z\3\2\2\2\t\\\3\2\2\2\13^\3\2\2\2\r"+
		"`\3\2\2\2\17b\3\2\2\2\21d\3\2\2\2\23f\3\2\2\2\25i\3\2\2\2\27l\3\2\2\2"+
		"\31n\3\2\2\2\33q\3\2\2\2\35s\3\2\2\2\37u\3\2\2\2!w\3\2\2\2#\177\3\2\2"+
		"\2%\u0085\3\2\2\2\'\u0089\3\2\2\2)\u0092\3\2\2\2+\u0099\3\2\2\2-\u009c"+
		"\3\2\2\2/\u00a1\3\2\2\2\61\u00a7\3\2\2\2\63\u00ad\3\2\2\2\65\u00b6\3\2"+
		"\2\2\67\u00bc\3\2\2\29\u00c5\3\2\2\2;\u00ca\3\2\2\2=\u00d0\3\2\2\2?\u00d5"+
		"\3\2\2\2A\u00d9\3\2\2\2C\u00df\3\2\2\2E\u00e9\3\2\2\2G\u00f3\3\2\2\2I"+
		"\u00f7\3\2\2\2K\u0100\3\2\2\2M\u0108\3\2\2\2O\u0119\3\2\2\2Q\u011f\3\2"+
		"\2\2S\u0121\3\2\2\2UV\7<\2\2VW\7?\2\2W\4\3\2\2\2XY\7=\2\2Y\6\3\2\2\2Z"+
		"[\7-\2\2[\b\3\2\2\2\\]\7/\2\2]\n\3\2\2\2^_\7,\2\2_\f\3\2\2\2`a\7\61\2"+
		"\2a\16\3\2\2\2bc\7>\2\2c\20\3\2\2\2de\7@\2\2e\22\3\2\2\2fg\7>\2\2gh\7"+
		"?\2\2h\24\3\2\2\2ij\7@\2\2jk\7?\2\2k\26\3\2\2\2lm\7?\2\2m\30\3\2\2\2n"+
		"o\7#\2\2op\7?\2\2p\32\3\2\2\2qr\7*\2\2r\34\3\2\2\2st\7+\2\2t\36\3\2\2"+
		"\2uv\7.\2\2v \3\2\2\2wx\7R\2\2xy\7T\2\2yz\7Q\2\2z{\7I\2\2{|\7T\2\2|}\7"+
		"C\2\2}~\7O\2\2~\"\3\2\2\2\177\u0080\7D\2\2\u0080\u0081\7G\2\2\u0081\u0082"+
		"\7I\2\2\u0082\u0083\7K\2\2\u0083\u0084\7P\2\2\u0084$\3\2\2\2\u0085\u0086"+
		"\7G\2\2\u0086\u0087\7P\2\2\u0087\u0088\7F\2\2\u0088&\3\2\2\2\u0089\u008a"+
		"\7H\2\2\u008a\u008b\7W\2\2\u008b\u008c\7P\2\2\u008c\u008d\7E\2\2\u008d"+
		"\u008e\7V\2\2\u008e\u008f\7K\2\2\u008f\u0090\7Q\2\2\u0090\u0091\7P\2\2"+
		"\u0091(\3\2\2\2\u0092\u0093\7T\2\2\u0093\u0094\7G\2\2\u0094\u0095\7V\2"+
		"\2\u0095\u0096\7W\2\2\u0096\u0097\7T\2\2\u0097\u0098\7P\2\2\u0098*\3\2"+
		"\2\2\u0099\u009a\7K\2\2\u009a\u009b\7H\2\2\u009b,\3\2\2\2\u009c\u009d"+
		"\7G\2\2\u009d\u009e\7N\2\2\u009e\u009f\7U\2\2\u009f\u00a0\7G\2\2\u00a0"+
		".\3\2\2\2\u00a1\u00a2\7G\2\2\u00a2\u00a3\7P\2\2\u00a3\u00a4\7F\2\2\u00a4"+
		"\u00a5\7K\2\2\u00a5\u00a6\7H\2\2\u00a6\60\3\2\2\2\u00a7\u00a8\7Y\2\2\u00a8"+
		"\u00a9\7J\2\2\u00a9\u00aa\7K\2\2\u00aa\u00ab\7N\2\2\u00ab\u00ac\7G\2\2"+
		"\u00ac\62\3\2\2\2\u00ad\u00ae\7E\2\2\u00ae\u00af\7Q\2\2\u00af\u00b0\7"+
		"P\2\2\u00b0\u00b1\7V\2\2\u00b1\u00b2\7K\2\2\u00b2\u00b3\7P\2\2\u00b3\u00b4"+
		"\7W\2\2\u00b4\u00b5\7G\2\2\u00b5\64\3\2\2\2\u00b6\u00b7\7D\2\2\u00b7\u00b8"+
		"\7T\2\2\u00b8\u00b9\7G\2\2\u00b9\u00ba\7C\2\2\u00ba\u00bb\7M\2\2\u00bb"+
		"\66\3\2\2\2\u00bc\u00bd\7G\2\2\u00bd\u00be\7P\2\2\u00be\u00bf\7F\2\2\u00bf"+
		"\u00c0\7Y\2\2\u00c0\u00c1\7J\2\2\u00c1\u00c2\7K\2\2\u00c2\u00c3\7N\2\2"+
		"\u00c3\u00c4\7G\2\2\u00c48\3\2\2\2\u00c5\u00c6\7T\2\2\u00c6\u00c7\7G\2"+
		"\2\u00c7\u00c8\7C\2\2\u00c8\u00c9\7F\2\2\u00c9:\3\2\2\2\u00ca\u00cb\7"+
		"Y\2\2\u00cb\u00cc\7T\2\2\u00cc\u00cd\7K\2\2\u00cd\u00ce\7V\2\2\u00ce\u00cf"+
		"\7G\2\2\u00cf<\3\2\2\2\u00d0\u00d1\7X\2\2\u00d1\u00d2\7Q\2\2\u00d2\u00d3"+
		"\7K\2\2\u00d3\u00d4\7F\2\2\u00d4>\3\2\2\2\u00d5\u00d6\7K\2\2\u00d6\u00d7"+
		"\7P\2\2\u00d7\u00d8\7V\2\2\u00d8@\3\2\2\2\u00d9\u00da\7H\2\2\u00da\u00db"+
		"\7N\2\2\u00db\u00dc\7Q\2\2\u00dc\u00dd\7C\2\2\u00dd\u00de\7V\2\2\u00de"+
		"B\3\2\2\2\u00df\u00e0\7U\2\2\u00e0\u00e1\7V\2\2\u00e1\u00e2\7T\2\2\u00e2"+
		"\u00e3\7K\2\2\u00e3\u00e4\7P\2\2\u00e4\u00e5\7I\2\2\u00e5D\3\2\2\2\u00e6"+
		"\u00e8\5S*\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2"+
		"\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ee"+
		"\7\60\2\2\u00ed\u00ef\5S*\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1F\3\2\2\2\u00f2\u00f4\5S*\2\u00f3"+
		"\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6H\3\2\2\2\u00f7\u00fb\7$\2\2\u00f8\u00fa\n\2\2\2\u00f9\u00f8"+
		"\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7$\2\2\u00ffJ\3\2\2\2\u0100"+
		"\u0105\5Q)\2\u0101\u0104\5S*\2\u0102\u0104\5Q)\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0102\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106L\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u0109\7/\2\2\u0109\u010a"+
		"\7/\2\2\u010a\u010e\3\2\2\2\u010b\u010d\n\3\2\2\u010c\u010b\3\2\2\2\u010d"+
		"\u0110\3\2\2\2\u010e\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0112\3\2"+
		"\2\2\u0110\u010e\3\2\2\2\u0111\u0113\7\17\2\2\u0112\u0111\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\7\f\2\2\u0115\u0116\3\2"+
		"\2\2\u0116\u0117\b\'\2\2\u0117N\3\2\2\2\u0118\u011a\t\4\2\2\u0119\u0118"+
		"\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011d\3\2\2\2\u011d\u011e\b(\2\2\u011eP\3\2\2\2\u011f\u0120\t\5\2\2\u0120"+
		"R\3\2\2\2\u0121\u0122\t\6\2\2\u0122T\3\2\2\2\f\2\u00e9\u00f0\u00f5\u00fb"+
		"\u0103\u0105\u010e\u0112\u011b\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}