package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class FunctionConstruct extends ConstructToken {

    private AccessModifier accessModifier;
    private boolean isStatic;
    private FunctionDeclaration functionDeclaration;
    private String name;
    private ClosureBody closureBody;

    public FunctionConstruct(String s) {
        this.name = "";
    }

    public FunctionConstruct(AccessModifier accessModifier, boolean isStatic,
                             FunctionDeclaration functionDeclaration, ClosureBody closureBody) {
        this.accessModifier = accessModifier;
        this.isStatic = isStatic;
        this.functionDeclaration = functionDeclaration;
        this.closureBody = closureBody;
        this.name = functionDeclaration.getName().toString();

        try {
            VariableRegistry.add(VariableDeclaration.newFunctionDeclaration(functionDeclaration.getName()), closureBody);
        } catch (HoneeException e) {
            System.out.println("Could not add Function " + this.toString() + " + to VariableRegistry");
            e.printStackTrace();
            System.exit(1);
        }

        this.functionDeclaration.setScope(this.closureBody);
        this.closureBody.propagateScope();
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " " + (isStatic ? "static " : "") + functionDeclaration.toString() + " {...}";
    }
}
