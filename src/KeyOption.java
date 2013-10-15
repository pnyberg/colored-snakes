
public class KeyOption {
	// value of the key
	private int keyValue;
	// the text representing the key
	private String keyText;
	
	/**
	 * Create a KeyOption-object
	 * @param keyValue
	 * @param keyText
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
		// TODO Auto-generated method stub

	}

}
