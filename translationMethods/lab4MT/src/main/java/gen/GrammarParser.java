package gen;

    import generator.helpers.*;
    import generator.Grammar;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, SPACE=17, 
		NAME=18, REGEXP=19, CODE=20;
	public static final int
		RULE_start = 0, RULE_constructGrammar = 1, RULE_header = 2, RULE_startState = 3, 
		RULE_state = 4, RULE_findState = 5, RULE_inhState = 6, RULE_syntState = 7, 
		RULE_findRule = 8, RULE_inhParameters = 9, RULE_codeLine = 10, RULE_tokens = 11, 
		RULE_findToken = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "constructGrammar", "header", "startState", "state", "findState", 
			"inhState", "syntState", "findRule", "inhParameters", "codeLine", "tokens", 
			"findToken"
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

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public Grammar grammar;
		public ConstructGrammarContext constructGrammar() {
			return getRuleContext(ConstructGrammarContext.class,0);
		}
		public TerminalNode EOF() { return getToken(GrammarParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);

		    ((StartContext)_localctx).grammar =  new Grammar();

		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			constructGrammar(_localctx.grammar);
			setState(27);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructGrammarContext extends ParserRuleContext {
		public Grammar grammar;
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public StartStateContext startState() {
			return getRuleContext(StartStateContext.class,0);
		}
		public StateContext state() {
			return getRuleContext(StateContext.class,0);
		}
		public TokensContext tokens() {
			return getRuleContext(TokensContext.class,0);
		}
		public ConstructGrammarContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ConstructGrammarContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_constructGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterConstructGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitConstructGrammar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitConstructGrammar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructGrammarContext constructGrammar(Grammar grammar) throws RecognitionException {
		ConstructGrammarContext _localctx = new ConstructGrammarContext(_ctx, getState(), grammar);
		enterRule(_localctx, 2, RULE_constructGrammar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			header(_localctx.grammar);
			setState(30);
			startState(_localctx.grammar);
			setState(31);
			state(_localctx.grammar);
			setState(32);
			tokens(_localctx.grammar);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HeaderContext extends ParserRuleContext {
		public Grammar grammar;
		public Token NAME;
		public TerminalNode NAME() { return getToken(GrammarParser.NAME, 0); }
		public HeaderContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public HeaderContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header(Grammar grammar) throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState(), grammar);
		enterRule(_localctx, 4, RULE_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			((HeaderContext)_localctx).NAME = match(NAME);
			setState(36);
			match(T__1);
			 _localctx.grammar.name = (((HeaderContext)_localctx).NAME!=null?((HeaderContext)_localctx).NAME.getText():null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StartStateContext extends ParserRuleContext {
		public Grammar grammar;
		public Token NAME;
		public TerminalNode NAME() { return getToken(GrammarParser.NAME, 0); }
		public StartStateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public StartStateContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_startState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterStartState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitStartState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitStartState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartStateContext startState(Grammar grammar) throws RecognitionException {
		StartStateContext _localctx = new StartStateContext(_ctx, getState(), grammar);
		enterRule(_localctx, 6, RULE_startState);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__2);
			setState(40);
			match(T__3);
			setState(41);
			((StartStateContext)_localctx).NAME = match(NAME);
			setState(42);
			match(T__1);
			 _localctx.grammar.startStateName = (((StartStateContext)_localctx).NAME!=null?((StartStateContext)_localctx).NAME.getText():null); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StateContext extends ParserRuleContext {
		public Grammar grammar;
		public FindStateContext findState;
		public List<FindStateContext> findState() {
			return getRuleContexts(FindStateContext.class);
		}
		public FindStateContext findState(int i) {
			return getRuleContext(FindStateContext.class,i);
		}
		public StateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public StateContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_state; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StateContext state(Grammar grammar) throws RecognitionException {
		StateContext _localctx = new StateContext(_ctx, getState(), grammar);
		enterRule(_localctx, 8, RULE_state);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__4);
			setState(46);
			match(T__5);
			setState(50); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				((StateContext)_localctx).findState = findState();
				 _localctx.grammar.addState(((StateContext)_localctx).findState.currState); 
				}
				}
				setState(52); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			setState(54);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FindStateContext extends ParserRuleContext {
		public CustomState currState;
		public Token NAME;
		public FindRuleContext r0;
		public FindRuleContext rNext;
		public TerminalNode NAME() { return getToken(GrammarParser.NAME, 0); }
		public InhStateContext inhState() {
			return getRuleContext(InhStateContext.class,0);
		}
		public SyntStateContext syntState() {
			return getRuleContext(SyntStateContext.class,0);
		}
		public List<FindRuleContext> findRule() {
			return getRuleContexts(FindRuleContext.class);
		}
		public FindRuleContext findRule(int i) {
			return getRuleContext(FindRuleContext.class,i);
		}
		public FindStateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_findState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFindState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFindState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitFindState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FindStateContext findState() throws RecognitionException {
		FindStateContext _localctx = new FindStateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_findState);

		    ((FindStateContext)_localctx).currState =  new CustomState();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			((FindStateContext)_localctx).NAME = match(NAME);
			 _localctx.currState.name = (((FindStateContext)_localctx).NAME!=null?((FindStateContext)_localctx).NAME.getText():null); 
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(58);
				match(T__7);
				setState(59);
				inhState(_localctx.currState);
				setState(60);
				match(T__8);
				}
			}

			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(64);
				match(T__9);
				setState(65);
				match(T__7);
				setState(66);
				syntState(_localctx.currState);
				setState(67);
				match(T__8);
				}
			}

			setState(71);
			match(T__10);
			{
			setState(72);
			((FindStateContext)_localctx).r0 = findRule();
			 _localctx.currState.addRule(((FindStateContext)_localctx).r0.currRule); 
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11) {
				{
				{
				setState(75);
				match(T__11);
				setState(76);
				((FindStateContext)_localctx).rNext = findRule();
				 _localctx.currState.addRule(((FindStateContext)_localctx).rNext.currRule); 
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InhStateContext extends ParserRuleContext {
		public CustomState currState;
		public Token type0;
		public Token name0;
		public Token typeNext;
		public Token nameNext;
		public List<TerminalNode> NAME() { return getTokens(GrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(GrammarParser.NAME, i);
		}
		public InhStateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public InhStateContext(ParserRuleContext parent, int invokingState, CustomState currState) {
			super(parent, invokingState);
			this.currState = currState;
		}
		@Override public int getRuleIndex() { return RULE_inhState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterInhState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitInhState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitInhState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InhStateContext inhState(CustomState currState) throws RecognitionException {
		InhStateContext _localctx = new InhStateContext(_ctx, getState(), currState);
		enterRule(_localctx, 12, RULE_inhState);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(86);
			((InhStateContext)_localctx).type0 = match(NAME);
			setState(87);
			((InhStateContext)_localctx).name0 = match(NAME);
			 _localctx.currState.addParameter((((InhStateContext)_localctx).type0!=null?((InhStateContext)_localctx).type0.getText():null), (((InhStateContext)_localctx).name0!=null?((InhStateContext)_localctx).name0.getText():null)); 
			}
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(90);
				match(T__12);
				setState(91);
				((InhStateContext)_localctx).typeNext = match(NAME);
				setState(92);
				((InhStateContext)_localctx).nameNext = match(NAME);
				 _localctx.currState.addParameter((((InhStateContext)_localctx).typeNext!=null?((InhStateContext)_localctx).typeNext.getText():null), (((InhStateContext)_localctx).nameNext!=null?((InhStateContext)_localctx).nameNext.getText():null)); 
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SyntStateContext extends ParserRuleContext {
		public CustomState currState;
		public Token type0;
		public Token name0;
		public Token typeNext;
		public Token nameNext;
		public List<TerminalNode> NAME() { return getTokens(GrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(GrammarParser.NAME, i);
		}
		public SyntStateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public SyntStateContext(ParserRuleContext parent, int invokingState, CustomState currState) {
			super(parent, invokingState);
			this.currState = currState;
		}
		@Override public int getRuleIndex() { return RULE_syntState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterSyntState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitSyntState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitSyntState(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SyntStateContext syntState(CustomState currState) throws RecognitionException {
		SyntStateContext _localctx = new SyntStateContext(_ctx, getState(), currState);
		enterRule(_localctx, 14, RULE_syntState);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(99);
			((SyntStateContext)_localctx).type0 = match(NAME);
			setState(100);
			((SyntStateContext)_localctx).name0 = match(NAME);
			 _localctx.currState.addReturnValue((((SyntStateContext)_localctx).type0!=null?((SyntStateContext)_localctx).type0.getText():null), (((SyntStateContext)_localctx).name0!=null?((SyntStateContext)_localctx).name0.getText():null)); 
			}
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(103);
				match(T__12);
				setState(104);
				((SyntStateContext)_localctx).typeNext = match(NAME);
				setState(105);
				((SyntStateContext)_localctx).nameNext = match(NAME);
				 _localctx.currState.addReturnValue((((SyntStateContext)_localctx).typeNext!=null?((SyntStateContext)_localctx).typeNext.getText():null), (((SyntStateContext)_localctx).nameNext!=null?((SyntStateContext)_localctx).nameNext.getText():null)); 
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FindRuleContext extends ParserRuleContext {
		public CustomRule currRule;
		public StringBuilder parameters;
		public StringBuilder codeLines;
		public Token NAME;
		public List<TerminalNode> NAME() { return getTokens(GrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(GrammarParser.NAME, i);
		}
		public List<InhParametersContext> inhParameters() {
			return getRuleContexts(InhParametersContext.class);
		}
		public InhParametersContext inhParameters(int i) {
			return getRuleContext(InhParametersContext.class,i);
		}
		public List<CodeLineContext> codeLine() {
			return getRuleContexts(CodeLineContext.class);
		}
		public CodeLineContext codeLine(int i) {
			return getRuleContext(CodeLineContext.class,i);
		}
		public FindRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_findRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFindRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFindRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitFindRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FindRuleContext findRule() throws RecognitionException {
		FindRuleContext _localctx = new FindRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_findRule);

		    ((FindRuleContext)_localctx).currRule =  new CustomRule();
		    ((FindRuleContext)_localctx).parameters =  new StringBuilder();
		    ((FindRuleContext)_localctx).codeLines =  new StringBuilder();

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				((FindRuleContext)_localctx).NAME = match(NAME);
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(113);
					match(T__7);
					setState(114);
					inhParameters(_localctx.parameters);
					setState(115);
					match(T__8);
					}
				}

				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CODE) {
					{
					setState(119);
					codeLine(_localctx.codeLines);
					}
				}


				    _localctx.currRule.addRule((((FindRuleContext)_localctx).NAME!=null?((FindRuleContext)_localctx).NAME.getText():null), _localctx.parameters.toString(), _localctx.codeLines.toString());
				    ((FindRuleContext)_localctx).parameters =  new StringBuilder();
				    ((FindRuleContext)_localctx).codeLines =  new StringBuilder();

				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==NAME );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InhParametersContext extends ParserRuleContext {
		public StringBuilder parameters;
		public Token name0;
		public Token nameNext;
		public List<TerminalNode> NAME() { return getTokens(GrammarParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(GrammarParser.NAME, i);
		}
		public InhParametersContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public InhParametersContext(ParserRuleContext parent, int invokingState, StringBuilder parameters) {
			super(parent, invokingState);
			this.parameters = parameters;
		}
		@Override public int getRuleIndex() { return RULE_inhParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterInhParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitInhParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitInhParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InhParametersContext inhParameters(StringBuilder parameters) throws RecognitionException {
		InhParametersContext _localctx = new InhParametersContext(_ctx, getState(), parameters);
		enterRule(_localctx, 18, RULE_inhParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(127);
			((InhParametersContext)_localctx).name0 = match(NAME);
			 _localctx.parameters.append((((InhParametersContext)_localctx).name0!=null?((InhParametersContext)_localctx).name0.getText():null)); 
			}
			setState(135);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__12) {
				{
				{
				setState(130);
				match(T__12);
				setState(131);
				((InhParametersContext)_localctx).nameNext = match(NAME);
				 _localctx.parameters.append(", " + (((InhParametersContext)_localctx).nameNext!=null?((InhParametersContext)_localctx).nameNext.getText():null)); 
				}
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CodeLineContext extends ParserRuleContext {
		public StringBuilder codeLines;
		public Token CODE;
		public TerminalNode CODE() { return getToken(GrammarParser.CODE, 0); }
		public CodeLineContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CodeLineContext(ParserRuleContext parent, int invokingState, StringBuilder codeLines) {
			super(parent, invokingState);
			this.codeLines = codeLines;
		}
		@Override public int getRuleIndex() { return RULE_codeLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterCodeLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitCodeLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitCodeLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CodeLineContext codeLine(StringBuilder codeLines) throws RecognitionException {
		CodeLineContext _localctx = new CodeLineContext(_ctx, getState(), codeLines);
		enterRule(_localctx, 20, RULE_codeLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			((CodeLineContext)_localctx).CODE = match(CODE);
			 _localctx.codeLines.append((((CodeLineContext)_localctx).CODE!=null?((CodeLineContext)_localctx).CODE.getText():null).substring(1, (((CodeLineContext)_localctx).CODE!=null?((CodeLineContext)_localctx).CODE.getText():null).length() - 1)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TokensContext extends ParserRuleContext {
		public Grammar grammar;
		public List<FindTokenContext> findToken() {
			return getRuleContexts(FindTokenContext.class);
		}
		public FindTokenContext findToken(int i) {
			return getRuleContext(FindTokenContext.class,i);
		}
		public TokensContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TokensContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_tokens; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterTokens(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitTokens(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitTokens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokensContext tokens(Grammar grammar) throws RecognitionException {
		TokensContext _localctx = new TokensContext(_ctx, getState(), grammar);
		enterRule(_localctx, 22, RULE_tokens);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__13);
			setState(142);
			match(T__5);
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NAME) {
				{
				{
				setState(143);
				findToken(grammar);
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(149);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FindTokenContext extends ParserRuleContext {
		public Grammar grammar;
		public boolean isSkipToken;
		public Token NAME;
		public Token REGEXP;
		public TerminalNode NAME() { return getToken(GrammarParser.NAME, 0); }
		public TerminalNode REGEXP() { return getToken(GrammarParser.REGEXP, 0); }
		public FindTokenContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FindTokenContext(ParserRuleContext parent, int invokingState, Grammar grammar) {
			super(parent, invokingState);
			this.grammar = grammar;
		}
		@Override public int getRuleIndex() { return RULE_findToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).enterFindToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GrammarListener ) ((GrammarListener)listener).exitFindToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GrammarVisitor) return ((GrammarVisitor<? extends T>)visitor).visitFindToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FindTokenContext findToken(Grammar grammar) throws RecognitionException {
		FindTokenContext _localctx = new FindTokenContext(_ctx, getState(), grammar);
		enterRule(_localctx, 24, RULE_findToken);

		    ((FindTokenContext)_localctx).isSkipToken =  false;

		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			((FindTokenContext)_localctx).NAME = match(NAME);
			setState(152);
			match(T__10);
			setState(153);
			((FindTokenContext)_localctx).REGEXP = match(REGEXP);
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(154);
				match(T__14);
				setState(155);
				match(T__15);
				 ((FindTokenContext)_localctx).isSkipToken =  true; 
				}
			}

			setState(159);
			match(T__1);
			 _localctx.grammar.addToken((((FindTokenContext)_localctx).NAME!=null?((FindTokenContext)_localctx).NAME.getText():null), (((FindTokenContext)_localctx).REGEXP!=null?((FindTokenContext)_localctx).REGEXP.getText():null), _localctx.isSkipToken); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u00a5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\6\6\65\n\6"+
		"\r\6\16\6\66\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7A\n\7\3\7\3\7\3\7\3\7"+
		"\3\7\5\7H\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7R\n\7\f\7\16\7U\13\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\ba\n\b\f\b\16\bd\13\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\7\tn\n\t\f\t\16\tq\13\t\3\n\3\n\3\n\3\n\3\n"+
		"\5\nx\n\n\3\n\5\n{\n\n\3\n\6\n~\n\n\r\n\16\n\177\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\7\13\u0088\n\13\f\13\16\13\u008b\13\13\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\7\r\u0093\n\r\f\r\16\r\u0096\13\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00a0\n\16\3\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\2\2\2\u00a3\2\34\3\2\2\2\4\37\3\2\2\2\6$\3\2\2\2\b)\3\2\2"+
		"\2\n/\3\2\2\2\f:\3\2\2\2\16X\3\2\2\2\20e\3\2\2\2\22}\3\2\2\2\24\u0081"+
		"\3\2\2\2\26\u008c\3\2\2\2\30\u008f\3\2\2\2\32\u0099\3\2\2\2\34\35\5\4"+
		"\3\2\35\36\7\2\2\3\36\3\3\2\2\2\37 \5\6\4\2 !\5\b\5\2!\"\5\n\6\2\"#\5"+
		"\30\r\2#\5\3\2\2\2$%\7\3\2\2%&\7\24\2\2&\'\7\4\2\2\'(\b\4\1\2(\7\3\2\2"+
		"\2)*\7\5\2\2*+\7\6\2\2+,\7\24\2\2,-\7\4\2\2-.\b\5\1\2.\t\3\2\2\2/\60\7"+
		"\7\2\2\60\64\7\b\2\2\61\62\5\f\7\2\62\63\b\6\1\2\63\65\3\2\2\2\64\61\3"+
		"\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\678\3\2\2\289\7\t\2\2"+
		"9\13\3\2\2\2:;\7\24\2\2;@\b\7\1\2<=\7\n\2\2=>\5\16\b\2>?\7\13\2\2?A\3"+
		"\2\2\2@<\3\2\2\2@A\3\2\2\2AG\3\2\2\2BC\7\f\2\2CD\7\n\2\2DE\5\20\t\2EF"+
		"\7\13\2\2FH\3\2\2\2GB\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\7\r\2\2JK\5\22\n\2"+
		"KL\b\7\1\2LS\3\2\2\2MN\7\16\2\2NO\5\22\n\2OP\b\7\1\2PR\3\2\2\2QM\3\2\2"+
		"\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TV\3\2\2\2US\3\2\2\2VW\7\4\2\2W\r\3\2"+
		"\2\2XY\7\24\2\2YZ\7\24\2\2Z[\b\b\1\2[b\3\2\2\2\\]\7\17\2\2]^\7\24\2\2"+
		"^_\7\24\2\2_a\b\b\1\2`\\\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\17\3\2"+
		"\2\2db\3\2\2\2ef\7\24\2\2fg\7\24\2\2gh\b\t\1\2ho\3\2\2\2ij\7\17\2\2jk"+
		"\7\24\2\2kl\7\24\2\2ln\b\t\1\2mi\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2"+
		"p\21\3\2\2\2qo\3\2\2\2rw\7\24\2\2st\7\n\2\2tu\5\24\13\2uv\7\13\2\2vx\3"+
		"\2\2\2ws\3\2\2\2wx\3\2\2\2xz\3\2\2\2y{\5\26\f\2zy\3\2\2\2z{\3\2\2\2{|"+
		"\3\2\2\2|~\b\n\1\2}r\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2"+
		"\2\u0080\23\3\2\2\2\u0081\u0082\7\24\2\2\u0082\u0083\b\13\1\2\u0083\u0089"+
		"\3\2\2\2\u0084\u0085\7\17\2\2\u0085\u0086\7\24\2\2\u0086\u0088\b\13\1"+
		"\2\u0087\u0084\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a"+
		"\3\2\2\2\u008a\25\3\2\2\2\u008b\u0089\3\2\2\2\u008c\u008d\7\26\2\2\u008d"+
		"\u008e\b\f\1\2\u008e\27\3\2\2\2\u008f\u0090\7\20\2\2\u0090\u0094\7\b\2"+
		"\2\u0091\u0093\5\32\16\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0097\u0098\7\t\2\2\u0098\31\3\2\2\2\u0099\u009a\7\24\2\2\u009a\u009b"+
		"\7\r\2\2\u009b\u009f\7\25\2\2\u009c\u009d\7\21\2\2\u009d\u009e\7\22\2"+
		"\2\u009e\u00a0\b\16\1\2\u009f\u009c\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a2\7\4\2\2\u00a2\u00a3\b\16\1\2\u00a3\33\3\2\2"+
		"\2\16\66@GSbowz\177\u0089\u0094\u009f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}