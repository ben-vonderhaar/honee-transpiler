package com.benvonderhaar.honee.transpiler.reducer.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.OptionalToken;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.StaticKeyword;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class FunctionConstructReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        AccessModifier accessModifier = AccessModifier.publicKeyword();
        boolean isStatic = false;

        // TODO make this generic with default behavior and/or specified handling logic via lambdas
        for (int i = 0; i < tokens.length; i++) {
            if (tokenIsOfType(tokens[i], AccessModifier.class)) {
                accessModifier = (AccessModifier) tokens[i];
            }
            if (tokenIsOfType(tokens[i], StaticKeyword.class)) {
                isStatic = true;
            }
        }

        return new FunctionConstruct(
            accessModifier,
            isStatic,
            (VariableExpression) tokens[tokens.length - 4],
            (ClosureBody) tokens[tokens.length - 1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { OPTIONAL_ACCESS_MODIFIER, OPTIONAL_STATIC, VARIABLE_EXPRESSION, L_PAREN, R_PAREN, CLOSURE_BODY};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return FunctionConstruct.class;
    }
}
