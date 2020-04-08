package com.benvonderhaar.honee.transpiler.operator;

import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperatorExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;

import java.util.function.Function;

public class UnaryOperator extends Operator {

    private final String operator;
    private Function<Expression, Expression> operation;

    public UnaryOperator(String operator) {

        this.operator = operator;

        if (operator.equals("++")) {
            this.operation = UnaryOperator::increment;
        } else if (operator.equals("--")) {
            this.operation = UnaryOperator::decrement;
        }
    }

    private static Expression increment(Expression expression) {

        IntegerLiteral variableValue = (IntegerLiteral) VariableAssignmentRegistry.getVariableValue(
                ((VariableExpression) expression).getVariable());
        VariableAssignmentRegistry.setVariableValue(((VariableExpression) expression).getVariable(), variableValue.increment());

        return expression;
    }

    private static Expression decrement(Expression expression) {

        IntegerLiteral variableValue = (IntegerLiteral) VariableAssignmentRegistry.getVariableValue(
                ((VariableExpression) expression).getVariable());
        VariableAssignmentRegistry.setVariableValue(((VariableExpression) expression).getVariable(), variableValue.decrement());

        return expression;
    }

    @Override
    public Literal evaluate(Expression... arguments) {
        return this.operation.apply(arguments[0]).evaluate();
    }
}
