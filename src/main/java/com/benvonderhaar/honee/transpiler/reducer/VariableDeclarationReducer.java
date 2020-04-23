package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.type.Type;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.ANY_TYPE;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_EXPRESSION;

public class VariableDeclarationReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new VariableDeclaration(
                (Type) tokens[0],
                (VariableExpression) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_TYPE, VARIABLE_EXPRESSION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return VariableDeclaration.class;
    }
}
