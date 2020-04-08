package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableExpression extends Expression {

	private Variable v;

	public VariableExpression(String variableExpression) {

	}
	
	public VariableExpression(Variable v) {
		this.v = v;
	}
	
	public Variable getVariable() {
		return this.v;
	}

	@Override
	public Literal evaluate() {
		return VariableAssignmentRegistry.getVariableValue(v);
	}

	@Override
	public String getRegex() {
		return "^[a-zA-Z]+[a-zA-Z0-9]?";
	}

	@Override
	public String toString() {
		return v.toString();
	}

}
