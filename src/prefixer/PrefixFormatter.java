package prefixer;

import prefixer.ast.Node;
import prefixer.ast.OperationNode;
import prefixer.ast.ValueNode;
import prefixer.ast.VariableNode;

/**
 * Formats the syntax tree into the prefix format.
 * 
 * @author John
 * 
 */
public class PrefixFormatter implements Formatter {
	public static final char OPEN = '(';
	public static final char CLOSE = ')';
	
	@Override
	public String getFlattened(Node root) {
		StringBuilder sb = new StringBuilder();
		
		if (root instanceof OperationNode) {
			sb.append(OPEN);
			OperationNode opNode = (OperationNode) root;
			sb.append(opNode.getOperation().getOpChar());
			sb.append(' ');

			sb.append(getFlattened(opNode.getLeft()));
			sb.append(' ');
			sb.append(getFlattened(opNode.getRight()));
			sb.append(CLOSE);
		} else if (root instanceof ValueNode) {
			ValueNode node = (ValueNode) root;
			sb.append(node.getValue());
		} else if (root instanceof VariableNode) {
			VariableNode node = (VariableNode) root;
			sb.append(node.getName());
		} else {
			System.out.println("Unknown node type!");
		}
		
		return sb.toString();
	}

}
