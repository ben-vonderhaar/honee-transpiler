package com.benvonderhaar.honee.transpiler.reducer.loc;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.Semicolon;
import com.benvonderhaar.honee.transpiler.type.Type;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class AssignmentLineOfCodeReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new LineOfCode(new AssignmentStatement(
                (Type) tokens[0],
                (VariableExpression) tokens[1],
                (Equal) tokens[2],
                (Expression) tokens[3]));

    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { ANY_TYPE, VARIABLE_EXPRESSION, EQUAL, ANY_EXPRESSION, SEMICOLON };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return LineOfCode.class;
    }
}
