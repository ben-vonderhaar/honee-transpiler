package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

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

	@Override
	public void setScope(Scope scope) {
		// do nothing
	}

}
