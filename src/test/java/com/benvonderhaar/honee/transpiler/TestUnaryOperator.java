package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestUnaryOperator {

    @Test
    public void testPreIncrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {

        List<Token> assignmentStatementTokens = Lexer.processLine("integer a = 1;");
        List<Token> preIncrementTokens = Lexer.processLine("integer b = ++a;");

        assertEquals(assignmentStatementTokens.size(), 1);
        assertEquals(preIncrementTokens.size(), 1);

        TokenList<LineOfCode> linesOfCode = new TokenList<>(
                (LineOfCode) assignmentStatementTokens.get(0),
                (LineOfCode) preIncrementTokens.get(0));

        Scope testScope = new ClosureBody(linesOfCode);

        testScope.propagateScope();
        testScope.evaluate();

        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("a", testScope).get());
        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("b", testScope).get());
    }
/*
    @Test
    public void testPreDecrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("--a;");

        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("a").get());
    }

    @Test
    public void testPostIncrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("a++;");

        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("a").get());
    }

    @Test
    public void testPostDecrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("a--;");

        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("a").get());
    }*/

    @After
    public void teardown() {
        VariableRegistry.clearRegistry();
    }
}
