package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class AssignmentStatement extends Statement {

	private Type t;
	private Variable v;
	private Expression exp;

	public AssignmentStatement(String a) {

	}
	
	public AssignmentStatement(Type t, VariableExpression v, Equal e, Expression exp) {
		this.t = t;
		this.v = v.getVariable();
		this.exp = exp;

		try {
			VariableAssignmentRegistry.add(t, this.v);
		} catch (HoneeException ex) {
			System.out.println("Could not add variable to registry: " + this.v.toString());
			ex.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void evaluate() {
		System.out.println("assign " + v + ":");
		System.out.println(exp.evaluate());
		
		VariableAssignmentRegistry.setVariableValue(v, exp.evaluate());
		
		// TODO ensure type consistency
		// TODO assign to variable registry in proper scope
	}

	@Override
	public String getRegex() throws HoneeException {
		throw new HoneeException("Cannot parse type " + this.getClass().getName());
	}

	@Override
	public String toString() {
		return t.toString() + " " + v.toString() + " = " + exp.toString();
	}

}
