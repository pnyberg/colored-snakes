package multiplayerSnake;

import originalSnake.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;

/**
 * The panel on which the snakes are painted (and moved)
 * 
 * @author Per Nyberg
 * @version 2013.10.17
 */
public class MultiplayerSnakePanel extends JPanel implements ActionListener {
	// list of the playser playing
	private LinkedList<SnakePlayer> snakePlayers;
	// the timer that updates the program (every 100 ms)
	private Timer timer;
	// list of items that makes a snake grow
	private LinkedList<SnakeGrowthItem> growthItems;
	// dimensions of the panel
	private int width;
	private int height;
	private int repositioningX;
	private int repositioningY;
	// number of players playing
	private int numberOfPlayers;
	
	/**
	 * Constructor Creating the timer with a "delay" on 150 ms The snake starts
	 * at the position 0,0 and starts moving down. A keylistener is added to
	 * enabled the players to control the movement of the snake
	 */
	public MultiplayerSnakePanel(int numberOfPlayers, int width, int height, int repositioningX, int repositioningY) {
		timer = new Timer(150, this);
		
		snakePlayers = new LinkedList<SnakePlayer>();
		growthItems = new LinkedList<SnakeGrowthItem>();
		
		this.width = width;
		this.height = height;
		this.repositioningX = repositioningX;
		this.repositioningY = repositioningY;
		
		this.numberOfPlayers = numberOfPlayers;

		init();
		
		addKeyListener(k1);

		repaint();
	}
	
	/**
	 * Initiates the game
	 */
	public void init() {
		createSnakes(numberOfPlayers);
		createGrowthItem();
	}

	/**
	 * Starts a new game (resets the old one if there was any)
	 */
	public void newGame() {
		growthItems.clear();

		resetSnakes();
		createGrowthItem();
		
		timer.start();
		
		repaint();
	}
	
	
	public void pause() {
		if(timer.isRunning())
			timer.stop();
		else
			timer.start();
	}
	
	/**
	 * Creates the snakes for the players
	 */
	public void createSnakes(int numberOfPlayers) {
		int[][] defaultKeyBindings = defaultKeybindings(numberOfPlayers);
		Color[] defaultColors = defaultColors(numberOfPlayers);
		
		for(int i = 0 ; i < numberOfPlayers ; i++) {
			int snakeX = 2 + (int)(Math.random() * (width-4));
			int snakeY = 2 + (int)(Math.random() * (height-4));
			// local variables to help determine direction for snake
			int snakeDirHelp = (int)(Math.random()*4);
			String snakeDir = "";
			
			if(snakeDirHelp == 0)
				snakeDir = "up";
			else if(snakeDirHelp == 1)
				snakeDir = "right";
			else if(snakeDirHelp == 2)
				snakeDir = "down";
			else if(snakeDirHelp == 3)
				snakeDir = "left";
			
			if(snakeDir.equals("up") && snakeY <= 4)
				snakeDir = "down";
			else if(snakeDir.equals("right") && snakeX >= (width-4))
				snakeDir = "left";
			else if(snakeDir.equals("down") && snakeY >= (height-4))
				snakeDir = "up";
			else if(snakeDir.equals("left") && snakeX <= 4)
				snakeDir = "right";
			
			snakePlayers.add(new SnakePlayer(snakeX, snakeY, snakeDir, defaultKeyBindings[i][0], defaultKeyBindings[i][1], defaultColors[i]));
			snakePlayers.getLast().setSnakePositionBase(repositioningX, repositioningY);
		}
		
	}

	/**
	 * 
	 */
	public void resetSnakes() {
		for(int i = 0 ; i < snakePlayers.size() ; i++) {
			int snakeX = 2 + (int)(Math.random() * (width-4));
			int snakeY = 2 + (int)(Math.random() * (height-4));
			// local variables to help determine direction for snake
			int snakeDirHelp = (int)(Math.random()*4);
			String snakeDir = "";
			
			if(snakeDirHelp == 0)
				snakeDir = "up";
			else if(snakeDirHelp == 1)
				snakeDir = "right";
			else if(snakeDirHelp == 2)
				snakeDir = "down";
			else if(snakeDirHelp == 3)
				snakeDir = "left";
			
			if(snakeDir.equals("up") && snakeY <= 4)
				snakeDir = "down";
			else if(snakeDir.equals("right") && snakeX >= (width-4))
				snakeDir = "left";
			else if(snakeDir.equals("down") && snakeY >= (height-4))
				snakeDir = "up";
			else if(snakeDir.equals("left") && snakeX <= 4)
				snakeDir = "right";
			
			if(snakePositionAlreadyTaken(i, snakeX, snakeY, snakeDir)) {
				i--;
				continue;
			}
			
			snakePlayers.get(i).resetSnake(snakeX, snakeY, snakeDir);
		}
	}
	
	/**
	 * All the keybindings put together to make it easier to read
	 * @param numberOfPlayers
	 * @return default keybindings for the players
	 */
	private int[][] defaultKeybindings(int numberOfPlayers) {
		int[][] defaultKeyBindings = new int[numberOfPlayers][2];
		
		if(numberOfPlayers > 0) {
			defaultKeyBindings[0][0] = KeyEvent.VK_A;
			defaultKeyBindings[0][1] = KeyEvent.VK_S;
		}
		if(numberOfPlayers > 1) {
			defaultKeyBindings[1][0] = KeyEvent.VK_J;
			defaultKeyBindings[1][1] = KeyEvent.VK_K;
		}
		if(numberOfPlayers > 2) {
			defaultKeyBindings[2][0] = KeyEvent.VK_V;
			defaultKeyBindings[2][1] = KeyEvent.VK_B;
		}
		if(numberOfPlayers > 3) {
			defaultKeyBindings[3][0] = KeyEvent.VK_LEFT;
			defaultKeyBindings[3][1] = KeyEvent.VK_RIGHT;
		}
		if(numberOfPlayers > 4) {
			defaultKeyBindings[4][0] = KeyEvent.VK_O;
			defaultKeyBindings[4][1] = KeyEvent.VK_P;
		}
		if(numberOfPlayers > 5) {
			defaultKeyBindings[5][0] = KeyEvent.VK_R;
			defaultKeyBindings[5][1] = KeyEvent.VK_T;
		}
		
		if(numberOfPlayers > 6) {
			defaultKeyBindings[6][0] = KeyEvent.VK_X;
			defaultKeyBindings[6][1] = KeyEvent.VK_C;
		}
		
		return defaultKeyBindings;
	}
	
	/**
	 * 
	 */
	private Color[] defaultColors(int numberOfPlayers) {
		Color[] defaultColors = new Color[numberOfPlayers];

		if(numberOfPlayers > 0)
			defaultColors[0] = Color.orange;
		if(numberOfPlayers > 1)
			defaultColors[1] = Color.blue;
		if(numberOfPlayers > 2)
			defaultColors[2] = Color.green;
		if(numberOfPlayers > 3)
			defaultColors[3] = Color.gray;
		if(numberOfPlayers > 4)
			defaultColors[4] = Color.red;
		if(numberOfPlayers > 5)
			defaultColors[5] = Color.magenta;
		if(numberOfPlayers > 6)
			defaultColors[6] = Color.cyan;
		
		return defaultColors;
	}
	
	/**
	 * Sets a position for the growth item
	 * Checking that it's not in the snake
	 */
	public void createGrowthItem() {
		SnakeGrowthItem growthItem = 
				new SnakeGrowthItem((int) (Math.random() * width), (int) (Math.random() * height));

		while (growthItemInAnySnake(growthItem)) {
			growthItem.setX((int) (Math.random() * width));
			growthItem.setY((int) (Math.random() * height));
		}
		
		growthItem.setPositionBase(repositioningX, repositioningY);

		growthItems.add(growthItem);
	}
	
	/**
	 * Checks for collision for the snake
	 * 
	 * @return
	 */
	public boolean checkForCollision(int playerNumber) {
		// loops through the players
		for (int n = 0 ; n < snakePlayers.size() ; n++) {
			// loops through the players snakeparts
			for (int i = 0; i < snakePlayers.get(n).getSnakeLength(); i++) {
				// the program shouldn't check the head against itself
				// since they will always be at the same place (head = index 0)
				if (i == 0 && playerNumber == n)
					continue;
				else if (atSamePosition(snakePlayers.get(playerNumber).getSnakePart(0),
						snakePlayers.get(n).getSnakePart(i))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean snakePositionAlreadyTaken(int playersGoneThrough, int snakeX, int snakeY, String snakeDir) {
		int snakeX2 = -1;
		int snakeX3 = -1;
		int snakeY2 = -1;
		int snakeY3 = -1;
		
		if(snakeDir.equals("up")) {
			snakeX2 = snakeX;
			snakeY2 = snakeY+1;
			snakeX3 = snakeX;
			snakeY3 = snakeY+2;
		}
		else if(snakeDir.equals("right")) {
			snakeX2 = snakeX-1;
			snakeY2 = snakeY;
			snakeX3 = snakeX-2;
			snakeY3 = snakeY;
		}
		else if(snakeDir.equals("down")) {
			snakeX2 = snakeX;
			snakeY2 = snakeY-1;
			snakeX3 = snakeX;
			snakeY3 = snakeY-2;
		}
		else if(snakeDir.equals("left")) {
			snakeX2 = snakeX+1;
			snakeY2 = snakeY;
			snakeX3 = snakeX+2;
			snakeY3 = snakeY;
		}
		
		for(int i = 0 ; i < playersGoneThrough ; i++) {
			for(int n = 0 ; n < snakePlayers.get(i).getSnakeLength() ; n++) {
				int x = snakePlayers.get(i).getSnakePart(n).getX();
				int y = snakePlayers.get(i).getSnakePart(n).getY();

				if(x == snakeX && y == snakeY)
					return true;
				if(x == snakeX2 && y == snakeY2)
					return true;
				if(x == snakeX3 && y == snakeY3)
					return true;
			}
		}
		
		return false;
	}
	/**
	 * Updates the program Moves the snake and repaints the panel
	 */
	public void actionPerformed(ActionEvent a) {
		// makes sure that the panel has the focus for coming actions (from
		// actionevent)
		requestFocus();

		for (int i = 0 ; i < snakePlayers.size() ; i++) {
			if (snakePlayers.get(i).isSnakeAlive()) {
				snakePlayers.get(i).move();
			}
		}

		for (int i = 0 ; i < snakePlayers.size() ; i++) {
			if (snakePlayers.get(i).isSnakeAlive() && (checkForCollision(i) || checkOutOfBounds(i))) {
				snakePlayers.get(i).setSnakeAlive(false);
				snakePlayers.get(i).killedThisRound(true);
			}
		}

		for (int i = 0 ; i < snakePlayers.size() ; i++) {
			if (snakePlayers.get(i).didJustDie()) {
				snakePlayers.get(i).killedThisRound(false);

				for(int n = 0 ; n < snakePlayers.size() ; n++)
					if(snakePlayers.get(n).isSnakeAlive())
						snakePlayers.get(n).addPoint(1);
					
				if(isMaxOneSnakeAlive()) {
					for(int n = 0 ; n < snakePlayers.size() ; n++)
						System.out.println(snakePlayers.get(n).getPoints());
					timer.stop();
				}
			}
		}

		for (int n = 0 ; n < snakePlayers.size() ; n++) {
			for (int i = 0 ; i < growthItems.size() ; i++) {
				// check if the growth item is in the snakes head (meaning the snake just ate it)
				if (snakePlayers.get(n).getSnakePart(0).getX() == growthItems.get(i).getX()
						&& snakePlayers.get(n).getSnakePart(0).getY() == growthItems.get(i).getY()) {
					snakePlayers.get(n).growSnake();
					
					// removes the eaten item
					growthItems.remove(i);
					i--;
					
					// creates a new item if the snake just ate it
					createGrowthItem();
				}
			}
		}

		
		repaint();
	}


	public void setKeyBindingsForPlayer(int playerNumber, int leftKeyValue, int rightKeyValue) {
		snakePlayers.get(playerNumber).setKeyBindings(leftKeyValue, rightKeyValue);
	}

	public boolean isGamePaused() {
		return !timer.isRunning();
	}
	
	public boolean isAnySnakeAlive() {
		for(int i = 0 ; i < snakePlayers.size() ; i++)
			if(snakePlayers.get(i).isSnakeAlive())
				return true;
		return false;
	}
	
	public boolean isMaxOneSnakeAlive() {
		int numberOfSnakesAlive = 0;
		
		for(int i = 0 ; i < snakePlayers.size() ; i++)
			if(snakePlayers.get(i).isSnakeAlive())
				numberOfSnakesAlive++;
		
		return (numberOfSnakesAlive <= 1);
	}
	
	public boolean isPlayersSnakeAlive(int playerNumber) {
		return snakePlayers.get(playerNumber).isSnakeAlive();
	}

	/**
	 * Method for checking if two snakeparts are at the same position
	 * 
	 * @param part1
	 *        part2 The two snakesparts the method is using
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
	 * Method to check if the growthitem is in the snake
	 * 
	 * @return
	 */
	public boolean growthItemInAnySnake(SnakeGrowthItem growthItem) {
		for (int n = 0 ; n < snakePlayers.size() ; n++)
			for (int i = 0; i < snakePlayers.get(n).getSnakeLength(); i++)
				if (growthItem.getX() == snakePlayers.get(n).getSnakePart(i).getX()
					&& growthItem.getY() == snakePlayers.get(n).getSnakePart(i).getY())
					return true;

		return false;
	}

	/**
	 * Method to check if the snake is out of bounds (out of the gameplan)
	 */
	public boolean checkOutOfBounds(int playerNumber) {
		if (snakePlayers.get(playerNumber).getSnakePart(0).getX() > (width-1)
				|| snakePlayers.get(playerNumber).getSnakePart(0).getX() < 0
				|| snakePlayers.get(playerNumber).getSnakePart(0).getY() > (height-1)
				|| snakePlayers.get(playerNumber).getSnakePart(0).getY() < 0)
			return true;
		else
			return false;
	}

	/**
	 * @return leftKeyValue
	 */
	public int getLeftKeyValue(int playerNumber) {
		return snakePlayers.get(playerNumber).getLeftKeyValue();
	}
	
	/**
	 * @return rightKeyValue
	 */
	public int getRightKeyValue(int playerNumber) {
		return snakePlayers.get(playerNumber).getRightKeyValue();
	}
	
	/**
	 * The keylistener decides depending on current direction the new direction
	 * (based on a "turn left or turn right"- alternative system)
	 */
	KeyListener k1 = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			for (int i = 0 ; i < snakePlayers.size() ; i++) {
				// turns "right"
				if (e.getKeyCode() == snakePlayers.get(i).getRightKeyValue()) {
					if (snakePlayers.get(i).getSnakeDirection().equals("up"))
						snakePlayers.get(i).turn("right");
					else if (snakePlayers.get(i).getSnakeDirection().equals("right"))
						snakePlayers.get(i).turn("down");
					else if (snakePlayers.get(i).getSnakeDirection().equals("down"))
						snakePlayers.get(i).turn("left");
					else if (snakePlayers.get(i).getSnakeDirection().equals("left"))
						snakePlayers.get(i).turn("up");
				}
	
				// turns "left"
				if (e.getKeyCode() == snakePlayers.get(i).getLeftKeyValue()) {
					if (snakePlayers.get(i).getSnakeDirection().equals("up"))
						snakePlayers.get(i).turn("left");
					else if (snakePlayers.get(i).getSnakeDirection().equals("right"))
						snakePlayers.get(i).turn("up");
					else if (snakePlayers.get(i).getSnakeDirection().equals("down"))
						snakePlayers.get(i).turn("right");
					else if (snakePlayers.get(i).getSnakeDirection().equals("left"))
						snakePlayers.get(i).turn("down");
				}
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

		// paint the growth-items
		for (int i = 0 ; i < growthItems.size() ; i++)
			growthItems.get(i).paint(g);

		// paints on the snakes
		for (int i = 0 ; i < snakePlayers.size() ; i++)
			snakePlayers.get(i).paintSnake(g);

		g.setColor(Color.white);
		g.drawString("Points:", width*10+40, 15);
		for (int i = 0 ; i < snakePlayers.size() ; i++) {
			g.setColor(snakePlayers.get(i).getColor());
			g.drawString((i + 1) + ": " + snakePlayers.get(i).getPoints(), width*10+50, 15+15*(i+1));
		}
//		g.drawString("Current size of snake: " + snakePlayer.getSnakeLength(), width*5-50, height*10+30);
	}
}