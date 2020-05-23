package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.ClassInstanceDeclarationReducer;
import com.benvonderhaar.honee.transpiler.reducer.VariableDeclarationReducer;
import com.benvonderhaar.honee.transpiler.reducer.clazz.ClassConstructReducer;
import com.benvonderhaar.honee.transpiler.reducer.expression.BinaryOperationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.expression.ParenthesisExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.expression.PostUnaryOperationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.expression.PreUnaryOperationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.function.ConstructorConstructReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.closurebody.MultiLineClosureBodyReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.closurebody.SingleLineClosureBodyReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.constructor.MultiParameterObjectInstantiationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.constructor.NoParameterObjectInstantiationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.constructor.SingleParameterObjectInstantiationExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.constructor.TwoExpressionReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.function.FoldClassBodyConstructIntoClassBodyConstructsReducer;
import com.benvonderhaar.honee.transpiler.reducer.function.FunctionConstructReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.function.SingleClassBodyConstructClassReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration.*;
import com.benvonderhaar.honee.transpiler.reducer.listable.loc.FoldLineOfCodeIntoLinesOfCodeReducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.loc.TwoLinesOfCodeReducer;
import com.benvonderhaar.honee.transpiler.reducer.loc.*;
import com.benvonderhaar.honee.transpiler.reducer.operator.MinusSpaceMinusToPlusReducer;
import com.benvonderhaar.honee.transpiler.reducer.operator.TwoEqualsToDoubleEqualsReducer;

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
    public static final VariableDeclarationReducer VARIABLE_DECLARATION_REDUCER
            = new VariableDeclarationReducer();
    public static final ClassInstanceDeclarationReducer CLASS_INSTANCE_DECLARATION_REDUCER
            = new ClassInstanceDeclarationReducer();

    public static final AssignmentLineOfCodeReducer ASSIGNMENT_LINE_OF_CODE_REDUCER
            = new AssignmentLineOfCodeReducer();
    public static final ExpressionLineOfCodeReducer EXPRESSION_LINE_OF_CODE_REDUCER
            = new ExpressionLineOfCodeReducer();
    public static final TwoExpressionReducer TWO_EXPRESSION_REDUCER
            = new TwoExpressionReducer();

    public static final NoParameterFunctionDeclarationReducer NO_PARAMETER_FUNCTION_DECLARATION_REDUCER
            = new NoParameterFunctionDeclarationReducer();
    public static final SingleParameterFunctionDeclarationReducer SINGLE_PARAMETER_FUNCTION_DECLARATION_REDUCER
            = new SingleParameterFunctionDeclarationReducer();
    public static final MultiParameterFunctionDeclarationReducer MULTI_PARAMETER_FUNCTION_DECLARATION_REDUCER
            = new MultiParameterFunctionDeclarationReducer();
    public static final TwoVariableDeclarationReducer TWO_VARIABLE_DECLARATION_REDUCER
            = new TwoVariableDeclarationReducer();
    public static final FoldVariableDeclarationIntoVariableDeclarationsReducer FOLD_VARIABLE_INTO_VARIABLES_REDUCER
            = new FoldVariableDeclarationIntoVariableDeclarationsReducer();

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
    public static final FunctionConstructReducer FUNCTION_CONSTRUCT_REDUCER
            = new FunctionConstructReducer();
    public static final ConstructorConstructReducer CONSTRUCTOR_CONSTRUCT_REDUCER
            = new ConstructorConstructReducer();
    public static final SingleClassBodyConstructClassReducer SINGLE_CLASS_BODY_CONSTRUCT_CLASS_REDUCER
            = new SingleClassBodyConstructClassReducer();
    public static final FoldClassBodyConstructIntoClassBodyConstructsReducer FOLD_CLASS_BODY_CONSTRUCT_INTO_CLASS_BODY_CONSTRUCTS_REDUCER
            = new FoldClassBodyConstructIntoClassBodyConstructsReducer();

    public static final NoParameterObjectInstantiationExpressionReducer NO_PARAMETER_OBJECT_INSTANTIATION_EXPRESSION_REDUCER
            = new NoParameterObjectInstantiationExpressionReducer();
    public static final SingleParameterObjectInstantiationExpressionReducer SINGLE_PARAMETER_OBJECT_INSTANTIATION_EXPRESSION_REDUCER
            = new SingleParameterObjectInstantiationExpressionReducer();
    public static final MultiParameterObjectInstantiationExpressionReducer MULTI_PARAMETER_OBJECT_INSTANTIATION_EXPRESSION_REDUCER
            = new MultiParameterObjectInstantiationExpressionReducer();

    public static boolean matchesTokenListType(Class<? extends Token> tokenType, Token token) {

        try {
            // TODO make this less awful
            //noinspection rawtypes
            return tokenType.isAssignableFrom(((TokenList) token).getListType());
        } catch (ClassCastException e) {
            return false;
        }
    }
}
