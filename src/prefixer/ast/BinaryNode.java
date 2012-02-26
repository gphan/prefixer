package prefixer.ast;

/**
 * Binary nodes are nodes that contain a left and a right child.
 * 
 * @author John
 * 
 */
public abstract class BinaryNode extends Node {
	private Node _left;
	private Node _right;

	/**
	 * Gets the left node.
	 * @return
	 */
	public Node getLeft() {
		return _left;
	}

	/**
	 * Sets the left node.
	 * @param _left
	 */
	public void setLeft(Node _left) {
		this._left = _left;
	}

	/**
	 * Gets the right node.
	 * @return
	 */
	public Node getRight() {
		return _right;
	}

	/**
	 * Sets the right node.
	 * @param _right
	 */
	public void setRight(Node _right) {
		this._right = _right;
	}
}
