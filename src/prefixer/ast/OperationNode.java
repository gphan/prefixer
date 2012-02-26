package prefixer.ast;

import prefixer.Operation;

/**
 * Operation node is a syntax tree node that defines a mathematical operation
 * between the left and the right node with one of the operations defined in the
 * enumeration Operation.
 * 
 * @author John
 * 
 */
public class OperationNode extends BinaryNode {
	private Operation _operation;

	/**
	 * Creates a new operation node.
	 * 
	 * @param operation
	 */
	public OperationNode(Operation operation) {
		_operation = operation;
	}

	/**
	 * Gets the operation for this node.
	 * 
	 * @return
	 */
	public Operation getOperation() {
		return _operation;
	}
}
