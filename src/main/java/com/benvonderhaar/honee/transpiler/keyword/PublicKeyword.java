package com.benvonderhaar.honee.transpiler.keyword;

public class PublicKeyword extends AccessModifier {

    public PublicKeyword(String pub) {
        super(pub);
    }

    @Override
    public String toString() {
        return "public";
    }
}
