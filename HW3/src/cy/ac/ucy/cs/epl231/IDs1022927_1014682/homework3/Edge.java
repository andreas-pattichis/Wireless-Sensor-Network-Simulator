package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Edge {

	private String ID;
	private Coordinates point;
	private int temperature;
	
	private LinkedList<Edge> neighbours;
	private LinkedList<Edge> children;

	private boolean sensor;
	private boolean controlCenter;

	private float maxDistance;

	public Edge(String str, float d) {
		String[] temp = new String[3];

		temp = str.split("\t");

		ID = temp[0];
		point = new Coordinates(temp[1]);
		temperature = Integer.parseInt(temp[2]);

		neighbours = new LinkedList<Edge>();
		children = new LinkedList<Edge>();

		maxDistance = d;

		if (ID.charAt(0) == '0') {
			controlCenter = true;
			sensor = false;
		} else {
			controlCenter = false;
			sensor = true;
		}

	}
	
	//-------
	
	public LinkedList<Edge> getChildren() {
		return children;

	}
	
	public boolean hasChildren() {
		return !children.isEmpty();
	}
	
	public void addChild(Edge newChild) {
		children.add(newChild);

	}

	//-------
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
	
	public boolean isAlreadyANeighbour(Edge other) {
		return neighbours.contains(other);
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

	public void deleteNeighbour(Edge toBeDeleted) {
		this.neighbours.remove(toBeDeleted);

	}

	public Edge getClosestNeighbour() {

		if (neighbours.size() > 0) {
			float minDistance = calculateDistance(neighbours.get(0));
			Edge e = neighbours.get(0);

			for (int i = 1; i < neighbours.size(); i++)
				if (calculateDistance(neighbours.get(i)) < minDistance) {
					minDistance = calculateDistance(neighbours.get(i));
					e = neighbours.get(i);
				}
			return e;
		}

		return null;
	}

	public Edge getClosestNeighbour(LinkedList<Edge> visited) {

		if (neighbours.size() > 0) {
			float minDistance = Float.POSITIVE_INFINITY;
			Edge e = null;

			for (int i = 0; i < neighbours.size(); i++)
				if (calculateDistance(neighbours.get(i)) < minDistance
						&& !visited.contains(neighbours.get(i))) {

					minDistance = calculateDistance(neighbours.get(i));
					e = neighbours.get(i);
				}
			return e;
		}

		return null;
	}

	
	


	public Float getClosestDistance() {

		Edge e = getClosestNeighbour();
		
		return calculateDistance(e);
	}
	
	
	
	

}
