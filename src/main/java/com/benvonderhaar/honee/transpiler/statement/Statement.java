package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.Token;

public abstract class Statement extends Token {

	public abstract void evaluate();
	
}
