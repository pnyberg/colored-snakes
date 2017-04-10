/**
 * The item that when eaten will grow the snake +1
 *
 * @author 			Per Nyberg
 * @created 		????
 * @last_updated	2017.04.10
 */


//package originalSnake;

import java.awt.Color;
import java.awt.Graphics;

public class SnakeGrowthItem {
	private final Color fillColor = Color.red;
	private final Color outlineColor = Color.black;

	private int x, y;
	private int adjustmentX, adjustmentY;
	
	/**
	 * Sets the position of the growth-object
	 * @param x
	 * @param y
	 */
	public SnakeGrowthItem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPositionBase(int adjustmentX, int adjustmentY) {
		this.adjustmentX = adjustmentX;
		this.adjustmentY = adjustmentY;
	}

	/**
	 * Sets the x-coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y-coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
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
	 * Paints the item
	 */
	public void paint(Graphics g) {
		int size = SnakePart.size;

		g.setColor(fillColor);
		g.fillOval(adjustmentX + x*size, adjustmentY + y*size, size, size);
		g.setColor(outlineColor);
		g.drawOval(adjustmentX + x*size, adjustmentY + y*size, size, size);
	}
}
