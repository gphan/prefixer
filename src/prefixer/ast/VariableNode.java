package prefixer.ast;

/**
 * Variable nodes are nodes that do not have child nodes and are algebraic
 * variables like "x" or "y" and have no numerical value.
 * 
 * @author John
 * 
 */
public class VariableNode extends Node {
	private char _name;

	/**
	 * Creates a new variable node with a single character name.
	 * 
	 * @param name
	 */
	public VariableNode(char name) {
		_name = name;
	}

	/**
	 * Gets the name of the variable.
	 * 
	 * @return
	 */
	public char getName() {
		return _name;
	}
}
