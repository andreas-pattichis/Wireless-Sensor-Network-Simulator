package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Node {
	
	Edge e;
	LinkedList<Edge> children;
	
	public Node(Edge nE) {
		this.e = nE;
		children = new LinkedList<Edge>();
	}
	
	public float calculateDistance(Node other) {
		
	}
}
