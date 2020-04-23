package com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.FunctionDeclaration;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class SingleParameterFunctionDeclarationReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new FunctionDeclaration((VariableExpression) tokens[0], (VariableDeclaration) tokens[2]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { VARIABLE_EXPRESSION, L_PAREN, VARIABLE_DECLARATION, R_PAREN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return FunctionDeclaration.class;
    }
}
