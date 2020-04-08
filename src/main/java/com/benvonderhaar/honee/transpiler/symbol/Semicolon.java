package com.benvonderhaar.honee.transpiler.symbol;

public class Semicolon extends Symbol {

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
