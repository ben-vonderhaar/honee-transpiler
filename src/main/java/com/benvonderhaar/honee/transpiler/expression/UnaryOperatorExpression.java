package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtils.tokenIsOfType;

public class UnaryOperatorExpression extends Expression {

    private final boolean isPreUnaryOperator;

    private final UnaryOperator op;
    private final VariableExpression exp;

    // pre-increment/pre-decrement
    public UnaryOperatorExpression(UnaryOperator op, VariableExpression exp) {

        isPreUnaryOperator = true;
        this.op = op;
        this.exp = exp;
    }

    // post-increment/post-decrement
    public UnaryOperatorExpression(VariableExpression exp, UnaryOperator op) {

        isPreUnaryOperator = false;
        this.op = op;
        this.exp = exp;
    }

    public static Boolean checkCanReduceToPreUnaryOperationExpression(Token[] tokens) {
        return tokenIsOfType(tokens[0], UnaryOperator.class)
                && tokenIsOfType(tokens[1], VariableExpression.class);
    }

    public static Token reduceToPreUnaryOperationExpression(Token[] tokens) {
        return new UnaryOperatorExpression(
                (UnaryOperator) tokens[0],
                (VariableExpression) tokens[1]);
    }

    public static Boolean checkCanReduceToPostUnaryOperationExpression(Token[] tokens) {
        return tokenIsOfType(tokens[0], VariableExpression.class)
                && tokenIsOfType(tokens[1], UnaryOperator.class);
    }

    public static Token reduceToPostUnaryOperationExpression(Token[] tokens) {
        return new UnaryOperatorExpression(
                (VariableExpression) tokens[0],
                (UnaryOperator) tokens[1]);
    }

    @Override
    public Literal evaluate() {

        Variable v = exp.getVariable();

        if (isPreUnaryOperator) {
            VariableAssignmentRegistry.setVariableValue(v, op.evaluate(exp));
            return VariableAssignmentRegistry.getVariableValue(v);
        } else {
            Literal valueToBeUsed = VariableAssignmentRegistry.getVariableValue(v);
            VariableAssignmentRegistry.setVariableValue(v, op.evaluate(exp));
            return valueToBeUsed;
        }

    }
}
