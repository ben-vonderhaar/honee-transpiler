package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Scopeable;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AssignmentStatement extends Statement implements Scopeable {

	private Type type;
	private VariableDeclaration variableDeclaration;
	private Variable variable;
	private Expression expression;
	private Scope scope;

	public AssignmentStatement(String a) {

	}

	public AssignmentStatement(VariableDeclaration variableDeclaration, Equal equal, Expression expression) {
		this.type = variableDeclaration.getType();
		this.variableDeclaration = variableDeclaration;
		this.variable = variableDeclaration.getVariable();
		this.expression = expression;
		this.scope = null;

	}

	@Override
	public void evaluate() {

		if (null == this.scope) {
			System.out.println("Attempted to assign value to " + variable + " but not assigned to a scope");
			System.exit(1);
		}


		VariableRegistry.setVariableValue(this.variableDeclaration, expression.evaluate(), this.scope);

	}

	@Override
	public String toString() {
		return type.toString() + " " + variable.toString() + " = " + expression.toString();
	}

	@Override
	public void setScope(Scope scope) {

		if (null != this.scope) {
			System.out.println("Cannot reassign scope to " + this.toString() + " - already set to " + this.scope.toString());
		}

		this.scope = scope;
		this.expression.setScope(this.scope);

		try {
			VariableRegistry.add(this.variableDeclaration, this.scope);
		} catch (HoneeException ex) {
			System.out.println("Could not add variable to registry: " + this.variable.toString());
			ex.printStackTrace();
			System.exit(1);
		}

		System.out.println(this.variableDeclaration.getType().getClass());
		System.out.println(this.expression.getEvaluatedType());

		// TODO properly check that the assignment is valid.
	}

	public Variable getVariable() {
		return this.variable;
	}

	public VariableDeclaration getVariableDeclaration() {
		return this.variableDeclaration;
	}
}
