package com.benvonderhaar.honee.transpiler.registry;

import java.util.*;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Tuple;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.type.FunctionType;
import com.benvonderhaar.honee.transpiler.type.IntegerType;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.type.VariableType;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableRegistry {

	private static Map<Scope, ArrayList<ScopedTypedVariableDeclarationTuple>> variablesInScopeMap = new HashMap<>();
	private static Map<ScopedTypedVariableDeclarationTuple, Literal> variableValuesMap = new HashMap<>();

	// NOTE: this is primarily for debugging at the moment, and will not function properly
	// if there are function name collisions
	private static Map<String, Scope> functionNameMap = new HashMap<>();
	
	public static void add(VariableDeclaration variableDeclaration, Scope scope) throws HoneeException {

		ArrayList<ScopedTypedVariableDeclarationTuple> scopeVariables = variablesInScopeMap.getOrDefault(scope, new ArrayList<>());

		long matchingScopeVariablesCount = scopeVariables.stream()
				.filter(scopeVariable -> variableDeclaration.getType().getClass()
						.isAssignableFrom(scopeVariable.getItem(1).getClass()))
				.filter(scopeVariable -> scopeVariable.getItem(2).equals(variableDeclaration))
				.count();

		if (matchingScopeVariablesCount > 0) {
			throw new HoneeException(variableDeclaration.toString() + " already exists in this scope.");
		}

		ScopedTypedVariableDeclarationTuple scopedTypedVariableDeclarationTuple =
			new ScopedTypedVariableDeclarationTuple(scope, variableDeclaration.getType(), variableDeclaration);

		if (variableDeclaration.getType().getClass().equals(FunctionType.class)) {
			functionNameMap.put(variableDeclaration.getVariable().getName(), scope);
		}

		scopeVariables.add(scopedTypedVariableDeclarationTuple);
		variablesInScopeMap.put(scope, scopeVariables);

		variableDeclaration.setScope(scope);
	}

	public static void setVariableValue(VariableDeclaration variableDeclaration, Literal l, Scope scope) {

		ArrayList<ScopedTypedVariableDeclarationTuple> scopeVariables = variablesInScopeMap.get(scope);

		ScopedTypedVariableDeclarationTuple declaredVariable = scopeVariables.stream()
				.filter(scopeVariable -> variableDeclaration.getType().getClass()
						.isAssignableFrom(scopeVariable.getItem(1).getClass()))
				.filter(scopeVariable -> scopeVariable.getItem(2).equals(variableDeclaration))
				.findFirst()
				.orElse(null);

		if (null == declaredVariable) {
			System.out.println("Cannot set undeclared variable");
			System.exit(1);
		}

		variableValuesMap.put(
				new ScopedTypedVariableDeclarationTuple(scope, variableDeclaration.getType(), variableDeclaration), l);
	}

	public static VariableDeclaration getVariableInScopeByName(String variableName, Type type, Scope scope) {

		ArrayList<ScopedTypedVariableDeclarationTuple> scopeVariables = variablesInScopeMap.get(scope);

		return (VariableDeclaration) Objects.requireNonNull(scopeVariables.stream()
				.filter(scopeVariable -> type.getClass()
						.isAssignableFrom(scopeVariable.getItem(1).getClass()))
				.filter(scopeVariable -> ((VariableDeclaration) scopeVariable.getItem(2))
						.getVariable().getName().equals(variableName))
				.findFirst()
				.orElse(null))
				.getItemByClass(VariableDeclaration.class);

	}

	public static Literal getVariableValue(String variableName, Type type, Scope scope) {

		ArrayList<ScopedTypedVariableDeclarationTuple> scopeVariables = variablesInScopeMap.get(scope);

		ScopedTypedVariableDeclarationTuple scopeVariable = scopeVariables.stream()
				.filter(scopeVar -> type.getClass()
						.isAssignableFrom(scopeVar.getItem(1).getClass()))
				.filter(scopeVar -> ((VariableDeclaration) scopeVar.getItem(2))
						.getVariable().getName().equals(variableName))
				.findFirst()
				.orElse(null);

		return variableValuesMap.get(scopeVariable);
	}

	public static IntegerLiteral getVariableValueAsInteger(String variableName, Scope scope) throws HoneeException {
		return (IntegerLiteral) getVariableValue(variableName, new IntegerType(""), scope);
	}

	public static void clearRegistry() {
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

class ScopedTypedVariableDeclarationTuple extends Tuple {

	public ScopedTypedVariableDeclarationTuple(Object... contents) {
		super(contents);
	}

	@Override
	public List<Class> getTupleTypes() {
		return List.of(Scope.class, Type.class, VariableDeclaration.class);
	}

	@Override
	public boolean equals(Object other) {

		if (!(other instanceof ScopedTypedVariableDeclarationTuple)) {
			return false;
		}

		ScopedTypedVariableDeclarationTuple otherTuple = (ScopedTypedVariableDeclarationTuple) other;

		return getItem(2).equals(otherTuple.getItem(2));

	}

}
