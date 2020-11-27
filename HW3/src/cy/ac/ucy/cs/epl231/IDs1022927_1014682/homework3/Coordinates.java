package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.lang.Math;

public class Coordinates {

	


	private int x;
	private int y;

	public Coordinates(String coordinates) {

		coordinates = coordinates.substring(1, coordinates.length() - 1); // remove first and last char

		String temp[] = new String[2];

		temp = coordinates.split(", ");

		x = Integer.parseInt(temp[0]);
		y = Integer.parseInt(temp[1]);

	}

	float calculateDistance(Coordinates other) {

		float result = (float) Math.sqrt((Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));

		return result;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String toString() {

		StringBuilder temp = new StringBuilder();

		temp.append("[" + x + ", " + y + "]");

		return temp.toString();
	}

}
