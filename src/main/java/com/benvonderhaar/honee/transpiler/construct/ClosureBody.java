package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;

public class ClosureBody extends ConstructToken implements Scope {

    private TokenList<LineOfCode> linesOfCode;

    public ClosureBody(LineOfCode lineOfCode) {
        this.linesOfCode = new TokenList<LineOfCode>(lineOfCode);
    }

    public ClosureBody(TokenList<LineOfCode> linesOfCode) {
        this.linesOfCode = linesOfCode;
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
        // TODO
    }

    @Override
    public String toString() {
        return "{ " + linesOfCode.toString() + " }";
    }

}
