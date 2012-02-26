package prefixer;

import prefixer.ast.Node;

/**
 * Formats a syntax tree into a different format that it was read in (or the
 * same if necessary).
 * 
 * @author John
 * 
 */
public interface Formatter {
	/**
	 * Gets the flattened string representation of the format specified by this
	 * formatter given the root node of the syntax tree.
	 * 
	 * @param root
	 *            Root node of syntax tree.
	 * @return Flattened string representation of syntax tree.
	 */
	String getFlattened(Node root);
}
