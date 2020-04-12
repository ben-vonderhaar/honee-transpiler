package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class VariableDeclaration extends Token implements Lexable {

    private Type type;
    private Variable variable;

    public VariableDeclaration(String v) {
        this.type = null;
        this.variable = null;
    }

    public VariableDeclaration(Type type, VariableExpression variableExpression) {
        this.type = type;
        this.variable = variableExpression.getVariable();
    }

    @Override
    public String getRegex() {
        return null;
    }

    public Type getType() {
        return this.type;
    }

    public Variable getVariable() {
        return this.variable;
    }
}
