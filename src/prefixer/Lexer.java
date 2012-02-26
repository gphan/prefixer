package prefixer;

import prefixer.lex.Token;

/**
 * Parses input into tokens.
 * 
 * @author gphan
 * 
 */
public interface Lexer {

	/**
	 * Gets the next token from the Lexer.
	 * 
	 * @return A token instance. Null if lexer has reached end-of-file.
	 * @throws Exception
	 *             If the lexer cannot match a token with a known token type, it
	 *             will throw an exception.
	 */
	public Token getNext() throws Exception;

	/**
	 * Returns true if lexer has another token.
	 * 
	 * @return
	 */
	public boolean hasNext();
}
