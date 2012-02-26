package prefixer.lex;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import prefixer.Lexer;
import prefixer.Operation;


/**
 * Lex the input string and provides different tokens for a parser.
 * 
 * @author John
 * 
 */
public class SimpleLexer implements Lexer {
	public static final Pattern ARITHMETIC_PATTERN = Pattern.compile("[+-/*]");
	public static final Pattern VARIABLE_PATTERN = Pattern.compile("[A-Za-z]");
	public static final Pattern VALUE_PATTERN = Pattern.compile("[0-9][0-9]*");
	public static final Pattern BRACE_PATTERN = Pattern.compile("[\\(\\)]");

	private final Queue<String> _tokenQueue = new LinkedList<String>();

	public SimpleLexer(String input) {
		// Break up the input by whitespace.
		String[] tokens = input.split("\\s+");
		// Push all tokens into the queue
		for (String token : tokens) {
			_tokenQueue.add(token);
		}
	}

	@Override
	public Token getNext() throws Exception {
		if (!hasNext())
			return null;
		String nextToken = _tokenQueue.poll();

		return getToken(nextToken);
	}

	/**
	 * This is the cake that does all the work (lots of conditional string
	 * checking with REGEX).
	 * 
	 * @param nextToken
	 * @return
	 */
	private Token getToken(String nextToken) throws Exception {
		// Braces
		Matcher matcher = BRACE_PATTERN.matcher(nextToken);
		if (matcher.matches()) {
			return new BraceToken(nextToken.equals(")"));
		}

		// Arithmetic operation
		matcher = ARITHMETIC_PATTERN.matcher(nextToken);
		if (matcher.matches()) {
			Operation op;
			if (nextToken.equals("+")) {
				op = Operation.ADD;
			} else if (nextToken.equals("-")) {
				op = Operation.SUBTRACT;
			} else if (nextToken.equals("*")) {
				op = Operation.MULTIPLY;
			} else if (nextToken.equals("/")) {
				op = Operation.DIVIDE;
			} else {
				throw new Exception(String.format(
						"Token %s is an unknown token.", nextToken));
			}
			return new OperationToken(op);
		}

		// Variable tokens (x, y, z, a, b, c, etc.)
		matcher = VARIABLE_PATTERN.matcher(nextToken);
		if (matcher.matches()) {
			return new VariableToken(nextToken.charAt(0));
		}

		// Integer values
		matcher = VALUE_PATTERN.matcher(nextToken);
		if (matcher.matches()) {
			return new ValueToken(Integer.parseInt(nextToken));
		}

		throw new Exception("Variable is of unknown type: " + nextToken);
	}

	@Override
	public boolean hasNext() {
		return !_tokenQueue.isEmpty();
	}

}
