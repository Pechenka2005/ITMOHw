// Generated from /Users/sj/IdeaProjects/lab3MT/src/main/java/ToImp.g4 by ANTLR 4.9.1

package translator;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ToImpParser}.
 */
public interface ToImpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ToImpParser#st}.
	 * @param ctx the parse tree
	 */
	void enterSt(ToImpParser.StContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#st}.
	 * @param ctx the parse tree
	 */
	void exitSt(ToImpParser.StContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#env}.
	 * @param ctx the parse tree
	 */
	void enterEnv(ToImpParser.EnvContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#env}.
	 * @param ctx the parse tree
	 */
	void exitEnv(ToImpParser.EnvContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ToImpParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ToImpParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(ToImpParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(ToImpParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ToImpParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ToImpParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#exprToken}.
	 * @param ctx the parse tree
	 */
	void enterExprToken(ToImpParser.ExprTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#exprToken}.
	 * @param ctx the parse tree
	 */
	void exitExprToken(ToImpParser.ExprTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#ifToken}.
	 * @param ctx the parse tree
	 */
	void enterIfToken(ToImpParser.IfTokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#ifToken}.
	 * @param ctx the parse tree
	 */
	void exitIfToken(ToImpParser.IfTokenContext ctx);
	/**
	 * Enter a parse tree produced by {@link ToImpParser#token}.
	 * @param ctx the parse tree
	 */
	void enterToken(ToImpParser.TokenContext ctx);
	/**
	 * Exit a parse tree produced by {@link ToImpParser#token}.
	 * @param ctx the parse tree
	 */
	void exitToken(ToImpParser.TokenContext ctx);
}