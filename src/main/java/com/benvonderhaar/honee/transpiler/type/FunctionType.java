package com.benvonderhaar.honee.transpiler.type;

public class FunctionType extends Type {

    public FunctionType(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return "function";
    }
}
