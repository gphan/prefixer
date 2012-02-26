package prefixer;

import prefixer.ast.Node;

/**
 * Develops a syntax tree using a Lexer.
 * 
 * @author John
 * 
 */
public interface Parser {
	/**
	 * Gets the syntax tree using the tokens from the lexer.
	 * 
	 * @param lexer
	 * @return A syntax tree's root node.
	 * @throws Exception
	 *             If there was a problem with the syntax tree building process,
	 *             an exception will be thrown.
	 */
	Node getSyntaxTree(Lexer lexer) throws Exception;
}
