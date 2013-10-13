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

JPanel buttonPane;
	/**
	 * Constructs the frame for the snake game
	 */
	public SnakeFrame() {
		snakePanel = new SnakePanel();
		buttonPanel = new JPanel();
		startButton = new JButton("start");
		
		buttonPanel.add(startButton);

		add(snakePanel);
		add(buttonPanel);

		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		
	}

	/**
	 * Testing ground, what up?
	 */
	public static void main(String[] args) {
		new SnakeFrame();
	}
}
