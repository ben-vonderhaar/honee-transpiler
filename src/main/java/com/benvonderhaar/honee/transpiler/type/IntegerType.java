package com.benvonderhaar.honee.transpiler.type;

public class IntegerType extends VariableType {

	public IntegerType(String integerType) {
		super(integerType);
	}

	@Override
	public String toString() {
		return "integer";
	}
}
