// Generated from /Users/sj/IdeaProjects/lab3MT/src/main/java/ToImp.g4 by ANTLR 4.9.1

package translator;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ToImpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ToImpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ToImpParser#st}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSt(ToImpParser.StContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#env}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnv(ToImpParser.EnvContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ToImpParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(ToImpParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ToImpParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#exprToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprToken(ToImpParser.ExprTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#ifToken}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfToken(ToImpParser.IfTokenContext ctx);
	/**
	 * Visit a parse tree produced by {@link ToImpParser#token}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToken(ToImpParser.TokenContext ctx);
}