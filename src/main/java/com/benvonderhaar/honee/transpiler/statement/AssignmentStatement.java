package com.benvonderhaar.honee.transpiler.statement;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Semicolon;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtils.tokenIsOfType;

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
	public String toString() {
		return t.toString() + " " + v.toString() + " = " + exp.toString();
	}

	public static Boolean checkCanReduceToAssignmentStatement(Token[] tokens) {
		return tokenIsOfType(tokens[0], Type.class)
				&& tokenIsOfType(tokens[1], VariableExpression.class)
				&& tokenIsOfType(tokens[2], Equal.class)
				&& tokenIsOfType(tokens[3], Expression.class);
	}

	public static Token reduceToAssignmentStatement(Token[] tokens) {

		try {
			return new AssignmentStatement(
					(Type) tokens[0],
					(VariableExpression) tokens[1],
					(Equal) tokens[2],
					(Expression) tokens[3]);
		} catch (HoneeException e) {
			System.out.println("Exception reducing to AssignmentStatement");
			e.printStackTrace();
			System.exit(1);
			return null;
		}

	}
}
