package com.benvonderhaar.honee.transpiler.reducer.listable.constructor;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class TwoExpressionReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new TokenList<>(
                (Expression) tokens[0],
                (Expression) tokens[2]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_EXPRESSION, COMMA, ANY_EXPRESSION };
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
        return "Reduced two expressions to list of expression";
    }

}
