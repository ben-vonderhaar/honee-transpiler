package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.symbol.LCurlyBracket;
import com.benvonderhaar.honee.transpiler.symbol.RCurlyBracket;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class MultiLineClosureBodyReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {

        if (tokens.length == 3 && tokenIsOfType(tokens[0], LCurlyBracket.class)
                && tokenIsOfType(tokens[1], TokenList.class)
                && tokenIsOfType(tokens[2], RCurlyBracket.class)) {
            try {
                // TODO make this less awful
                //noinspection unchecked
                return LineOfCode.class.isAssignableFrom(((TokenList<LineOfCode>) tokens[1]).getListType());
            } catch (ClassCastException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public Token reduce(Token[] tokens) {
        //noinspection unchecked
        return new ClosureBody((TokenList<LineOfCode>) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { L_CURLY_BRACKET, LINES_OF_CODE, R_CURLY_BRACKET };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ClosureBody.class;
    }
}
