/**
 * The panel on which the snake is painted (and moved)
 * 
 * @author Per Nyberg
 * @version 2013.10.10
 */

//package originalSnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakePanel extends JPanel implements ActionListener {
	// the player which controlls the snake
	private SnakePlayer snakePlayer;
	// the timer that updates the program (every 100 ms)
	private Timer timer;
	// The item that makes the snake grow
	private SnakeGrowthItem growthItem;
	// 
	private int width;
	// 
	private int height;
	// 
	private int repositioningX;
	//
	private int repositioningY;

	/**
	 * Constructor Creating the timer with a "delay" on 150 ms The snake starts
	 * at the position 0,0 and starts moving down A keylistener is added to
	 * enabled the player to control the movement of the snake
	 */
	public SnakePanel(int width, int height, int repositioningX, int repositioningY) {
		timer = new Timer(150, this);
		
		this.width = width;
		this.height = height;
		this.repositioningX = repositioningX;
		this.repositioningY = repositioningY;

		init();
		
		addKeyListener(k1);

		repaint();
	}
	
	/**
	 * Initiates the snake
	 */
	public void init() {
		int snakeX = 2 + (int)(Math.random() * (width-4));
		int snakeY = 2 + (int)(Math.random() * (height-4));
		int snakeDir = (int)(Math.random()*4);
		
		if(snakeDir == 0 && snakeY <= 4)
			snakeDir = 2;
		else if(snakeDir == 1 && snakeX >= (width-4))
			snakeDir = 3;
		else if(snakeDir == 2 && snakeY >= (height-4))
			snakeDir = 0;
		else if(snakeDir == 3 && snakeX <= 4)
			snakeDir = 1;
		
		growthItem = new SnakeGrowthItem((int) (Math.random() * width),
				(int) (Math.random() * height));

		snakePlayer = new SnakePlayer(snakeX, snakeY, snakeDir);
		
		snakePlayer.setSnakePositionBase(repositioningX, repositioningY);
		growthItem.setPositionBase(repositioningX, repositioningY);
		
		setGrowthItemPosition();
	}

	public void setKeyBindings(int leftKeyValue, int rightKeyValue) {
		snakePlayer.setKeyBindings(leftKeyValue, rightKeyValue);
	}

	/**
	 * Starts a new game (resets the old one if there was any)
	 */
	public void newGame() {
		int snakeX = 2 + (int)(Math.random() * (width-4));
		int snakeY = 2 + (int)(Math.random() * (height-4));
		int snakeDir = (int)(Math.random()*4);
		
		if(snakeDir == 0 && snakeY <= 4)
			snakeDir = 2;
		else if(snakeDir == 1 && snakeX >= (width-4))
			snakeDir = 3;
		else if(snakeDir == 2 && snakeY >= (height-4))
			snakeDir = 0;
		else if(snakeDir == 3 && snakeX <= 4)
			snakeDir = 1;
		
		
		snakePlayer.resetSnake(snakeX, snakeY, snakeDir);
		setGrowthItemPosition();
		timer.start();
	}
	
	public void handlePause() {
		if(timer.isRunning())
			timer.stop();
		else
			timer.start();
	}
	/**
	 * Sets a position for the growth item
	 * Checking that it's not in the snake
	 */
	public void setGrowthItemPosition() {
		while (growthItemInSnake()) {
			growthItem.setX((int) (Math.random() * width));
			growthItem.setY((int) (Math.random() * height));
		}
	}
	
	public boolean isGamePaused() {
		return !timer.isRunning();
	}
	
	public boolean isSnakeAlive() {
		return snakePlayer.isSnakeAlive();
	}

	/**
	 * Method for checking if two snakeparts are at the same position
	 * 
	 * @param part1
	 *            , part2 The two snakesparts the method is using
	 */
	public boolean atSamePosition(SnakePart part1, SnakePart part2) {
		// if both the x-values and the y-values are the same
		// they are at the same position
		if (part1.getX() == part2.getX() && part1.getY() == part2.getY())
			return true;
		else
			return false;
	}

	/**
	 * Checks for collision for the snake
	 * 
	 * @return
	 */
	public boolean checkForCollision() {
		// loops through the players snakeparts
		for (int i = 0; i < snakePlayer.getSnakeLength(); i++) {
			// the program shouldn't check the head against itself
			// since they will always be at the same place (head = index 0)
			if (i == 0)
				continue;
			else if (atSamePosition(snakePlayer.getSnakePart(0),
					snakePlayer.getSnakePart(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if the snake is out of bounds (out of the gameplan)
	 */
	public boolean checkOutOfBounds() {
		if (snakePlayer.getSnakePart(0).getX() > (width-1)
				|| snakePlayer.getSnakePart(0).getX() < 0
				|| snakePlayer.getSnakePart(0).getY() > (height-1)
				|| snakePlayer.getSnakePart(0).getY() < 0)
			return true;
		else
			return false;
	}

	/**
	 * Method to check if the growthitem is in the snake
	 * 
	 * @return
	 */
	public boolean growthItemInSnake() {
		for (int i = 0; i < snakePlayer.getSnakeLength(); i++)
			if (growthItem.getX() == snakePlayer.getSnakePart(i).getX()
					&& growthItem.getY() == snakePlayer.getSnakePart(i).getY())
				return true;

		return false;
	}

	/**
	 * @return leftKeyValue
	 */
	public int getLeftKeyValue() {
		return snakePlayer.getLeftKeyValue();
	}
	
	/**
	 * @return rightKeyValue
	 */
	public int getRightKeyValue() {
		return snakePlayer.getRightKeyValue();
	}
	
	/**
	 * Updates the program Moves the snake and repaints the panel
	 */
	public void actionPerformed(ActionEvent a) {
		// makes sure that the panel has the focus for coming actions (from
		// actionevent)
		requestFocus();

		snakePlayer.move();

		// stops the time of the snake does an illegal move
		if (checkForCollision() || checkOutOfBounds()) {
			timer.stop();
			snakePlayer.setSnakeAlive(false);
		}

		// check if the growth item is in the snakes head (meaning the snake just ate it)
		if (snakePlayer.getSnakePart(0).getX() == growthItem.getX()
				&& snakePlayer.getSnakePart(0).getY() == growthItem.getY()) {
			snakePlayer.growSnake();
		}

		// gives it a new position (if the snake just ate it)
		setGrowthItemPosition();
		
		repaint();
	}

	/**
	 * The keylistener decides depending on current direction the new direction
	 * (based on a "turn left or turn right"- alternative system)
	 */
	KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			// turns "right"
			if (e.getKeyCode() == snakePlayer.getRightKeyValue()) {
				if (snakePlayer.getSnakeDirection() == 0)
					snakePlayer.turn(1);
				else if (snakePlayer.getSnakeDirection() == 1)
					snakePlayer.turn(2);
				else if (snakePlayer.getSnakeDirection() == 2)
					snakePlayer.turn(3);
				else if (snakePlayer.getSnakeDirection() == 3)
					snakePlayer.turn(0);
			}

			// turns "left"
			if (e.getKeyCode() == snakePlayer.getLeftKeyValue()) {
				if (snakePlayer.getSnakeDirection() == 0)
					snakePlayer.turn(3);
				else if (snakePlayer.getSnakeDirection() == 1)
					snakePlayer.turn(0);
				else if (snakePlayer.getSnakeDirection() == 2)
					snakePlayer.turn(1);
				else if (snakePlayer.getSnakeDirection() == 3)
					snakePlayer.turn(2);
			}
		}
	};

	/**
	 * Paints the panel
	 */
	public void paintComponent(Graphics g) {
		// sets the background to white
		g.setColor(Color.black);
		g.fillRect(0,0,20000,20000); // ridicoulusly big background, for not showing other background
		g.setColor(Color.white);
		g.fillRect(repositioningX, repositioningY, width*10, height*10);

		// paint the growth-item
		growthItem.paint(g);

		// paints on the snake
		snakePlayer.paintSnake(g);

		g.setColor(Color.white);
		g.drawString("Current size of snake: " + snakePlayer.getSnakeLength(), width*5-50, height*10+30);
	}

	public static void main(String[] args) {
		System.out.println("Compiling SnakePanel");
	}
}
