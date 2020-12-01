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

public class Graph1014682_1022927 {

	private LinkedList<Vertex1014682_1022927> hashTable[]; // Table of linkedlists that indicates a hashtable

	private static int hashTableSize; // Indicates the hashtable size

	private int noOfVertices; // Indicates the number of edges that are placed in the hashtable

	/**
	 * Constructor for the class Graph that sets the noOfVertices equal to 0, the
	 * hashTableSize equal to 5 and initializes all the linkedlists of the hashtable
	 */
	public Graph1014682_1022927() {

		noOfVertices = 0;

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
	public void findNeighbors(Vertex1014682_1022927 n) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (n.isNeighbour(hashTable[i].get(j)) && !n.isAlreadyANeighbour(hashTable[i].get(j))) {
					n.addNeighbour(hashTable[i].get(j));
					hashTable[i].get(j).addNeighbour(n);
				}
		}
	}

	/**
	 * Method that will find and return the vertex that contains the id of the parameter.
	 * 
	 * @param ID The id of the wanted vertex
	 * 
	 * @return The vertex that was found
	 */
	public Vertex1014682_1022927 findVertex(String ID) {
		for (int i = 0; i < hashTableSize; i++) {
			for (int j = 0; j < hashTable[i].size(); j++)
				if (hashTable[i].get(j).getID().compareTo(ID) == 0) {
					return hashTable[i].get(j);
				}
		}

		return null;
	}
	
	
	public void displayNodes(int cellNumber) {
		System.out.println("\nCell " + cellNumber + ":  " );
		for(int i = 0; i < hashTable[cellNumber].size(); i++) {
			System.out.print(hashTable[cellNumber].get(i).getID() + " ");
		}
		
		System.out.println();
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
	public int calculateHashkey(Vertex1014682_1022927 newNode) {
		int temp = Integer.parseInt(newNode.getID());

		return temp % hashTableSize;

	}

	private void rehashTable() {

		LinkedList<Vertex1014682_1022927> temp[] = hashTable;
		int previousSize = hashTableSize;

		hashTableSize *= 10;
		hashTable = new LinkedList[hashTableSize];

		for (int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}

		for (int i = 0; i < previousSize; i++) {
			while (!temp[i].isEmpty()) {
				insertVertex(temp[i].remove());
			}
		}

	}

	/**
	 * Method that will find the vertex that was given as a parameter and delete it
	 * from the graph. The method will return the deleted vertex.
	 * 
	 * @param ID The id of the vertex that will be removed.
	 * 
	 * @return The deleted vertex
	 */
	public Vertex1014682_1022927 deleteVertex(String ID) {

		Vertex1014682_1022927 toBeRemoved = findVertex(ID);

		for (int i = 0; i < toBeRemoved.neighbours.size(); i++) {
			toBeRemoved.neighbours.get(i).deleteNeighbour(toBeRemoved);
		}

		hashTable[this.calculateHashkey(toBeRemoved)].remove(toBeRemoved);

		noOfVertices--;

		return toBeRemoved;

	}

	/**
	 * Method that will add the new vertex that is given as a parameter to the graph
	 * 
	 * @param newNode
	 */
	public void insertVertex(Vertex1014682_1022927 newNode) {

		noOfVertices++;

		int key = calculateHashkey(newNode);

		findNeighbors(newNode);
		hashTable[key].add(newNode);

		if (hashTable[key].size() > 20) {
			//System.out.println("Table has been rehashed.");
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
	public MST1014682_1022927 calculateMST(Vertex1014682_1022927 e) {

		Node1014682_1022927 root = new Node1014682_1022927(e);

		MST1014682_1022927 mst = new MST1014682_1022927(root);

		LinkedList<Vertex1014682_1022927> visited = new LinkedList<Vertex1014682_1022927>();
		visited.add(e);

		while (mst.getTreeSize() < noOfVertices) {

			LinkedList<Vertex1014682_1022927> closest = new LinkedList<Vertex1014682_1022927>();
			LinkedList<Float> distance = new LinkedList<Float>();

			for (int i = 0; i < visited.size(); i++)
				if (visited.get(i).getClosestNeighbour(visited) != null) {
					closest.add(visited.get(i).getClosestNeighbour(visited));
					distance.add(visited.get(i).getClosestDistance(visited));
				}else {
					closest.add(null);
					distance.add(Float.POSITIVE_INFINITY);
				}

			int idxMinDistance = 0;
			float minDistance = distance.get(idxMinDistance);

			for (int i = 1; i < distance.size(); i++)
				if (distance.get(i) < minDistance) {
					idxMinDistance = i;
					minDistance = distance.get(i);
				}

			Node1014682_1022927 newNode = new Node1014682_1022927(closest.get(idxMinDistance));

			mst.insertNodeAsChild(visited.get(idxMinDistance), newNode);
			visited.add(closest.get(idxMinDistance));

		}

		return mst;
	}

	/**
	 * Getter method that returns the number of edges
	 * 
	 * @return the number of vertex
	 */
	public int getNoOfVertices() {

		return noOfVertices;

	}

}