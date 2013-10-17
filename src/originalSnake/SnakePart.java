package originalSnake;
/**
 * A part of the snake (the position of that part)
 * 
 * @author Per Nyberg
 * @version 2013.10.08
 */

import java.awt.*;

public class SnakePart {
	// x-coordinate
	private int x;
	// y-coordinate
	private int y;
	
	/**
	 * @param x		x-coordinate of the snake
	 * @param y		y-coordinate of the snake
	 */
	public SnakePart(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Sets a new position for the SnakePart using another SnakePart
	 * 
	 * @param p		SnakePart used to set position
	 */
	public SnakePart(SnakePart p) {
		setPosition(p);
	}
	
	/**
	 * Sets a new position for the SnakePart using another SnakePart
	 * 
	 * @param p		SnakePart used to set position
	 */
	public void setPosition(SnakePart p) {
		setPosition(p.x, p.y);
	}
	
	/**
	 * Sets a new position for the SnakePart
	 * 
	 * @param x		x-coordinate for the new position
	 * @param y		y-coordinate for the new position
	 */
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	/**
	 * @param x		x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y		y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}


	/**
	 * Help-method who prints out the parts of the snake
	 * 
	 * @return		the position of the SnakePart
	 */
	public String printPosition() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Paint the snakePart
	 * 
	 * @param g		Graphics-parameter, used to paint the snakePart
	 */
	public void paint(Graphics g, int additionalPositionX, int additionalPositionY) {
		g.setColor(Color.red);
		g.fillOval(additionalPositionX + x*10, additionalPositionY + y*10, 10, 10);
		g.setColor(Color.black);
		g.fillOval(additionalPositionX + x*10, additionalPositionY + y*10, 10, 10);
	}
	public static void main(String[] args) {
		System.out.println("Compiling SnakePart");
	}
}