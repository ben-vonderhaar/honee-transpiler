package com.benvonderhaar.honee.transpiler.symbol;

public class Variable extends Symbol {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
