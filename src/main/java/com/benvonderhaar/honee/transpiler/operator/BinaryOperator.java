package com.benvonderhaar.honee.transpiler.operator;

import java.util.function.BiFunction;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ConstructToken;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.LiteralExpression;
import com.benvonderhaar.honee.transpiler.literal.BooleanLiteral;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.util.TriFunction;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class BinaryOperator extends Operator implements Lexable {

	private final String operator;
	private TriFunction<Scope, Expression, Expression, Expression> operation;
	
	public BinaryOperator(String operator) {
		
		this.operator = operator;

		switch (operator) {
			case "+":
				this.operation = BinaryOperator::plus;
				break;
			case "-":
				this.operation = BinaryOperator::minus;
				break;
			case "*":
				this.operation = BinaryOperator::multiply;
				break;
			case "/":
				this.operation = BinaryOperator::divide;
				break;
			case "==":
				this.operation = BinaryOperator::isEqual;
				break;
			case "<":
				// TODO
				this.operation = BinaryOperator::minus;
				break;
			case "<=":
				// TODO
				this.operation = BinaryOperator::minus;
				break;
			case ">":
				// TODO
				this.operation = BinaryOperator::minus;
				break;
			case ">=":
				// TODO
				this.operation = BinaryOperator::minus;
				break;
		}

	}

	public static BinaryOperator getBinaryOperator(String operator) {

		return new BinaryOperator(operator);
	}

	@Override
	public Literal evaluate(Scope scope, Expression... arguments) {
		
		if (arguments.length != 2) {
			throw new RuntimeException("Binary Operator accepts two arguments only");
		}
		
		return this.operation.apply(scope, arguments[0], arguments[1]).evaluate();
		
	}

	@Override
	public String getRegex() {
		return "^[\\+\\-\\*\\/]?(==)?";
	}

	@Override
	public String toString() {
		return this.operator;
	}
	
	// based on https://introcs.cs.princeton.edu/java/11precedence/
	private static short getPriority(BinaryOperator bo) {
		if (bo.operator.equals("+")) {
			return 11;
		}
		if (bo.operator.equals("-")) {
			return 11;
		}
		if (bo.operator.equals("*")) {
			return 12;
		}
		if (bo.operator.equals("/")) {
			return 12;
		}
		if (bo.operator.equals("==")) {
			return 8;
		}
		if (bo.operator.equals("<")) {
			return 9;
		}
		if (bo.operator.equals("<=")) {
			return 9;
		}
		if (bo.operator.equals(">")) {
			return 9;
		}
		if (bo.operator.equals(">=")) {
			return 9;
		}
		return 0;
	}
	
	public static boolean isLowerPriority(Token a, Token b) {
		return getPriority((BinaryOperator) a) < getPriority((BinaryOperator) b);
	}


	// TODO allow any type of number/number variable
	public static Expression plus(Scope scope, Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() + ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	public static Expression minus(Scope scope, Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() - ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression multiply(Scope scope, Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() * ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression divide(Scope scope, Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() / ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression isEqual(Scope scope, Expression arg0, Expression arg1) {

		Literal arg0E = arg0.evaluate();
		Literal arg1E = arg1.evaluate();

		if (BooleanLiteral.class.isAssignableFrom(arg0E.getClass())
				&& BooleanLiteral.class.isAssignableFrom(arg1E.getClass())) {
			return new LiteralExpression(
					new BooleanLiteral(String.valueOf(((BooleanLiteral) arg0E).get() == ((BooleanLiteral) arg1E).get())));
		} else {
			return new LiteralExpression(
					new BooleanLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get()
							.equals(((IntegerLiteral) arg1.evaluate()).get()))));
		}
	}
}
