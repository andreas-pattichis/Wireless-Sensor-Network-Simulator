package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

	private LinkedList<Edge> hashTable[];

	private static int hashTableSize;

	private int noOfEdges;

	public Graph() {

		noOfEdges = 0;

		hashTableSize = 5;

		hashTable = new LinkedList[hashTableSize];

		for (int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}

	}

	public void findNeighbors(Edge n) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (n.isNeighbour(hashTable[i].get(j)) && !n.isAlreadyANeighbour(hashTable[i].get(j))) {
					n.addNeighbour(hashTable[i].get(j));
					hashTable[i].get(j).addNeighbour(n);
				}
		}
	}

	public Edge findEdge(String ID) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (hashTable[i].get(j).getID().compareTo(ID) == 0) {
					return hashTable[i].get(j);
				}
		}

		return null;
	}

	public void printHashTable() {
		for (int i = 0; i < hashTableSize; i++) {
			System.out.println("hashTable[" + i + "]");
			for (int j = 0; j < hashTable[i].size(); j++)
				System.out.println(hashTable[i].get(j).toString());
			System.out.println();
		}
	}

	public int calculateHashkey(Edge newNode) {
		int temp = Integer.parseInt(newNode.getID());

		return temp % hashTableSize;

	}

	private void rehashTable() {

		LinkedList<Edge> temp[] = hashTable;
		int previousSize = hashTableSize;

		hashTableSize *= 10;
		hashTable = new LinkedList[hashTableSize];

		for (int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}

		for (int i = 0; i < previousSize; i++) {
			while (!temp[i].isEmpty()) {
				insertEdge(temp[i].remove());
			}
		}

	}

	public void insertEdge(Edge newNode) {

		noOfEdges++;

		int key = calculateHashkey(newNode);

		findNeighbors(newNode);
		hashTable[key].add(newNode);

		if (hashTable[key].size() > 20) {
			System.out.println("Table has been rehashed.");
			rehashTable();
		}

	}

	public MST calculateMST(Edge e) {

		Node root = new Node(e);

		MST mst = new MST(root);

		LinkedList<Node> visited = new LinkedList<Node>();
		visited.add(root);
		
		

		while (mst.getTreeSize() <= noOfEdges) {
			int testing = mst.getTreeSize();
			LinkedList<Edge> closest = new LinkedList<Edge>();
			LinkedList<Float> distance = new LinkedList<Float>();

			for (int i = 0; i < visited.size(); i++)
				if (!visited.contains(new Node(visited.get(i).getEdge().getClosestNeighbour(visited)))) {
					closest.add(visited.get(i).getEdge().getClosestNeighbour(visited));
					distance.add(visited.get(i).getEdge().getClosestDistance());
				}

			int idxMinDistance = 0;
			float minDistance = distance.get(idxMinDistance);

			for (int i = 1; i < distance.size(); i++)
				if (distance.get(i) < minDistance) {
					idxMinDistance = i;
					minDistance = distance.get(i);
				}

			mst.insertNodeAsChild(mst.getNodeFromMST(visited.get(idxMinDistance)), new Node(closest.get(idxMinDistance)));
			visited.add(new Node(closest.get(idxMinDistance)));
			
			/*
			 * for (int i = 0; i < visited.size(); i++) {
			 * visited.get(i).getEdge().deleteNeighbour(closest.get(idxMinDistance)); }
			 */
			
		
		}

		return mst;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (!Arrays.equals(hashTable, other.hashTable))
			return false;
		if (noOfEdges != other.noOfEdges)
			return false;
		return true;
	}

	public int getNoOfEdges() {

		return noOfEdges;

	}

}
