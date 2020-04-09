package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;

import java.util.Arrays;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class AssignmentLineOfCodeReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        System.out.println("Check AssignmentLineOfCodeReducer");
        System.out.println(Arrays.asList(tokens));
        return false;
    }

    @Override
    public Token reduce(Token[] tokens) {
        return null;
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] {ASSIGNMENT_STATEMENT, SEMICOLON};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return LineOfCode.class;
    }
}
