package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.construct.ConstructToken;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class Variable extends Symbol {

	private Scope scope;
	private String name;

	public Variable(String name) {
		this.name = name;
		this.scope = null;
	}

	public Variable(String name, Scope scope) {
		this.name = name;
	}

	public void setScope(Scope scope) {
		if (null != this.scope) {
			System.out.println("Cannot re-assign scope for variable " + this.name
					+ " - already in following scope: " + this.scope);
			System.exit(1);
		}

		this.scope = scope;
	}

	public Literal getValue() throws HoneeException {
		return VariableRegistry.getVariableValue(this.name, scope);
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return name;
	}

}
