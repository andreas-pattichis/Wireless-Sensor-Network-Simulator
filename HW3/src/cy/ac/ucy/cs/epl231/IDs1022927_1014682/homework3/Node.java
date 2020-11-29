package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Node extends Edge {

	private LinkedList<Node> children;

	public Node(Edge e) {
		super(e);
		children = new LinkedList<Node>();
	}

	public LinkedList<Node> getChildren() {
		return children;

	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public void addChild(Node newChild) {
		children.add(newChild);

	}

	public void deleteChild(Node child) {
		this.children.remove(child);
	}

	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		return true;
	}

	
	
	
	
	

}
