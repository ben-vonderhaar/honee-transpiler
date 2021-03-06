package com.benvonderhaar.honee.transpiler.reducer.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.*;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.StaticKeyword;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class ConstructorConstructReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        AccessModifier accessModifier = AccessModifier.publicKeyword();

        // TODO make this generic with default behavior and/or specified handling logic via lambdas
        for (int i = 0; i < tokens.length; i++) {
            if (tokenIsOfType(tokens[i], AccessModifier.class)) {
                accessModifier = (AccessModifier) tokens[i];
            }
        }

        return new ConstructorConstruct(
            accessModifier,
            (ConstructorDeclaration) tokens[tokens.length - 2],
            (ClosureBody) tokens[tokens.length - 1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { OPTIONAL_ACCESS_MODIFIER, CONSTRUCTOR_DECLARATION, CLOSURE_BODY};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ConstructorConstruct.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public String getDebugText() {
        return "Reduced constructor definition";
    }
}
