package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;

public class ConstructorDeclaration extends Token {

    private TokenList<VariableDeclaration> parameters;

    public ConstructorDeclaration() {
        this.parameters = new TokenList<>(VariableDeclaration.class);
    }

    public ConstructorDeclaration(VariableDeclaration parameter) {
        this.parameters = new TokenList<>(parameter);
    }

    public ConstructorDeclaration(TokenList<VariableDeclaration> parameters) {
        this.parameters = parameters;
    }

    public void setScope(Scope scope) {
        scope.addParameters(parameters);
    }

    @Override
    public String toString() {
        return "constructor(" + parameters.toString() + ")";
    }
}
