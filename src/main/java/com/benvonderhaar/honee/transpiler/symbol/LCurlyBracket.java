package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Lexable;

public class LCurlyBracket extends Symbol implements Lexable {

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
