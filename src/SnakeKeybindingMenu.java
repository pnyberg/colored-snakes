import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SnakeKeybindingMenu extends JFrame implements ActionListener {
	//
	private SnakeFrame snakeFrame;
	//
	private LinkedList<KeyOption> keyOptions;
	//
	private JPanel panel;
	//
	private JPanel buttonPane;
	//
	private JPanel leftKeyPane;
	//
	private JPanel rightKeyPane;
	//
	private JLabel leftKeyLabel;
	//
	private JLabel rightKeyLabel;
	// when you're done
	private JButton exitButton;
	//
	private JComboBox<String> leftKeyString;
	//
	private JComboBox<String> rightKeyString;

	public SnakeKeybindingMenu(SnakeFrame snakeFrame) {
		// the parameters give the frame so the keybindings can be changed
		this.snakeFrame = snakeFrame;

		keyOptions = new LinkedList<KeyOption>();
		
		// creates some panels
		panel = new JPanel();
		buttonPane = new JPanel();
		leftKeyPane = new JPanel();
		rightKeyPane = new JPanel();
		
		// create some labels
		leftKeyLabel = new JLabel("Left Key");
		rightKeyLabel = new JLabel("Right Key");
		
		// create an exit-button
		exitButton = new JButton("Done");
		
		// create some comob-boxes
		leftKeyString = new JComboBox<String>();
		rightKeyString = new JComboBox<String>();
		
		// makes the objects listens for actions
		leftKeyString.addActionListener(this);
		rightKeyString.addActionListener(this);
		exitButton.addActionListener(this);
		
		initOptions();

		// set the layout of 'panel' to vertical boxlayout
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		// adds the boxes and the buttons to their respective panel
		leftKeyPane.add(leftKeyString);
		rightKeyPane.add(rightKeyString);
		buttonPane.add(exitButton);

		// adds the panels and the labels to 'panel'
		panel.add(leftKeyLabel);
		panel.add(leftKeyPane);
		panel.add(rightKeyLabel);
		panel.add(rightKeyPane);
		panel.add(buttonPane);

		// puts the panel in the frame
		add(panel);
		
		// set size, visible and what happends when the frame is closed
		setSize(120,200);
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
		
		// add som items to the "leftKey"-box and the "rightKey"-box

		for (KeyOption item : keyOptions) {
			leftKeyString.addItem(item.getText());
			rightKeyString.addItem(item.getText());
		}

		int leftValue = snakeFrame.getLeftKeyValue();
		for (int i = 0 ; i < keyOptions.size() ; i++) {
			if (keyOptions.get(i).getValue() == leftValue)
				leftKeyString.setSelectedIndex(i);
		}
			
		int rightValue = snakeFrame.getRightKeyValue();
		for (int i = 0 ; i < keyOptions.size() ; i++) {
			if (keyOptions.get(i).getValue() == rightValue)
				rightKeyString.setSelectedIndex(i);
		}
	}
	
	public void checkSameKeys() {
		if(leftKeyString.getSelectedIndex() == rightKeyString.getSelectedIndex())
			exitButton.setEnabled(false);
		else
			exitButton.setEnabled(true);
	}
	
	/**
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitButton) {
			snakeFrame.setKeybindings(keyOptions.get(leftKeyString.getSelectedIndex()).getValue(), keyOptions.get(rightKeyString.getSelectedIndex()).getValue());
			setVisible(false);
		}
		
		checkSameKeys();
	}
}
