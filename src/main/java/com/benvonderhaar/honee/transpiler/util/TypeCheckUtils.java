package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;

public class TypeCheckUtils {

    public static boolean tokenIsOfType(Token token, Class<? extends Token> clazz) {
        return clazz.isAssignableFrom(token.getClass());
    }
}
