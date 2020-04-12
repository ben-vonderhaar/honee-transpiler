package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class Whitespace extends Symbol implements Lexable {

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
