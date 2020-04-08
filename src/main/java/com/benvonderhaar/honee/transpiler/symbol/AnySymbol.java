package com.benvonderhaar.honee.transpiler.symbol;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AnySymbol extends Token {

    @Override
    public String getRegex() throws HoneeException {
        throw new HoneeException("Cannot parse type " + this.getClass().getName());
    }

}
