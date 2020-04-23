package com.benvonderhaar.honee.transpiler.reducer.listable;

import com.benvonderhaar.honee.transpiler.Token;

import java.util.List;

public interface ListableTokenReducer {

    List<List<Token>> getListableTokenListOptions();

}
