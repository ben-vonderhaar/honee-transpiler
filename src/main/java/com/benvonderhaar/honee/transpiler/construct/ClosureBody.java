package com.benvonderhaar.honee.transpiler.construct;

public class ClosureBody extends ConstructToken {

    private TokenList<LineOfCode> linesOfCode;

    public ClosureBody(LineOfCode lineOfCode) {
        this.linesOfCode = new TokenList<LineOfCode>(lineOfCode);
    }

    public ClosureBody(TokenList<LineOfCode> linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    @Override
    public String toString() {
        return "{ " + linesOfCode.toString() + " }";
    }
}
