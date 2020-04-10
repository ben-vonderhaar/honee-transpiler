package com.benvonderhaar.honee.transpiler.reducer.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.FUNCTION;

public class SingleFunctionClassReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
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
