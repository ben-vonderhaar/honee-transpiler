package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;

public class FunctionConstruct extends ConstructToken {

    private AccessModifier accessModifier;
    private boolean isStatic;
    private VariableExpression name;
    private ClosureBody body;

    public FunctionConstruct(String s) {

    }

    public FunctionConstruct(AccessModifier accessModifier, boolean isStatic, VariableExpression name, ClosureBody body) {
        this.accessModifier = accessModifier;
        this.isStatic = isStatic;
        this.name = name;
        this.body = body;
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " " + (isStatic ? "static " : "") + name.toString() + " {...}";
    }
}
