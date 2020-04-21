package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class FunctionConstruct extends ConstructToken {

    private AccessModifier accessModifier;
    private boolean isStatic;
    private VariableExpression variableExpression;
    private ClosureBody closureBody;

    public FunctionConstruct(String s) {

    }

    public FunctionConstruct(AccessModifier accessModifier, boolean isStatic,
                             VariableExpression variableExpression, ClosureBody closureBody) {
        this.accessModifier = accessModifier;
        this.isStatic = isStatic;
        this.variableExpression = variableExpression;
        this.closureBody = closureBody;

        try {
            VariableRegistry.add(VariableDeclaration.newFunctionDeclaration(variableExpression), closureBody);
        } catch (HoneeException e) {
            System.out.println("Could not add Function " + this.toString() + " + to VariableRegistry");
            e.printStackTrace();
            System.exit(1);
        }

        this.variableExpression.setScope(this.closureBody);
        this.closureBody.propagateScope();
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " " + (isStatic ? "static " : "") + variableExpression.toString() + " {...}";
    }
}
