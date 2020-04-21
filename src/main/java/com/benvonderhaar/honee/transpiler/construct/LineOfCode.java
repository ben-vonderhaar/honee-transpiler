package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperationExpression;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;

public class LineOfCode extends ConstructToken {

    private AssignmentStatement assignmentStatement;
    private AnyExpression expression;

    public LineOfCode(AssignmentStatement assignmentStatement) {
        this.assignmentStatement = assignmentStatement;
    }

    public LineOfCode(AnyExpression expression) {
        this.expression = expression;
    }

    public void setScope(Scope scope) {
        if (null != assignmentStatement) {
            assignmentStatement.setScope(scope);
        } else if (this.expression.getClass().isAssignableFrom(UnaryOperationExpression.class)) {
            ((UnaryOperationExpression) this.expression).setScope(scope);
        }
    }

    public AssignmentStatement getAssignmentStatement() {
        return this.assignmentStatement;
    }

    @Override
    public String toString() {
        if (null != this.assignmentStatement) {
            return assignmentStatement.toString();
        } else if (null != this.expression) {
            return this.expression.toString();
        } else {
            return "(Empty LOC)";
        }
    }

    public void evaluate() {
        if (null != this.assignmentStatement) {
            this.assignmentStatement.evaluate();
        } else if (null != this.expression) {
            if (this.expression.getClass().isAssignableFrom(UnaryOperationExpression.class)) {
                ((UnaryOperationExpression) this.expression).evaluate();
            }
        }
    }
}
