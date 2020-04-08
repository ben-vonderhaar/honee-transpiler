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
	
	public AssignmentStatement(Type t, VariableExpression v, Equal e, Expression exp) throws HoneeException {
		this.t = t;
		this.v = v.getVariable();
		this.exp = exp;
		
		VariableAssignmentRegistry.add(t, this.v);
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
