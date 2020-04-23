package com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class TwoVariableDeclarationReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new TokenList<>(
                (VariableDeclaration) tokens[0],
                (VariableDeclaration) tokens[2]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { VARIABLE_DECLARATION, COMMA, VARIABLE_DECLARATION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }
}
