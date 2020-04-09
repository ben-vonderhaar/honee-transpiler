package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClassConstruct;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.ClassKeyword;
import com.benvonderhaar.honee.transpiler.symbol.LCurlyBracket;
import com.benvonderhaar.honee.transpiler.symbol.RCurlyBracket;

import java.util.Arrays;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class ClassConstructReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        if (tokens.length == 6 && tokenIsOfType(tokens[0], AccessModifier.class)
                && tokenIsOfType(tokens[1], ClassKeyword.class)
                && tokenIsOfType(tokens[2], VariableExpression.class)
                && tokenIsOfType(tokens[3], LCurlyBracket.class)
                && tokenIsOfType(tokens[4], TokenList.class)
                && tokenIsOfType(tokens[5], RCurlyBracket.class)) {
            try {
                // TODO make this less awful
                //noinspection unchecked
                return FunctionConstruct.class.isAssignableFrom(((TokenList<FunctionConstruct>) tokens[4]).getListType());
            } catch (ClassCastException e) {
                return false;
            }
        }

        return false;
    }

    @Override
    public Token reduce(Token[] tokens) {
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
