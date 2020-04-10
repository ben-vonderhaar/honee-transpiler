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

    public static final AssignmentLineOfCodeReducer ASSIGNMENT_LINE_OF_CODE_REDUCER
            = new AssignmentLineOfCodeReducer();
    public static final ExpressionLineOfCodeReducer EXPRESSION_LINE_OF_CODE_REDUCER
            = new ExpressionLineOfCodeReducer();

    public static final TwoLinesOfCodeReducer TWO_LINES_OF_CODE_REDUCER
            = new TwoLinesOfCodeReducer();
    public static final FoldLineOfCodeIntoLinesOfCodeReducer FOLD_LINE_OF_CODE_INTO_LINES_OF_CODE_REDUCER
            = new FoldLineOfCodeIntoLinesOfCodeReducer();
    public static final SingleLineClosureBodyReducer SINGLE_LINE_CLOSURE_BODY_REDUCER
            = new SingleLineClosureBodyReducer();
    public static final MultiLineClosureBodyReducer MULTI_LINE_CLOSURE_BODY_REDUCER
            = new MultiLineClosureBodyReducer();

    public static final ClassConstructReducer CLASS_CONSTRUCT_REDUCER
            = new ClassConstructReducer();
    public static final StaticFunctionConstructReducer STATIC_FUNCTION_CONSTRUCT_REDUCER
            = new StaticFunctionConstructReducer();
    public static final SingleFunctionClassReducer SINGLE_FUNCTION_CLASS_REDUCER
            = new SingleFunctionClassReducer();

}
