package com.benvonderhaar.honee.transpiler.registry;

import java.util.*;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.type.FunctionType;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableRegistry<T> {

	// TODO ensure that variables in different scopes with the same declaration don't collide
	private static Map<String, VariableDeclaration> variableDeclarationMap = new HashMap<>();
	private static Map<Scope, ArrayList<VariableDeclaration>> variablesInScopeMap = new HashMap<>();
	private static Map<VariableDeclaration, Literal> variableValuesMap = new HashMap<>();

	// NOTE: this is primarily for debugging at the moment, and will not function properly
	// if there are function name collisions
	private static Map<String, Scope> functionNameMap = new HashMap<>();
	
	public static void add(VariableDeclaration variableDeclaration, Scope scope) throws HoneeException {

		ArrayList<VariableDeclaration> scopeVariables = variablesInScopeMap.getOrDefault(scope, new ArrayList<>());

		if (scopeVariables.contains(variableDeclaration)) {
			throw new HoneeException(variableDeclaration.toString() + " already exists in this scope.");
		}

		// TODO introduce enum for scope type
		// TODO
		if (variableDeclaration.getType().toString().equals("function")) {
			functionNameMap.put(variableDeclaration.getVariable().getName(), scope);
		}

		scopeVariables.add(variableDeclaration);
		variablesInScopeMap.put(scope, scopeVariables);
		// TODO handle type/name more cleanly
		variableDeclarationMap.put(variableDeclaration.toString(), variableDeclaration);
	}

	public static void setVariableValue(VariableDeclaration v, Literal l, Scope scope)  {

		ArrayList<VariableDeclaration> declaredVariablesInScope = variablesInScopeMap.get(scope);
		VariableDeclaration declaredVariable = declaredVariablesInScope
				.get(declaredVariablesInScope.indexOf(variableDeclarationMap.get(v.toString())));

		if (null == declaredVariable) {
			System.out.println("Cannot set undeclared variable");
			System.exit(1);
		}

		variableValuesMap.put(declaredVariable, l);
	}

	// TODO make this take a Type parameter as well
	public static VariableDeclaration getVariableInScopeByName(String variableName, Scope scope) {
		return variablesInScopeMap.get(scope).stream()
				.filter(variableDeclaration -> variableDeclaration.getVariable().getName().equals(variableName))
				.findFirst()
				.orElse(null);
	}

	// TODO make this take a Type parameter as well
	public static Literal getVariableValue(String variableName, Scope scope) throws HoneeException {
		// TODO scope sensitivity
		return variableValuesMap.get(
				variableDeclarationMap.get(
						variableDeclarationMap.keySet().stream()
								.filter(variable -> variable.split(" ")[1].equals(variableName))
								.findFirst()
								.orElseThrow(() -> new HoneeException("Variable with name "
										+ variableName + " does not exist in specified scope"))));
	}

	public static IntegerLiteral getVariableValueAsInteger(String variableName, Scope scope) throws HoneeException {
		return (IntegerLiteral) getVariableValue(variableName, scope);
	}

	public static void clearRegistry() {
		variableDeclarationMap.clear();
		variablesInScopeMap.clear();
		variableValuesMap.clear();
	}

	public static Scope getScope(String scopeName, String type) {

		// TODO introduce enum for scope type
		// TODO check type after fleshing out function name map capabilities (see notes above)
		return functionNameMap.get(scopeName);

		//variablesInScopeMap.keySet().stream()
		//		.filter(scope -> scope.toString())
	}
}
