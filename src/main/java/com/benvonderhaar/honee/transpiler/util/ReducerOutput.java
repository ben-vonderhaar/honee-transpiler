package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;

import java.lang.reflect.Constructor;
import java.util.function.Function;

public interface ReducerOutput extends Function<Token[], Token> {

    static Constructor constructor(Class<? extends ReducerOutput> reducerOutputClass) {
        return reducerOutputClass.getConstructors()[0];
    }

}
