package com.benvonderhaar.honee.transpiler.symbol;

public class LParen extends Symbol {

	public LParen(String lparen) {
		
	}

	@Override
	public String getRegex() {
		return "^\\(";
	}

	@Override
	public String toString() {
		return "(";
	}

}
