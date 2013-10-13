import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The panel on which the snake is painted (and moved)
 * 
 * @author Per Nyberg
 * @version 2013.10.10
 */
public class SnakePanel extends JPanel implements ActionListener {
	// the player which controlls the snake
	private SnakePlayer snakePlayer;
	// the timer that updates the program (every 100 ms)
	private Timer timer;
	// The item that makes the snake grow
	private SnakeGrowthItem growthItem;

	/**
	 * Constructor Creating the timer with a "delay" on 100 ms The snake starts
	 * at the position 0,0 and starts moving down A keylistener is added to
	 * enabled the player to control the movement of the snake
	 */
	public SnakePanel() {
		timer = new Timer(150, this);

		snakePlayer = new SnakePlayer(0, 0, "down");
		growthItem = new SnakeGrowthItem((int) (Math.random() * 10),
				(int) (Math.random() * 10));

		snakePlayer.setSnakePositionBase(10,10);
		growthItem.setPositionBase(10,10);

		timer.start();

		addKeyListener(k1);

		repaint();
	}

	/**
	 * 
	 */
	public boolean atSamePosition(SnakePart part1, SnakePart part2) {
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
		for (int i = 0; i < snakePlayer.getSnakeLength(); i++)
			if (i == 0)
				continue;
			else if (atSamePosition(snakePlayer.getSnakePart(0),
					snakePlayer.getSnakePart(i))) {
				return true;
			}
		return false;
	}
	
	/**
	 * 
	 */
	public boolean checkOutOfBounds() {
		if(snakePlayer.getSnakePart(0).getX() > 19 ||
			snakePlayer.getSnakePart(0).getX() < 0 ||
			snakePlayer.getSnakePart(0).getY() > 19 ||
			snakePlayer.getSnakePart(0).getY() < 0)
			return true;
		else
			return false;
	}
	
	public boolean growthItemInSnake() {
		for(int i = 0 ; i < snakePlayer.getSnakeLength() ; i++)
			if (growthItem.getX() == snakePlayer.getSnakePart(i).getX() && 
				growthItem.getY() == snakePlayer.getSnakePart(i).getY())
				return true;

		return false;
	}

	/**
	 * Updates the program Moves the snake and repaints the panel
	 */
	public void actionPerformed(ActionEvent a) {
		requestFocus();

		snakePlayer.move();
		
		if(checkForCollision())
			timer.stop();
		if(checkOutOfBounds())
			timer.stop();

		if (snakePlayer.getSnakePart(0).getX() == growthItem.getX()
				&& snakePlayer.getSnakePart(0).getY() == growthItem.getY()) {
			snakePlayer.growSnake();
		}
		while(growthItemInSnake()) {
				growthItem.setX((int) (Math.random() * 10));
				growthItem.setY((int) (Math.random() * 10));
		}
		repaint();
	}

	/**
	 * The keylistener decides depending on current direction the new direction
	 * (based on a "turn left or turn right"- alternative system)
	 */
	KeyListener k1 = new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			// turns "right"
			if (e.getKeyChar() == 's') {
				if (snakePlayer.getSnakeDirection().equals("up"))
					snakePlayer.turn("right");
				else if (snakePlayer.getSnakeDirection().equals("right"))
					snakePlayer.turn("down");
				else if (snakePlayer.getSnakeDirection().equals("down"))
					snakePlayer.turn("left");
				else if (snakePlayer.getSnakeDirection().equals("left"))
					snakePlayer.turn("up");
			}

			// turns left
			if (e.getKeyChar() == 'a') {
				if (snakePlayer.getSnakeDirection().equals("up"))
					snakePlayer.turn("left");
				else if (snakePlayer.getSnakeDirection().equals("right"))
					snakePlayer.turn("up");
				else if (snakePlayer.getSnakeDirection().equals("down"))
					snakePlayer.turn("right");
				else if (snakePlayer.getSnakeDirection().equals("left"))
					snakePlayer.turn("down");
			}
		}
	};

	/**
	 * paints the panel
	 */
	public void paintComponent(Graphics g) {
		// sets the background to white
		g.setColor(Color.white);
		g.fillRect(10, 10, 200, 200);
		g.setColor(Color.black);
		g.drawRect(9, 9, 202, 202);

		// paint the growth-item
		growthItem.paint(g);

		// paints on the snake
		snakePlayer.paintSnake(g);
	}
}
