package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import java.util.Objects;

public class ClosureBody extends ConstructToken implements Scope {

    private TokenList<LineOfCode> linesOfCode;
    private String stringRepresentation;

    public ClosureBody(LineOfCode lineOfCode) {
        this.linesOfCode = new TokenList<>(lineOfCode);
        this.stringRepresentation = "{ " + linesOfCode.toString() + " }";
    }

    public ClosureBody(TokenList<LineOfCode> linesOfCode) {
        this.linesOfCode = linesOfCode;
        this.stringRepresentation = "{ " + linesOfCode.toString() + " }";
    }

    public TokenList<LineOfCode> getLinesOfCode() {
        return this.linesOfCode;
    }

    // TODO make interface evaluateable?
    @Override
    public void evaluate() {
        linesOfCode.getTokenList().forEach(LineOfCode::evaluate);
    }

    @Override
    public void propagateScope() {
        this.linesOfCode.getTokenList().forEach(
                lineOfCode -> lineOfCode.setScope(this)
        );
    }

    @Override
    public void addParameters(TokenList<VariableDeclaration> parameters) {
        parameters.getTokenList().forEach(parameter -> {
            try {
                VariableRegistry.add(parameter, this);
            } catch (HoneeException e) {
                System.out.println("Failed to add parameter: " + parameter);
                e.printStackTrace();
                System.exit(1);
            }
        });
    }

    @Override
    public String toString() {
        return this.stringRepresentation;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.stringRepresentation);
    }
}
