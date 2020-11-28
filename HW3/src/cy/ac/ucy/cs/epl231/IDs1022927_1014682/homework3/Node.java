package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Node {

	private Edge e;
	private LinkedList<Node> children;

	public Node(Edge nE) {
		this.e = nE;
		children = new LinkedList<Node>();
	}

	public float calculateDistance(Node other) {

		return (e.calculateDistance(other.e));

	}

	public boolean isNeighbour(Node other) {

		return (e.isNeighbour(other.e));

	}

	public void addChild(Node newChild) {
		children.add(newChild);

	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		return true;
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public Edge getEdge() {
		return e;
	}

	public LinkedList<Node> getChildren() {
		return children;

	}

	public String toString() {
		return e.toString();
	}

}
