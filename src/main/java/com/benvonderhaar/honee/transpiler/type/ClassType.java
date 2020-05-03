package com.benvonderhaar.honee.transpiler.type;

public class ClassType extends Type {

    public ClassType(String type) {
        super(type);
    }

    @Override
    public String toString() {
        return "class";
    }
}
