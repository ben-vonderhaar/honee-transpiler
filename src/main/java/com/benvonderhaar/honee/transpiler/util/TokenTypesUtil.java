package com.benvonderhaar.honee.transpiler.util;

import com.benvonderhaar.honee.transpiler.Token;
import com.benvonderhaar.honee.transpiler.construct.*;
import com.benvonderhaar.honee.transpiler.expression.AnyExpression;
import com.benvonderhaar.honee.transpiler.construct.VariableDeclaration;
import com.benvonderhaar.honee.transpiler.expression.Expression;
import com.benvonderhaar.honee.transpiler.expression.VariableExpression;
import com.benvonderhaar.honee.transpiler.keyword.AccessModifier;
import com.benvonderhaar.honee.transpiler.keyword.ClassKeyword;
import com.benvonderhaar.honee.transpiler.keyword.NewKeyword;
import com.benvonderhaar.honee.transpiler.keyword.StaticKeyword;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.reducer.listable.ListableTokenReducer;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.statement.AssignmentStatement;
import com.benvonderhaar.honee.transpiler.symbol.*;
import com.benvonderhaar.honee.transpiler.type.AnyType;
import com.benvonderhaar.honee.transpiler.type.VariableType;

import java.util.*;
import java.util.stream.Collectors;

import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class TokenTypesUtil {

    public static final LParen L_PAREN = new LParen("");
    public static final RParen R_PAREN = new RParen("");
    public static final LCurlyBracket L_CURLY_BRACKET = new LCurlyBracket("");
    public static final RCurlyBracket R_CURLY_BRACKET = new RCurlyBracket("");
    public static final Equal EQUAL = new Equal("");
    public static final Comma COMMA = new Comma("");
    public static final Semicolon SEMICOLON = new Semicolon("");
    public static final Whitespace WHITESPACE = new Whitespace("");

    public static final AnyType ANY_TYPE = new AnyType();
    public static final VariableType VARIABLE_TYPE = new VariableType("");
    public static final AccessModifier ANY_ACCESS_MODIFIER = new AccessModifier("");

    // TODO clean up?
    public static final Expression EXPRESSION = new Expression() {
        @Override
        public Literal evaluate() {
            return null;
        }
    };

    public static final AnyExpression ANY_EXPRESSION = new AnyExpression();
    public static final VariableExpression VARIABLE_EXPRESSION = new VariableExpression("");
    public static final OptionalToken<VariableExpression> OPTIONAL_VARIABLE_EXPRESSION = new OptionalToken<>(VARIABLE_EXPRESSION);
    public static final AssignmentStatement ASSIGNMENT_STATEMENT = new AssignmentStatement("");
    public static final VariableDeclaration VARIABLE_DECLARATION = new VariableDeclaration("");
    public static final TokenList<VariableDeclaration> LIST_OF_VARIABLE_DECLARATIONS = new TokenList<>(VARIABLE_DECLARATION);
    public static final TokenList<AnyExpression> LIST_OF_EXPRESSIONS = new TokenList<>(ANY_EXPRESSION);

    public static final BinaryOperator ANY_BINARY_OPERATOR = new BinaryOperator("");
    public static final BinaryOperator MINUS_BINARY_OPERATOR = new BinaryOperator("-");
    public static final UnaryOperator ANY_UNARY_OPERATOR = new UnaryOperator("");
    public static final UnaryOperator INCREMENT_UNARY_OPERATOR = new UnaryOperator("++");
    public static final UnaryOperator DECREMENT_UNARY_OPERATOR = new UnaryOperator("--");

    public static final StaticKeyword STATIC = new StaticKeyword("");
    public static final ClassKeyword CLASS = new ClassKeyword("");
    public static final NewKeyword NEW_KEYWORD = new NewKeyword();

    public static final FunctionConstruct FUNCTION = new FunctionConstruct("");
    public static final ClassBodyConstructToken CLASS_BODY_CONSTRUCT_TOKEN = new ClassBodyConstructToken();
    public static final TokenList<ClassBodyConstructToken> LIST_OF_CLASS_BODY_CONSTRUCT_TOKEN = new TokenList<>(CLASS_BODY_CONSTRUCT_TOKEN);

    public static final LineOfCode LINE_OF_CODE = new LineOfCode(EXPRESSION);
    public static final TokenList<LineOfCode> LINES_OF_CODE = new TokenList<>(LINE_OF_CODE);

    public static final ConstructorDeclaration CONSTRUCTOR_DECLARATION = new ConstructorDeclaration(VARIABLE_DECLARATION);
    public static final FunctionDeclaration FUNCTION_DECLARATION = new FunctionDeclaration(VARIABLE_EXPRESSION, VARIABLE_DECLARATION);
    public static final ClosureBody CLOSURE_BODY = new ClosureBody(LINE_OF_CODE);

    public static final OptionalToken<StaticKeyword> OPTIONAL_STATIC = new OptionalToken<>(STATIC);
    public static final OptionalToken<AccessModifier> OPTIONAL_ACCESS_MODIFIER = new OptionalToken<>(ANY_ACCESS_MODIFIER);


    public static List<List<Token>> getAllTokenListOptions(Reducer reducer) {

        if (reducer.getClass().isAssignableFrom(ListableTokenReducer.class)) {
            return ((ListableTokenReducer) reducer).getListableTokenListOptions();
        } else {
            return getAllTokenListOptions(reducer.getInputTokenTypes());
        }
    }

    public static List<List<Token>> getAllTokenListOptions(Token[] tokens) {


        Set<List<Token>> tokenListOptions = new HashSet<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokenIsOfType(tokens[i], OptionalToken.class)) {
                tokenListOptions.addAll(getAllTokenListOptions(removeTokenFromList(tokens, i)));
            }
        }

        List<Token> materializedTokenList = materializeTokensList(tokens);

        tokenListOptions.add(materializedTokenList);

        return new ArrayList<>(tokenListOptions);
    }

    public static List<Token> materializeTokensList(Token[] tokens) {

        return Arrays.stream(tokens)
                .map(token -> tokenIsOfType(token, OptionalToken.class)
                        ? ((OptionalToken<? extends Token>) token).getMaterializedToken() : token)
                .collect(Collectors.toList());

    }

    public static Token[] removeTokenFromList(Token[] tokens, int index) {
        Token[] reducedTokenList = new Token[tokens.length - 1];

        for (int i = 0; i < index; i++) {
            reducedTokenList[i] = tokens[i];
        }

        for (int i = index + 1; i < tokens.length; i++) {
            reducedTokenList[i - 1] = tokens[i];
        }

        return reducedTokenList;
    }
}
