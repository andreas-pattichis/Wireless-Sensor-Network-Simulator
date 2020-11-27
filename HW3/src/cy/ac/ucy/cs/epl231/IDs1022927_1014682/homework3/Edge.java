package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Edge {

	private String ID;
	private Coordinates point;
	private int temperature;
	private LinkedList<Edge> neighbours;

	private boolean sensor;
	private boolean controlCenter;

	private int maxDistance;

	public Edge(String str, int d) {
		String[] temp = new String[3];

		temp = str.split("\t");

		ID = temp[0];
		point = new Coordinates(temp[1]);
		temperature = Integer.parseInt(temp[2]);

		neighbours = new LinkedList<Edge>();

		maxDistance = d;

		if (ID.charAt(0) == '0') {
			controlCenter = true;
			sensor = false;
		} else {
			controlCenter = false;
			sensor = true;
		}

	}
	public float calculateDistance(Edge other) {
		return point.calculateDistance(other.point);
	}

	public boolean isSensor() {
		return sensor;
	}

	public boolean isControlCenter() {
		return controlCenter;
	}

	public boolean isNeighbour(Edge other) {

		float temp = point.calculateDistance(other.point);

		return (temp > 0 && temp <= maxDistance);
	}
	
	public void addNeighbour(Edge node) {
		neighbours.add(node);
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
