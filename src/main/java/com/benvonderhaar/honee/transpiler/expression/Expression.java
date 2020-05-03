package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Scopeable;
import com.benvonderhaar.honee.transpiler.literal.Literal;

public abstract class Expression extends AnyExpression implements Scopeable {

	public abstract Literal evaluate();
	
}
