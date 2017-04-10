/**
 * The player that handled the snake. 
 * Points are awarded for every part of the snake added. 
 *
 * @author Per Nyberg
 * @version 2013.10.08
 * @last_updated 2017.04.10
 */

//package originalSnake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class SnakePlayer {
	private ColoredSnake snake;
	private boolean alreadyTurned, diedThisRound;
	private int leftKeyValue, rightKeyValue;
	private int points;
	
	/**
	 * Set the snakes position and moving direction
	 * 
	 * @param x 		the x-coordinate for the head of the snake
	 * @param y 		the y-coordinate for the head of the snake
	 * @param direction	the 90-degree direction for the head of the snake
	 */
	public SnakePlayer(int x, int y, int direction) {
		this(x, y, direction, KeyEvent.VK_A, KeyEvent.VK_S, Color.black);
	}
	
	/**
	 * @param x 			the x-coordinate for the head of the snake
	 * @param y 			the y-coordinate for the head of the snake
	 * @param direction		the 90-degree direction for the head of the snake
	 * @param leftKeyValue 	the key binded for left-turns
	 * @param rightKeyValue the key binded for right-turns
	 * @param color 		the color for the snake
	 */
	public SnakePlayer(int x, int y, int direction, int leftKeyValue, int rightKeyValue, Color color) {
		snake = new ColoredSnake(x, y, direction, color);

		this.leftKeyValue = leftKeyValue;
		this.rightKeyValue = rightKeyValue;
		
		alreadyTurned = false;
		
		points = 0;
	}

	/**
	 * Reset the snakes position and moving direction
	 * 
	 * @param x 		the new x-coordinate for the head of the snake
	 * @param y 		the new y-coordinate for the head of the snake
	 * @param direction	the new 90-degree direction for the head of the snake
	 */
	public void resetSnake(int x, int y, int direction) {
		snake.renew(x, y, direction);
	}
	
	/**
	 * Sets the base of the players snake
	 * That is, add x-coordinates and y-coordinates to the 
	 * snakes position
	 *
	 * @param adjustmentX 	adjustment of the x-coordinate
	 * @param adjustmentY 	adjustment of the y-coordinate
	 */
	public void setSnakePositionBase(int adjustmentX, int adjustmentY) {
		snake.setPositionBase(adjustmentX, adjustmentY);
	}

	/**
	 * @param leftKeyValue 		set the keybinding for left-turns
	 * @param rightKeyValue 	set the keybinding for right-turns
	 */
	public void setKeyBindings(int leftKeyValue, int rightKeyValue) {
		this.leftKeyValue = leftKeyValue;
		this.rightKeyValue = rightKeyValue;
	}
	
	/**
	 * Sets the alive-variable for the snake of the player
	 *
	 * @param isAlive 	if the snake is alive or not
	 */
	public void setSnakeAlive(boolean isAlive) {
		snake.setAlive(isAlive);
	}
	
	/**
	 * @param diedThisRound 	if the snake died this turn
	 */
	public void killedThisRound(boolean diedThisRound) {
		this.diedThisRound = diedThisRound;
	}
	
	/**
	 * Calls the move-function of the snake
	 * The direction is pre-determined
	 */
	public void move() {
		snake.move();
		
		alreadyTurned = false; // enables turning again
	}

	/**
	 * Sets a new direction for the snake
	 * Makes a "turn"
	 * 
	 * @param direction
	 */
	public void turn(int direction) {		
		if(!alreadyTurned) { // prevents the snake to turn into itself
			snake.setDirection(direction);
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
	 * @param points 	the amount of points to be added for the player
	 */
	public void addPoints(int points) {
		this.points += points;
	}

	/**
	 * @return	the direction of the snake
	 */
	public int getSnakeDirection() {
		return snake.getDirection();
	}
	
	/**
	 * @return 	the number of points the player have
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @return 	the color of the players snake
	 */
	public Color getColor() {
		return snake.getColor();
	}
	
	/**
	 * @param index 	the index of the snake-part
	 * @return 			the snakepart that matches the given index
	 */
	public SnakePart getSnakePart(int index) {
		return snake.getSnakePart(index);
	}
	
	/**
	 * @return length of the snake measured in parts
	 */
	public int getSnakeLength() {
		return snake.getSnakeLength();
	}
	
	/**
	 * @return 	if the snake for the player is alive (or not)
	 */
	public boolean isSnakeAlive() {
		return snake.isAlive();
	}
	
	/**
	 * Method used for helping giving points to players
	 * 
	 * @return if the snake died during the last movement-phase
	 */
	public boolean didJustDie() {
		return diedThisRound;
	}
	
	/**
	 * @return 	the key binded to the left-turn
	 */
	public int getLeftKeyValue() {
		return leftKeyValue;
	}
	
	/**
	 * @return 	the key binded to the right-turn
	 */
	public int getRightKeyValue() {
		return rightKeyValue;
	}
	 
	public static void main(String[] args) {
		System.out.println("Compiling SnakePlayer");
	}
}
