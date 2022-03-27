// Generated from /Users/sj/IdeaProjects/lab3MT/src/main/java/ToImp.g4 by ANTLR 4.9.1

package translator;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ToImpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, IF=2, PRINT=3, IF_TOKEN=4, EXPR_TOKEN=5, VAR=6, NUM=7, SPACE_SKIP=8;
	public static final int
		RULE_st = 0, RULE_env = 1, RULE_body = 2, RULE_condition = 3, RULE_expr = 4, 
		RULE_exprToken = 5, RULE_ifToken = 6, RULE_token = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"st", "env", "body", "condition", "expr", "exprToken", "ifToken", "token"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'if'", "'print'", null, null, null, null, "' '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "IF", "PRINT", "IF_TOKEN", "EXPR_TOKEN", "VAR", "NUM", "SPACE_SKIP"
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
	public String getGrammarFileName() { return "ToImp.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ToImpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StContext extends ParserRuleContext {
		public Env _env;
		public BodyContext body;
		public ConditionContext condition;
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public StContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_st; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterSt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitSt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StContext st() throws RecognitionException {
		StContext _localctx = new StContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_st);
		try {
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				((StContext)_localctx).body = body();

				        ((StContext)_localctx)._env =  new Env(null, ((StContext)_localctx).body._body);
				        translator.Utils.st(_localctx._env);
				    
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(19);
				((StContext)_localctx).condition = condition();

				        ((StContext)_localctx)._env =  new Env(((StContext)_localctx).condition._cond, null);
				        translator.Utils.st(_localctx._env);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class EnvContext extends ParserRuleContext {
		public Env _env;
		public BodyContext body;
		public ConditionContext condition;
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public EnvContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_env; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterEnv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitEnv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitEnv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnvContext env() throws RecognitionException {
		EnvContext _localctx = new EnvContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_env);
		try {
			setState(30);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				((EnvContext)_localctx).body = body();

				        ((EnvContext)_localctx)._env =  new Env(null, ((EnvContext)_localctx).body._body);
				    
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				((EnvContext)_localctx).condition = condition();

				        ((EnvContext)_localctx)._env =  new Env(((EnvContext)_localctx).condition._cond, null);
				    
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BodyContext extends ParserRuleContext {
		public Body _body;
		public ExprContext expr;
		public Token VAR;
		public TerminalNode PRINT() { return getToken(ToImpParser.PRINT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode VAR() { return getToken(ToImpParser.VAR, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_body);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(PRINT);
				setState(33);
				((BodyContext)_localctx).expr = expr();
				((BodyContext)_localctx)._body =  new Body(((BodyContext)_localctx).expr._expr);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(36);
				match(T__0);
				setState(37);
				((BodyContext)_localctx).VAR = match(VAR);
				setState(38);
				((BodyContext)_localctx).expr = expr();
				((BodyContext)_localctx)._body =  new Body(((BodyContext)_localctx).expr._expr, (((BodyContext)_localctx).VAR!=null?((BodyContext)_localctx).VAR.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ConditionContext extends ParserRuleContext {
		public Cond _cond;
		public IfTokenContext ifToken;
		public ExprContext expr1;
		public ExprContext expr2;
		public EnvContext envIf;
		public EnvContext envElse;
		public TerminalNode IF() { return getToken(ToImpParser.IF, 0); }
		public IfTokenContext ifToken() {
			return getRuleContext(IfTokenContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<EnvContext> env() {
			return getRuleContexts(EnvContext.class);
		}
		public EnvContext env(int i) {
			return getRuleContext(EnvContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_condition);
		try {
			setState(58);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				match(IF);
				setState(44);
				((ConditionContext)_localctx).ifToken = ifToken();
				setState(45);
				((ConditionContext)_localctx).expr1 = expr();
				setState(46);
				((ConditionContext)_localctx).expr2 = expr();
				setState(47);
				((ConditionContext)_localctx).envIf = env();
				setState(48);
				((ConditionContext)_localctx).envElse = env();

				        ((ConditionContext)_localctx)._cond =  new Cond((((ConditionContext)_localctx).ifToken!=null?_input.getText(((ConditionContext)_localctx).ifToken.start,((ConditionContext)_localctx).ifToken.stop):null), ((ConditionContext)_localctx).expr1._expr, ((ConditionContext)_localctx).expr2._expr, ((ConditionContext)_localctx).envIf._env, ((ConditionContext)_localctx).envElse._env);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(IF);
				setState(52);
				((ConditionContext)_localctx).ifToken = ifToken();
				setState(53);
				((ConditionContext)_localctx).expr1 = expr();
				setState(54);
				((ConditionContext)_localctx).expr2 = expr();
				setState(55);
				((ConditionContext)_localctx).envIf = env();

				        ((ConditionContext)_localctx)._cond =  new Cond((((ConditionContext)_localctx).ifToken!=null?_input.getText(((ConditionContext)_localctx).ifToken.start,((ConditionContext)_localctx).ifToken.stop):null), ((ConditionContext)_localctx).expr1._expr, ((ConditionContext)_localctx).expr2._expr, ((ConditionContext)_localctx).envIf._env, null);
				    
				}
				break;
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

	public static class ExprContext extends ParserRuleContext {
		public Expr _expr;
		public ExprTokenContext exprToken;
		public ExprContext expr1;
		public ExprContext expr2;
		public ExprContext expr3;
		public TokenContext token;
		public ExprTokenContext exprToken() {
			return getRuleContext(ExprTokenContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TokenContext token() {
			return getRuleContext(TokenContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expr);
		try {
			setState(74);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				((ExprContext)_localctx).exprToken = exprToken();
				setState(61);
				((ExprContext)_localctx).expr1 = expr();
				setState(62);
				((ExprContext)_localctx).expr2 = expr();
				setState(63);
				((ExprContext)_localctx).expr3 = expr();

				        ((ExprContext)_localctx)._expr =  new Expr((((ExprContext)_localctx).exprToken!=null?_input.getText(((ExprContext)_localctx).exprToken.start,((ExprContext)_localctx).exprToken.stop):null), ((ExprContext)_localctx).expr1._expr, ((ExprContext)_localctx).expr2._expr, ((ExprContext)_localctx).expr3._expr);
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				((ExprContext)_localctx).exprToken = exprToken();
				setState(67);
				((ExprContext)_localctx).expr1 = expr();
				setState(68);
				((ExprContext)_localctx).expr2 = expr();

				        ((ExprContext)_localctx)._expr =  new Expr((((ExprContext)_localctx).exprToken!=null?_input.getText(((ExprContext)_localctx).exprToken.start,((ExprContext)_localctx).exprToken.stop):null), ((ExprContext)_localctx).expr1._expr, ((ExprContext)_localctx).expr2._expr);
				    
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				((ExprContext)_localctx).token = token();

				        _localctx._expr = new Expr((((ExprContext)_localctx).token!=null?_input.getText(((ExprContext)_localctx).token.start,((ExprContext)_localctx).token.stop):null));
				    
				}
				break;
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

	public static class ExprTokenContext extends ParserRuleContext {
		public String _token;
		public Token EXPR_TOKEN;
		public TerminalNode EXPR_TOKEN() { return getToken(ToImpParser.EXPR_TOKEN, 0); }
		public ExprTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterExprToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitExprToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitExprToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprTokenContext exprToken() throws RecognitionException {
		ExprTokenContext _localctx = new ExprTokenContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_exprToken);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			((ExprTokenContext)_localctx).EXPR_TOKEN = match(EXPR_TOKEN);
			((ExprTokenContext)_localctx)._token =  (((ExprTokenContext)_localctx).EXPR_TOKEN!=null?((ExprTokenContext)_localctx).EXPR_TOKEN.getText():null);
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

	public static class IfTokenContext extends ParserRuleContext {
		public String _token;
		public Token IF_TOKEN;
		public TerminalNode IF_TOKEN() { return getToken(ToImpParser.IF_TOKEN, 0); }
		public IfTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterIfToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitIfToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitIfToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfTokenContext ifToken() throws RecognitionException {
		IfTokenContext _localctx = new IfTokenContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifToken);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			((IfTokenContext)_localctx).IF_TOKEN = match(IF_TOKEN);
			((IfTokenContext)_localctx)._token =  (((IfTokenContext)_localctx).IF_TOKEN!=null?((IfTokenContext)_localctx).IF_TOKEN.getText():null);
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

	public static class TokenContext extends ParserRuleContext {
		public String _token;
		public Token NUM;
		public Token VAR;
		public TerminalNode NUM() { return getToken(ToImpParser.NUM, 0); }
		public TerminalNode VAR() { return getToken(ToImpParser.VAR, 0); }
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ToImpListener ) ((ToImpListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ToImpVisitor ) return ((ToImpVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_token);
		try {
			setState(86);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				((TokenContext)_localctx).NUM = match(NUM);
				((TokenContext)_localctx)._token =  (((TokenContext)_localctx).NUM!=null?((TokenContext)_localctx).NUM.getText():null);
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((TokenContext)_localctx).VAR = match(VAR);
				((TokenContext)_localctx)._token =  (((TokenContext)_localctx).VAR!=null?((TokenContext)_localctx).VAR.getText():null);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n[\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\5\2\31\n\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3!\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\5\4,\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\tY\n\t\3"+
		"\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2Y\2\30\3\2\2\2\4 \3\2\2\2\6+\3\2\2\2\b"+
		"<\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16Q\3\2\2\2\20X\3\2\2\2\22\23\5\6\4\2"+
		"\23\24\b\2\1\2\24\31\3\2\2\2\25\26\5\b\5\2\26\27\b\2\1\2\27\31\3\2\2\2"+
		"\30\22\3\2\2\2\30\25\3\2\2\2\31\3\3\2\2\2\32\33\5\6\4\2\33\34\b\3\1\2"+
		"\34!\3\2\2\2\35\36\5\b\5\2\36\37\b\3\1\2\37!\3\2\2\2 \32\3\2\2\2 \35\3"+
		"\2\2\2!\5\3\2\2\2\"#\7\5\2\2#$\5\n\6\2$%\b\4\1\2%,\3\2\2\2&\'\7\3\2\2"+
		"\'(\7\b\2\2()\5\n\6\2)*\b\4\1\2*,\3\2\2\2+\"\3\2\2\2+&\3\2\2\2,\7\3\2"+
		"\2\2-.\7\4\2\2./\5\16\b\2/\60\5\n\6\2\60\61\5\n\6\2\61\62\5\4\3\2\62\63"+
		"\5\4\3\2\63\64\b\5\1\2\64=\3\2\2\2\65\66\7\4\2\2\66\67\5\16\b\2\678\5"+
		"\n\6\289\5\n\6\29:\5\4\3\2:;\b\5\1\2;=\3\2\2\2<-\3\2\2\2<\65\3\2\2\2="+
		"\t\3\2\2\2>?\5\f\7\2?@\5\n\6\2@A\5\n\6\2AB\5\n\6\2BC\b\6\1\2CM\3\2\2\2"+
		"DE\5\f\7\2EF\5\n\6\2FG\5\n\6\2GH\b\6\1\2HM\3\2\2\2IJ\5\20\t\2JK\b\6\1"+
		"\2KM\3\2\2\2L>\3\2\2\2LD\3\2\2\2LI\3\2\2\2M\13\3\2\2\2NO\7\7\2\2OP\b\7"+
		"\1\2P\r\3\2\2\2QR\7\6\2\2RS\b\b\1\2S\17\3\2\2\2TU\7\t\2\2UY\b\t\1\2VW"+
		"\7\b\2\2WY\b\t\1\2XT\3\2\2\2XV\3\2\2\2Y\21\3\2\2\2\b\30 +<LX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}