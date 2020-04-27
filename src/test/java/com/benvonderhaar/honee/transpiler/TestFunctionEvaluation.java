package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.FunctionDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.VARIABLE_TYPE;
import static org.junit.Assert.assertEquals;

public class TestFunctionEvaluation {

    @Test
    public void test() throws HoneeException {

        TestScopeHarness harness = new TestScopeHarness("integer a = 1;", "integer b = --a;");
        harness.evaluateScope();

        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("a", harness.getScope()).get());
        assertEquals(Integer.valueOf(0), VariableRegistry
                .getVariableValueAsInteger("b", harness.getScope()).get());

        //new FunctionConstruct(AccessModifier.publicKeyword(), false, new FunctionDeclaration(""), harness.getScope());
    }

    @Test
    public void testParseClassWithMethodsWithParameter() throws Throwable {
        BufferedReader reader =
                new BufferedReader(new FileReader("src/test/resources/TestMethodParameters.hn"));

        String fileContents = reader.lines()
                .collect(Collectors.joining("\n"));

        Lexer.processHnFileContents(fileContents);

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

        //   System.out.println("b = " + VariableRegistry.getVariableValue("b"));
        //   System.out.println("c = " + VariableRegistry.getVariableValue("c"));
    }

    @Test
    public void testParseClassWithMethodsWithParameters() throws Throwable {
        BufferedReader reader =
                new BufferedReader(new FileReader("src/test/resources/TestMethodParameters.hn"));

        String fileContents = reader.lines()
                .collect(Collectors.joining("\n"));

        Lexer.processHnFileContents(fileContents);

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
