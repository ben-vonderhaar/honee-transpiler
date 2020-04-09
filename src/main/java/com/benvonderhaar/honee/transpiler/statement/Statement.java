package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;

public abstract class Statement extends Token {

	public abstract void evaluate();
	
}
