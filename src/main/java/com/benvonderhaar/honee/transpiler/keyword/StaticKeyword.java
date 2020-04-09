package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class StaticKeyword extends Token {

    public StaticKeyword(String s) {

    }

    @Override
    public String getRegex() {
        return "^static ";
    }

    @Override
    public String toString() {
        return "static";
    }
}
