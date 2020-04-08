package com.benvonderhaar.honee.transpiler.operator;

import java.util.function.BiFunction;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.LiteralExpression;
import com.benvonderhaar.honee.transpiler.literal.BooleanLiteral;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Whitespace;

public class BinaryOperator extends Operator {

	private final String operator;
	private BiFunction<Expression, Expression, Expression> operation;
	
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

	@Override
	public Literal evaluate(Expression... arguments) {
		
		if (arguments.length != 2) {
			throw new RuntimeException("Binary Operator accepts two arguments only");
		}
		
		return this.operation.apply(arguments[0], arguments[1]).evaluate();
		
	}

	public static Boolean checkCanReduceMinusSpaceMinusToPlus(Token[] tokens) {
		return tokens.length == 3
				&& BinaryOperator.class.equals(tokens[0].getClass()) && tokens[0].toString().equals("-")
				&& Whitespace.class.equals(tokens[1].getClass())
				&& BinaryOperator.class.equals(tokens[2].getClass()) && tokens[2].toString().equals("-");
	}


	public static Token reduceMinusSpaceMinusToPlus(Token[] tokens) {
		return new BinaryOperator("+");

	}

	public static Boolean checkCanReduceTwoEqualsToDoubleEquals(Token[] tokens) {
		return tokens.length == 2 && Equal.class.equals(tokens[0].getClass()) && Equal.class.equals(tokens[1].getClass());
	}

	public static Token reduceTwoEqualsToDoubleEquals(Token[] tokens) {
		return new BinaryOperator("==");
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
	
	public static Expression plus(Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() + ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	public static Expression minus(Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() - ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression multiply(Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() * ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression divide(Expression arg0, Expression arg1) {
		
		return new LiteralExpression(
				new IntegerLiteral(String.valueOf(((IntegerLiteral) arg0.evaluate()).get() / ((IntegerLiteral) arg1.evaluate()).get())));
	}
	
	private static Expression isEqual(Expression arg0, Expression arg1) {

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
