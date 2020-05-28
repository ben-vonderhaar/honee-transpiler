package com.benvonderhaar.honee.transpiler.reducer.listable.closurebody;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class SingleLineClosureBodyReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new ClosureBody((LineOfCode) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { L_CURLY_BRACKET, LINE_OF_CODE, R_CURLY_BRACKET };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ClosureBody.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getDebugText() {
        return "Reduced single LOC closure body";
    }
}
