package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.reducer.ClassInstanceDeclarationReducer;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
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

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class ReducerUtil {

    public static List<Reducer> reducers = List.of(
                new MinusSpaceMinusToPlusReducer(),
                new TwoEqualsToDoubleEqualsReducer(),

                new PreUnaryOperationExpressionReducer(),
                new PostUnaryOperationExpressionReducer(),
                new BinaryOperationExpressionReducer(),
                new ParenthesisExpressionReducer(),
                new VariableDeclarationReducer(),
                new ClassInstanceDeclarationReducer(),

                new AssignmentLineOfCodeReducer(),
                new ExpressionLineOfCodeReducer(),
                new TwoExpressionReducer(),

                new NoParameterFunctionDeclarationReducer(),
                new SingleParameterFunctionDeclarationReducer(),
                new MultiParameterFunctionDeclarationReducer(),
                new TwoVariableDeclarationReducer(),
                new FoldVariableDeclarationIntoVariableDeclarationsReducer(),

                new TwoLinesOfCodeReducer(),
                new FoldLineOfCodeIntoLinesOfCodeReducer(),
                new SingleLineClosureBodyReducer(),
                new MultiLineClosureBodyReducer(),

                new ClassConstructReducer(),
                new FunctionConstructReducer(),
                new ConstructorConstructReducer(),
                new SingleClassBodyConstructClassReducer(),
                new FoldClassBodyConstructIntoClassBodyConstructsReducer(),

                new NoParameterObjectInstantiationExpressionReducer(),
                new SingleParameterObjectInstantiationExpressionReducer(),
                new MultiParameterObjectInstantiationExpressionReducer())
            .stream()
            .sorted(Comparator.comparingInt(Reducer::getPriorityValue).reversed())
            .collect(Collectors.toList());

    public static ListIterator<Reducer> getReducers() {
        return reducers.listIterator();
    }


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
