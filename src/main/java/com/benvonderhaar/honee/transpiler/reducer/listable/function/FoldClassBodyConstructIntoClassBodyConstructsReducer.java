package com.benvonderhaar.honee.transpiler.reducer.listable.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClassBodyConstructToken;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class FoldClassBodyConstructIntoClassBodyConstructsReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        //noinspection unchecked
        TokenList<ClassBodyConstructToken> functions = (TokenList<ClassBodyConstructToken>) tokens[0];
        return functions.fold((ClassBodyConstructToken) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { LIST_OF_CLASS_BODY_CONSTRUCT_TOKEN, CLASS_BODY_CONSTRUCT_TOKEN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public String getDebugText() {
        return "Folded class body construct into existing class body constructs";
    }
}
