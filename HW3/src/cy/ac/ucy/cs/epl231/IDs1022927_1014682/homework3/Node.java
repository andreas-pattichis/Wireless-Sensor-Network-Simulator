package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Node {

	String ID;
	Coordinates point;
	int temperature;
	LinkedList<Node> neighbours;

	boolean sensor;
	boolean controlCenter;

	int maxDistance;

	public Node(String str, int d) {
		String[] temp = new String[3];

		temp = str.split("\t");

		ID = temp[0];
		point = new Coordinates(temp[1]);
		temperature = Integer.parseInt(temp[2]);

		neighbours = new LinkedList<Node>();

		maxDistance = d;

		if (ID.charAt(0) == '0') {
			controlCenter = true;
			sensor = false;
		} else {
			controlCenter = false;
			sensor = true;
		}

	}

	public boolean isSensor() {
		return sensor;
	}

	public boolean isControlCenter() {
		return controlCenter;
	}

	public boolean isNeighbour(Node other) {

		int temp = point.calculateDistance(other.point);

		return (temp > 0 && temp <= maxDistance);
	}

	public String getID() {
		return ID;
	}

	public String toString() {

		StringBuilder temp = new StringBuilder();

		temp.append(ID + "\t" + point + "\t" + temperature);

		return temp.toString();
	}

}
