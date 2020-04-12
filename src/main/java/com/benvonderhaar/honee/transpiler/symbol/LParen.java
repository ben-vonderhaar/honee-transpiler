package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class LParen extends Symbol implements Lexable {

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
