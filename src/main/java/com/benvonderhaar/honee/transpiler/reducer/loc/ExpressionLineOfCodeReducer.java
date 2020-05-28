package com.benvonderhaar.honee.transpiler.reducer.loc;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class ExpressionLineOfCodeReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new LineOfCode((Expression) tokens[0]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] {ANY_EXPRESSION, SEMICOLON};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return LineOfCode.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.MEDIUM;
    }

    @Override
    public String getDebugText() {
        return "Reduced expression LOC";
    }
}
