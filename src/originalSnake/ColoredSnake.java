/**
 * The snake who will crawl the maze
 * 
 * @author 			Per Nyberg
 * @created 		2013.10.16
 * @last_updated	2015.06.24
 */

import java.awt.*;
import java.util.*;

public class ColoredSnake {
	private LinkedList<SnakePart> bodyPartPositions;
	
	private boolean growOnNextMove;
	private boolean alive;
	
	private int snakeDirection;
	private final int 	UP = 1,
						RIGHT = 2,
						DOWN = 3,
						LEFT = 4;
	
	private int repositioningX;
	private int repositioningY;
	private Color snakeColor;
	
	/**
	 * Initiates the snake with it's body in the form of a 
	 * list and the position and moving-direction of the head.
	 */
	public ColoredSnake(int x, int y, int snakeDirection, Color snakeColor) {
		bodyPartPositions = new LinkedList<SnakePart>();
		
		this.snakeColor = snakeColor;
		
		initSnake(x, y, snakeDirection);
	}
	
	/**
	 * Sets a new position with a new direction and recreates the snake
	 */
	public void renew(int x, int y, int snakeDirection) {
		bodyPartPositions.clear();
		
		initSnake(x, y, snakeDirection);
	}
	
	/**
	 * Initiate the details of the snake:
	 * - position of the head of the snake
	 * - direction for moving (of the head)
	 */
	private void initSnake(int x, int y, int snakeDirection) {
		this.snakeDirection = snakeDirection;
		
		growOnNextMove = false;
		alive = true;
		
		// body
		bodyPartPositions.add(new SnakePart(x, y, snakeColor)); // head
		createRemainderSnake(x, y, snakeDirection);
	}

	/**
	 * Creates the rest of the body of the snake
	 * Starts at a total of three parts
	 */
	public void createRemainderSnake(int x, int y, int snakeDirection) {
		if (snakeDirection == UP) {
			bodyPartPositions.add(new SnakePart(x, y + 1, snakeColor));
			bodyPartPositions.add(new SnakePart(x, y + 2, snakeColor));
		} else if (snakeDirection == RIGHT) {
			bodyPartPositions.add(new SnakePart(x - 1, y, snakeColor));
			bodyPartPositions.add(new SnakePart(x - 2, y, snakeColor));
		} else if (snakeDirection == DOWN) {
			bodyPartPositions.add(new SnakePart(x, y - 1, snakeColor));
			bodyPartPositions.add(new SnakePart(x, y - 2, snakeColor));
		} else if (snakeDirection == LEFT) {
			bodyPartPositions.add(new SnakePart(x + 1, y, snakeColor));
			bodyPartPositions.add(new SnakePart(x + 2, y, snakeColor));
		}
	}
	
	/**
	 * Sets the position of the head of the snake
	 */
	public void setPosition(int x, int y) {
		bodyPartPositions.get(0).setX(x);
		bodyPartPositions.get(0).setY(y);
	}

	/**
	 * Sets the shifting of the snakes position
	 * Shifting meaning the amount of pixels the picture is moved
	 * from where the paintComponent-method counts as 0.
	 */
	public void setPositionBase(int repositioningX, int repositioningY) {
		this.repositioningX = repositioningX;
		this.repositioningY = repositioningY;
	}
	
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
			growingPosition = new SnakePart(bodyPartPositions.get(bodyPartPositions.size()-1));
		}
		
		// moves all of the current snakeparts except from the head (part-index 0)
		for(int i = bodyPartPositions.size()-1 ; i > 0 ; i--) {
			bodyPartPositions.get(i).setPosition(bodyPartPositions.get(i-1));
		}
		
		// moves the head of the snake in the specified direction
		if(snakeDirection == UP)
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX(),bodyPartPositions.get(0).getY()-1);
		else if(snakeDirection == RIGHT)
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX()+1,bodyPartPositions.get(0).getY());
		else if(snakeDirection == DOWN)
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX(),bodyPartPositions.get(0).getY()+1);
		else if(snakeDirection == LEFT)
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX()-1,bodyPartPositions.get(0).getY());
		
		// adds the new part to the snake (if needed) and resets the "should grow"-boolean
		if(growOnNextMove) {
			bodyPartPositions.addLast(growingPosition);
			growOnNextMove = false;
		}
	}
	
	/**
	 * Method that adds a new part to the snake
	 */
	public void addSnakePart(SnakePart snakePart) {
		bodyPartPositions.add(snakePart);
	}
	
	/**
	 * Sets a new direction for the snake
	 */
	public void setSnakeDirection(int snakeDirection) {
		this.snakeDirection = snakeDirection;
	}

	/**
	 * Returns whether the snake will grow on the next movement
	 */
	public boolean isGrowOnNextMove() {
		return growOnNextMove;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void grow() {
		growOnNextMove = true;
	}
	
	public int getSnakeDirection() {
		return snakeDirection;
	}
	
	public SnakePart getSnakePart(int index) {
		return bodyPartPositions.get(index);
	}
	
	public int getSnakeLength() {
		return bodyPartPositions.size();
	}
	
	public Color getColor() {
		return snakeColor;
	}

	public void paint(Graphics g) {
		for(int i = 0 ; i < bodyPartPositions.size() ; i++)
			bodyPartPositions.get(i).paint(g, repositioningX, repositioningY);
	}
}
