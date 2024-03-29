/**
 * Class that represents a Minimum Spanning Tree
 * 
 * @author Christos Kasoulides, Andreas pattichis
 */

package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;
import java.util.Stack;

public class MST1014682_1022927 {

	private static int treeSize; // Indicates the size of the minimum spanning tree

	private Node1014682_1022927 startingNode; // The first node of the minimum spanning tree.

	private float maxTemp;

	/**
	 * Constructor that gets a node as the parameter and stores it as a starting
	 * node of the minimum spanning tree. It also sets the treeSize equal to 1 and
	 * sets the maxTemp.
	 * 
	 * @param n the Node that will be set as the first node of the MST
	 */
	public MST1014682_1022927(Node1014682_1022927 n) {

		this.startingNode = n;
		treeSize = 1;
		maxTemp = n.getTemperature();

	}

	/**
	 * Method that implicates the second operation, and prints the minimum spanning
	 * tree.
	 */
	public void display() {

		LinkedList<Node1014682_1022927> currentLevel = new LinkedList<Node1014682_1022927>();
		LinkedList<Node1014682_1022927> nextLevel = new LinkedList<Node1014682_1022927>();

		currentLevel.add(startingNode);

		int level = 0;

		do {

			System.out.print("Level " + level + "\t");

			while (!currentLevel.isEmpty()) {

				Node1014682_1022927 temp = null;
				temp = currentLevel.remove();

				System.out.print(temp + "   ");

				LinkedList<Node1014682_1022927> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {
					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Node1014682_1022927> tempList = currentLevel;
			currentLevel = nextLevel;
			nextLevel = tempList;

			System.out.println();
			level++;

		} while (!currentLevel.isEmpty());

	}

	/**
	 * Getter method that returns the tree size of the Minimum Spanning Tree
	 * 
	 * @return the treeSize
	 */
	public int getTreeSize() {
		return treeSize;
	}

	/**
	 * Setter method that sets the tree size of the Minimum Spanning Tree
	 * 
	 * @param treeSize the treeSize to set
	 */
	public void setTreeSize(int treeSize) {
		this.treeSize = treeSize;
	}

	/**
	 * Method that will insert the parameter newNode as a a child of the parameter
	 * dest.
	 * 
	 * @param dest    The edge that will be the parent
	 * @param newNode The new node that will be the child of the dest
	 */
	public void insertNodeAsChild(Vertex1014682_1022927 dest, Node1014682_1022927 newNode) {

		LinkedList<Node1014682_1022927> temp = getListOfNodesInMST();

		for (int i = 0; i < temp.size(); i++)
			if (temp.get(i).getID().compareTo(dest.getID()) == 0) {
				temp.get(i).addChild(newNode);
				treeSize++;
			}
	}

	private LinkedList<Node1014682_1022927> getListOfNodesInMST() {

		LinkedList<Node1014682_1022927> listOfNodes = new LinkedList<Node1014682_1022927>();

		LinkedList<Node1014682_1022927> currentLevel = new LinkedList<Node1014682_1022927>();
		LinkedList<Node1014682_1022927> nextLevel = new LinkedList<Node1014682_1022927>();

		currentLevel.add(startingNode);

		do {

			while (!currentLevel.isEmpty()) {

				Node1014682_1022927 temp = null;
				temp = currentLevel.remove();

				listOfNodes.add(temp);

				LinkedList<Node1014682_1022927> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {

					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Node1014682_1022927> tempList = currentLevel;
			currentLevel = nextLevel;
			nextLevel = tempList;

		} while (!currentLevel.isEmpty());

		return listOfNodes;

	}

	/**
	 * Method that implicates the third operation that was asked. It gets the new
	 * node that will be put in the MST as a parameter and adds it.
	 * 
	 * @param newNode The node that will be put to the MST
	 */

	  public void insertNode(Node1014682_1022927 newNode) {
	  
	  LinkedList<Node1014682_1022927> listOfNodes = this.getListOfNodesInMST(); LinkedList<Float>
	  distance = new LinkedList<Float>();
	  
	  for (int i = 0; i < listOfNodes.size(); i++) {
	  distance.add(newNode.calculateDistance(listOfNodes.get(i))); }
	  
	  int idxMinDistance = 0; float minDistance = distance.get(idxMinDistance);
	  
	  for (int i = 1; i < listOfNodes.size(); i++) { if (distance.get(i) <
	  minDistance) { idxMinDistance = i; minDistance = distance.get(i); } }
	  
	  this.insertNodeAsChild(listOfNodes.get(idxMinDistance), newNode);
	  
	  }
	 

	

	/**
	 * Method that implicates the fourth operation that was asked. It gets the new
	 * node that will be removed from the MST as a parameter and removes it.
	 * 
	 * @param edgeToBeRemoved The node that will be removed from the MST
	 */
	public void removeNode(Node1014682_1022927 edgeToBeRemoved) {

		LinkedList<Node1014682_1022927> listOfNodes = this.getListOfNodesInMST();

		for (int i = 0; i < listOfNodes.size(); i++) {

			LinkedList<Node1014682_1022927> currentChildren = listOfNodes.get(i).getChildren();

			if (currentChildren.contains(edgeToBeRemoved)) {

				listOfNodes.get(i).deleteChild(edgeToBeRemoved);

				for (int j = 0; j < edgeToBeRemoved.getChildren().size(); j++) {
					insertNode(edgeToBeRemoved.getChildren().get(j));
				}

			}
		}

	}

	/**
	 * Method that implicates the fifth operation that was asked. It informs the
	 * fire station and returns the highest temperature.
	 */
	public float informFireStation() {
		informNode(startingNode);

		return (maxTemp);
	}

	private void informNode(Node1014682_1022927 newNode) {
		if (newNode == null)
			return;

		for (int i = 0; i < newNode.getChildren().size(); i++) {
			informNode(newNode.getChildren().get(i));

			if (newNode.getChildren().get(i).getTemperature() > this.maxTemp)
				maxTemp = newNode.getChildren().get(i).getTemperature();
		}

	}

}
