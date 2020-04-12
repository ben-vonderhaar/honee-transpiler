package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class Semicolon extends Symbol implements Lexable {

	public Semicolon(String semicolon) {
		
	}

	@Override
	public String getRegex() {
		return "^;";
	}

	@Override
	public String toString() {
		return ";";
	}
	
}
