/**
 * 
 * @author Per Nyberg
 * @version 2013.10.08
 */

import java.awt.*;

public class SnakePlayer {
	// The snake of the player
	private ColoredSnake snake;
	// variable to prevent the snake from turning into itself
	private boolean alreadyTurned;
	
	/**
	 * Should also have a parameter for the keys used to navigate
	 * the snake (up/down/right/left)
	 * 
	 * @param x
	 * @param y
	 */
	public SnakePlayer(int x, int y, String snakeDirection) {
		snake = new ColoredSnake(x, y, snakeDirection);

		alreadyTurned = false;

		snake.addSnakePart(new SnakePart(x+1,y));
		snake.addSnakePart(new SnakePart(x+2,y));
	}
	
	/**
	 * Sets the base of the players snake
	 * That is, add x-coordinates and y-coordinates to the 
	 * snakes position
	 */
	public void setSnakePositionBase(int addX, int addY) {
		snake.setPositionBase(addX, addY);
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
	// getKeyCodeUp()
	
	// getKeyCodeRight()
	
	// getKeyCodeDown()
	
	// getKeyCodeLeft()
	
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
	 * Testing-ground
	 * DO not BE here
	 */
	public static void main(String[] args) {
		SnakePlayer snakePlayer = new SnakePlayer(0,0,"up");
		
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.turn("right");
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.turn("down");
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.turn("left");
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
		snakePlayer.move();
		System.out.println("The position of the snake is now: " + snakePlayer.getSnakePart(0).printPosition());
	}
}
