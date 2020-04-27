package com.benvonderhaar.honee.transpiler.type;

public class VariableType extends Type {

    public VariableType(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return "variable";
    }
}
