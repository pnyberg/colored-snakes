// package originalSnake;

/**
 * A part of the snake (the position of that part)
 * 
 * @author Per Nyberg
 * @version 2013.10.08
 * @last_updated 2017.04.10
 */

import java.awt.Color;
import java.awt.Graphics;

public class SnakePart {
	public static final int size = 10;

	private int x, y;
	private Color snakeColor;
	
	/**
	 * @param x		x-coordinate of the snake
	 * @param y		y-coordinate of the snake
	 */
	public SnakePart(int x, int y, Color snakeColor) {
		this.x = x;
		this.y = y;
		this.snakeColor = snakeColor;
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
	 * @param addX	the adjustment to the x-coordinate
	 * @param addY	the adjustment to the y-coordinate
	 */
	public void paint(Graphics g, int addX, int addY) {
		g.setColor(snakeColor);
		g.fillOval(x*size + addX, y*size + addY, size, size);
	}

	public static void main(String[] args) {
		System.out.println("Compiling SnakePart");
	}
}