/**
 * Class that represents an edge
 * 
 * @author Christos Kasoulides, Andreas pattichis
 */

package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Edge {

	private String ID; // String value that indicates the id of each edge
	private Coordinates point; // Coordinates that represent the coordinates of each edge
	private int temperature; // Integer value that represents the temperature of each edge
	private boolean sensor; // Boolean value of that stores if each edge is a sensor
	private boolean controlCenter; // Boolean value of that stores if each edge is a control center

	protected LinkedList<Edge> neighbours; // Linked list of Edges that stores all the neighbours of each edge

	private float maxDistance; // Float value that will store the maximum distance

	/**
	 * Constructor of the Edge class that gets as parameters a string value and a
	 * float value. The string value contains the id, the coordinates and the
	 * temperature of the edge, along with the distance d.
	 * 
	 * @param str id, the coordinates and the temperature in the appropriate form
	 * @param d   the distance
	 */
	public Edge(String str, float d) {

		maxDistance = d; // Stores the distance

		String[] temp = new String[3];
		temp = str.split("\t");

		ID = temp[0]; // Stores the id
		point = new Coordinates(temp[1]); // Stores the coordinate
		temperature = Integer.parseInt(temp[2]); // Stores the temperature

		neighbours = new LinkedList<Edge>(); // Initializes the neighbours list

		// Checks if the edge is a sensor and/or a control center
		if (ID.charAt(0) == '0') {
			controlCenter = true;
			sensor = false;
		} else {
			controlCenter = false;
			sensor = true;
		}

	}

	/**
	 * Copy constructor of the class edge that will get another Edge as a parameter
	 * and copy the values of it, by creating a new edge with the same content.
	 * 
	 * @param other The edge that will be copied
	 */
	public Edge(Edge other) {

		this.ID = other.ID;
		this.point = other.point;
		this.temperature = other.temperature;
		this.neighbours = other.neighbours;
		this.sensor = other.sensor;
		this.controlCenter = other.controlCenter;
		this.maxDistance = other.maxDistance;

	}

	// -------

	/*
	 * public LinkedList<Edge> getChildren() { return children;
	 * 
	 * }
	 * 
	 * public boolean hasChildren() { return !children.isEmpty(); }
	 * 
	 * public void addChild(Edge newChild) { children.add(newChild);
	 * 
	 * }
	 * 
	 * public void deleteChild(Edge child) { this.children.remove(child); }
	 */

	// -------

	/**
	 * Method that gets as a parameter another edge and calculates the distance
	 * between those two edges by using the method calculateDistance() of the
	 * Coordinates class.
	 * 
	 * @param other The other edge
	 * 
	 * @return The distance between the current edge and the edge of the parameter
	 */
	public float calculateDistance(Edge other) {
		return point.calculateDistance(other.point);
	}

	/**
	 * Method that returns if the edge is a sensor
	 * 
	 * @return a boolean value true or false
	 */
	public boolean isSensor() {
		return sensor;
	}

	/**
	 * Method that returns if the edge is a control center
	 * 
	 * @return a boolean value true or false
	 */
	public boolean isControlCenter() {
		return controlCenter;
	}

	/**
	 * Method that checks if the Edge that is on the parameter is a neighbour of the
	 * current node.
	 * 
	 * @return a boolean value true or false
	 */
	public boolean isNeighbour(Edge other) {

		float temp = point.calculateDistance(other.point);

		return (temp > 0 && temp <= maxDistance);
	}

	/**
	 * Method that will check if the edge that is placed in the parameter is already
	 * placed as a neighbour of the current edge.
	 * 
	 * @param other The edge that will be checked
	 * 
	 * @return a boolean value true or false
	 */
	public boolean isAlreadyANeighbour(Edge other) {
		return neighbours.contains(other);
	}

	/**
	 * Method that will get an edge as a parameter and add it to the neighbours list
	 * of the current edge.
	 * 
	 * @param node
	 */
	public void addNeighbour(Edge node) {
		neighbours.add(node);
	}

	/**
	 * String method that will return a string that indicates the value of the id.
	 * 
	 * @return a string that indicates the value of the id
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Method that will return a string showing the id,coordinate and temperature of
	 * the edge in the appropriate form.
	 * 
	 * @return a string showing the id,coordinate and temperature
	 */
	public String toString() {

		StringBuilder temp = new StringBuilder();

		temp.append(ID + "\t" + point + "\t" + temperature);

		return temp.toString();
	}

	/**
	 * Method that gets an edge as a parameter and precedes to delete it from the
	 * neighbors list, showing that that edge is no longer a neighbour of the
	 * current edge.
	 * 
	 * @param toBeDeleted The edge that will be deleted
	 */
	public void deleteNeighbour(Edge toBeDeleted) {
		this.neighbours.remove(toBeDeleted);

	}

	/**
	 * 
	 * @return
	 */
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

	/**
	 * Method that finds and returns the closest neighbour of the current edge.
	 * 
	 * @param visited List with all the edges that will be checked
	 * 
	 * @return the closest neighbour as an edge
	 */
	public Edge getClosestNeighbour(LinkedList<Edge> visited) {

		if (this.neighbours.size() > 0) {
			float minDistance = Float.POSITIVE_INFINITY;
			Edge e = null;

			for (int i = 0; i < neighbours.size(); i++)
				if (calculateDistance(neighbours.get(i)) < minDistance && !visited.contains(neighbours.get(i))) {

					minDistance = calculateDistance(neighbours.get(i));
					e = neighbours.get(i);
				}
			return e;
		}

		return null;
	}

	/**
	 * Method that finds and returns the distance of the closest neighbour of the
	 * current edge.
	 * 
	 * @param visited List with all the edges that will be checked
	 * 
	 * @return float value that indicates the distance
	 */
	public Float getClosestDistance(LinkedList<Edge> visited) {

		Edge e = getClosestNeighbour(visited);

		return calculateDistance(e);
	}

	@Override
	/**
	 * Method that returns the hash code value of the of the object
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + (controlCenter ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(maxDistance);
		result = prime * result + ((neighbours == null) ? 0 : neighbours.hashCode());
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		result = prime * result + (sensor ? 1231 : 1237);
		result = prime * result + temperature;
		return result;
	}

	@Override
	/**
	 * Equals method that overrides the method equals of the Class object and
	 * returns a boolean value that indicates if the Edge in the parameter is
	 * equal to the current Edge.
	 * 
	 * @return a boolean value of true or false
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Edge other = (Edge) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;

		if (controlCenter != other.controlCenter)
			return false;

		if (Float.floatToIntBits(maxDistance) != Float.floatToIntBits(other.maxDistance))
			return false;

		if (neighbours == null) {
			if (other.neighbours != null)
				return false;
		} else if (!neighbours.equals(other.neighbours))
			return false;

		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;

		if (sensor != other.sensor)
			return false;

		if (temperature != other.temperature)
			return false;

		return true;
	}

}
