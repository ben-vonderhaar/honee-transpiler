package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class StaticKeyword extends Token implements Lexable {

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
