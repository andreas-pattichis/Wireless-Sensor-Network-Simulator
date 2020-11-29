package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.util.LinkedList;

public class Edge {

	private String ID;
	private Coordinates point;
	private int temperature;
	
	protected LinkedList<Edge> neighbours;
	//private LinkedList<Edge> children;

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
		//children = new LinkedList<Edge>();

		maxDistance = d;

		if (ID.charAt(0) == '0') {
			controlCenter = true;
			sensor = false;
		} else {
			controlCenter = false;
			sensor = true;
		}

	}
	
	public Edge(Edge other) {
		
		this.ID = other.ID;
		this.point = other.point;
		this.temperature = other.temperature;
		this.neighbours = other.neighbours;
		this.sensor = other.sensor;
		this.controlCenter = other.controlCenter;
		this.maxDistance = other.maxDistance;
		
	}
	
	
	


	//-------
	
	
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
	
	public Float getClosestDistance(LinkedList<Edge> visited) {

		Edge e = getClosestNeighbour(visited);
		
		return calculateDistance(e);
	}

	@Override
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
