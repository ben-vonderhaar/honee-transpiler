package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import org.junit.Before;
import org.junit.Test;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_TYPE;
import static org.junit.Assert.assertEquals;

public class TestFunctionEvaluation {

    @Before
    public void setup() throws Throwable {
        HoneeClassLoader.loadHoneeClass("src/test/resources/TestMethodParameters.hn");
    }

    @Test
    public void testParseClassWithMethodsWithParameter() throws Throwable {

        ClosureBody postincrementParameterAndAssignScope = (ClosureBody) VariableRegistry.getScope(
                "postincrementParameterAndAssign", "function");

        VariableRegistry.setVariableValue(
                VariableRegistry.getVariableInScopeByName("g", VARIABLE_TYPE, postincrementParameterAndAssignScope),
                new IntegerLiteral("5"),
                postincrementParameterAndAssignScope);

        postincrementParameterAndAssignScope.evaluate();

        assertEquals(Integer.valueOf(6), VariableRegistry
                .getVariableValueAsInteger("g", postincrementParameterAndAssignScope).get());
        assertEquals(Integer.valueOf(5), VariableRegistry
                .getVariableValueAsInteger("a", postincrementParameterAndAssignScope).get());
    }

    @Test
    public void testParseClassWithMethodsWithParameters() throws Throwable {

        ClosureBody addParametersAndAssignScope = (ClosureBody) VariableRegistry.getScope(
                "addParametersAndAssign", "function");

        VariableRegistry.setVariableValue(
                VariableRegistry.getVariableInScopeByName("g", VARIABLE_TYPE, addParametersAndAssignScope),
                new IntegerLiteral("5"),
                addParametersAndAssignScope);
        VariableRegistry.setVariableValue(
                VariableRegistry.getVariableInScopeByName("h", VARIABLE_TYPE, addParametersAndAssignScope),
                new IntegerLiteral("8"),
                addParametersAndAssignScope);

        // FIXME Causes a NPE when trying to get value of g even after injecting values above
        addParametersAndAssignScope.evaluate();

        assertEquals(Integer.valueOf(13), VariableRegistry
                .getVariableValueAsInteger("a", addParametersAndAssignScope).get());
    }

}
