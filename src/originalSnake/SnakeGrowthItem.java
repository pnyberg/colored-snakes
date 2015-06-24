/**
 * The item that when eaten will grow the snake +1
 */

//package originalSnake;

import java.awt.*;

public class SnakeGrowthItem {
	// the x-coordinate
	private int x;
	// the y-coordinate
	private int y;
	// the shift in x-coordinate
	private int repositioningX;
	// the shift in y-coordinate
	private int repositioningY;
	
	/**
	 * Sets the position of the growth-object
	 * @param x
	 * @param y
	 */
	public SnakeGrowthItem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPositionBase(int additionalX, int additionalY) {
		repositioningX = additionalX;
		repositioningY = additionalY;
	}

	/**
	 * Paints the item
	 */
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(repositioningX + x*10, repositioningY + y*10, 10, 10);
		g.setColor(Color.black);
		g.drawOval(repositioningX + x*10, repositioningY + y*10, 10, 10);
	}

	/**
	 * @return x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x-coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y-coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}
