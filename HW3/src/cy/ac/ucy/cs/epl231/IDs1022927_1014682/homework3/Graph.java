package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;



public class Graph {

	LinkedList<Node> hashTable[];
	
	static int hashTableSize;
	
	
	public Graph() {
		
		hashTableSize = 5;
		
		hashTable  = new LinkedList[hashTableSize];
		
		for(int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}
		
		
		
	
	}
	
	public int calculateHashkey(Node newNode) {
		int temp = Integer.parseInt(newNode.getID());
		
		return temp%hashTableSize; 

	}
	private void rehashTable() {
		
		LinkedList<Node> temp[] = hashTable;
		int previousSize = hashTableSize;
		
		
		hashTableSize *= 10;
		hashTable = new LinkedList[hashTableSize];
		
		for(int i = 0; i < hashTableSize; i++) {
			hashTable[i] = new LinkedList();
		}
		
		for(int i = 0; i < previousSize; i++) {
			while(!temp[i].isEmpty()) {
				insertNode(temp[i].remove());
			}
		}
		
		
	}
	public void insertNode(Node newNode){
		
		int key = calculateHashkey(newNode);
		
		hashTable[key].add(newNode);
		
		if(hashTable[key].size() > 20) {
			rehashTable();
		}
		
		
	}

}
