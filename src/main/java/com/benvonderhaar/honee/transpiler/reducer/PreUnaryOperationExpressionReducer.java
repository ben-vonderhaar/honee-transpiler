package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperationExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_UNARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_EXPRESSION;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class PreUnaryOperationExpressionReducer implements Reducer {
    @Override
    public Boolean check(Token[] tokens) {
        return tokenIsOfType(tokens[0], UnaryOperator.class)
                && tokenIsOfType(tokens[1], VariableExpression.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new UnaryOperationExpression(
                (UnaryOperator) tokens[0],
                (VariableExpression) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_UNARY_OPERATOR, VARIABLE_EXPRESSION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return UnaryOperationExpression.class;
    }
}
