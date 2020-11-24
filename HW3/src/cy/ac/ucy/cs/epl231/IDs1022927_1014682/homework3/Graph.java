package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;



public class Graph {

	LinkedList<Node> hashTable[];
	
	static int hashTableSize;
	
	
	public Graph() {
		hashTable  = new LinkedList[hashTableSize];
		
		for(int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}
		
		hashTableSize = 5;
		
	
	}
	
	public int calculateHashkey(Node newNode) {
		int temp = Integer.parseInt(newNode.getID());
		
		return temp%hashTableSize; 

	}
	private void rehashTable() {
		
	}
	public void insertNode(Node newNode){
		
		int key = calculateHashkey(newNode);
		
		hashTable[key].add(newNode);
		
		if(hashTable[key].size() > 20) {
			rehashTable();
		}
		
		
	}

}
