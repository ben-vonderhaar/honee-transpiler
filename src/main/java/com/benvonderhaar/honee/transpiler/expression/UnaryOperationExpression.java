package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;

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

    @Override
    public String toString() {
        Variable v = exp.getVariable();

        if (isPreUnaryOperator) {
            return op.toString() + v.toString();
        } else {
            return v.toString() + op.toString();
        }
    }
}
