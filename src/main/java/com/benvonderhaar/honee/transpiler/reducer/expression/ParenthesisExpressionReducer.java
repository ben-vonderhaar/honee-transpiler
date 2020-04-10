package com.benvonderhaar.honee.transpiler.reducer.expression;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.ParenthesisExpression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class ParenthesisExpressionReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new ParenthesisExpression(
                (LParen) tokens[0],
                (Expression) tokens[1],
                (RParen) tokens[2]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { L_PAREN, ANY_EXPRESSION, R_PAREN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ParenthesisExpression.class;
    }
}
