package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
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

			g.printHashTable();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2nd function
		MST mst = g.calculateMST(g.findEdge("02"));

		mst.display();

		System.out.println("\n\n\n");

		// 4th funtion
		
		/*
		 * Edge toBeRemoved = g.findEdge("20"); mst.removeEdge(toBeRemoved);
		 * 
		 * mst.display();
		 * 
		 * System.out.println("\n\n\n");
		 */
		 

		// 3rd function

		mst.insertEdge(new Edge("20	[2, 5]	30", d));

		mst.display();

		System.out.println("\n\n\n");

	}

}