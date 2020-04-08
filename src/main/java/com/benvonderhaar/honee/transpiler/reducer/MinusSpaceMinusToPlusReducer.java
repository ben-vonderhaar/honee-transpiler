package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.symbol.Whitespace;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.MINUS_BINARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.WHITESPACE;

public class MinusSpaceMinusToPlusReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return tokens.length == 3
                && BinaryOperator.class.equals(tokens[0].getClass()) && tokens[0].toString().equals("-")
                && Whitespace.class.equals(tokens[1].getClass())
                && BinaryOperator.class.equals(tokens[2].getClass()) && tokens[2].toString().equals("-");
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new BinaryOperator("+");
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { MINUS_BINARY_OPERATOR, WHITESPACE, MINUS_BINARY_OPERATOR };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return BinaryOperator.class;
    }
}
