package ac.uk.brunel.cs17pml;

import javax.swing.JOptionPane;

public class MainClass {
	
	/*
	 *First method to be executed
	 **/
	public static void main(String[] args0) {
		JOptionPane.showMessageDialog(null, "The program will now begin...", "Startup", JOptionPane.INFORMATION_MESSAGE);
		input();
	}
	
	/**
	 *Input is called recursively if any values given by user are not valid
	 */
	private static void input() {
		String result = JOptionPane.showInputDialog(null, "Please type a Hexadecimal value to start... ", "Awating for input... ", JOptionPane.PLAIN_MESSAGE);
		// If the user presses cancel or closes the form, the input will be null so the program terminates
		if(result == null) {
			System.exit(0);
		} else if(result.length() == 2 && result.matches("[0-9A-Fa-f]{2}")) { // Check if the length is 2 and the format is within Hexadecimal rules
			new TaskManager(result);
		} else { // If the format is invalid, let the user know what format is considered to be correct
			JOptionPane.showMessageDialog(null, "It must have exactly 2 characters of\n"
					+ "(a) positive digits\n"
					+ "(b) characters from A-F lower or uppercase", "Invalid Input Error", JOptionPane.WARNING_MESSAGE);
			input(); // attempt for new input by recursive call
		}
	}
}
