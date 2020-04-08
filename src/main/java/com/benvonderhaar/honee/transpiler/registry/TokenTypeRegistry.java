package com.benvonderhaar.honee.transpiler.registry;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TokenTypeRegistry {

    private static Map<Class<? extends Token>, Token> tokenTypeToTokenMap = new HashMap<>();

    public static Token getTokenType(Class<? extends Token> tokenType) throws HoneeException {

        Token token = tokenTypeToTokenMap.get(tokenType);

        if (null == token) {
            try {
                // Forces all tokens to implement a constructor that takes a single object
                // TODO remove this restriction
                token = (Token) tokenType.getConstructors()[0].newInstance((Object) null);
            } catch (Exception e) {
                e.printStackTrace();
                throw new HoneeException("Unable to register the following token type: " + tokenType.getName());
            }

            tokenTypeToTokenMap.put(tokenType, token);
        }

        return token;
    }

    public static boolean hasRegex(Class<? extends Token> tokenType) {

        Token token = tokenTypeToTokenMap.get(tokenType);

        if (null == token) {

            try {
                // Forces all tokens to implement a constructor that takes a single object
                // TODO remove this restriction
                token = (Token) tokenType.getConstructors()[0].newInstance("");
            } catch (Exception e) {
                return false;
            }

            tokenTypeToTokenMap.put(tokenType, token);
        }

        return true;
    }
}
