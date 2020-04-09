package com.benvonderhaar.honee.transpiler.keyword;

public class ClassKeyword extends Keyword {

    public ClassKeyword(String clazz) {

    }

    @Override
    public String getRegex() {
        return "^class ";
    }

    @Override
    public String toString() {
        return "class";
    }
}
