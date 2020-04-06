package com.benvonderhaar.honee.transpiler.literal;

public class IntegerLiteral extends NumberLiteral {
	
	private String number;
	
	public IntegerLiteral(String number) {
		this.number = number;
	}
	
	public Integer get() {
		return Integer.parseInt(this.number);
	}
	
	public String getRegex() {
		return "";
	}
	
	@Override
	public String toString() {
		return this.number;
	}
}
