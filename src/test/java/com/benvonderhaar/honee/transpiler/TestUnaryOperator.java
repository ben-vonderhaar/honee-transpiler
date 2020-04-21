package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUnaryOperator {

    @Test
    public void testPreIncrement() throws HoneeException {

        TestScopeHarness harness = new TestScopeHarness("integer a = 1;", "integer b = ++a;");
        harness.evaluateScope();

        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("a", harness.getScope()).get());
        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("b", harness.getScope()).get());
    }

    @Test
    public void testPreDecrement() throws HoneeException {
        TestScopeHarness harness = new TestScopeHarness("integer a = 1;", "integer b = --a;");
        harness.evaluateScope();

        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("a", harness.getScope()).get());
        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("b", harness.getScope()).get());
    }

    @Test
    public void testPostIncrement() throws HoneeException {
        TestScopeHarness harness = new TestScopeHarness("integer a = 1;", "integer b = a++;");
        harness.evaluateScope();

        assertEquals(Integer.valueOf(2), VariableRegistry
                .getVariableValueAsInteger("a", harness.getScope()).get());
        assertEquals(Integer.valueOf(1), VariableRegistry
                .getVariableValueAsInteger("b", harness.getScope()).get());
    }

    @Test
    public void testPostDecrement() throws HoneeException {
        TestScopeHarness harness = new TestScopeHarness("integer a = 1;", "integer b = a--;");
        harness.evaluateScope();

        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("a", harness.getScope()).get());
        assertEquals(Integer.valueOf(1), VariableRegistry
                .getVariableValueAsInteger("b", harness.getScope()).get());
    }

    @After
    public void teardown() {
        VariableRegistry.clearRegistry();
    }
}
