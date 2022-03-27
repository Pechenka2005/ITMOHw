// Generated from /home/vankataganai/Documents/university/TM/lab4/src/main/java/Grammar.g4 by ANTLR 4.9.2

    import generator.helpers.*;
    import generator.Grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(GrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#constructGrammar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructGrammar(GrammarParser.ConstructGrammarContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(GrammarParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#startState}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartState(GrammarParser.StartStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#state}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitState(GrammarParser.StateContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#findState}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFindState(GrammarParser.FindStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#inhState}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInhState(GrammarParser.InhStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#syntState}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyntState(GrammarParser.SyntStateContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#findRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFindRule(GrammarParser.FindRuleContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#inhParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInhParameters(GrammarParser.InhParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#codeLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCodeLine(GrammarParser.CodeLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#tokens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTokens(GrammarParser.TokensContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#findToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFindToken(GrammarParser.FindTokenContext ctx);
}