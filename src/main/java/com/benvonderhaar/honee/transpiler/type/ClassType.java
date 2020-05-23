package com.benvonderhaar.honee.transpiler.type;

public class ClassType extends Type {

    public ClassType(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "class[" + name + "]";
    }
}
