package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.type.FunctionType;
import com.benvonderhaar.honee.transpiler.type.Type;

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

    public static VariableDeclaration newFunctionDeclaration(VariableExpression name) {
        // TODO create a TypeUtil class a la ReducerUtil
        return new VariableDeclaration(new FunctionType(""), name);
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

    @Override
    public String toString() {
        return this.type.toString() + " " + this.getVariable().getName();
    }
}
