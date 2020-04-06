package com.benvonderhaar.honee.transpiler.util;

import java.util.List;

import com.benvonderhaar.honee.transpiler.Token;

public class BooleanTokenArrayTuple {

	private boolean bool;
	private List<Token> tokens;
	
	public BooleanTokenArrayTuple(boolean bool, List<Token> tokens) {
		this.bool = bool;
		this.tokens = tokens;
	}
	
	public boolean bool() {
		return this.bool;
	}
	
	public List<Token> getTokens() {
		return this.tokens;
	}
}
