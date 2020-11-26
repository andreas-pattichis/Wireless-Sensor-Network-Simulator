package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Wrong input.\nYou have to enter the value of d and the name of the text file.");
			return;
		}  
		
		int d = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		 
		Graph g = new Graph();
		
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()){
                Node test = new Node(sc.nextLine(),d);
                g.insertNode(test);
                //System.out.println(test.toString());
            }
            
            System.out.println(g.hashTableSize);
            
            for(int i=0;i<g.hashTableSize;i++) {
            	System.out.println("hashTable[" + i +"]");
            	for(int j=0;j<g.hashTable[i].size();j++)
            		System.out.println(g.hashTable[i].get(j).toString());
            	System.out.println();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
		//System.out.println("01\t[42, 85]\t30");

		//Coordinates test1 = new Coordinates("[42, 85]");

		//Coordinates test2 = new Coordinates("[30, 60]");

		//System.out.println(test1.calculateDistance(test2));

		//Node sensor = new Node("01\t[42, 85]\t30", 4);

		//System.out.println(sensor);
	}
}
