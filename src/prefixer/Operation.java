package prefixer;

/**
 * Static enumeration of different operations and various properties of theirs.
 * 
 * @author John
 * 
 */
public enum Operation {
	ADD(2, '+') {
		public int compute(int x, int y) {
			return x + y;
		}
	},
	SUBTRACT(1, '-') {
		public int compute(int x, int y) {
			return x - y;
		}
	},
	MULTIPLY(3, '*') {
		public int compute(int x, int y) {
			return x * y;
		}
	},
	DIVIDE(4, '/') {
		public int compute(int x, int y) {
			return x / y;
		}
	};

	private int _orderOfOperation;
	private char _opChar;

	/**
	 * Operation computes a value given the two operands.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract int compute(int x, int y);

	/**
	 * Constructor for operation enumerations and allows defining the order of
	 * operations. The larger the integer, the higher the priority of the
	 * operation.
	 * 
	 * Parenthesis Exponents Multiply Divide Add Subtract
	 * 
	 * @param orderOfOperation
	 * @param opChar
	 *            Operation's char representation.
	 */
	private Operation(int orderOfOperation, char opChar) {
		_orderOfOperation = orderOfOperation;
		_opChar = opChar;
	}

	/**
	 * Gets the operator's char representation.
	 * 
	 * @return
	 */
	public char getOpChar() {
		return _opChar;
	}

	/**
	 * Determines if the left operation is lesser or greater than the operation
	 * on the right.
	 * 
	 * @param left
	 * @param right
	 * @return True if the left operation is lesser or equal to that of the
	 *         right. False if otherwise.
	 */
	public static boolean isLesserOrEqual(Operation left, Operation right) {
		return (left._orderOfOperation <= right._orderOfOperation);
	}
}