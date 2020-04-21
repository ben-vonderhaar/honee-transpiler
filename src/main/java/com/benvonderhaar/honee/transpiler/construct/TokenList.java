package com.benvonderhaar.honee.transpiler.construct;

import com.benvonderhaar.honee.transpiler.Token;

import java.util.ArrayList;
import java.util.List;

public class TokenList<T extends Token> extends ConstructToken {

    private Class<? extends Token> listType;

    private List<T> tokenList = new ArrayList<>();

    public TokenList(T t1) {
        listType = t1.getClass();
        tokenList.add(t1);
    }

    public TokenList(T t1, T t2) {
        listType = t1.getClass();
        tokenList.add(t1);
        tokenList.add(t2);
    }

    public TokenList<T> fold(T t1) {
        this.tokenList.add(t1);
        return this;
    }

    public Class<? extends Token> getListType() {
        return this.listType;
    }

    public List<T> getTokenList() {
        return this.tokenList;
    }

    @Override
    public String toString() {
        return tokenList.toString();
    }
}
