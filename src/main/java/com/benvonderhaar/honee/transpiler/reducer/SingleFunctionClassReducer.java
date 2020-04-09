package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;

import java.util.Arrays;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.FUNCTION;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class SingleFunctionClassReducer implements Reducer {
    @Override
    public Boolean check(Token[] tokens) {
        return tokens.length == 1 && tokenIsOfType(tokens[0], FunctionConstruct.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new TokenList<>((FunctionConstruct) tokens[0]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { FUNCTION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }
}
