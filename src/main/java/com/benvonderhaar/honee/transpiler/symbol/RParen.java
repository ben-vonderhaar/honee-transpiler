package com.benvonderhaar.honee.transpiler.symbol;

public class RParen extends Symbol {

	public RParen(String rParen) {
		
	}

	@Override
	public String getRegex() {
		return "^\\)";
	}
	
	@Override
	public String toString() {
		return ")";
	}
	
}