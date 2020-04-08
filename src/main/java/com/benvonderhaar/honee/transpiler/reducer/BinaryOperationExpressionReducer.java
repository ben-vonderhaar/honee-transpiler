package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.BinaryOperationExpression;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_BINARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_EXPRESSION;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class BinaryOperationExpressionReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return tokenIsOfType(tokens[0], Expression.class)
                && tokenIsOfType(tokens[1], BinaryOperator.class)
                && tokenIsOfType(tokens[2], Expression.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new BinaryOperationExpression(
                (Expression) tokens[0],
                (BinaryOperator) tokens[1],
                (Expression) tokens[2]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_EXPRESSION, ANY_BINARY_OPERATOR, ANY_EXPRESSION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return BinaryOperationExpression.class;
    }
}
