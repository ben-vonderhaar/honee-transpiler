package com.benvonderhaar.honee.transpiler.reducer.function;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.FUNCTION;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.LIST_OF_FUNCTIONS;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class FoldFunctionIntoFunctionsReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        //noinspection unchecked
        TokenList<FunctionConstruct> functions = (TokenList<FunctionConstruct>) tokens[0];
        return functions.fold((FunctionConstruct) tokens[1]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { LIST_OF_FUNCTIONS, FUNCTION };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return TokenList.class;
    }
}
