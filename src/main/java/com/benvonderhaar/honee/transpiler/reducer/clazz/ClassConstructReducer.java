package com.benvonderhaar.honee.transpiler.reducer.clazz;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClassConstruct;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.util.ReducerUtil;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class ClassConstructReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {

        if (!Reducer.super.check(tokens)) {
            return false;
        } else {
            return ReducerUtil.matchesTokenListType(FunctionConstruct.class, tokens[4]);
        }

    }

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {
        return new ClassConstruct(
                (AccessModifier) tokens[0],
                (VariableExpression) tokens[2],
                (TokenList<FunctionConstruct>) tokens[4]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_ACCESS_MODIFIER, CLASS, VARIABLE_EXPRESSION, L_CURLY_BRACKET,
        LIST_OF_FUNCTIONS, R_CURLY_BRACKET};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return ClassConstruct.class;
    }
}
