package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.Lexable;

public class NewKeyword extends Keyword implements Lexable {

    @Override
    public String getRegex() {
        return "^new ";
    }

    @Override
    public String toString() {
        return "new";
    }
}
