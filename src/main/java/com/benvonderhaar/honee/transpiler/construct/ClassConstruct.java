package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;

public class ClassConstruct extends ConstructToken {

    private AccessModifier accessModifier;
    private VariableExpression name;
    private TokenList<FunctionConstruct> functions;

    public ClassConstruct(AccessModifier accessModifier, VariableExpression name, TokenList<FunctionConstruct> functions) {
        this.accessModifier = accessModifier;
        this.name = name;
        this.functions = functions;
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " class " + name + " { " + functions.toString() + " }";
    }
}
