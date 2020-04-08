package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtils.tokenIsOfType;

public class ParenthesisExpression extends Expression {

	private Expression expression;
	
	public ParenthesisExpression(LParen lParen, Expression expression, RParen rParen) {
		this.expression = expression;
	}
	
	@Override
	public Literal evaluate() {
		return expression.evaluate();
	}
	
	@Override
	public String toString() {
		return "(" + this.expression.toString() + ")";
	}

	public static Boolean checkCanReduceToParenthesisExpression(Token[] tokens) {
		return tokenIsOfType(tokens[0], LParen.class)
				&& tokenIsOfType(tokens[1], Expression.class)
				&& tokenIsOfType(tokens[2], RParen.class);
	}

    public static Token reduceToParenthesisExpression(Token[] tokens) {
		return new ParenthesisExpression(
				(LParen) tokens[0],
				(Expression) tokens[1],
				(RParen) tokens[2]);
    }

}
