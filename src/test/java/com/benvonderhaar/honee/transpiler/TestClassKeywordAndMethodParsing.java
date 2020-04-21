package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class TestClassKeywordAndMethodParsing {

    @Test
    public void testParseSimpleClass() throws Throwable {
        BufferedReader reader =
                new BufferedReader(new FileReader("src/test/resources/Test.hn"));

        String fileContents = reader.lines()
                .collect(Collectors.joining("\n"));

        Lexer.processHnFileContents(fileContents);

     //   System.out.println("b = " + VariableRegistry.getVariableValue("b"));
     //   System.out.println("c = " + VariableRegistry.getVariableValue("c"));
    }
}
