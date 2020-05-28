package com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class FoldVariableDeclarationIntoVariableDeclarationsReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        //noinspection unchecked
        TokenList<VariableDeclaration> variables = (TokenList<VariableDeclaration>) tokens[0];
        return variables.fold((VariableDeclaration) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { LIST_OF_VARIABLE_DECLARATIONS, COMMA, VARIABLE_DECLARATION};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getDebugText() {
        return "Folded variable into list of variable declarations";
    }
}
