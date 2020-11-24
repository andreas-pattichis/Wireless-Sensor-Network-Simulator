package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;



public class Graph {
	
	LinkedList<Node> hashTable;
	
	static int hashTableSize;
	
	
	public Graph() {
		hashTable  = new LinkedList<Node>();
		hashTableSize = 5;
		
	
	}
	
	public int calculateHashkey(String id) {
		int temp = Integer.parseInt(id);
		
		return temp%hashTableSize; 
	}

}
