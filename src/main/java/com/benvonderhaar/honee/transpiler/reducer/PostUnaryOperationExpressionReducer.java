package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperationExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_UNARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_EXPRESSION;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class PostUnaryOperationExpressionReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return tokenIsOfType(tokens[0], VariableExpression.class)
                && tokenIsOfType(tokens[1], UnaryOperator.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new UnaryOperationExpression(
                (VariableExpression) tokens[0],
                (UnaryOperator) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { VARIABLE_EXPRESSION, ANY_UNARY_OPERATOR };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return UnaryOperationExpression.class;
    }
}
