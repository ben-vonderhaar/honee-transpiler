package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class TestClassInstantiation {

    // TODO add ability to instantiate a class with an HObject

    BufferedReader reader;

    @Before
    public void setup() throws Throwable {
        reader = new BufferedReader(new FileReader("src/test/resources/TestInstantiatableClass.hn"));

        String fileContents = reader.lines()
                .collect(Collectors.joining("\n"));

        Lexer.processHnFileContents(fileContents);
    }

    @Test
    public void testInstantiateClassWithSeveralParameterOptionConstructors() throws Throwable {

        BufferedReader reader2 = new BufferedReader(new FileReader("src/test/resources/TestClassInstantiation.hn"));

        String fileContents = reader2.lines()
                .collect(Collectors.joining("\n"));

        Lexer.processHnFileContents(fileContents);

        ClosureBody testFunction = (ClosureBody) VariableRegistry.getScope(
                "testInstantiateClass", "function");

        //System.out.println(testFunction);

        reader2.close();
    }

    @After
    public void teardown() throws Exception {
        reader.close();
    }
}
