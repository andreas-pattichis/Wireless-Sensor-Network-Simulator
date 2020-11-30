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

		String filename = args[1];

		float d = Integer.parseInt(args[0]);
		File file = new File(filename);

		Graph g = new Graph();

		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				Vertex test = new Vertex(sc.nextLine(), d);
				g.insertVertex(test);
				g.findNeighbors(test);
			}

			// System.out.println("HASHTABLE:\n----------");
			// g.printHashTable();
			// System.out.println();

		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner in = new Scanner(System.in);

		int choice = 0;

		MST mst = null;

		while (choice != 6) {
			System.out.print("-------------------------------------------------------------+");
			System.out.print(
					"\nMENU:\n1. Calculate Minimum Spanning Tree.\n2. Print Minimum Spanning Tree.\n3. Insert new node.\n4. Delete node.\n5. Inform fire station A for the highest network temperature.\n6. Exit the simulation.\n\nInsert your choice: ");
			choice = in.nextInt();
			System.out.println();

			switch (choice) {
			case 1:

				mst = g.calculateMST(g.findVertex("02"));
				System.out.print("\nThe MST has been calculated.\n\n");
				break;

			case 2:
				if (mst == null) {
					System.out.print("\nMST has to be calculated first!\n\n");
					break;
				}
				mst.display();
				System.out.println("\n");
				break;

			case 3:

				if (mst == null) {
					System.out.print("\nMST has to be calculated first!\n\n");
					break;
				}

				System.out.print("Give the new node that you want to insert: \n\t> ");
				in.nextLine();
				String newNode = in.nextLine();

				Vertex test = new Vertex(newNode, d);
				g.insertVertex(test);
				g.findNeighbors(test);
				mst = g.calculateMST(g.findVertex("02"));

				// mst.insertEdge(new Node(new Edge(newNode, d)));
				System.out.println();
				break;
			case 4:

				if (mst == null) {
					System.out.print("\nMST has to be calculated first!\n\n");
					break;
				}

				System.out.print("Give the ID of the node you want to remove: \n\t> ");
				String edgeID = in.next();
				Vertex removed = g.deleteVertex(edgeID);
				mst = g.calculateMST(g.findVertex("02"));
				/* mst.removeEdge(new Node(new Edge(toBeRemoved, d))); */
				System.out.println("\tNode " + removed + " was removed successfully!");
				break;
			case 5:

				if (mst == null) {
					System.out.print("\nMST has to be calculated first!\n\n");
					break;
				}

				System.out.print("Give the ID of the fire station that you want to start: \n\t> ");
				String id = in.next();
				MST temp = g.calculateMST(g.findVertex(id));
				System.out.println("\nThe highest temperature recorded was " + temp.informFireStation() + " °C.\n\n");
				break;

			case 6:

				try {
					g.printHashTableInFile(filename);
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.print("-------------------------------------------------------------+");
				System.out.print("\nYou have selected to terminate the simulation.Good bye!\n");
				System.out.print("-------------------------------------------------------------+");
				break;

			default:
				System.out.println("Wrong input. Please try again!\n");

			}
		}

	}

}