package com.benvonderhaar.honee.transpiler.symbol;

public class Whitespace extends Symbol {

	public Whitespace(String whitespace) {

	}

	@Override
	public String getRegex() {
		return "^(\\s)+";
	}

	@Override
	public String toString() {
		return "_";
	}
}
