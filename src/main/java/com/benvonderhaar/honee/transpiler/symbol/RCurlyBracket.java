package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class RCurlyBracket extends Symbol implements Lexable {

    public RCurlyBracket(String rCurlyBracket) {

    }

    @Override
    public String getRegex() {
        return "^\\}";
    }

    @Override
    public String toString() {
        return "}";
    }

}
