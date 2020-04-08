package com.benvonderhaar.honee.transpiler.symbol;

public class Equal extends Symbol {

	public Equal(String equal) {
		
	}

	@Override
	public String getRegex() {
		return "^\\=";
	}

	@Override
	public String toString() {
		return "=";
	}
	
}
