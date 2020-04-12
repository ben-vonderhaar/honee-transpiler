package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AssignmentStatement extends Statement {

	private Type type;
	private Variable variable;
	private Expression expression;

	public AssignmentStatement(String a) {

	}

	public AssignmentStatement(VariableDeclaration variableDeclaration, Equal equal, Expression expression) {
		this.type = variableDeclaration.getType();
		this.variable = variableDeclaration.getVariable();
		this.expression = expression;

		try {
			VariableAssignmentRegistry.add(type, this.variable);
		} catch (HoneeException ex) {
			System.out.println("Could not add variable to registry: " + this.variable.toString());
			ex.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void evaluate() {
		System.out.println("assign " + variable + ":");
		System.out.println(expression.evaluate());
		
		VariableAssignmentRegistry.setVariableValue(variable, expression.evaluate());
		
		// TODO ensure type consistency
		// TODO assign to variable registry in proper scope
	}

	@Override
	public String toString() {
		return type.toString() + " " + variable.toString() + " = " + expression.toString();
	}

}
