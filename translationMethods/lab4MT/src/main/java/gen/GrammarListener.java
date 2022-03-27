package gen;

    import generator.helpers.*;
    import generator.Grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(GrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(GrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#constructGrammar}.
	 * @param ctx the parse tree
	 */
	void enterConstructGrammar(GrammarParser.ConstructGrammarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#constructGrammar}.
	 * @param ctx the parse tree
	 */
	void exitConstructGrammar(GrammarParser.ConstructGrammarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#startState}.
	 * @param ctx the parse tree
	 */
	void enterStartState(GrammarParser.StartStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#startState}.
	 * @param ctx the parse tree
	 */
	void exitStartState(GrammarParser.StartStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#state}.
	 * @param ctx the parse tree
	 */
	void enterState(GrammarParser.StateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#state}.
	 * @param ctx the parse tree
	 */
	void exitState(GrammarParser.StateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#findState}.
	 * @param ctx the parse tree
	 */
	void enterFindState(GrammarParser.FindStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#findState}.
	 * @param ctx the parse tree
	 */
	void exitFindState(GrammarParser.FindStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#inhState}.
	 * @param ctx the parse tree
	 */
	void enterInhState(GrammarParser.InhStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#inhState}.
	 * @param ctx the parse tree
	 */
	void exitInhState(GrammarParser.InhStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#syntState}.
	 * @param ctx the parse tree
	 */
	void enterSyntState(GrammarParser.SyntStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#syntState}.
	 * @param ctx the parse tree
	 */
	void exitSyntState(GrammarParser.SyntStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#findRule}.
	 * @param ctx the parse tree
	 */
	void enterFindRule(GrammarParser.FindRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#findRule}.
	 * @param ctx the parse tree
	 */
	void exitFindRule(GrammarParser.FindRuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#inhParameters}.
	 * @param ctx the parse tree
	 */
	void enterInhParameters(GrammarParser.InhParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#inhParameters}.
	 * @param ctx the parse tree
	 */
	void exitInhParameters(GrammarParser.InhParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#codeLine}.
	 * @param ctx the parse tree
	 */
	void enterCodeLine(GrammarParser.CodeLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#codeLine}.
	 * @param ctx the parse tree
	 */
	void exitCodeLine(GrammarParser.CodeLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#tokens}.
	 * @param ctx the parse tree
	 */
	void enterTokens(GrammarParser.TokensContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#tokens}.
	 * @param ctx the parse tree
	 */
	void exitTokens(GrammarParser.TokensContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#findToken}.
	 * @param ctx the parse tree
	 */
	void enterFindToken(GrammarParser.FindTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#findToken}.
	 * @param ctx the parse tree
	 */
	void exitFindToken(GrammarParser.FindTokenContext ctx);
}