package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class BinaryOperationExpression extends Expression {

	private Expression lhs, rhs;
	private BinaryOperator op;
	
	public BinaryOperationExpression(Expression lhs, BinaryOperator op, Expression rhs) {
		this.lhs = lhs;
		this.op = op;
		this.rhs = rhs;
	}
	
	@Override
	public Literal evaluate() {
		return this.op.evaluate(lhs, rhs);
	}

	@Override
	public String toString() {
		return "(" + lhs.toString() + op.toString() + rhs.toString() + ")";
	}

    public static Boolean satisfiesOperatorPriorityRules(List<Token> stack, Token lookahead, int stackPosition) {

		// 1 hardcoded for BinOp, will have to lookup for UnaryOp for pre/post increment/decrement support
		if (null != lookahead
				&& BinaryOperator.class.isAssignableFrom(stack.get(stackPosition + 1).getClass())
				&& BinaryOperator.class.isAssignableFrom(lookahead.getClass())
				&& BinaryOperator.isLowerPriority(stack.get(stackPosition + 1), lookahead)) {
			return false;
		}

		if (BinaryOperator.class.isAssignableFrom(stack.get(stackPosition + 1).getClass())) {

			for (int j = stackPosition + 1; j < stack.size(); j++) {
				if (BinaryOperator.class.isAssignableFrom(stack.get(j).getClass())
						&& BinaryOperator.isLowerPriority(stack.get(stackPosition + 1), stack.get(j))) {
					return false;
				}
			}
		}

		return true;
	}

}
