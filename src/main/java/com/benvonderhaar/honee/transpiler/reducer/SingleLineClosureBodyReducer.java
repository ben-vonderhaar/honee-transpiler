package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.symbol.LCurlyBracket;
import com.benvonderhaar.honee.transpiler.symbol.RCurlyBracket;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class SingleLineClosureBodyReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        return tokens.length == 3 && tokenIsOfType(tokens[0], LCurlyBracket.class)
                && tokenIsOfType(tokens[1], LineOfCode.class)
                && tokenIsOfType(tokens[2], RCurlyBracket.class);
    }

    @Override
    public Token reduce(Token[] tokens) {
        return new ClosureBody((LineOfCode) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { L_CURLY_BRACKET, LINE_OF_CODE, R_CURLY_BRACKET };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ClosureBody.class;
    }
}
