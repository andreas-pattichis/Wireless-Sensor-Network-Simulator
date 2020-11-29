package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

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

	public void printHashTableInFile(String fileName) throws IOException {

		File file = new File(fileName);

		FileWriter eraser;
		eraser = new FileWriter(fileName);

		FileWriter writer = null;
		writer = new FileWriter(fileName, true);

		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++) {
				writer.write(hashTable[i].get(j).toString());
				writer.write("\r\n");

			}
		}
		writer.close();

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

	public Edge deleteEdge(String ID) {

		Edge toBeRemoved = findEdge(ID);

		for (int i = 0; i < toBeRemoved.neighbours.size(); i++) {
			toBeRemoved.neighbours.get(i).deleteNeighbour(toBeRemoved);
		}

		hashTable[this.calculateHashkey(toBeRemoved)].remove(toBeRemoved);

		noOfEdges--;

		return toBeRemoved;

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

		LinkedList<Edge> visited = new LinkedList<Edge>();
		visited.add(e);

		while (mst.getTreeSize() < noOfEdges) {

			LinkedList<Edge> closest = new LinkedList<Edge>();
			LinkedList<Float> distance = new LinkedList<Float>();

			for (int i = 0; i < visited.size(); i++)
				if (visited.get(i).getClosestNeighbour(visited) != null) {
					closest.add(visited.get(i).getClosestNeighbour(visited));
					distance.add(visited.get(i).getClosestDistance(visited));
				}

			int idxMinDistance = 0;
			float minDistance = distance.get(idxMinDistance);

			for (int i = 1; i < distance.size(); i++)
				if (distance.get(i) < minDistance) {
					idxMinDistance = i;
					minDistance = distance.get(i);
				}

			Node newNode = new Node(closest.get(idxMinDistance));

			mst.insertNodeAsChild(visited.get(idxMinDistance), newNode);
			visited.add(closest.get(idxMinDistance));

		}

		return mst;

	}

	public int getNoOfEdges() {

		return noOfEdges;

	}

}