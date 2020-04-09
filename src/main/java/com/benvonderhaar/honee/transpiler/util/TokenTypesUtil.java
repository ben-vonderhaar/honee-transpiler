package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.construct.ClosureBody;
import com.benvonderhaar.honee.transpiler.construct.FunctionConstruct;
import com.benvonderhaar.honee.transpiler.construct.LineOfCode;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.ClassKeyword;
import com.benvonderhaar.honee.transpiler.keyword.StaticKeyword;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.symbol.*;
import com.benvonderhaar.honee.transpiler.type.AnyType;

public class TokenTypesUtil {

    public static final LParen L_PAREN = new LParen("");
    public static final RParen R_PAREN = new RParen("");
    public static final LCurlyBracket L_CURLY_BRACKET = new LCurlyBracket("");
    public static final RCurlyBracket R_CURLY_BRACKET = new RCurlyBracket("");
    public static final Equal EQUAL = new Equal("");
    public static final Semicolon SEMICOLON = new Semicolon("");
    public static final Whitespace WHITESPACE = new Whitespace("");

    public static final AnyType ANY_TYPE = new AnyType();
    public static final AccessModifier ANY_ACCESS_MODIFIER = new AccessModifier("");

    public static final AnyExpression ANY_EXPRESSION = new AnyExpression();
    public static final VariableExpression VARIABLE_EXPRESSION = new VariableExpression("");
    public static final AssignmentStatement ASSIGNMENT_STATEMENT = new AssignmentStatement("");

    public static final BinaryOperator ANY_BINARY_OPERATOR = new BinaryOperator("");
    public static final BinaryOperator MINUS_BINARY_OPERATOR = new BinaryOperator("-");
    public static final UnaryOperator ANY_UNARY_OPERATOR = new UnaryOperator("");

    public static final StaticKeyword STATIC = new StaticKeyword("");
    public static final ClassKeyword CLASS = new ClassKeyword("");

    public static final FunctionConstruct FUNCTION = new FunctionConstruct("");
    public static final TokenList<FunctionConstruct> LIST_OF_FUNCTIONS = new TokenList<>(FUNCTION);

    public static final LineOfCode LINE_OF_CODE = new LineOfCode(ANY_EXPRESSION);
    public static final TokenList<LineOfCode> LINES_OF_CODE = new TokenList<>(LINE_OF_CODE);

    public static final ClosureBody CLOSURE_BODY = new ClosureBody(LINE_OF_CODE);
}
