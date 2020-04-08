package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.literal.Literal;

public abstract class Expression extends AnyExpression {

	public abstract Literal evaluate();
	
}
