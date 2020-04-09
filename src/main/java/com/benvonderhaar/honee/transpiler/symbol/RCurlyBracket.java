package com.benvonderhaar.honee.transpiler.symbol;

public class RCurlyBracket extends Symbol {

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
