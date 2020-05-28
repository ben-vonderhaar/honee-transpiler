package com.benvonderhaar.honee.transpiler.reducer.loc;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.symbol.Equal;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class AssignmentLineOfCodeReducer implements Reducer {

    @Override
    public Token reduce(Token[] tokens, List<Token> tokenTypes) {

        return new LineOfCode(new AssignmentStatement(
                (VariableDeclaration) tokens[0],
                (Equal) tokens[1],
                (Expression) tokens[2]));

    }

    @Override
    public Token[] getInputTokenTypes() {
        return new Token[] { VARIABLE_DECLARATION, EQUAL, ANY_EXPRESSION, SEMICOLON };
    }

    @Override
    public Class<? extends Token> getOutputClass() {
        return LineOfCode.class;
    }

    @Override
    public Reducer.Priority getPriority() {
        return Priority.HIGH;
    }

    @Override
    public String getDebugText() {
        return "Reduced Assignment LOC";
    }
}
