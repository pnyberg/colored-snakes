package multiplayerSnake;

import originalSnake.KeyOption;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiplayerSnakeKeybindingMenu  extends JFrame implements ActionListener {
	// the panel where the game takes place
	private MultiplayerSnakeFrame snakeFrame;
	// a list of possible KeyBindings
	private LinkedList<KeyOption> keyOptions;
	//
	private JPanel panel;
	//
	private JPanel buttonPane;
	//
	private JPanel snakePlayerPane;
	//
	private JPanel leftKeyPane;
	//
	private JPanel rightKeyPane;
	//
	private JLabel snakePlayerLabel;
	//
	private JLabel leftKeyLabel;
	//
	private JLabel rightKeyLabel;
	// to exit from the menu-window
	private JButton exitButton;
	// alternativ för
	private JComboBox<String> snakePlayerString;
	// list of names of the keybindings for the right
	private JComboBox<String> leftKeyString;
	//
	private JComboBox<String> rightKeyString;
	// 
	private int numberOfPlayers;

	public MultiplayerSnakeKeybindingMenu(MultiplayerSnakeFrame snakeFrame, int numberOfPlayers) {
		// the parameters give the frame so the keybindings can be changed
		this.snakeFrame = snakeFrame;
		
		this.numberOfPlayers = numberOfPlayers;

		keyOptions = new LinkedList<KeyOption>();
		
		// creates some panels
		panel = new JPanel();
		buttonPane = new JPanel();
		snakePlayerPane = new JPanel();
		leftKeyPane = new JPanel();
		rightKeyPane = new JPanel();
		
		// create some labels
		snakePlayerLabel = new JLabel("Choose player");
		leftKeyLabel = new JLabel("Left Key");
		rightKeyLabel = new JLabel("Right Key");
		
		// create an exit-button
		exitButton = new JButton("Done");
		
		// create some comob-boxes
		snakePlayerString = new JComboBox<String>();
		leftKeyString = new JComboBox<String>();
		rightKeyString = new JComboBox<String>();
		
		/**
		 * Varför måste denna ligga före addActionListener?
		 * - Går på actionPerformed innan initOptions?
		 */
		initOptions();

		// makes the objects listens for actions
		snakePlayerString.addActionListener(this);
		leftKeyString.addActionListener(this);
		rightKeyString.addActionListener(this);
		exitButton.addActionListener(this);
		
		// set the layout of 'panel' to vertical boxlayout
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// adds the boxes and the buttons to their respective panel
		snakePlayerPane.add(snakePlayerString);
		leftKeyPane.add(leftKeyString);
		rightKeyPane.add(rightKeyString);
		buttonPane.add(exitButton);

		// adds the panels and the labels to 'panel'
		panel.add(snakePlayerLabel);
		panel.add(snakePlayerPane);
		panel.add(leftKeyLabel);
		panel.add(leftKeyPane);
		panel.add(rightKeyLabel);
		panel.add(rightKeyPane);
		panel.add(buttonPane);

		// puts the panel in the frame
		add(panel);
		
		// set size, visible and what happends when the frame is closed
		setSize(150,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public void initOptions() {
		keyOptions.add(new KeyOption(KeyEvent.VK_A, "A"));
		keyOptions.add(new KeyOption(KeyEvent.VK_B, "B"));
		keyOptions.add(new KeyOption(KeyEvent.VK_C, "C"));
		keyOptions.add(new KeyOption(KeyEvent.VK_D, "D"));
		keyOptions.add(new KeyOption(KeyEvent.VK_E, "E"));
		keyOptions.add(new KeyOption(KeyEvent.VK_F, "F"));
		keyOptions.add(new KeyOption(KeyEvent.VK_G, "G"));
		keyOptions.add(new KeyOption(KeyEvent.VK_H, "H"));
		keyOptions.add(new KeyOption(KeyEvent.VK_I, "I"));
		keyOptions.add(new KeyOption(KeyEvent.VK_J, "J"));
		keyOptions.add(new KeyOption(KeyEvent.VK_K, "K"));
		keyOptions.add(new KeyOption(KeyEvent.VK_L, "L"));
		keyOptions.add(new KeyOption(KeyEvent.VK_M, "M"));
		keyOptions.add(new KeyOption(KeyEvent.VK_N, "N"));
		keyOptions.add(new KeyOption(KeyEvent.VK_O, "O"));
		keyOptions.add(new KeyOption(KeyEvent.VK_P, "P"));
		keyOptions.add(new KeyOption(KeyEvent.VK_Q, "Q"));
		keyOptions.add(new KeyOption(KeyEvent.VK_R, "R"));
		keyOptions.add(new KeyOption(KeyEvent.VK_S, "S"));
		keyOptions.add(new KeyOption(KeyEvent.VK_T, "T"));
		keyOptions.add(new KeyOption(KeyEvent.VK_U, "U"));
		keyOptions.add(new KeyOption(KeyEvent.VK_V, "V"));
		keyOptions.add(new KeyOption(KeyEvent.VK_W, "W"));
		keyOptions.add(new KeyOption(KeyEvent.VK_X, "X"));
		keyOptions.add(new KeyOption(KeyEvent.VK_Y, "Y"));
		keyOptions.add(new KeyOption(KeyEvent.VK_Z, "Z"));
		keyOptions.add(new KeyOption(KeyEvent.VK_0, "0"));
		keyOptions.add(new KeyOption(KeyEvent.VK_1, "1"));
		keyOptions.add(new KeyOption(KeyEvent.VK_2, "2"));
		keyOptions.add(new KeyOption(KeyEvent.VK_3, "3"));
		keyOptions.add(new KeyOption(KeyEvent.VK_4, "4"));
		keyOptions.add(new KeyOption(KeyEvent.VK_5, "5"));
		keyOptions.add(new KeyOption(KeyEvent.VK_6, "6"));
		keyOptions.add(new KeyOption(KeyEvent.VK_7, "7"));
		keyOptions.add(new KeyOption(KeyEvent.VK_8, "8"));
		keyOptions.add(new KeyOption(KeyEvent.VK_9, "9"));
		keyOptions.add(new KeyOption(KeyEvent.VK_SLASH, "/"));
		keyOptions.add(new KeyOption(KeyEvent.VK_PLUS, "+"));
		keyOptions.add(new KeyOption(KeyEvent.VK_MINUS, "-"));
		keyOptions.add(new KeyOption(KeyEvent.VK_ASTERISK, "*"));
		keyOptions.add(new KeyOption(KeyEvent.VK_COMMA, ","));
		keyOptions.add(new KeyOption(KeyEvent.VK_CONTROL, "Ctrl"));
		keyOptions.add(new KeyOption(KeyEvent.VK_ALT, "Alt"));
		keyOptions.add(new KeyOption(KeyEvent.VK_DELETE, "Del"));
		keyOptions.add(new KeyOption(KeyEvent.VK_ESCAPE, "Esc"));
		keyOptions.add(new KeyOption(KeyEvent.VK_UP, "Up Arrow"));
		keyOptions.add(new KeyOption(KeyEvent.VK_RIGHT, "Right Arrow"));
		keyOptions.add(new KeyOption(KeyEvent.VK_DOWN, "Down Arrow"));
		keyOptions.add(new KeyOption(KeyEvent.VK_LEFT, "Left Arrow"));
		
		// add som items to the "leftKey"-box and the "rightKey"-box

		for (int i = 0 ; i < numberOfPlayers ; i++)
			snakePlayerString.addItem("" + (i + 1) + "");
		
		for (KeyOption item : keyOptions) {
			leftKeyString.addItem(item.getText());
			rightKeyString.addItem(item.getText());
		}
		
		snakePlayerString.setSelectedIndex(0);

		updateKeyComboBoxes();
	}
	
	public void updateKeyComboBoxes() {
		int leftValue = snakeFrame.getLeftKeyValue(snakePlayerString.getSelectedIndex());
		for (int i = 0 ; i < keyOptions.size() ; i++) {
			if (keyOptions.get(i).getValue() == leftValue)
				leftKeyString.setSelectedIndex(i);
		}
			
		int rightValue = snakeFrame.getRightKeyValue(snakePlayerString.getSelectedIndex());
		for (int i = 0 ; i < keyOptions.size() ; i++) {
			if (keyOptions.get(i).getValue() == rightValue)
				rightKeyString.setSelectedIndex(i);
		}
	}
	
	/**
	 * 
	 */
	public void checkValidKeys() {
		// if the left- and the right-key are the same, if
		// so the exit-button should not be clickable
		if (leftKeyString.getSelectedIndex() == rightKeyString.getSelectedIndex())
			exitButton.setEnabled(false);
		else
			exitButton.setEnabled(true);

		for (int i = 0 ; i < snakeFrame.getNumberOfPlayers() ; i++) {
			// should not do the check for the player against itself
			if (i == snakePlayerString.getSelectedIndex())
				continue;
			
			// if the chosen key is already in use by some of the 
			// other players the exit-button should not be clickable
			if (snakeFrame.getLeftKeyValue(i) == keyOptions.get(leftKeyString.getSelectedIndex()).getValue())
				exitButton.setEnabled(false);
			if (snakeFrame.getRightKeyValue(i) == keyOptions.get(rightKeyString.getSelectedIndex()).getValue())
				exitButton.setEnabled(false);
		}
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exitButton) {
			snakeFrame.setKeybindings(snakePlayerString.getSelectedIndex(), keyOptions.get(leftKeyString.getSelectedIndex()).getValue(), keyOptions.get(rightKeyString.getSelectedIndex()).getValue());
			setVisible(false);
		}

		if (e.getSource() == snakePlayerString) {
			updateKeyComboBoxes();
		}

		checkValidKeys();
	}
}
