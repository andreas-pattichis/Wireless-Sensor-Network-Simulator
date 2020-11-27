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
				if (n.isNeighbour(hashTable[i].get(j))) {
					n.addNeighbour(hashTable[i].get(j));
					hashTable[i].get(j).addNeighbour(n);
				}
		}
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

		while (mst.getTreeSize() != noOfEdges) {

			LinkedList<Edge> totalNeighbours = new LinkedList<Edge>();

			int cnt = 0;
			
			Node temp = mst.getRoot();

			while (cnt != mst.getTreeSize()) {
				
				
				
				totalNeighbours.add

			}

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
