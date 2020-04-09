package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class LineOfCode extends ConstructToken {

    private AssignmentStatement assignmentStatement;
    private AnyExpression expression;

    public LineOfCode(AssignmentStatement assignmentStatement) {
        this.assignmentStatement = assignmentStatement;
    }

    public LineOfCode(AnyExpression expression) {
        this.expression = expression;
    }

    @Override
    public String getRegex() throws HoneeException {
        throw new HoneeException("Cannot parse type " + this.getClass().getName());
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
}
