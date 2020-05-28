package com.benvonderhaar.honee.transpiler.reducer.operator;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.symbol.Whitespace;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.MINUS_BINARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.WHITESPACE;

public class MinusSpaceMinusToPlusReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
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

    @Override
    public Reducer.Priority getPriority() {
        return Priority.HIGHEST;
    }

    @Override
    public String getDebugText() {
        return "Reduced - - -> +";
    }
}
