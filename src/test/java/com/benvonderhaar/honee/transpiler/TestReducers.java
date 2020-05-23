package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.expression.LiteralExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.type.ClassType;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;

public class TestReducers {

    @Test
    public void test0() throws HoneeException {
        List<Token> tokenStack = new ArrayList<>();
        tokenStack.add(new ClassType("Test"));
        tokenStack.add(new VariableExpression("test2"));
        tokenStack.add(EQUAL);
        tokenStack.add(NEW_KEYWORD);
        tokenStack.add(new VariableExpression("Test"));
        tokenStack.add(L_PAREN);
        tokenStack.add(new LiteralExpression("4"));
        tokenStack.add(R_PAREN);
        tokenStack.add(SEMICOLON);

        List<Token> reducedTokens = Lexer.reduceTokens(tokenStack);
        System.out.println(reducedTokens);
    }

    @Test
    public void test1() throws HoneeException {
        List<Token> tokenStack = new ArrayList<>();
        tokenStack.add(new ClassType("Test"));
        tokenStack.add(new VariableExpression("test2"));
        tokenStack.add(EQUAL);
        tokenStack.add(NEW_KEYWORD);
        tokenStack.add(new VariableExpression("Test"));
        tokenStack.add(L_PAREN);
        tokenStack.add(new LiteralExpression("4"));
        tokenStack.add(COMMA);
        tokenStack.add(new LiteralExpression("5"));
        tokenStack.add(R_PAREN);
        tokenStack.add(SEMICOLON);

        List<Token> reducedTokens = Lexer.reduceTokens(tokenStack);
        System.out.println(reducedTokens);
    }
}
