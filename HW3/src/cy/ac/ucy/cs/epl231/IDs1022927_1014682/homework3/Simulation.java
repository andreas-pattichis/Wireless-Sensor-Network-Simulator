package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Wrong input.\nYou have to enter the value of d and the name of the text file.");
			return;
		}

		float d = Integer.parseInt(args[0]);
		File file = new File(args[1]);

		Graph g = new Graph();

		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				Edge test = new Edge(sc.nextLine(), d);
				g.insertEdge(test);
				g.findNeighbors(test);
			}
			
			//System.out.println("HASHTABLE:\n----------");
			//g.printHashTable();
			//System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner in = new Scanner(System.in);

		int choice = 0;
		
		MST mst = null;

		while (choice != 6) {
			System.out.print("-------------------------------------");
			System.out.print(
					"\nMENU:\n1. Calculate Minimum Spanning Tree.\n2. Print Minimum Spanning Tree.\n3. Insert new node.\n4. Delete node.\n5. Inform fire station A for the highest network temperature.\n6. Exit the simulation.\n\nInsert your choice: ");
			choice = in.nextInt();
			System.out.println();

			

			switch (choice) {
			case 1:
				mst = g.calculateMST(g.findEdge("02"));
				// 2nd function
				System.out.println("\nThe MST has been calculated.");
				break;
			case 2:
				// 3rd function
				mst.display();
				System.out.println("\n\n\n");
				break;
			case 3:
				System.out.print("Give the new node that you want to insert: \n\t>");
				String newNode = in.next();
				mst.insertEdge(new Node(new Edge(newNode, d)));
				System.out.println();
				break;
			case 4:
				System.out.print("Give the node that you want to remove: \n\t>");
				String toBeRemoved = in.next();
				mst.removeEdge(new Node(new Edge(toBeRemoved, d)));
				System.out.println();
			case 5:
				System.out.print("Not done yet.");
			case 6:
				System.out.print("You have selected to end the simulation.");
			default:
				System.out.println("Wrong input. Please try again!\n");
			}
		}

	}

}