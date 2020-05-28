package com.benvonderhaar.honee.transpiler.reducer.listable.loc;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.LINE_OF_CODE;

public class TwoLinesOfCodeReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new TokenList<>((LineOfCode) tokens[0], (LineOfCode) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { LINE_OF_CODE, LINE_OF_CODE };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.LOW;
    }

    @Override
    public String getDebugText() {
        return "Reduced two LOC into LinesOfCode";
    }
}
