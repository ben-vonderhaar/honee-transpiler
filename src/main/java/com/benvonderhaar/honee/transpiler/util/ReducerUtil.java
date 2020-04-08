package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.reducer.*;

public class ReducerUtil {

    public static final MinusSpaceMinusToPlusReducer MINUS_SPACE_MINUS_TO_PLUS_REDUCER
            = new MinusSpaceMinusToPlusReducer();
    public static final TwoEqualsToDoubleEqualsReducer TWO_EQUALS_TO_DOUBLE_EQUALS_REDUCER
            = new TwoEqualsToDoubleEqualsReducer();

    public static final PreUnaryOperationExpressionReducer PRE_UNARY_OPERATION_EXPRESSION_REDUCER
            = new PreUnaryOperationExpressionReducer();
    public static final PostUnaryOperationExpressionReducer POST_UNARY_OPERATION_EXPRESSION_REDUCER
            = new PostUnaryOperationExpressionReducer();
    public static final BinaryOperationExpressionReducer BINARY_OPERATION_EXPRESSION_REDUCER
            = new BinaryOperationExpressionReducer();
    public static final ParenthesisExpressionReducer PARENTHESIS_EXPRESSION_REDUCER
            = new ParenthesisExpressionReducer();

    public static final AssignmentStatementReducer ASSIGNMENT_STATEMENT_REDUCER
            = new AssignmentStatementReducer();
}
