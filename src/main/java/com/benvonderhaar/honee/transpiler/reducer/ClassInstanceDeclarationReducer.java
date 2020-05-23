package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.type.ClassType;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_EXPRESSION;

public class ClassInstanceDeclarationReducer extends VariableDeclarationReducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new VariableDeclaration(new ClassType(((VariableExpression) tokens[0]).getVariable().getName()),
                (VariableExpression) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { VARIABLE_EXPRESSION, VARIABLE_EXPRESSION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return VariableDeclaration.class;
    }
}
