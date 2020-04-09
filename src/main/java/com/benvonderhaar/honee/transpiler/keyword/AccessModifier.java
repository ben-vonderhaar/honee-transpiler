package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AccessModifier extends Keyword {

    public AccessModifier(String accessModifier) {

    }

    public static Class<? extends AccessModifier> getAccessModifier(String accessModifier) throws HoneeException {
        if (accessModifier.equals("public")) {
            return PublicKeyword.class;
        } else if (accessModifier.equals("private")) {
            return PrivateKeyword.class;
        } else {
            throw new HoneeException("Unrecognized access modifier: " + accessModifier);
        }

    }

    @Override
    public String getRegex() {
        return "(^public )|(^private )";
    }

}
