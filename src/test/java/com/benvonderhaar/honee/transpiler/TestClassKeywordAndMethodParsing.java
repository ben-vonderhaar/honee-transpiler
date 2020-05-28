package com.benvonderhaar.honee.transpiler;

import org.junit.Test;

public class TestClassKeywordAndMethodParsing {

    @Test
    public void testParseSimpleClass() {
        HoneeClassLoader.loadHoneeClass("src/test/resources/Test.hn");

     //   System.out.println("b = " + VariableRegistry.getVariableValue("b"));
     //   System.out.println("c = " + VariableRegistry.getVariableValue("c"));
    }
}
