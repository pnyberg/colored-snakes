import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeFrame extends JFrame implements ActionListener {
	// the panel for the snake-game
	private SnakePanel snakePanel;
	// panel for the button
	private JPanel buttonPanel;
	// the button to start the game
	private JButton startButton;
	// the button to pause the game
	private JButton pauseButton;
	// the button to get to the menu where you can change keybindings
	private JButton keyButton;

	/**
	 * Constructs the frame for the snake game
	 */
	public SnakeFrame() {
		snakePanel = new SnakePanel(20,20,20,15);
		buttonPanel = new JPanel();
		startButton = new JButton("Start");
		pauseButton = new JButton("Pause");
		keyButton = new JButton("Change keybindings");

		startButton.addActionListener(this);
		pauseButton.addActionListener(this);
		keyButton.addActionListener(this);

		buttonPanel.add(startButton);
		buttonPanel.add(pauseButton);
		buttonPanel.add(keyButton);

		add(snakePanel);
		add(BorderLayout.PAGE_END, buttonPanel);

		setSize(330, 330);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	
	public void setKeybindings(int leftKeyValue, int rightKeyValue) {
		snakePanel.setKeyBindings(leftKeyValue, rightKeyValue);
	}
	
	public int getLeftKeyValue() {
		return snakePanel.getLeftKeyValue();
	}
	
	public int getRightKeyValue() {
		return snakePanel.getRightKeyValue();
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton) {
			snakePanel.newGame();
		}
		if(e.getSource() == pauseButton && snakePanel.isSnakeAlive()) {
			snakePanel.pause();
			pauseButton.setText((snakePanel.isGamePaused() ? "Unpause" : "Pause"));
		}
		if(e.getSource() == keyButton) {
			SnakeKeybindingMenu snakeKeybindingMenu = new SnakeKeybindingMenu(this);
			// show keybinding-menu
		}
	}

	/**
	 * Testing ground, what up?
	 */
	public static void main(String[] args) {
		new SnakeFrame();
	}
}
