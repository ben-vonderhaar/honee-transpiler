package com.benvonderhaar.honee.transpiler.keyword;

import com.benvonderhaar.honee.transpiler.Lexable;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class AccessModifier extends Keyword implements Lexable {

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

    public static AccessModifier publicKeyword() {
        return new PublicKeyword("");
    }

    public static AccessModifier privateKeyword() {
        return new PrivateKeyword("");
    }

    @Override
    public String getRegex() {
        return "(^public )|(^private )";
    }

}
