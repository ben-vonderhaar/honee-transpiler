package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.ClassConstruct;
import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.StaticKeyword;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;

import java.util.Arrays;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class StaticFunctionConstructReducer implements Reducer {

    @Override
    public Boolean check(Token[] tokens) {
        // TODO parameters

        return tokens.length == 6 && tokenIsOfType(tokens[0], AccessModifier.class)
                && tokenIsOfType(tokens[1], StaticKeyword.class)
                && tokenIsOfType(tokens[2], VariableExpression.class)
                && tokenIsOfType(tokens[3], LParen.class)
                && tokenIsOfType(tokens[4], RParen.class)
                && tokenIsOfType(tokens[5], ClosureBody.class);
    }

    @Override
    public Token reduce(Token[] tokens) {

        return new FunctionConstruct(
                (AccessModifier) tokens[0],
                true,
                (VariableExpression) tokens[2],
                (ClosureBody) tokens[5]);
    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_ACCESS_MODIFIER, STATIC, VARIABLE_EXPRESSION, L_PAREN, R_PAREN, CLOSURE_BODY};
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return FunctionConstruct.class;
    }
}
