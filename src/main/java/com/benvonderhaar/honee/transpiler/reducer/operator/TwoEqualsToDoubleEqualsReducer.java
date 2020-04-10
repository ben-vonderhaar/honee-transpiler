package com.benvonderhaar.honee.transpiler.reducer.operator;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.EQUAL;

public class TwoEqualsToDoubleEqualsReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new BinaryOperator("==");
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { EQUAL, EQUAL } ;
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return BinaryOperator.class;
    }
}
