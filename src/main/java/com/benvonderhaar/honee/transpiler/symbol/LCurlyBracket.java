package com.benvonderhaar.honee.transpiler.symbol;

public class LCurlyBracket extends Symbol {

    public LCurlyBracket(String lCurlyBracket) {

    }

    @Override
    public String getRegex() {
        return "^\\{";
    }

    @Override
    public String toString() {
        return "{";
    }

}
