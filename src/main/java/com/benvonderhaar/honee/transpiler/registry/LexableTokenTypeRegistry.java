package com.benvonderhaar.honee.transpiler.registry;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class LexableTokenTypeRegistry {

    private static Map<Class<? extends Token>, Lexable> tokenTypeToTokenMap = new HashMap<>();

    public static Lexable getTokenType(Class<? extends Token> tokenType) throws HoneeException {

        Lexable lexableToken = tokenTypeToTokenMap.get(tokenType);

        if (null == lexableToken) {

            try {
                lexableToken = (Lexable) tokenType.getConstructor().newInstance();
            } catch (Exception e) {
                try {
                    lexableToken = (Lexable) tokenType.getConstructor(String.class).newInstance("");
                } catch (Exception e2) {
                    throw new HoneeException("Unable to register the following token type: " + tokenType.getName());
                }
            }

            tokenTypeToTokenMap.put(tokenType, lexableToken);
        }

        return lexableToken;
    }

    public static boolean hasRegex(Class<? extends Token> tokenType) {

        Lexable lexableToken = tokenTypeToTokenMap.get(tokenType);

        if (null == lexableToken) {

            try {
                lexableToken = (Lexable) tokenType.getConstructor().newInstance();
            } catch (Exception e) {
                try {
                    lexableToken = (Lexable) tokenType.getConstructor(String.class).newInstance("");
                } catch (Exception e2) {
                    return false;
                }
            }

            tokenTypeToTokenMap.put(tokenType, lexableToken);
        }

        return true;
    }
}
