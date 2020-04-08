package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.symbol.Equal;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.EQUAL;

public class TwoEqualsToDoubleEqualsReducer implements Reducer {
    @Override
    public Boolean check(Token[] tokens) {
        return tokens.length == 2 && Equal.class.equals(tokens[0].getClass()) && Equal.class.equals(tokens[1].getClass());
    }

    @Override
    public Token reduce(Token[] tokens) {
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
