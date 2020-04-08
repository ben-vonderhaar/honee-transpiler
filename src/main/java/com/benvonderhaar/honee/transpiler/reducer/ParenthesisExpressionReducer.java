package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.ParenthesisExpression;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class ParenthesisExpressionReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return tokenIsOfType(tokens[0], LParen.class)
                && tokenIsOfType(tokens[1], Expression.class)
                && tokenIsOfType(tokens[2], RParen.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
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
