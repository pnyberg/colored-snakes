package multiplayerSnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The frame where the snakegame is played
 * 
 * TODO:
 * 		- Lägga in labels som räknar antalet bitar av snaken
 * 		- Lägga in en till spelare
 * 		- Lägga in ändringar av keybindings
 * 		- Lägga in keybinding för att starta om spelet (parallellt med "newGame")
 * 
 * @author Per Nyberg
 * @version 2013.10.17
 */
public class MultiplayerSnakeFrame extends JFrame implements ActionListener {
	// the panel for the snake-game
	private MultiplayerSnakePanel snakePanel;
	//
	private JPanel buttonPanel;
	// the button to start the game
	private JButton startButton;
	// the button to pause the game
	private JButton pauseButton;
	// the button to get to the menu where you can change keybindings
	private JButton keyButton;
	// 
	private int numberOfPlayers;

	/**
	 * Creates the panel for the snakegame and it's belonging buttons
	 */
	public MultiplayerSnakeFrame(int numberOfPlayers) {
		// sätter panelens dimensioner (förskjuten i x-led, förskjuten i y-led, bredd, höjd)
		snakePanel = new MultiplayerSnakePanel(numberOfPlayers, 20,20,20,15);

		buttonPanel = new JPanel();
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		keyButton = new JButton("Change keybindings");
		
		this.numberOfPlayers = numberOfPlayers;

		// gör att knapparna kan lyssna på tryckningar
		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		keyButton.addActionListener(this);

		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(keyButton);

		add(snakePanel);
		// lägger panelen längst ner i framet
		add(BorderLayout.PAGE_END, buttonPanel);

		setSize(330, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * @param leftKeyValue
	 * @param rightKeyValue
	 */
	public void setKeybindings(int playerNumber, int leftKeyValue, int rightKeyValue) {
		snakePanel.setKeyBindingsForPlayer(playerNumber, leftKeyValue, rightKeyValue);
	}

	/**
	 * @return
	 */
	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @return value of the "turn left"-key
	 */
	public int getLeftKeyValue(int playerNumber) {
		return snakePanel.getLeftKeyValue(playerNumber);
	}
	
	/**
	 * @return value of the "turn right"-key
	 */
	public int getRightKeyValue(int playerNumber) {
		return snakePanel.getRightKeyValue(playerNumber);
	}
	
	/**
	 * For every "action" that should be done
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			snakePanel.newGame();
		}

		// pauses the game (renamed the pause-button)
		if(e.getSource() == pauseButton && !snakePanel.isMaxOneSnakeAlive()) {
			snakePanel.pause();
			pauseButton.setText((snakePanel.isGamePaused() ? "Unpause" : "Pause"));
		}
		
		// creates a temporary keybBindingMenu (should be trashed when done with)
		if(e.getSource() == keyButton) {
			MultiplayerSnakeKeybindingMenu multiplayerSnakeKeybindingMenu = new MultiplayerSnakeKeybindingMenu(this, numberOfPlayers);
		}
	}

	/**
	 * Testing ground, what up?
	 * Not a part of the gamecode
	 */
	public static void main(String[] args) {
		new MultiplayerSnakeFrame(7);
	}
}