/**
 * Class that represents a coordinate.
 * 
 * @author Christos Kasoulides, Andreas pattichis
 */

package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.lang.Math;

public class Coordinates {

	private int x; // The X coordinate
	private int y; // The Y coordinate

	/**
	 * Constructor that get the coordinate as a parameter in the form: [x, y], get
	 * the values of x and y and stores them into the local variables x,y.
	 * 
	 * @param coordinates [x, y]
	 */
	public Coordinates(String coordinates) {

		coordinates = coordinates.substring(1, coordinates.length() - 1); // remove first and last char

		String temp[] = new String[2];

		temp = coordinates.split(", ");

		x = Integer.parseInt(temp[0]);
		y = Integer.parseInt(temp[1]);
	}

	/**
	 * Method that calculates and returns the distance between two coordinates.
	 * 
	 * @param other The other coordinate that we want to find the distance of it
	 *              with the current coordinate.
	 * 
	 * @return The distance between the two coordinates
	 */
	float calculateDistance(Coordinates other) {

		float result = (float) Math.sqrt((Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));

		return result;
	}

	/**
	 * Getter method that returns the X coordinate
	 * 
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter method that sets the X coordinate
	 * 
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter method that returns the Y coordinate
	 * 
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter method that sets the Y coordinate
	 * 
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	/**
	 * Returns the hash code value of the coordinate
	 * 
	 * @return the hash code value of the coordinate
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	/**
	 * Equals method that overrides the method equals of the Class object and
	 * returns a boolean value that indicates if the coordinate in the parameter is
	 * equal to the current coordinate.
	 * 
	 * @return boolean value true or false
	 */
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

	/**
	 * Method that return in written form the coordinates.
	 * 
	 * @return The coordinates in the form: "[x, y]"
	 */
	public String toString() {

		StringBuilder temp = new StringBuilder();

		temp.append("[" + x + ", " + y + "]");

		return temp.toString();
	}

}
