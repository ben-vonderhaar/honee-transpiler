package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class Comma extends Symbol implements Lexable {

	public Comma(String comma) {
		
	}

	@Override
	public String getRegex() {
		return "^\\,";
	}

	@Override
	public String toString() {
		return ",";
	}
	
}
