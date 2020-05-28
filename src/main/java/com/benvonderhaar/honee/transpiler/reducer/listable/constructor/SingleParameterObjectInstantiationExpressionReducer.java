package com.benvonderhaar.honee.transpiler.reducer.listable.constructor;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.ObjectInstantiationExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.R_PAREN;

public class SingleParameterObjectInstantiationExpressionReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new ObjectInstantiationExpression((VariableExpression) tokens[1],
                List.of((Expression) tokens[3]));

    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { NEW_KEYWORD, VARIABLE_EXPRESSION, L_PAREN, ANY_EXPRESSION, R_PAREN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ObjectInstantiationExpression.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.HIGH;
    }

    @Override
    public String getDebugText() {
        return "Reduced to object instance";
    }

}

