/**
 * A class to save the keys with the name and 
 * the keycode for the key
 * 
 * @author Per Nyberg
 * @version 2013.10.16
 */

//package originalSnake;

public class KeyOption {
	// value of the key (KeyEvent.[keyValue])
	private int keyValue;
	// the text representing the key
	private String keyText;
	
	/**
	 * Create a KeyOption-object with a 
	 */
	public KeyOption(int keyValue, String keyText) {
		this.keyValue = keyValue;
		this.keyText = keyText;
	}
	
	/**
	 * @return text of the KeyOption
	 */
	public String getText() {
		return keyText;
	}

	/**
	 * @return value of the KeyOption
	 */
	public int getValue() {
		return keyValue;
	}

	public static void main(String[] args) {
		System.out.println("Compiling KeyOption");
	}
}
