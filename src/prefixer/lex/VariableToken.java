package prefixer.lex;

/**
 * Variable tokens are non-valued algebraic tokens.
 * 
 * @author John
 * 
 */
public class VariableToken implements Token {
	private final char _name;

	/**
	 * Creates a new variable token with a name.
	 * 
	 * @param name
	 */
	public VariableToken(char name) {
		_name = name;
	}

	/**
	 * Gets the variable character name.
	 * 
	 * @return
	 */
	public char getName() {
		return _name;
	}
	
	@Override
	public String toString() {
		return "Variable: " + _name;
	}
}
