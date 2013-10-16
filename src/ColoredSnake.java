/**
 * A first attempt to create a "snake" that may crawl 
 * through any hypothetical maze I will create later
 * 
 * @author Per Nyberg
 * @version 2013.10.16
 */

import java.awt.*;
import java.util.*;

public class ColoredSnake {
	// A list to hold the positions of the snakes bits
	private LinkedList<SnakePart> bodyPartPositions;
	
	// a variable to help determine if the snake should become longer
	private boolean growOnNextMove;
	// variable describing if snake is alive
	private boolean alive;
	
	// gives the direction of the snake
	private String snakeDirection;
	
	// the "repositioning" of the x-coordinate
	private int repositioningX;
	// the "repositioning" of the y-coordinate
	private int repositioningY;
	
	/**
	 * Initiates the snake with it's body in the form of a 
	 * list and the position and moving-direction of the head.
	 * @param x	the x-coordinate of the snake
	 * @param y	the y-coordinate of the snake
	 */
	public ColoredSnake(int x, int y, String snakeDirection) {
		// creates an empty list of SnakeParts
		bodyPartPositions = new LinkedList<SnakePart>();
		
		initSnake(x, y, snakeDirection);
	}
	
	/**
	 * Renews the position and moving-direction of the 
	 * snake (the head).
	 */
	public void renew(int x, int y, String snakeDirection) {
		bodyPartPositions.clear();
		
		initSnake(x, y, snakeDirection);
	}
	
	/**
	 * Initiate the details of the snake:
	 * - position of the head of the snake
	 * - direction for moving (of the head)
	 * - sets if the snake is alive and if it should grow
	 * - creates the head and the body
	 */
	private void initSnake(int x, int y, String snakeDirection) {
		this.snakeDirection = snakeDirection;
		
		// the snake should now grow on startup
		growOnNextMove = false;
		// however, it should be alive :)
		alive = true;
		
		// create the head
		bodyPartPositions.add(new SnakePart(x, y));
		
		createRemainderSnake(x, y, snakeDirection);
	}

	/**
	 * Creates the rest of the body of the snake
	 * For now, it's a three-part-body in the beginning
	 */
	public void createRemainderSnake(int x, int y, String snakeDirection) {
		// depending on the direction of the snake the rest of 
		// the bodyparts are placed out differently
		if (snakeDirection.equals("up")) {
			bodyPartPositions.add(new SnakePart(x, y + 1));
			bodyPartPositions.add(new SnakePart(x, y + 2));
		} else if (snakeDirection.equals("right")) {
			bodyPartPositions.add(new SnakePart(x - 1, y));
			bodyPartPositions.add(new SnakePart(x - 2, y));
		} else if (snakeDirection.equals("down")) {
			bodyPartPositions.add(new SnakePart(x, y - 1));
			bodyPartPositions.add(new SnakePart(x, y - 2));
		} else if (snakeDirection.equals("left")) {
			bodyPartPositions.add(new SnakePart(x + 1, y));
			bodyPartPositions.add(new SnakePart(x + 2, y));
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
	public void setPositionBase(int additionalX, int additionalY) {
		repositioningX = additionalX;
		repositioningY = additionalY;
	}
	
	/**
	 * Setting the snake to dead or alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * Moves the snake in a specified direction
	 * If the snake should grow the end of the snake "stands still",
	 * that is, becomes the new part of the snake (while the other 
	 * part moves one step "forward").
	 * @param direction	a string representing "up"/"right"/"down"/"left"
	 */
	public void move() {
		 // a variable to help expand the snake
		SnakePart growingPosition = null;
		
		// puts the last part of the snake in the "growing"-variable
		if(growOnNextMove) {
			growingPosition = new SnakePart(bodyPartPositions.get(bodyPartPositions.size()-1));
		}
		
		// moves all of the current snakeparts except from the head (part-index 0)
		// the bodyparts new position is the position of the bodypart who is "in 
		// front of" the bodypart looked at
		for(int i = bodyPartPositions.size()-1 ; i > 0 ; i--) {
			bodyPartPositions.get(i).setPosition(bodyPartPositions.get(i-1));
		}
		
		// moves the head of the snake in the specified direction
		if(snakeDirection.equals("up"))
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX(),bodyPartPositions.get(0).getY()-1);
		else if(snakeDirection.equals("right"))
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX()+1,bodyPartPositions.get(0).getY());
		else if(snakeDirection.equals("down"))
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX(),bodyPartPositions.get(0).getY()+1);
		else if(snakeDirection.equals("left"))
			bodyPartPositions.get(0).setPosition(bodyPartPositions.get(0).getX()-1,bodyPartPositions.get(0).getY());
		
		// adds the new part to the snake and resets the "should grow"-boolean
		if(growOnNextMove) {
			bodyPartPositions.addLast(growingPosition);
			growOnNextMove = false;
		}
	}
	
	/**
	 * Method that adds a new part to the snake
	 * The new part is placed last in the list/body
	 * @param snakePart	the snakepart that is added to the snake
	 */
	public void addSnakePart(SnakePart snakePart) {
		bodyPartPositions.add(snakePart);
	}
	
	/**
	 * Sets a new direction for the snake
	 * The string should be "up"/"right"/"down"/"left"
	 * @param snakeDirection
	 */
	public void setSnakeDirection(String snakeDirection) {
		this.snakeDirection = snakeDirection;
	}

	/**
	 * Returns whether the snake will grow (one part) on
	 * the next movement (or not).
	 * @return growOnNextMove	boolean
	 */
	public boolean isGrowOnNextMove() {
		return growOnNextMove;
	}
	
	/**
	 * Tells whether the snake is alive or not
	 * @return alive	boolean
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Method that tells the snake to grow on next movement
	 */
	public void grow() {
		growOnNextMove = true;
	}
	
	public String getSnakeDirection() {
		return snakeDirection;
	}
	
	/**
	 * @param index	specifies which part to return
	 * @return specific part of the snake
	 */
	public SnakePart getSnakePart(int index) {
		return bodyPartPositions.get(index);
	}
	
	/**
	 * @return the length of the snake
	 */
	public int getSnakeLength() {
		return bodyPartPositions.size();
	}

	/**
	 * Paints the snake (part by part)
	 * @param g	Graphics-parameter to paint
	 */
	public void paint(Graphics g) {
		for(int i = 0 ; i < bodyPartPositions.size() ; i++)
			bodyPartPositions.get(i).paint(g, repositioningX, repositioningY);
	}
}
