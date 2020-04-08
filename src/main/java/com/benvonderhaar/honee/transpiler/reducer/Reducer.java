package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;

public interface Reducer {

    Boolean check(Token[] tokens);

    Token reduce(Token[] tokens);

    Token[] getInputTokenTypes();

    Class<? extends Token> getOutputClass();

}
