package com.benvonderhaar.honee.transpiler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.benvonderhaar.honee.transpiler.construct.OptionalToken;
import com.benvonderhaar.honee.transpiler.construct.TokenList;
import com.benvonderhaar.honee.transpiler.expression.*;
import com.benvonderhaar.honee.transpiler.keyword.*;
import com.benvonderhaar.honee.transpiler.literal.*;
import com.benvonderhaar.honee.transpiler.operator.*;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.reducer.listable.functiondeclaration.MultiParameterFunctionDeclarationReducer;
import com.benvonderhaar.honee.transpiler.registry.LexableTokenTypeRegistry;
import com.benvonderhaar.honee.transpiler.symbol.*;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.BooleanMatchedTokensArrayTokenTypesArrayTuple;
import com.benvonderhaar.honee.transpiler.util.HoneeException;
import com.benvonderhaar.honee.transpiler.util.ReducerUtil;
import com.benvonderhaar.honee.transpiler.util.TokenTypesAndMatchedTokensTuple;

import static com.benvonderhaar.honee.transpiler.util.ReducerUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class Lexer {

	public static void main(String[] args) throws HoneeException, IllegalAccessException, InvocationTargetException, InstantiationException {

		String[] lines = {
				"integer a = (1+2)*(8/4)+2*(1+5+6);", 
				"boolean b = a==31;" };

		processLine(lines[0]);
		processLine(lines[1]);
	}

	public static List<Token> processHnFileContents(String fileContents) throws Throwable {
		System.out.println("File Contents: \n--------------------\n" + fileContents + "\n--------------------");
		List<Token> tokens = tokenize(fileContents);
		return reduceTokens(tokens);
	}

	public static List<Token> tokenize(String honeeCodeInput) throws HoneeException, IllegalAccessException, InvocationTargetException, InstantiationException {

		List<Class<? extends Token>> tokenTypes = new ArrayList<>();
		tokenTypes.add(LiteralExpression.class);
		tokenTypes.add(Type.class);
		tokenTypes.add(AccessModifier.class);
		tokenTypes.add(StaticKeyword.class);
		tokenTypes.add(ClassKeyword.class);
		tokenTypes.add(NewKeyword.class);

		tokenTypes.add(VariableExpression.class);

		tokenTypes.add(Equal.class);
		tokenTypes.add(Comma.class);
		tokenTypes.add(LParen.class);
		tokenTypes.add(RParen.class);
		tokenTypes.add(LCurlyBracket.class);
		tokenTypes.add(RCurlyBracket.class);
		tokenTypes.add(Semicolon.class);
		tokenTypes.add(Whitespace.class);

		tokenTypes.add(UnaryOperator.class);
		tokenTypes.add(BinaryOperator.class);

		Stack<Token> tokens = new Stack<>();

		boolean foundToken;

		do {

			foundToken = false;

			//System.out.println(tokens);

			for (Class<? extends Token> tokenType : tokenTypes) {

				if (!LexableTokenTypeRegistry.hasRegex(tokenType)) {
					continue;
				}

				Pattern pattern = Pattern.compile(
						LexableTokenTypeRegistry.getTokenType(tokenType).getRegex());

				Matcher matcher = pattern.matcher(honeeCodeInput);

				if (matcher.find() && !matcher.group(0).equals("")) {

					String match = matcher.group(0);

					if (tokenType.equals(Whitespace.class)) {

						tokens.add(WHITESPACE);

					} else {

						match = match.trim();

						if (tokenType.equals(LiteralExpression.class)) {

							if (match.equals("true") || match.equals("false")) {
								tokens.add(new LiteralExpression(
										new BooleanLiteral(match)));
							} else {
								tokens.add(new LiteralExpression(
										new IntegerLiteral(match)));
							}

						} else if (tokenType.equals(Type.class)) {

							tokens.add((Token) LexableTokenTypeRegistry.getTokenType(Type.getType(match)));

						} else if (tokenType.equals(AccessModifier.class)) {

							tokens.add((Token) LexableTokenTypeRegistry.getTokenType(AccessModifier.getAccessModifier(match)));

						} else if (tokenType.equals(VariableExpression.class)) {

							tokens.add(new VariableExpression(new Variable(match)));

						} else if (tokenType.equals(UnaryOperator.class)) {

							tokens.add(UnaryOperator.getUnaryOperator(match));

						} else if (tokenType.equals(BinaryOperator.class)) {

							tokens.add(BinaryOperator.getBinaryOperator(match));

						} else {

							tokens.add((Token) LexableTokenTypeRegistry.getTokenType(tokenType));

						}
					}

					honeeCodeInput = honeeCodeInput.substring(match.length());
					foundToken = true;
					break;
				}
			}

			if (!foundToken && !honeeCodeInput.isEmpty()) {
				throw new HoneeException("Could not match next token, remaining line: " + honeeCodeInput);
			}

		} while (!honeeCodeInput.isEmpty());

		System.out.println("\nTokenization result:");
		tokens.forEach(token -> System.out.println(token.toString() + " :: " + token.getClass()));

		return tokens.stream()
				.filter(token -> !token.getClass().equals(Whitespace.class)).collect(Collectors.toList());
	}

	public static List<Token> reduceTokens(List<Token> tokens) throws HoneeException {
		List<Token> parserStack = new ArrayList<>();
		Token lookahead = null;

		boolean didReduction = false;

		System.out.println("\nReducing:");

		while (!tokens.isEmpty() || didReduction) {

			if (!tokens.isEmpty()) {
				parserStack.add(tokens.remove(0));
				lookahead = tokens.isEmpty() ? null : tokens.get(0);
			}

			System.out.println("Parser Stack: " + parserStack);

			didReduction = false;

			ListIterator<Reducer> reducers = ReducerUtil.getReducers();

			while (reducers.hasNext()) {
				Reducer r = reducers.next();

				if (reduce(parserStack, lookahead, r).bool()) {
					didReduction = true;
					System.out.println(r.getDebugText());
					System.out.println("Parser Stack: " + parserStack);
					System.out.println();
					break;
				}
			}

		}

		return parserStack;

	}

	public static List<Token> processLine(String line) throws HoneeException, IllegalAccessException, InstantiationException, InvocationTargetException {
		// TODO:
		// * Simplify BinaryOperator rules
		// * Support for floats
		// * Support for strings
		// * Control support (if, loops)
		
		/*{ regex: /^\s+/, tokenType: 'WHITESPACE' },
	    { regex: /^[{}]/, tokenType: 'BRACE' },
	    { regex: /^[\[\]]/, tokenType: 'BRACKET' },
	    { regex: /^:/, tokenType: 'COLON' },
	    { regex: /^,/, tokenType: 'COMMA' },
	    { regex: /^-?\d+(?:\.\d+)?(?:e[+\-]?\d+)?/i, tokenType: 'NUMBER_LITERAL' },
	    { regex: /^"(?:\\.|[^"])*"(?=:)/, tokenType: 'STRING_KEY'},
	    { regex: /^"(?:\\.|[^"])*"/, tokenType: 'STRING_LITERAL'},
	    { regex: /^true|false/, tokenType: 'BOOLEAN_LITERAL' },
	    { regex: /^null/, tokenType: 'NULL' }
		*/

		List<Token> tokens = tokenize(line);
		return reduceTokens(tokens);
	}

	/**
	 * Given a list of tokens, a lookahead (the next token), a type of expression to look for,
	 * and a list of token types to satisfy the desired expression type, attempt to reduce the
	 * known token list and the lookahead to the desired expression by matching all specified token types.
	 *
	 * A simple case is:
	 * reduce([1, +, 2], null, BinaryOperationExpression, AnyExpression, BinaryOperation, AnyExpression)
	 * => returns (true, BinaryOperationExpression(1+2))
	 *
	 * By this, we mean that when provided three tokens (an expression, a binary operation, and another expression),
	 * and we are instructed to look for those three token types in order to reduce to a BinaryOperationExpression,
	 * we remove the three tokens from the token list and
	 *
	 * @param stack
	 * @param lookahead
	 * @return
	 * @throws Exception
	 */
	private static BooleanMatchedTokensArrayTokenTypesArrayTuple reduce(
			List<Token> stack,
			Token lookahead,
			Reducer reducer) {

		Class<? extends Token> outputClass = reducer.getOutputClass();

		List<TokenTypesAndMatchedTokensTuple> matches = getAllTokenListOptions(reducer).stream()
				.sorted((option1, option2) -> option2.size() - option1.size())
				.map(tokenTypesOption -> {

					List<Token> matchedTokens = new ArrayList<>();

					for (int i = 0; i <= stack.size() - tokenTypesOption.size(); i++) {

						boolean firstTokenIsOptional = tokenIsOfType(tokenTypesOption.get(0), OptionalToken.class);
						Class<? extends Token> materializedFirstTokenClass = firstTokenIsOptional ?
								((OptionalToken<? extends Token>) tokenTypesOption.get(0)).getMaterializedToken().getClass() :
								tokenTypesOption.get(0).getClass();

						if (materializedFirstTokenClass.isAssignableFrom(stack.get(i).getClass())) {

							boolean matchedAllTokens = true;

							for (int j = 0; j < tokenTypesOption.size(); j++) {

								boolean tokenIsOptional = tokenIsOfType(tokenTypesOption.get(j), OptionalToken.class);
								Class<? extends Token> materializedTokenClass = tokenIsOptional ?
										((OptionalToken<? extends Token>) tokenTypesOption.get(j)).getMaterializedToken().getClass() :
										tokenTypesOption.get(j).getClass();

								boolean tokenIsList = tokenIsOfType(tokenTypesOption.get(j), TokenList.class);
								Class<? extends Token> materializedTokenListClass = tokenIsList ?
										((TokenList<? extends Token>) tokenTypesOption.get(j)).getListType() :
										materializedTokenClass;

								if (!materializedTokenClass.isAssignableFrom(stack.get(i + j).getClass())
									|| (tokenIsList && !materializedTokenListClass.isAssignableFrom(
										((TokenList<? extends Token>) stack.get(i + j)).getListType()))) {
									matchedAllTokens = false;
									break;
								}

							}

							if (!matchedAllTokens) {
								continue;
							}

							// Operator priority
							if (outputClass.equals(BinaryOperationExpression.class)
									&& !BinaryOperationExpression.satisfiesOperatorPriorityRules(stack, lookahead, i)) {
								continue;
							}

							for (int j = 0; j < tokenTypesOption.size(); j++) {

								Token thisToken = stack.remove(i);

								matchedTokens.add(Literal.class.isAssignableFrom(thisToken.getClass())
										? new LiteralExpression((Literal) thisToken) : thisToken);
							}

							Token[] matchedTokensArray = matchedTokens.toArray(new Token[0]);

							if (reducer.check(matchedTokensArray)) {
								stack.add(i, reducer.reduce(matchedTokensArray, tokenTypesOption));
							} else {
								// Put matched tokens back onto the stack
								while (!matchedTokens.isEmpty()) {
									stack.add(i, matchedTokens.remove(matchedTokens.size() - 1));
								}
							}
						}
					}

					return new TokenTypesAndMatchedTokensTuple(tokenTypesOption, matchedTokens);
				})
				.filter(TokenTypesAndMatchedTokensTuple::hasMatchedTokens)
				.collect(Collectors.toList());

		if (1 == matches.size()) {

			return new BooleanMatchedTokensArrayTokenTypesArrayTuple(
					true, matches.get(0).getMatchedTokens(), matches.get(0).getTokenTypesOption());

		} else if (matches.isEmpty()) {

			return new BooleanMatchedTokensArrayTokenTypesArrayTuple(
					false, null, null);

		} else {

			System.out.println("Multiple token pattern matches for " + reducer.getClass().getName());
			System.exit(1);
			return null;

		}
	}

}
