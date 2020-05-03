package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class ConstructorConstruct extends ClassBodyConstructToken {
    private AccessModifier accessModifier;
    private ConstructorDeclaration constructorDeclaration;
    private ClosureBody closureBody;

    public ConstructorConstruct(String s) {
        // Do nothing
    }

    public ConstructorConstruct(AccessModifier accessModifier,
                                ConstructorDeclaration constructorDeclaration, ClosureBody closureBody) {
        this.accessModifier = accessModifier;
        this.constructorDeclaration = constructorDeclaration;
        this.closureBody = closureBody;

        this.constructorDeclaration.setScope(this.closureBody);
        this.closureBody.propagateScope();
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " " + constructorDeclaration.toString() + " {...}";
    }
}
