package com.benvonderhaar.honee.transpiler.registry;

import java.util.HashMap;
import java.util.Map;

import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableAssignmentRegistry<T> {

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

	public static Literal getVariableValue(String variableName) {
		return variableToValueMap.get(variableName);
	}

	public static IntegerLiteral getVariableValueAsInteger(String variableName) {
		return (IntegerLiteral) variableToValueMap.get(variableName);
	}

	public static void clearRegistry() {
		variableToTypeMap.clear();
		variableToValueMap.clear();
	}
}
