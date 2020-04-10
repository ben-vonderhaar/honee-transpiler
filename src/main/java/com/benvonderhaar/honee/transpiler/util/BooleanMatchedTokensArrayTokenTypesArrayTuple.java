package com.benvonderhaar.honee.transpiler.util;

import java.util.List;

import com.benvonderhaar.honee.transpiler.Token;

public class BooleanMatchedTokensArrayTokenTypesArrayTuple {

	private boolean bool;
	private List<Token> matchedTokens, tokenTypes;
	
	public BooleanMatchedTokensArrayTokenTypesArrayTuple(boolean bool, List<Token> matchedTokens, List<Token> tokenTypes) {
		this.bool = bool;
		this.matchedTokens = matchedTokens;
		this.tokenTypes = tokenTypes;
	}
	
	public boolean bool() {
		return this.bool;
	}
	
	public List<Token> getMatchedTokens() {
		return this.matchedTokens;
	}

	public List<Token> getTokenTypes() {
		return this.tokenTypes;
	}
}
