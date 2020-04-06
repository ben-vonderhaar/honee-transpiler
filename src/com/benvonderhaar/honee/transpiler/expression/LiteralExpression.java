package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.literal.Literal;

public class LiteralExpression extends Expression {

	private Literal literal;
	
	public LiteralExpression(Literal literal) {
		this.literal = literal;
	}
	
	@Override
	public Literal evaluate() {
		return this.literal;
	}

	@Override
	public String toString() {
		return null == this.literal ? "" : this.literal.toString();
	}
}
