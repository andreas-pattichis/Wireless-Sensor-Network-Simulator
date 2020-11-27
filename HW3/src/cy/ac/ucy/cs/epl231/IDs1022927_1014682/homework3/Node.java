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
		
		return(e.calculateDistance(other.e));
		
	}
	
	public boolean isNeighbour(Node other) {

		return (e.isNeighbour(other.e));

	}
	
	public void addChild(Node newChild) {
		children.add(newChild);
		
	}
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	
	public Edge getEdge() {
		return e;
	}
	
	
}
