package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;

public class VariableExpression extends Expression {

	private Variable v;
	
	public VariableExpression(Variable v) {
		this.v = v;
	}
	
	public Variable getVariable() {
		return this.v;
	}
	
	@Override
	public String toString() {
		return v.toString();
	}
	
	@Override
	public Literal evaluate() {
		return VariableAssignmentRegistry.getVariableValue(v);
	}

}
