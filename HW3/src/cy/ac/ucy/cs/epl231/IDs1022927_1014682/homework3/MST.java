package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class MST {

	private Edge root;

	private static int treeSize;

	public MST(Edge root) {

		this.root = root;
		treeSize = 1;

	}
	
	
	
//2nd function
	public void display() {

		LinkedList<Edge> currentLevel = new LinkedList<Edge>();
		LinkedList<Edge> nextLevel = new LinkedList<Edge>();

		currentLevel.add(root);

		int level = 0;

		do {

			System.out.print("Level " + level + "\t");

			while (!currentLevel.isEmpty()) {

				Edge temp = null;
				temp = currentLevel.remove();
				
				

				System.out.print(temp + "   ");

				LinkedList<Edge> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {
					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Edge> tempList = currentLevel;
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

	public void insertNodeAsChild(Edge dest, Edge newNode) {

		dest.addChild(newNode);
		treeSize++;

	}

	private LinkedList<Edge> getListOfNodesInMST() {

		LinkedList<Edge> listOfNodes = new LinkedList<Edge>();
		

		LinkedList<Edge> currentLevel = new LinkedList<Edge>();
		LinkedList<Edge> nextLevel = new LinkedList<Edge>();

		currentLevel.add(root);

		do {

			while (!currentLevel.isEmpty()) {

				Edge temp = null;
				temp = currentLevel.remove();

				listOfNodes.add(temp);

				LinkedList<Edge> children = temp.getChildren();

				for (int i = 0; i < children.size(); i++) {

					nextLevel.add(children.get(i));
				}

			}

			LinkedList<Edge> tempList = currentLevel;
			currentLevel = nextLevel;
			nextLevel = tempList;

		} while (!currentLevel.isEmpty());

		return listOfNodes;

	}

	// 3rd function
	public void insertEdge(Edge newNode) {

		LinkedList<Edge> listOfNodes = this.getListOfNodesInMST();
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
	public void removeEdge(Edge edgeToBeRemoved) {

		LinkedList<Edge> listOfNodes = this.getListOfNodesInMST();

		for (int i = 0; i < listOfNodes.size(); i++) {
			
			LinkedList<Edge> currentChildren = listOfNodes.get(i).getChildren();

			if (currentChildren.contains(edgeToBeRemoved)) {

				listOfNodes.get(i).deleteChild(edgeToBeRemoved);
				
				

				for (int j = 0; j < edgeToBeRemoved.getChildren().size(); j++) {
					insertEdge(edgeToBeRemoved.getChildren().get(j));
				}

			}
		}

	}
	
	

	/**
	 * @return the root
	 */
	public Edge getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Edge root) {
		this.root = root;
	}

}
