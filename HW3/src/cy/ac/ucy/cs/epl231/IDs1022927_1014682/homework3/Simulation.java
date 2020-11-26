package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

public class Simulation {

	public static void main(String[] args) {
		
		if (args.length != 2) {
			System.out.println("Wrong input.\nYou have to enter the value of d and the name of the text file.");
			return;
		}
		
		System.out.println("01\t[42, 85]\t30");

		Coordinates test1 = new Coordinates("[42, 85]");

		Coordinates test2 = new Coordinates("[30, 60]");

		System.out.println(test1.calculateDistance(test2));

		Node sensor = new Node("01\t[42, 85]\t30", 4);

		System.out.println(sensor);
	}
}
