/**
 * Class that represents a Graph
 * 
 * @author Christos Kasoulides, Andreas pattichis
 */

package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

	private LinkedList<Edge> hashTable[]; // Table of linkedlists that indicates a hashtable

	private static int hashTableSize; // Indicates the hashtable size

	private int noOfEdges; // Indicates the number of edges that are placed in the hashtable

	/**
	 * Constructor for the class Graph that sets the noOfEdges equal to 0, the
	 * hashTableSize equal to 5 and initializes all the linkedlists of the hashtable
	 */
	public Graph() {

		noOfEdges = 0;

		hashTableSize = 5;

		hashTable = new LinkedList[hashTableSize];

		for (int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}

	}

	/**
	 *  
	 * @param n
	 */
	public void findNeighbors(Edge n) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (n.isNeighbour(hashTable[i].get(j)) && !n.isAlreadyANeighbour(hashTable[i].get(j))) {
					n.addNeighbour(hashTable[i].get(j));
					hashTable[i].get(j).addNeighbour(n);
				}
		}
	}

	/**
	 * Method that will find and return the edge that contains the id of the parameter.
	 * 
	 * @param ID The id of the wanted edge
	 * 
	 * @return The edge that was found
	 */
	public Edge findEdge(String ID) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (hashTable[i].get(j).getID().compareTo(ID) == 0) {
					return hashTable[i].get(j);
				}
		}

		return null;
	}

	
	/**
	 * Method that prints the hashtable to the console
	 */
	public void printHashTable() {
		for (int i = 0; i < hashTableSize; i++) {
			System.out.println("hashTable[" + i + "]");
			for (int j = 0; j < hashTable[i].size(); j++)
				System.out.println(hashTable[i].get(j).toString());
			System.out.println();
		}
	}

	/**
	 * Method that prints the hashtable to a text file
	 * 
	 * @param fileName The name of the text file
	 * 
	 * @throws IOException
	 */
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

	/**
	 * Method that will calculate the hashkey and return the value of it.
	 * 
	 * @param newNode
	 * 
	 * @return
	 */
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

	/**
	 * Method that will find the edge that was given as a parameter and delete it
	 * from the graph. The method will return the deleted edge.
	 * 
	 * @param ID The id of the edge that will be removed.
	 * 
	 * @return The deleted edge
	 */
	public Edge deleteEdge(String ID) {

		Edge toBeRemoved = findEdge(ID);

		for (int i = 0; i < toBeRemoved.neighbours.size(); i++) {
			toBeRemoved.neighbours.get(i).deleteNeighbour(toBeRemoved);
		}

		hashTable[this.calculateHashkey(toBeRemoved)].remove(toBeRemoved);

		noOfEdges--;

		return toBeRemoved;

	}

	/**
	 * Method that will add the new edge that is given as a parameter to the graph
	 * 
	 * @param newNode
	 */
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

	/**
	 * Method that implicates the first operation that was asked. It gets the
	 * starting edge as a parameter and goes to calculate and store the mst. The
	 * method returns the mst.
	 * 
	 * @param e The starting edge
	 * 
	 * @return The minimum spanning tree that was created.
	 */
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

	/**
	 * Getter method that returns the number of edges
	 * 
	 * @return the number of edges
	 */
	public int getNoOfEdges() {

		return noOfEdges;

	}

}