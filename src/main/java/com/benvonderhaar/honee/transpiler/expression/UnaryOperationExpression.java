package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class UnaryOperationExpression extends Expression {

    private final boolean isPreUnaryOperator;

    private final UnaryOperator op;
    private final VariableExpression exp;

    // pre-increment/pre-decrement
    public UnaryOperationExpression(UnaryOperator op, VariableExpression exp) {

        isPreUnaryOperator = true;
        this.op = op;
        this.exp = exp;
    }

    // post-increment/post-decrement
    public UnaryOperationExpression(VariableExpression exp, UnaryOperator op) {

        isPreUnaryOperator = false;
        this.op = op;
        this.exp = exp;
    }

    @Override
    public Literal evaluate() {

        try {
            VariableDeclaration v = VariableRegistry.getVariableInScopeByName(exp.getVariable().getName(), exp.getScope());

            if (isPreUnaryOperator) {
                VariableRegistry.setVariableValue(v, op.evaluate(exp.getScope(), exp), exp.getScope());
                return VariableRegistry.getVariableValue(exp.getVariable().getName(), exp.getScope());
            } else {
                // TODO find a cleaner way to do this, probably during number refactor stage
                Literal valueToBeUsed = new IntegerLiteral(
                        VariableRegistry.getVariableValue(exp.getVariable().getName(), exp.getScope()).toString());
                VariableRegistry.setVariableValue(v, op.evaluate(exp.getScope(), exp), exp.getScope());
                return valueToBeUsed;
            }
        } catch (HoneeException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    @Override
    public String toString() {
        Variable v = exp.getVariable();

        if (isPreUnaryOperator) {
            return op.toString() + v.toString();
        } else {
            return v.toString() + op.toString();
        }
    }

    @Override
    public void setScope(Scope scope) {
        this.exp.setScope(scope);
    }
}
