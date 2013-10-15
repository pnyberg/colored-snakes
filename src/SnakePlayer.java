/**
 * 
 * @author Per Nyberg
 * @version 2013.10.08
 */

import java.awt.*;
import java.awt.event.KeyEvent;

public class SnakePlayer {
	// The snake of the player
	private ColoredSnake snake;
	// variable to prevent the snake from turning into itself
	private boolean alreadyTurned;
	// value of the left key
	private int leftKeyValue;
	// value of the right key
	private int rightKeyValue;
	
	/**
	 * TODO Should also have a parameter for the keys used to navigate
	 * the snake (up/down/right/left)
	 * 
	 * @param x
	 * @param y
	 */
	public SnakePlayer(int x, int y, String snakeDirection) {
		snake = new ColoredSnake(x, y, snakeDirection);

		alreadyTurned = false;
		
		leftKeyValue = KeyEvent.VK_A;
		rightKeyValue = KeyEvent.VK_S;
	}
	
	public void resetSnake(int x, int y, String snakeDirection) {
		snake.renew(x, y, snakeDirection);
	}
	
	/**
	 * Sets the base of the players snake
	 * That is, add x-coordinates and y-coordinates to the 
	 * snakes position
	 */
	public void setSnakePositionBase(int additionalX, int additionalY) {
		snake.setPositionBase(additionalX, additionalY);
	}
	
	public void setKeyBindings(int leftKeyValue, int rightKeyValue) {
		this.leftKeyValue = leftKeyValue;
		this.rightKeyValue = rightKeyValue;
	}
	
	public void setSnakeAlive(boolean isAlive) {
		snake.setAlive(isAlive);
	}
	/**
	 * Calls the move-function of the snake
	 * The direction is pre-determined
	 */
	public void move() {
		snake.move();
		
		// enables turning again
		alreadyTurned = false;
	}

	/**
	 * Sets a new direction for the snake
	 * Makes a "turn"
	 * 
	 * @param direction
	 */
	public void turn(String direction) {
		// prevents the snake to turn into itself
		if(!alreadyTurned) {
			// sets the new direction for the snake and makes sure the snake won't "turn into itself"
			snake.setSnakeDirection(direction);
			alreadyTurned = true;
		}
	}
	
	/**
	 * Tells the snake to grow
	 * The snake then internally handles how to and when to
	 * do the actually "growing"
	 */
	public void growSnake() {
		snake.grow();
	}

	/**
	 * Paints the snake
	 * 
	 * @param g		Graphics-parameter used to paint
	 */
	public void paintSnake(Graphics g) {
		snake.paint(g);
	}
	
	/**
	 * Returns the direction of the snake
	 * 
	 * @return	the direction of the snake
	 */
	public String getSnakeDirection() {
		return snake.getSnakeDirection();
	}
	
	// TODO getKeyCodeLeft()
	
	// TODO getKeyCodeRight()
	
	/**
	 * Returns a specified part of the snake
	 * 
	 * @param index
	 * @return snakePart
	 */
	public SnakePart getSnakePart(int index) {
		return snake.getSnakePart(index);
	}
	
	/**
	 * Gives the length of the snake (number of SnakeParts)
	 * 
	 * @return snakeLength
	 */
	public int getSnakeLength() {
		return snake.getSnakeLength();
	}
	
	/**
	 * 
	 */
	public boolean isSnakeAlive() {
		return snake.isAlive();
	}
	
	public int getLeftKeyValue() {
		return leftKeyValue;
	}
	
	public int getRightKeyValue() {
		return rightKeyValue;
	}
	 
}
