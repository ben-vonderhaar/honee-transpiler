package com.benvonderhaar.honee.transpiler.keyword;

public class PrivateKeyword extends AccessModifier {

    public PrivateKeyword(String priv) {
        super(priv);
    }

    @Override
    public String toString() {
        return "private";
    }
}
