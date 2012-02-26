package prefixer.lex;

import prefixer.Operation;

/**
 * An operation token is one of the arithmetic operations defined in the
 * enumeration Operation.
 * 
 * @author John
 * 
 */
public class OperationToken implements Token {
	private final Operation _operation;

	/**
	 * Creates a new Arithmetic token.
	 * 
	 * @param op
	 */
	public OperationToken(Operation op) {
		_operation = op;
	}

	public final Operation getOperation() {
		return _operation;
	}

	@Override
	public String toString() {
		return "Operation: " + _operation.toString();
	}
}
