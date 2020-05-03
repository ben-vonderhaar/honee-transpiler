package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperationExpression;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;

public class LineOfCode extends ConstructToken {

    private AssignmentStatement assignmentStatement;
    private Expression expression;

    public LineOfCode(AssignmentStatement assignmentStatement) {
        this.assignmentStatement = assignmentStatement;
    }

    public LineOfCode(Expression expression) {
        this.expression = expression;
    }

    public void setScope(Scope scope) {

        if (null != assignmentStatement) {
            this.assignmentStatement.setScope(scope);
        } else {
            this.expression.setScope(scope);
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
            this.expression.evaluate();
        }
    }
}
