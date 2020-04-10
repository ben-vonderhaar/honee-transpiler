package com.benvonderhaar.honee.transpiler.reducer.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.util.ReducerUtil;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class MultiLineClosureBodyReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {

        if (!Reducer.super.check(tokens)) {
            return false;
        } else {
            return ReducerUtil.matchesTokenListType(LineOfCode.class, tokens[1]);
        }

    }

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        //noinspection unchecked
        return new ClosureBody((TokenList<LineOfCode>) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { L_CURLY_BRACKET, LINES_OF_CODE, R_CURLY_BRACKET };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ClosureBody.class;
    }
}
