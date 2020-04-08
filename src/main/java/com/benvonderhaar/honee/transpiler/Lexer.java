package com.benvonderhaar.honee.transpiler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.benvonderhaar.honee.transpiler.expression.*;
import com.benvonderhaar.honee.transpiler.literal.BooleanLiteral;
import com.benvonderhaar.honee.transpiler.literal.IntegerLiteral;
import com.benvonderhaar.honee.transpiler.literal.Literal;
import com.benvonderhaar.honee.transpiler.operator.BinaryOperator;
import com.benvonderhaar.honee.transpiler.operator.UnaryOperator;
import com.benvonderhaar.honee.transpiler.reducer.Reducer;
import com.benvonderhaar.honee.transpiler.registry.TokenTypeRegistry;
import com.benvonderhaar.honee.transpiler.statement.Statement;
import com.benvonderhaar.honee.transpiler.symbol.Equal;
import com.benvonderhaar.honee.transpiler.symbol.LParen;
import com.benvonderhaar.honee.transpiler.symbol.RParen;
import com.benvonderhaar.honee.transpiler.symbol.Semicolon;
import com.benvonderhaar.honee.transpiler.symbol.Variable;
import com.benvonderhaar.honee.transpiler.symbol.Whitespace;
import com.benvonderhaar.honee.transpiler.type.Type;
import com.benvonderhaar.honee.transpiler.util.BooleanTokenArrayTuple;
import com.benvonderhaar.honee.transpiler.util.HoneeException;

import static com.benvonderhaar.honee.transpiler.util.ReducerUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TokenTypesUtil.*;
import static com.benvonderhaar.honee.transpiler.util.TypeCheckUtil.tokenIsOfType;

public class Lexer {

	public static void main(String[] args) throws HoneeException, IllegalAccessException, InvocationTargetException, InstantiationException {
		String[] lines = { 
				"integer a = (1+2)*(8/4)+2*(1+5+6);", 
				"boolean b = a==31;" };

		//String line = "((((4+2)/(5-4))))==6";
		//String line = "(1+2)*(8/4)+2*(1+5+6)==31==false";

		processLine(lines[0]);
		processLine(lines[1]);

		/*processLine("integer c = 1 - - - 1");
		processLine("integer c = 1 - -- 1");
		processLine("integer c = 1 -- - 1");
		processLine("integer c = 1 --- 1");*/
		int c = 2;
		int d = c---2;
	}
	
	public static void processLine(String line) throws HoneeException, IllegalAccessException, InstantiationException, InvocationTargetException {
		// TODO:
		// * Simplify BinaryOperator rules
		// * Support for floats
		// * Support for strings
		// * Control support (if, loops)
		
		String line2 = line;
		
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
		List<Class<? extends Token>> tokenTypes = new ArrayList<>();
		tokenTypes.add(LiteralExpression.class);
		tokenTypes.add(Type.class);
		tokenTypes.add(VariableExpression.class); // Must go after type
		tokenTypes.add(Equal.class);
		tokenTypes.add(LParen.class);
		tokenTypes.add(RParen.class);
		tokenTypes.add(Semicolon.class);
		tokenTypes.add(Whitespace.class);
		tokenTypes.add(UnaryOperator.class);
		tokenTypes.add(BinaryOperator.class);

	    Stack<Token> tokens = new Stack<>();
	   
	    boolean foundToken;
	    
	    do {
	    	
	    	foundToken = false;

			for (Class<? extends Token> tokenType : tokenTypes) {

				if (!TokenTypeRegistry.hasRegex(tokenType)) {
					continue;
				}

				Pattern pattern = Pattern.compile(
						TokenTypeRegistry.getTokenType(tokenType).getRegex());

				Matcher matcher = pattern.matcher(line);

				if (matcher.find() && !matcher.group(0).equals("")) {

					if (tokenType.equals(Whitespace.class)) {
						tokens.add(WHITESPACE);
					} else if (tokenType.equals(LiteralExpression.class)) {

						if (matcher.group(0).equals("true") || matcher.group(0).equals("false")) {
							tokens.add(new LiteralExpression(
									new BooleanLiteral(matcher.group(0))));
						} else {
							tokens.add(new LiteralExpression(
									new IntegerLiteral(matcher.group(0))));
						}

					} else if (tokenType.equals(Type.class)) {

						// TODO use tokentyperegistry
						tokens.add((Token) Type.getType(matcher.group(0)).getConstructors()[0].newInstance(""));
					} else if (tokenType.equals(VariableExpression.class)) {
						tokens.add(new VariableExpression(new Variable(matcher.group(0))));
					} else {
						tokens.add(tokenType.cast(
								tokenType.getConstructors()[0].newInstance(matcher.group(0))));
					}
					line = line.substring(matcher.group(0).length());
					foundToken = true;
					break;
				}
			}
	    	
	    	if (!foundToken) {
	    		throw new HoneeException("Could not match next token, remaining line: " + line);
	    	}
	    	
	    } while (!line.isEmpty());
		
	    System.out.println(line2);
		tokens.forEach(token -> System.out.println(token.toString() + " :: " + token.getClass()));
	    
		List<Token> parserStack = new ArrayList<>();
		Token lookahead = null;
		
		boolean didReduction = false;
		
		while (!tokens.isEmpty() || didReduction) {

			System.out.println("Tokens: " + tokens);

			if (!tokens.isEmpty()) {
				System.out.println("Operating on: " + tokens.get(0));
			}

			if (!tokens.empty()) {
				parserStack.add(tokens.remove(0));
				lookahead = tokens.isEmpty() ? null : tokens.get(0);
			}

			System.out.println("Parser Stack: " + parserStack);

			didReduction = false;

			if (reduce(parserStack, lookahead, PRE_UNARY_OPERATION_EXPRESSION_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced PRE UNOP Expression");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}

			if (reduce(parserStack, lookahead, POST_UNARY_OPERATION_EXPRESSION_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced POST UNOP Expression");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}

			if (reduce(parserStack, lookahead, BINARY_OPERATION_EXPRESSION_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced BINOP Expression");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}
			
			if (reduce(parserStack, lookahead, PARENTHESIS_EXPRESSION_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced Parenthesis");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}
			
			if (reduce(parserStack, lookahead, TWO_EQUALS_TO_DOUBLE_EQUALS_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced BINOP ==");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}

			// [-, _, -] -> [+]
			if (reduce(parserStack, lookahead, MINUS_SPACE_MINUS_TO_PLUS_REDUCER).bool()) {
				didReduction = true;
				System.out.println("Reduced - - -> +");
				System.out.println("Parser Stack: " + parserStack);
				System.out.println();
				continue;
			}

			System.out.println("No reduction\n");
			
		}

		System.out.println(parserStack);
		
		parserStack = parserStack.stream()
				.filter(token -> !token.getClass().equals(Whitespace.class)).collect(Collectors.toList());

		System.out.println(parserStack);
		
		// Parse assignment
		reduce(parserStack, lookahead, ASSIGNMENT_STATEMENT_REDUCER);

		System.out.println(parserStack);

		if (tokenIsOfType(parserStack.get(0), Statement.class)) {
			((Statement) parserStack.get(0)).evaluate();
		} else if (tokenIsOfType(parserStack.get(0), Expression.class)) {
			((Expression) parserStack.get(0)).evaluate();
		}

		// TODO last token on stack isn't handled, but so far is only a semicolon.  Verify this is ok and/or
		// implement overarching strategy for dealing with semicolons
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
	private static BooleanTokenArrayTuple reduce(
			List<Token> stack,
			Token lookahead,
			Reducer reducer) throws HoneeException {

		Token[] tokenTypes = reducer.getInputTokenTypes();
		Class<? extends Token> outputClass = reducer.getOutputClass();

		for (int i = 0; i <= stack.size() - tokenTypes.length; i++) {

			if (tokenTypes[0].getClass().isAssignableFrom(stack.get(i).getClass())) {

				boolean matchedAllTokens = true;
				
				for (int j = 0; j < tokenTypes.length; j++) {

					if (!tokenTypes[j].getClass().isAssignableFrom(stack.get(i + j).getClass())) {
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

				List<Token> matchedTokens = new ArrayList<>();
				
				for (int j = 0; j < tokenTypes.length; j++) {
					
					Token thisToken = stack.remove(i);
					
					matchedTokens.add(Literal.class.isAssignableFrom(thisToken.getClass()) 
							? new LiteralExpression((Literal) thisToken) : thisToken);
				}

				Token[] matchedTokensArray = matchedTokens.toArray(new Token[0]);

				if (reducer.check(matchedTokensArray)) {
					stack.add(i, reducer.reduce(matchedTokensArray));
				}

				return new BooleanTokenArrayTuple(true, matchedTokens);
				
			} 
		}
		
		return new BooleanTokenArrayTuple(false, null);
	}

}
