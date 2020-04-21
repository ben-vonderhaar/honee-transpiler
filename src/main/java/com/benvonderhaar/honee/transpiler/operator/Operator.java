package com.benvonderhaar.honee.transpiler.operator;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ConstructToken;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.literal.Literal;

public abstract class Operator extends Token {

	public abstract Literal evaluate(Scope scope, Expression... arguments);
	
}
