package com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ConstructorDeclaration;
import com.benvonderhaar.honee.transpiler.construct.FunctionDeclaration;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.R_PAREN;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class MultiParameterFunctionDeclarationReducer implements Reducer {

    @SuppressWarnings("unchecked")
    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        if (tokenIsOfType(tokens[0], VariableExpression.class)) {
            return new FunctionDeclaration(
                    (VariableExpression) tokens[0],
                    (TokenList<VariableDeclaration>) tokens[2]);
        } else {
            return new ConstructorDeclaration((TokenList<VariableDeclaration>) tokens[1]);
        }
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { OPTIONAL_VARIABLE_EXPRESSION, L_PAREN, LIST_OF_VARIABLE_DECLARATIONS, R_PAREN };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return FunctionDeclaration.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getDebugText() {
        return "Reduced multiple parameter function declaration";
    }
}
