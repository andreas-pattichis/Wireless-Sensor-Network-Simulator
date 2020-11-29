package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;
import java.util.Stack;

public class MST {

	private Node startingNode;

	private static int treeSize;
	
	private float maxTemp;

	public MST(Node n) {

		this.startingNode = n;
		treeSize = 1;
		maxTemp = n.getTemperature();

	}

//2nd function
	public void display() {

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> nextLevel = new LinkedList<Node>();

		currentLevel.add(startingNode);

		int level = 0;

		do {

			System.out.print("Level " + level + "\t");

			while (!currentLevel.isEmpty()) {

				Node temp = null;
				temp = currentLevel.remove();

				System.out.print(temp + "   ");

				LinkedList<Node> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {
					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Node> tempList = currentLevel;
			currentLevel = nextLevel;
			nextLevel = tempList;

			System.out.println();
			level++;

		} while (!currentLevel.isEmpty());

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

	public void insertNodeAsChild(Edge dest, Node newNode) {

		LinkedList<Node> temp = getListOfNodesInMST();

		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getID().compareTo(dest.getID()) == 0) {
				temp.get(i).addChild(newNode);
				treeSize++;
			}
		}

	}

	private LinkedList<Node> getListOfNodesInMST() {

		LinkedList<Node> listOfNodes = new LinkedList<Node>();

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> nextLevel = new LinkedList<Node>();

		currentLevel.add(startingNode);

		do {

			while (!currentLevel.isEmpty()) {

				Node temp = null;
				temp = currentLevel.remove();

				listOfNodes.add(temp);

				LinkedList<Node> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {

					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Node> tempList = currentLevel;
			currentLevel = nextLevel;
			nextLevel = tempList;

		} while (!currentLevel.isEmpty());

		return listOfNodes;

	}

	// 3rd function
	public void insertEdge(Node newNode) {

		LinkedList<Node> listOfNodes = this.getListOfNodesInMST();
		LinkedList<Float> distance = new LinkedList<Float>();

		for (int i = 0; i < listOfNodes.size(); i++) {
			distance.add(newNode.calculateDistance(listOfNodes.get(i)));
		}

		int idxMinDistance = 0;
		float minDistance = distance.get(idxMinDistance);

		for (int i = 1; i < listOfNodes.size(); i++) {
			if (distance.get(i) < minDistance) {
				idxMinDistance = i;
				minDistance = distance.get(i);
			}
		}

		this.insertNodeAsChild(listOfNodes.get(idxMinDistance), newNode);

	}

	// 4th function
	public void removeEdge(Node edgeToBeRemoved) {

		LinkedList<Node> listOfNodes = this.getListOfNodesInMST();

		for (int i = 0; i < listOfNodes.size(); i++) {

			LinkedList<Node> currentChildren = listOfNodes.get(i).getChildren();

			if (currentChildren.contains(edgeToBeRemoved)) {

				listOfNodes.get(i).deleteChild(edgeToBeRemoved);

				for (int j = 0; j < edgeToBeRemoved.getChildren().size(); j++) {
					insertEdge(edgeToBeRemoved.getChildren().get(j));
				}

			}
		}

	}

	// 5th Function
	public float informFireStation() {
		informNode(startingNode);
		
		return (maxTemp);
	}

	
	private void informNode(Node newNode) {
		if(newNode == null)
			return;
		
		for(int i = 0; i < newNode.getChildren().size(); i++) {
			informNode(newNode.getChildren().get(i));
			
			
			if(newNode.getChildren().get(i).getTemperature() > this.maxTemp)
				maxTemp = newNode.getChildren().get(i).getTemperature();
		}
		
		
		
		  
	}


}
