/**
 * Class that represents a node
 * 
 * @author Christos Kasoulides, Andreas pattichis
 */

package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Node extends Edge {

	private LinkedList<Node> children; // List that stores the children of the node

	/**
	 * Constructor of the Node class that gets an edge as a parameter,calls the
	 * constructor of the edge to store the edge, and initializes the list of
	 * children.
	 * 
	 * @param e
	 */
	public Node(Edge e) {
		super(e);
		children = new LinkedList<Node>();
	}

	/**
	 * Getter method that will return the list of nodes that show the children of
	 * the current node.
	 * 
	 * @return the list of nodes that show the children of the current node.
	 */
	public LinkedList<Node> getChildren() {
		return children;

	}

	/**
	 * Method that will check and return a boolean value that indicates if the
	 * current node has any children.
	 * 
	 * @return a boolean value true or false
	 */
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	/**
	 * Method that will get a new node as a parameter and add it to the list of
	 * children of the current node.
	 * 
	 * @param newChild The new child that will be added the the list of children.
	 */
	public void addChild(Node newChild) {
		children.add(newChild);

	}

	/**
	 * Method that will get a node as a parameter and proceed to find the node in the
	 * list of children and delete it, showing that now the node of the parameter is
	 * not a child of the current node.
	 * 
	 * @param child The child that will be removed
	 */
	public void deleteChild(Node child) {
		this.children.remove(child);
	}

	@Override
	/**
	 * Returns the hash code value of the Node
	 * 
	 * @return the hash code value of the Node
	 */
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		return result;
	}

	@Override
	/**
	 * Equals method that overrides the method equals of the Class object and
	 * returns a boolean value that indicates if the Node in the parameter is
	 * equal to the current Node.
	 * 
	 * @return boolean value true or false
	 */
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
