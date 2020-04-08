package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;
import com.benvonderhaar.honee.transpiler.symbol.Whitespace;
import com.benvonderhaar.honee.transpiler.type.AnyType;

public class TokenTypesUtil {

    public static final LParen L_PAREN = new LParen("");
    public static final RParen R_PAREN = new RParen("");
    public static final Equal EQUAL = new Equal("");
    public static final Whitespace WHITESPACE = new Whitespace("");

    public static final AnyType ANY_TYPE = new AnyType();

    public static final AnyExpression ANY_EXPRESSION = new AnyExpression();
    public static final VariableExpression VARIABLE_EXPRESSION = new VariableExpression("");

    public static final BinaryOperator ANY_BINARY_OPERATOR = new BinaryOperator("");
    public static final BinaryOperator MINUS_BINARY_OPERATOR = new BinaryOperator("-");
    public static final UnaryOperator ANY_UNARY_OPERATOR = new UnaryOperator("");
}
