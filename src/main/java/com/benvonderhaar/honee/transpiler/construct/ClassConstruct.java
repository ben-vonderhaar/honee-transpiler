package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Scope;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.registry.VariableRegistry;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

public class ClassConstruct extends ConstructToken implements Scope {

    private AccessModifier accessModifier;
    private VariableExpression name;
    private TokenList<ClassBodyConstructToken> classBodyItems;

    public ClassConstruct(AccessModifier accessModifier, VariableExpression name, TokenList<ClassBodyConstructToken> classBodyItems) {

        this.accessModifier = accessModifier;
        this.name = name;
        this.classBodyItems = classBodyItems;

        try {
            VariableRegistry.add(VariableDeclaration.newClassDeclaration(name), this);
        } catch (HoneeException e) {
            System.out.println("Could not add Class " + this.toString() + " + to VariableRegistry");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String toString() {
        return accessModifier.toString() + " class " + name + " { " + classBodyItems.toString() + " }";
    }


    @Override
    public void propagateScope() {
        // TODO
    }

    @Override
    public void addParameters(TokenList<VariableDeclaration> parameters) {
        System.out.println("add params to class? " + parameters);
    }

    @Override
    public void evaluate() {
        // TODO setup statics when class is evaluated?
    }
}
