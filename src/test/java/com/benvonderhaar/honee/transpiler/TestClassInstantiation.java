package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import org.junit.Before;
import org.junit.Test;

public class TestClassInstantiation {

    // TODO add ability to instantiate a class with an HObject

    @Before
    public void setup() {
        HoneeClassLoader.loadHoneeClass("src/test/resources/TestInstantiatableClass.hn");
    }

    @Test
    public void testInstantiateClassWithSeveralParameterOptionConstructors() {

        HoneeClassLoader.loadHoneeClass("src/test/resources/TestClassInstantiation.hn");

        ClosureBody testFunction = (ClosureBody) VariableRegistry.getScope(
                "testInstantiateClass", "function");

        //System.out.println(testFunction);
    }

}
