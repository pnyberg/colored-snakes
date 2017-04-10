/**
 * The frame where the snakegame is played
 * 
 * @author Per Nyberg
 * @version 2013.10.17
 * @last_updated 2017.04.10
 */

//package originalSnake;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnakeFrame extends JFrame implements ActionListener {
	private final int frameWidth = 370;
	private final int frameHeight = 330;
	private final int adjustmentX = 20;
	private final int adjustmentY = 20;
	private final int boardWidth = 20;
	private final int boardHeight = 20;

	private SnakePanel snakePanel;
	private JPanel buttonPanel;
	private JButton startButton, pauseButton, keyButton;
	private boolean gameStarted;

	/**
	 * Creates the panel for the snakegame and it's belonging buttons
	 */
	public SnakeFrame() {
		snakePanel = new SnakePanel(adjustmentX, adjustmentY, boardWidth, boardHeight);

		buttonPanel = new JPanel();
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		keyButton = new JButton("Change keybindings");

		gameStarted = false;

		pauseButton.setEnabled(false);

		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		keyButton.addActionListener(this);

		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(keyButton);

		add(snakePanel);

		add(BorderLayout.PAGE_END, buttonPanel);

		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * @param leftKeyValue 	the key binded for left turns
	 * @param rightKeyValue	the key binded for right turns
	 */
	public void setKeybindings(int leftKeyValue, int rightKeyValue) {
		snakePanel.setKeyBindings(leftKeyValue, rightKeyValue);
	}

	/**
	 * @return value of the "turn left"-key
	 */
	public int getLeftKeyValue() {
		return snakePanel.getLeftKeyValue();
	}
	
	/**
	 * @return value of the "turn right"-key
	 */
	public int getRightKeyValue() {
		return snakePanel.getRightKeyValue();
	}
	
	/**
	 * For every "action" that should be done
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			snakePanel.newGame();
			pauseButton.setEnabled(true);
			gameStarted = true;
		}

		// pauses the game (renamed the pause-button)
		if(e.getSource() == pauseButton && snakePanel.isSnakeAlive() && gameStarted) {
			snakePanel.handlePause();
			pauseButton.setText((snakePanel.isGamePaused() ? "Unpause" : "Pause"));
		}
		
		// creates a temporary keybBindingMenu (should be trashed when done with)
		if(e.getSource() == keyButton) {
			SnakeKeybindingMenu snakeKeybindingMenu = new SnakeKeybindingMenu(this);
		}
	}

	public static void main(String[] args) {
		new SnakeFrame();
	}
}
