package prefixer.lex;

/**
 * Token for braces, left and right parenthesis.
 * 
 * @author John
 * 
 */
public class BraceToken implements Token {
	private final boolean _closingBrace;

	/**
	 * Creates a new braces token "(" or ")".
	 * 
	 * @param closingBrace
	 *            True if the brace token is the closing variety ")".
	 */
	public BraceToken(boolean closingBrace) {
		_closingBrace = closingBrace;
	}

	/**
	 * If this brace token is the closing brace, this shall return true.
	 * 
	 * @return
	 */
	public boolean isClosingBrace() {
		return _closingBrace;
	}
	
	@Override
	public String toString() {
		return "Brace: " + (_closingBrace ? ")" : "("); 
	}
}
