package com.benvonderhaar.honee.transpiler.expression;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.Scopeable;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AnyExpression extends Token implements Scopeable {

    private Scope scope;

    @Override
    public void setScope(Scope scope) {
        this.scope = scope;
    }
}
