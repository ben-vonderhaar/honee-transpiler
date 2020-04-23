package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class TestScopeHarness {

    private ClosureBody scope;

    public TestScopeHarness(String... locs) {

        if (0 == locs.length) {
            fail("TestScopeHarness must be provided at least one line of code");
        }

        List<LineOfCode> linesOfCodeList = Stream.of(locs)
                .map(loc -> {
                    try {
                        return Lexer.processLine(loc);
                    } catch (HoneeException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        e.printStackTrace();
                        fail("Exception processing the following loc: " + loc);
                        return null;
                    }
                })
                .peek(tokens -> assertEquals(tokens.size(), 1))
                .map(tokens -> (LineOfCode) tokens.get(0))
                .collect(Collectors.toList());

        TokenList<LineOfCode> linesOfCode = new TokenList<>(linesOfCodeList.get(0));

        linesOfCodeList.stream()
                .skip(1)
                .forEach(linesOfCode::fold);

        this.scope = new ClosureBody(linesOfCode);

        this.scope.propagateScope();
    }

    public void evaluateScope() {
        this.scope.evaluate();
    }

    public ClosureBody getScope() {
        return this.scope;
    }
}
