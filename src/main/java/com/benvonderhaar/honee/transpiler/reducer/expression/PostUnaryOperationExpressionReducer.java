package com.benvonderhaar.honee.transpiler.reducer.expression;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.UnaryOperationExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_UNARY_OPERATOR;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_EXPRESSION;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class PostUnaryOperationExpressionReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
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

    @Override
    public Reducer.Priority getPriority() {
        return Priority.HIGHEST;
    }

    @Override
    public String getDebugText() {
        return "Reduced POST UNOP Expression";
    }
}
