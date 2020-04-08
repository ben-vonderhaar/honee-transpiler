package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.registry.VariableAssignmentRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class TestUnaryOperator {

    @Test
    public void testPreIncrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("++a;");

        Assert.assertEquals(Integer.valueOf(2), VariableAssignmentRegistry
                .getVariableValueAsInteger("a").get());
    }

    @Test
    public void testPreDecrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("--a;");

        Assert.assertEquals(Integer.valueOf(0), VariableAssignmentRegistry
                .getVariableValueAsInteger("a").get());
    }

    @Test
    public void testPostIncrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("a++;");

        Assert.assertEquals(Integer.valueOf(2), VariableAssignmentRegistry
                .getVariableValueAsInteger("a").get());
    }

    @Test
    public void testPostDecrement() throws InvocationTargetException, HoneeException, InstantiationException, IllegalAccessException {
        Lexer.processLine("integer a = 1;");
        Lexer.processLine("a--;");

        Assert.assertEquals(Integer.valueOf(0), VariableAssignmentRegistry
                .getVariableValueAsInteger("a").get());
    }

    @After
    public void teardown() {
        VariableAssignmentRegistry.clearRegistry();
    }
}
