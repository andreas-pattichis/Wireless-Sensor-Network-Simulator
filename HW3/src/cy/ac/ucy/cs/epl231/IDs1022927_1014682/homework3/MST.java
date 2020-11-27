package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class MST {
	
	private Node root;
	
	private int treeSize;
	
	
	
	
	public MST (Node root) {
		
		this.root = root;
		treeSize = 1;
		
	}
	
	
	/**
	 * @return the treeSize
	 */
	public int getTreeSize() {
		return treeSize;
	}


	/**
	 * @param treeSize the treeSize to set
	 */
	public void setTreeSize(int treeSize) {
		this.treeSize = treeSize;
	}


	public void insertNode(Node dest, Node newNode) {
		
		dest.addChild(newNode);
		treeSize++;
		
	}


	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}


	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}
	
	
	
	

}
