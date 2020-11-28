package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class MST {

	private Node root;

	private static int treeSize;

	public MST(Node root) {

		this.root = root;
		treeSize = 1;

	}

//2nd funtion
	public void display() {

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> nextLevel = new LinkedList<Node>();

		currentLevel.add(root);

		int level = 0;

		do {

			System.out.print("Level " + level + "\t");

			while (!currentLevel.isEmpty()) {

				Node temp = null;
				temp = currentLevel.remove();

				System.out.print(temp + " ");

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

	public void insertNodeAsChild(Node dest, Node newNode) {

		dest.addChild(newNode);
		treeSize++;

	}

	private LinkedList<Node> getListOfNodesInMST() {

		LinkedList<Node> listOfNodes = new LinkedList<Node>();
		listOfNodes.add(root);

		LinkedList<Node> currentLevel = new LinkedList<Node>();
		LinkedList<Node> nextLevel = new LinkedList<Node>();

		currentLevel.add(root);

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
	public void insertNode(Node newNode) {

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
	public void removeEdge(Edge newEdge) {

		LinkedList<Node> listOfNodes = this.getListOfNodesInMST();

		for (int i = 0; i < listOfNodes.size(); i++) {

			if (newEdge.getID().compareTo(listOfNodes.get(i).getEdge().getID()) == 0) {

				Node removedNode = listOfNodes.remove(i);

				for (int j = 0; j < removedNode.getChildren().size(); j++) {
					insertNode(removedNode.getChildren().get(j));
				}

			}
		}

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
