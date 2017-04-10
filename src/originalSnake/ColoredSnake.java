/**
 * The snake who will crawl the maze
 * 
 * @author 			Per Nyberg
 * @created 		2013.10.16
 * @last_updated	2017.04.10
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class ColoredSnake {
	public final int 	UP = 1,
						RIGHT = 2,
						DOWN = 3,
						LEFT = 4;
	
	private LinkedList<SnakePart> bodyParts;
	
	private boolean growOnNextMove;
	private boolean alive;
	
	private int direction;
	private int adjustmentX;
	private int adjustmentY;
	private Color color;
	
	/**
	 * Initiates the snake with it's body in the form of a 
	 * list and the position and moving-direction of the head.
	 *
	 * @param x 		the x-coordinate for the head of the snake
	 * @param y 		the y-coordinate for the head of the snake
	 * @param direction	the 90-degree direction for the head of the snake
	 * @param color 	the color the snake should have
	 */
	public ColoredSnake(int x, int y, int direction, Color color) {
		bodyParts = new LinkedList<SnakePart>();
		
		this.color = color;
		
		initSnake(x, y, direction);
	}
	
	/**
	 * Sets a new position with a new direction and recreates the snake
	 *
	 * @param x 		the new x-coordinate for the head of the snake
	 * @param y 		the new y-coordinate for the head of the snake
	 * @param direction	the new 90-degree direction for the head of the snake
	 */
	public void renew(int x, int y, int direction) {
		bodyParts.clear();
		
		initSnake(x, y, direction);
	}
	
	/**
	 * Initiate the details of the snake:
	 * - position of the head of the snake
	 * - direction for moving (of the head)
	 *
	 * @param x 		the x-coordinate for the head of the snake
	 * @param y 		the y-coordinate for the head of the snake
	 * @param direction	the 90-degree direction for the head of the snake
	 */
	private void initSnake(int x, int y, int direction) {
		this.direction = direction;
		
		growOnNextMove = false;
		alive = true;
		
		// body
		bodyParts.add(new SnakePart(x, y, color)); // head
		createRemainderOfSnake(x, y, direction);
	}

	/**
	 * Creates the rest of the body of the snake
	 * Starts at a total of three parts
	 *
	 * @param x 		the x-coordinate for the head of the snake
	 * @param y 		the y-coordinate for the head of the snake
	 * @param direction	the 90-degree direction for the head of the snake
	 */
	public void createRemainderOfSnake(int x, int y, int direction) {
		if (direction == UP) {
			bodyParts.add(new SnakePart(x, y + 1, color));
			bodyParts.add(new SnakePart(x, y + 2, color));
		} else if (direction == RIGHT) {
			bodyParts.add(new SnakePart(x - 1, y, color));
			bodyParts.add(new SnakePart(x - 2, y, color));
		} else if (direction == DOWN) {
			bodyParts.add(new SnakePart(x, y - 1, color));
			bodyParts.add(new SnakePart(x, y - 2, color));
		} else if (direction == LEFT) {
			bodyParts.add(new SnakePart(x + 1, y, color));
			bodyParts.add(new SnakePart(x + 2, y, color));
		}
	}
	
	/**
	 * Sets the position of the head of the snake
	 *
	 * @param x 		the new x-coordinate for the head of the snake
	 * @param y 		the new y-coordinate for the head of the snake
	 */
	public void setPosition(int x, int y) {
		bodyParts.get(0).setX(x);
		bodyParts.get(0).setY(y);
	}

	/**
	 * Sets the shifting of the snakes position
	 * Shifting meaning the amount of pixels the picture is moved
	 * from where the paintComponent-method counts as 0.
	 *
	 * @param adjustmentX 	adjustment of x-coordinate
	 * @param adjustmentY	adjustment of y-coordinate
	 */
	public void setPositionBase(int adjustmentX, int adjustmentY) {
		this.adjustmentX = adjustmentX;
		this.adjustmentY = adjustmentY;
	}

	/**	
	 * @param alive	false if the snake is dead and should be removed from play (multiplayer only)
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * Moves the snake in a specified direction
	 * If the snake should grow the end of the snake "stands still"
	 */
	public void move() {
		SnakePart growingPosition = null;
		
		// puts the last part of the snake in the "growing"-variable
		if(growOnNextMove) {
			SnakePart lastPart = bodyParts.getLast();
			growingPosition = new SnakePart(lastPart);
		}
		
		// moves all of the current snakeparts except from the head (part-index 0)
		for(int i = bodyParts.size()-1 ; i > 0 ; i--) {
			bodyParts.get(i).setPosition(bodyParts.get(i-1));
		}
		
		// moves the head of the snake in the specified direction
		int x = bodyParts.get(0).getX();
		int y = bodyParts.get(0).getY();
		SnakePart head = bodyParts.getFirst();

		if(direction == UP) {
			head.setPosition(x, y-1);
		} else if(direction == RIGHT) {
			head.setPosition(x+1, y);
		} else if(direction == DOWN) {
			head.setPosition(x, y+1);
		} else if(direction == LEFT) {
			head.setPosition(x-1, y);
		}

		// adds the new part to the snake (if needed) and resets the "should grow"-boolean
		if(growOnNextMove) {
			bodyParts.addLast(growingPosition);
			growOnNextMove = false;
		}
	}
	
	/**
	 * Method that adds a new part to the snake
	 * 
	 * @param snakePart		the new tail of the snake
	 */
	public void addSnakePart(SnakePart snakePart) {
		bodyParts.add(snakePart);
	}
	
	public void grow() {
		growOnNextMove = true;
	}
	
	/**
	 * Sets a new direction for the snake
	 * 
	 * @param direction		the new 90-degree direction for the snake
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Returns whether the snake will grow on the next movement
	 *
	 * @return	whether or not the snake will grow on the next move-round
	 */
	public boolean isGrowOnNextMove() {
		return growOnNextMove;
	}
	
	/**
	 * @return	if the snake is alive (or if dead and should be removed)
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @return	the direction for the (head of the) snake
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * @return	get the specified part of the snake
	 */
	public SnakePart getSnakePart(int index) {
		return bodyParts.get(index);
	}
	
	/**
	 * @return	the length of the snake (in number of parts)
	 */
	public int getSnakeLength() {
		return bodyParts.size();
	}
	
	/**
	 * @return	color of the snake
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Paint the snake part by part
	 */
	public void paint(Graphics g) {
		for(int i = 0 ; i < bodyParts.size() ; i++) {
			bodyParts.get(i).paint(g, adjustmentX, adjustmentY);
		}
	}
}
