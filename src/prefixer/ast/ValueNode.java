package prefixer.ast;

/**
 * Value nodes are integer values that have no leaf nodes.
 * 
 * @author John
 * 
 */
public class ValueNode extends Node {
	private int _value;

	/**
	 * Creates a new value node.
	 * 
	 * @param value
	 */
	public ValueNode(int value) {
		_value = value;
	}

	/**
	 * Get the integer value associated with this node.
	 * 
	 * @return
	 */
	public int getValue() {
		return _value;
	}
}
