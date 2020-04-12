package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class RParen extends Symbol implements Lexable {

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
