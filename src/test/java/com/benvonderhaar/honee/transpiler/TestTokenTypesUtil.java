package com.benvonderhaar.honee.transpiler;

import com.benvonderhaar.honee.transpiler.reducer.function.FunctionConstructReducer;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;
import com.benvonderhaar.honee.transpiler.util.TokenTypesUtil;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;
import static org.junit.Assert.*;

public class TestTokenTypesUtil {

    @Test
    public void testTokenRemovalAtIndex0() {

        Token[] tokens = { OPTIONAL_STATIC, L_PAREN, R_PAREN };

        tokens = TokenTypesUtil.removeTokenFromList(tokens, 0);

        assertEquals(2, tokens.length);
        assertTrue(tokenIsOfType(tokens[0], LParen.class));
        assertTrue(tokenIsOfType(tokens[1], RParen.class));
    }

    @Test
    public void testTokenRemovalAtIndexNminus1() {

        Token[] tokens = {L_PAREN, R_PAREN, OPTIONAL_STATIC };

        tokens = TokenTypesUtil.removeTokenFromList(tokens, 2);

        assertEquals(2, tokens.length);
        assertTrue(tokenIsOfType(tokens[0], LParen.class));
        assertTrue(tokenIsOfType(tokens[1], RParen.class));
    }

    @Test
    public void testTokenRemovalAtInnerIndex() {

        Token[] tokens = { L_PAREN, OPTIONAL_STATIC, R_PAREN };

        tokens = TokenTypesUtil.removeTokenFromList(tokens, 1);

        assertEquals(2, tokens.length);
        assertTrue(tokenIsOfType(tokens[0], LParen.class));
        assertTrue(tokenIsOfType(tokens[1], RParen.class));

    }

    @Test
    public void testTokenListOptions() {

        Token[] tokens = { L_PAREN, OPTIONAL_STATIC, R_PAREN };

        List<List<Token>> tokenListOptions = TokenTypesUtil.getAllTokenListOptions(tokens);

        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(tokens)));
        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(TokenTypesUtil.removeTokenFromList(tokens, 1))));
    }

    @Test
    public void testFunctionConstructReducerTokens() {

        Token[] tokens = new FunctionConstructReducer().getInputTokenTypes();
        
        List<List<Token>> tokenListOptions = TokenTypesUtil.getAllTokenListOptions(tokens);

        tokenListOptions.forEach(System.out::println);
        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(tokens)));
        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(TokenTypesUtil.removeTokenFromList(tokens, 0))));
        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(TokenTypesUtil.removeTokenFromList(tokens, 1))));
        assertTrue(optionsContainsOption(tokenListOptions,
                materializeTokensList(TokenTypesUtil.removeTokenFromList(
                                TokenTypesUtil.removeTokenFromList(tokens, 0), 0))));
    }
    
    private boolean optionsContainsOption(List<List<Token>> options, List<Token> option) {

        return 1 == options.stream()
                .filter(thisOption -> {
                    if (thisOption.size() != option.size()) {
                        return false;
                    }

                    return IntStream.range(0, thisOption.size())
                            .mapToObj(i -> thisOption.get(i).getClass().equals(option.get(i).getClass()))
                            .noneMatch(Predicate.not(Boolean::booleanValue));
                })
                .count();
    }
}
