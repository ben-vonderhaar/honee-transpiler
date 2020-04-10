package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;

import java.util.List;

public class TokenTypesAndMatchedTokensTuple {

    private List<Token> tokenTypesOption, matchedTokens;

    public TokenTypesAndMatchedTokensTuple(List<Token> tokenTypesOption, List<Token> matchedTokens) {
        this.tokenTypesOption = tokenTypesOption;
        this.matchedTokens = matchedTokens;
    }

    public List<Token> getTokenTypesOption() {
        return tokenTypesOption;
    }

    public List<Token> getMatchedTokens() {
        return matchedTokens;
    }

    public boolean hasMatchedTokens() {
        return !matchedTokens.isEmpty();
    }
}
