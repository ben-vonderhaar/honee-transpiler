package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.type.IntegerType;
import com.benvonderhaar.honee.transpiler.type.Type;

public class LiteralExpression extends Expression implements Lexable {

	private Literal literal;
	private Class<? extends Type> type;

	public LiteralExpression(String literalExpression) {

		// TODO this needs to be type-aware in the future
		this.literal = new IntegerLiteral(literalExpression);
		this.type = IntegerType.class;
	}

	public LiteralExpression(Literal literal) {

		this.literal = literal;
		// TODO this needs to be type-aware in the future
		this.type = IntegerType.class;
	}
	
	@Override
	public Literal evaluate() {
		return this.literal;
	}

	@Override
	public Class<? extends Type> getEvaluatedType() {
		return this.type;
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
