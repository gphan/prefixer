package prefixer.lex;

/**
 * Value tokens contain an integer value.
 * 
 * @author John
 * 
 */
public class ValueToken implements Token {
	private final int _value;

	/**
	 * Creates a new value token with the passed in value.
	 * 
	 * @param value
	 */
	public ValueToken(int value) {
		_value = value;
	}

	public final int getValue() {
		return _value;
	}
	
	@Override
	public String toString() {
		return "Value: " + _value;
	}
}
