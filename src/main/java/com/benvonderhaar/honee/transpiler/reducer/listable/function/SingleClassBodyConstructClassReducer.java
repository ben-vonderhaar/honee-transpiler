package com.benvonderhaar.honee.transpiler.reducer.listable.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClassBodyConstructToken;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.CLASS_BODY_CONSTRUCT_TOKEN;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.FUNCTION;

public class SingleClassBodyConstructClassReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new TokenList<>((ClassBodyConstructToken) tokens[0]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { CLASS_BODY_CONSTRUCT_TOKEN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.LOWEST;
    }

    @Override
    public String getDebugText() {
        return "Reduced single function to list of functions";
    }
}
