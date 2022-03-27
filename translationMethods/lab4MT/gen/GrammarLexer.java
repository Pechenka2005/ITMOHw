// Generated from /home/vankataganai/Documents/university/TM/lab4/src/main/java/Grammar.g4 by ANTLR 4.9.2

    import generator.helpers.*;
    import generator.Grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, SPACE=17, 
		NAME=18, REGEXP=19, CODE=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "SPACE", 
			"NAME", "REGEXP", "CODE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'@grammar'", "';'", "'@startState'", "'='", "'@states'", "'{'", 
			"'}'", "'['", "']'", "'returns'", "':'", "'|'", "','", "'@tokens'", "'->'", 
			"'skip'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "SPACE", "NAME", "REGEXP", "CODE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u008f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\6\22t\n\22\r\22\16\22u\3\22\3\22\3\23\6\23{\n\23\r\23\16\23|\3\24\3\24"+
		"\7\24\u0081\n\24\f\24\16\24\u0084\13\24\3\24\3\24\3\25\3\25\6\25\u008a"+
		"\n\25\r\25\16\25\u008b\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2"+
		"\6\5\2\13\f\17\17\"\"\4\2C\\c|\3\2$$\3\2bb\2\u0092\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5\64\3\2\2\2\7\66\3\2\2\2\tB\3\2\2\2\13D"+
		"\3\2\2\2\rL\3\2\2\2\17N\3\2\2\2\21P\3\2\2\2\23R\3\2\2\2\25T\3\2\2\2\27"+
		"\\\3\2\2\2\31^\3\2\2\2\33`\3\2\2\2\35b\3\2\2\2\37j\3\2\2\2!m\3\2\2\2#"+
		"s\3\2\2\2%z\3\2\2\2\'~\3\2\2\2)\u0087\3\2\2\2+,\7B\2\2,-\7i\2\2-.\7t\2"+
		"\2./\7c\2\2/\60\7o\2\2\60\61\7o\2\2\61\62\7c\2\2\62\63\7t\2\2\63\4\3\2"+
		"\2\2\64\65\7=\2\2\65\6\3\2\2\2\66\67\7B\2\2\678\7u\2\289\7v\2\29:\7c\2"+
		"\2:;\7t\2\2;<\7v\2\2<=\7U\2\2=>\7v\2\2>?\7c\2\2?@\7v\2\2@A\7g\2\2A\b\3"+
		"\2\2\2BC\7?\2\2C\n\3\2\2\2DE\7B\2\2EF\7u\2\2FG\7v\2\2GH\7c\2\2HI\7v\2"+
		"\2IJ\7g\2\2JK\7u\2\2K\f\3\2\2\2LM\7}\2\2M\16\3\2\2\2NO\7\177\2\2O\20\3"+
		"\2\2\2PQ\7]\2\2Q\22\3\2\2\2RS\7_\2\2S\24\3\2\2\2TU\7t\2\2UV\7g\2\2VW\7"+
		"v\2\2WX\7w\2\2XY\7t\2\2YZ\7p\2\2Z[\7u\2\2[\26\3\2\2\2\\]\7<\2\2]\30\3"+
		"\2\2\2^_\7~\2\2_\32\3\2\2\2`a\7.\2\2a\34\3\2\2\2bc\7B\2\2cd\7v\2\2de\7"+
		"q\2\2ef\7m\2\2fg\7g\2\2gh\7p\2\2hi\7u\2\2i\36\3\2\2\2jk\7/\2\2kl\7@\2"+
		"\2l \3\2\2\2mn\7u\2\2no\7m\2\2op\7k\2\2pq\7r\2\2q\"\3\2\2\2rt\t\2\2\2"+
		"sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\b\22\2\2x$\3\2\2"+
		"\2y{\t\3\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}&\3\2\2\2~\u0082"+
		"\7$\2\2\177\u0081\n\4\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082"+
		"\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0085\u0086\7$\2\2\u0086(\3\2\2\2\u0087\u0089\7b\2\2\u0088\u008a"+
		"\n\5\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\7b\2\2\u008e*\3\2\2\2\7"+
		"\2u|\u0082\u008b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}