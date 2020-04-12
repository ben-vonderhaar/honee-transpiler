package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.Lexable;

public class ClassKeyword extends Keyword implements Lexable {

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
