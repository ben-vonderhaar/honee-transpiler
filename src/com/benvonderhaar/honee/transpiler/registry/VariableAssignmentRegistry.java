package com.benvonderhaar.honee.transpiler.registry;

import java.util.HashMap;
import java.util.Map;

import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableAssignmentRegistry {

	private static Map<String, Type> variableToTypeMap = new HashMap<>();
	private static Map<String, Literal> variableToValueMap = new HashMap<>();
	
	public static void add(Type type, Variable v) throws HoneeException {
		
		if (variableToTypeMap.containsKey(v.toString())) {
			throw new HoneeException("Variable with name " + v.toString() + " already exists in this scope.");
		}
		
		variableToTypeMap.put(v.toString(), type);
	}
	
	public static Type getVariableType(Variable v) {
		return variableToTypeMap.get(v.toString());
	}
	
	public static void setVariableValue(Variable v, Literal l) {
		variableToValueMap.put(v.toString(), l);		
	}
	
	public static Literal getVariableValue(Variable v) {
		return variableToValueMap.get(v.toString());
	}
}
