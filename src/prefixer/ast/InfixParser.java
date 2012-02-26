package prefixer.ast;

import java.util.Stack;

import prefixer.Lexer;
import prefixer.Operation;
import prefixer.Parser;
import prefixer.lex.*;

/**
 * Parses the infix equation from a lexer and creates a syntax tree.
 * 
 * @author John
 * 
 */
public class InfixParser implements Parser {
	private final Stack<Node> _opStack = new Stack<Node>();
	private final Stack<Node> _nodeStack = new Stack<Node>();
	private final boolean _reduce;

	/**
	 * Creates a new infix parser with equation reduction capabilities.
	 * 
	 * @param reduce
	 *            True if the parser should go through the final syntax tree and
	 *            reduce operations.
	 */
	public InfixParser(boolean reduce) {
		_reduce = reduce;
	}

	@Override
	public Node getSyntaxTree(Lexer lexer) throws Exception {
		while (lexer.hasNext()) {
			Token token = lexer.getNext();

			if (token instanceof ValueToken) {
				ValueToken valToken = (ValueToken) token;
				ValueNode node = new ValueNode(valToken.getValue());
				_nodeStack.push(node);
			} else if (token instanceof VariableToken) {
				VariableToken varToken = (VariableToken) token;
				VariableNode node = new VariableNode(varToken.getName());
				_nodeStack.push(node);
			} else if (token instanceof OperationToken) {
				OperationToken opToken = (OperationToken) token;
				OperationNode node = new OperationNode(opToken.getOperation());

				// Peek the top of the operation stack if it's not empty and the
				// top
				// node is an operation node.
				if (!_opStack.isEmpty()
						&& (_opStack.peek() instanceof OperationNode)) {
					OperationNode topNode = (OperationNode) _opStack.peek();

					// Check if the new node is less than or equal to the
					// top of the operation stack.
					if (Operation.isLesserOrEqual(node.getOperation(),
							topNode.getOperation())) {
						// Pop the top node
						_opStack.pop();

						// Pop two values off the node stack
						topNode.setRight(_nodeStack.pop());
						topNode.setLeft(_nodeStack.pop());

						// Push onto node stack
						_nodeStack.push(topNode);
					}

				}

				// Push new node onto stack
				_opStack.push(node);
			} else if (token instanceof BraceToken) {
				BraceToken bToken = (BraceToken) token;
				if (bToken.isClosingBrace()) {
					Node topOpNode = _opStack.pop();
					while (!(topOpNode instanceof BraceNode)) {
						if (topOpNode instanceof OperationNode) {
							OperationNode node = (OperationNode) topOpNode;
							node.setRight(_nodeStack.pop());
							node.setLeft(_nodeStack.pop());
							_nodeStack.push(topOpNode);
						}

						topOpNode = _opStack.pop();
					}
				} else {
					BraceNode node = new BraceNode();
					_opStack.push(node);
				}
			} else {
				throw new Exception("Token is not parseable: "
						+ token.toString());
			}
		}

		while (_nodeStack.size() > 1) {
			OperationNode top = (OperationNode) _opStack.pop();
			top.setRight(_nodeStack.pop());
			top.setLeft(_nodeStack.pop());
			_nodeStack.push(top);
		}

		Node finalNode = _nodeStack.pop();
		if (_reduce) {
			return reduceTree(finalNode);
		}

		return finalNode;
	}

	/**
	 * Reduces a tree by simplifying operation nodes that have two value nodes.
	 * 
	 * @param root
	 *            Root node
	 * @return A simplified tree.
	 */
	private Node reduceTree(Node root) {
		if (root instanceof OperationNode) {
			OperationNode opNode = (OperationNode) root;

			// Call reduceTree recursively. If they are not value nodes on
			// return, then will not reduce.
			Node left = reduceTree(opNode.getLeft());
			Node right = reduceTree(opNode.getRight());

			if (left instanceof ValueNode && right instanceof ValueNode) {
				ValueNode leftVal = (ValueNode) left;
				ValueNode rightVal = (ValueNode) right;

				// Don't simplify division if value becomes < 0
				int valLeft = leftVal.getValue();
				int valRight = rightVal.getValue();

				if (opNode.getOperation() == Operation.DIVIDE
						&& valLeft % valRight != 0) {
					return root;
				}

				return new ValueNode(opNode.getOperation().compute(
						leftVal.getValue(), rightVal.getValue()));
			}

			// They might be the same, but set them anyway.
			opNode.setLeft(left);
			opNode.setRight(right);
		}

		return root;
	}
}
