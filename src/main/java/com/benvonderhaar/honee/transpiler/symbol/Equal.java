package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class Equal extends Symbol implements Lexable {

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
