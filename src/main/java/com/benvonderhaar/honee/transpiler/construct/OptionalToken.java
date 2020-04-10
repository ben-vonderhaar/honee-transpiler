package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Token;

public class OptionalToken<T extends Token> extends ConstructToken {

    private Class<? extends Token> tokenType;
    private T token;

    public OptionalToken(T token) {
        this.tokenType = token.getClass();
        this.token = token;
    }

    public T getMaterializedToken() {
        return this.token;
    }
}
