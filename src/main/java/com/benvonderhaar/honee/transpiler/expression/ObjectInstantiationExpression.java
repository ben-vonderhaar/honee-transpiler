package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.type.Type;

import java.util.List;

public class ObjectInstantiationExpression extends Expression {

    private VariableExpression className;
    private List<Expression> parameters;

    public ObjectInstantiationExpression(VariableExpression className, List<Expression> parameters) {
        this.className = className;
        this.parameters = parameters;
    }

    public ObjectInstantiationExpression(VariableExpression className, TokenList<Expression> parameters) {
        this.className = className;
        this.parameters = parameters.getTokenList();
    }

    @Override
    public Literal evaluate() {
        return null;
    }

    @Override
    public Class<? extends Type> getEvaluatedType() {
        return className.getEvaluatedType();
    }

    @Override
    public String toString() {
        return "instance[" + className + "](params" + parameters.toString() + ")";
    }
}
