package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;

public class FunctionDeclaration extends Token {

    private VariableExpression name;
    private TokenList<VariableDeclaration> parameters;

    public FunctionDeclaration(VariableExpression name, VariableDeclaration parameter) {
        this.name = name;
        this.parameters = new TokenList<>(parameter);
    }

    public FunctionDeclaration(VariableExpression name, TokenList<VariableDeclaration> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public VariableExpression getName() {
        return this.name;
    }

    public void setScope(Scope scope) {
        this.name.setScope(scope);
        scope.addParameters(parameters);
    }

    @Override
    public String toString() {
        return this.name.toString() + "(" + parameters.toString() + ")";
    }
}
