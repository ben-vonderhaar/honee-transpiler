package com.benvonderhaar.honee.transpiler.type;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AnyType extends Token {

    @Override
    public String getRegex() throws HoneeException {
        throw new HoneeException("Cannot parse type " + this.getClass().getName());
    }

}
