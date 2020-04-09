package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;

import javax.sound.sampled.Line;
import java.util.Arrays;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.LINE_OF_CODE;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class TwoLinesOfCodeReducer implements Reducer {
    @Override
    public Boolean check(Token[] tokens) {
        return tokens.length == 2 && tokenIsOfType(tokens[0], LineOfCode.class)
                && tokenIsOfType(tokens[1], LineOfCode.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
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
}
