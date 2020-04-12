package com.benvonderhaar.honee.transpiler.literal;

import com.benvonderhaar.honee.transpiler.Lexable;

public class IntegerLiteral extends NumberLiteral implements Lexable {
	
	private String number;
	
	public IntegerLiteral(String number) {
		this.number = number;
	}
	
	public Integer get() {
		return Integer.parseInt(this.number);
	}

	@Override
	public String getRegex() {
		return "";
	}
	
	@Override
	public String toString() {
		return this.number;
	}

	public Literal increment() {
		this.number = String.valueOf(Integer.parseInt(this.number) + 1);
		return this;
	}

	public Literal decrement() {
		this.number = String.valueOf(Integer.parseInt(this.number) - 1);
		return this;
	}
}
