package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.literal.Literal;

public class LiteralExpression extends Expression implements Lexable {

	private Literal literal;

	public LiteralExpression(String literalExpression) {

	}

	public LiteralExpression(Literal literal) {
		this.literal = literal;
	}
	
	@Override
	public Literal evaluate() {
		return this.literal;
	}

	@Override
	public String getRegex() {
		return "^\\d+(\\.\\d+)?|(true|false)?"; // TODO Strings
	}

	@Override
	public String toString() {
		return null == this.literal ? "" : this.literal.toString();
	}

	@Override
	public void setScope(Scope scope) {
		// do nothing
	}
}
