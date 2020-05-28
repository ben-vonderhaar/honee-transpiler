package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.construct.ConstructToken;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.type.VariableType;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableExpression extends Expression implements Lexable {

	private Variable variable;
	private Scope scope;

	public VariableExpression(String variableExpression) {
		variable = new Variable(variableExpression);
	}
	
	public VariableExpression(Variable v) {
		this.variable = v;
	}
	
	public Variable getVariable() {
		return this.variable;
	}

	@Override
	public Literal evaluate() {
		return VariableRegistry.getVariableValue(this.variable.getName(), new VariableType(""), this.scope);
	}

	@Override
	public Class<? extends Type> getEvaluatedType() {
		return VariableRegistry
				.getVariableInScopeByName(this.variable.getName(), new VariableType(""), this.scope)
				.getType()
				.getClass();
	}

	@Override
	public String getRegex() {
		return "^[a-zA-Z]+[a-zA-Z0-9]?";
	}

	@Override
	public String toString() {
		return variable.toString();
	}

	@Override
	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public Scope getScope() {
		return this.scope;
	}
}
