package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;

public class TwoFunctionClassReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return null;
    }

    @Override
    public Token reduce(Token[] tokens) {
        return null;
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[0];
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return null;
    }
}
