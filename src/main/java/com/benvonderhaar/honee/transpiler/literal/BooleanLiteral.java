package com.benvonderhaar.honee.transpiler.literal;

public class BooleanLiteral extends Literal {

	private String bool;
	
	public BooleanLiteral(String bool) {
		this.bool = bool;
	}
	
	public Boolean get() {
		return Boolean.parseBoolean(this.bool);
	}

	@Override
	public String toString() {
		return bool;
	}

}
