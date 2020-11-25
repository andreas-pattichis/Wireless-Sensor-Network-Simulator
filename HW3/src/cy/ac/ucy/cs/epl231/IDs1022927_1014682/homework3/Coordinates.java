package cy.ac.ucy.cs.epl231.IDs1022927_1014682.homework3;

import java.lang.Math;

public class Coordinates {
	
	int x;
	int y;
	
	
	public Coordinates(String coordinates) {
		
		coordinates = coordinates.substring(1, coordinates.length()-1); //remove first and last char
		
		String temp[] = new String[2];
		
		temp = coordinates.split(", ");
		
		x = Integer.parseInt(temp[0]);
		y = Integer.parseInt(temp[1]);
		
	}
	
	int calculateDistance(Coordinates other) {
		
		int result = (int) Math.sqrt((Math.pow(this.x-other.x,2) + Math.pow(this.y - other.y,2)));
		
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
	
	public String toString() {
		
		StringBuilder temp = new StringBuilder();
		
		temp.append("[" + x +", " + y + "]");
		
		return temp.toString();
	}

}
