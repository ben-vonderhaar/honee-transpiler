package com.benvonderhaar.honee.transpiler.reducer;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.OptionalToken;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import com.benvonderhaar.honee.transpiler.util.TokenTypesUtil;

import java.util.List;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public interface Reducer {

    Token reduce(Token[] tokens, List<Token> tokenTypes);

    Token[] getInputTokenTypes();

    Class<? extends Token> getOutputClass();

    default Boolean check(Token[] tokens) {
        // TODO parameters

        Token[] tokenInputTypes = getInputTokenTypes();

        for (int inputTypeIndex = 0, tokensIndex = 0; inputTypeIndex < tokenInputTypes.length; inputTypeIndex++, tokensIndex++) {

            boolean tokenIsOptional = tokenIsOfType(tokenInputTypes[inputTypeIndex], OptionalToken.class);

            if (!tokenIsOptional && !tokenIsOfType(tokens[tokensIndex], tokenInputTypes[inputTypeIndex].getClass())) {
                // No match if the current token is not optional and the type of the current token does not match
                // the current token type
                return false;

            } else if (tokenIsOptional && !tokenIsOfType(
                    tokens[tokensIndex], ((OptionalToken<? extends Token>) tokenInputTypes[inputTypeIndex])
                            .getMaterializedToken().getClass())) {

                // Signifies that an optional token was skipped, so decrement the tokensIndex
                // in order to compare the current token to the next token type
                tokensIndex--;
            }
        }
        return true;
    }

}
