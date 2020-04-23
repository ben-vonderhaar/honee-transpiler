package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;

public interface Scope {

    void evaluate();
    void propagateScope();
    void addParameters(TokenList<VariableDeclaration> parameters);
}
