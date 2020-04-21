package com.benvonderhaar.honee.transpiler.operator;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.util.function.BiFunction;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class UnaryOperator extends Operator implements Lexable {

    private final String operator;
    private BiFunction<Expression, Scope, Expression> operation;

    public UnaryOperator(String operator) {

        this.operator = operator;

        if (operator.equals("++")) {
            this.operation = UnaryOperator::increment;
        } else if (operator.equals("--")) {
            this.operation = UnaryOperator::decrement;
        }

    }

    private static Expression increment(Expression expression, Scope scope) {

        try {
            VariableDeclaration v = VariableRegistry.getVariableInScopeByName(
                    ((VariableExpression) expression).getVariable().getName(), scope);
            IntegerLiteral variableValue = (IntegerLiteral) VariableRegistry.getVariableValue(
                    ((VariableExpression) expression).getVariable().getName(), scope);
            VariableRegistry.setVariableValue(v, variableValue.increment(), scope);

            return expression;
        } catch (HoneeException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    private static Expression decrement(Expression expression, Scope scope) {

        try {
            VariableDeclaration v = VariableRegistry.getVariableInScopeByName(
                    ((VariableExpression) expression).getVariable().getName(), scope);
            IntegerLiteral variableValue = (IntegerLiteral) VariableRegistry.getVariableValue(
                    ((VariableExpression) expression).getVariable().getName(), scope);
            VariableRegistry.setVariableValue(v, variableValue.decrement(), scope);

            return expression;
        } catch (HoneeException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public static UnaryOperator getUnaryOperator(String operator) {

        if (operator.equals("++")) {
            return INCREMENT_UNARY_OPERATOR;
        } else if (operator.equals("--")) {
            return DECREMENT_UNARY_OPERATOR;
        }

        return ANY_UNARY_OPERATOR;
    }

    @Override
    public Literal evaluate(Scope scope, Expression... arguments) {
        return this.operation.apply(arguments[0], scope).evaluate();
    }

    @Override
    public String toString() {
        return this.operator;
    }

    @Override
    public String getRegex() {
        return "^(\\-\\-)|(\\+\\+)";
    }
}
