package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;

public class Variable extends Symbol {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}

	public Literal getValue() {
		return VariableAssignmentRegistry.getVariableValue(this);
	}

	@Override
	public String toString() {
		return name;
	}

}
