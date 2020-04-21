package com.benvonderhaar.honee.transpiler;

public interface Scope {

    void evaluate();
    void propagateScope();
}
