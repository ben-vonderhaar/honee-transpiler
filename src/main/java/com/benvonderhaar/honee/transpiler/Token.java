package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.util.HoneeException;

public abstract class Token {

    public abstract String getRegex() throws HoneeException;

}
