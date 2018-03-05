package ac.uk.brunel.cs17pml;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class TaskManager {

	private String hex; // user input value
	private int dec; // decimal value to be converted from input
	private String binary; // binary String to be converted from decimal
	private Finch finch; // the Finch to use
	int speedometer; // Store the speed value
	int[] rgb; // Store the RGB values in an array

	public TaskManager(String hex) {
		this.hex = hex;
		this.dec = Converter.hexToDec(this.hex);
		this.binary = Converter.decToBinary(this.dec);
		this.speedometer = speedManager();
		this.rgb = RGBManager();
		start();
	}
	
	/**
	 *Get the decimal value.
	 * @return the decimal value
	 */
	private int getDec() {
		return this.dec;
	}
	
	/**
	 *Get the decimal's binary format.
	 * @return the binary format
	 */
	private String getBinary() {
		return this.binary;
	}
	
	/**
	 *Get the right speed.
	 *If the decimal is less than 80, it will return the decimal +30;
	 *Else, the actual decimal value.
	 */
	private int speedManager() {
		if(getDec()<80) {
			return getDec()+30;
		}
		return getDec();
	}
	
	/**
	 *Get and compute RGB values.
	 * @return RGB values in an array of length 3; each position from 0 to 2 has red, green and blue values, in order;
	 */
	private int[] RGBManager() {
		int red, green, blue;
		red = getDec(); // Red value is set to be the decimal value
		green = (red%80)+60; // Green value is (red value modulo 80) +60
		blue = (red+green)/2; // Blue value equals the average of red and green
		return new int[] {red, green, blue}; // Each value on one position in the array
	}
	
	private void start() {
		JOptionPane.showMessageDialog // Let the user know what values have been computed
		(null,
				"Initial Input Is " + this.hex + "\n"
				+ "Input To Decimal Equals " + getDec() + "\n"
				+ "Decimal To Binary Equals " + getBinary() + "\n"
				+ "Wheels Speed Equals " + this.speedometer + "\n"
				+ "LED RGB(" + this.rgb[0] + ", " + this.rgb[1] + ", " + this.rgb[2] + ")",
		"Settings", JOptionPane.INFORMATION_MESSAGE);
		// Let the user know finch's connection is now being handled by the program
		JOptionPane.showMessageDialog
		(null,
				"Connecting to Finch... This may take a few seconds...",
				"Hardware Connection",
		JOptionPane.WARNING_MESSAGE);
		finch = new Finch();
		finch.setLED(this.rgb[0], this.rgb[1], this.rgb[2]); // Set the LED color using the RGB values from rgb[] array
		for(int i=0; i<getBinary().length(); i++) { // Iterate over the binary format to check each position for 0 or 1 values
			if(getBinary().charAt(i) == '0') { // If the value is 0... Then...
				finch.setWheelVelocities(-speedometer, -speedometer, 2000); // Set finch's speed to negative to move backwards
			} else { // Otherwise...
				finch.setWheelVelocities(+speedometer, +speedometer, 2000); // Set the finch's speed to positive to move forward
			}
		}
		
		finch.setLED(0, 0, 0); // Switch of finch's beak LED by seting all RGB values to 0
		finch.quit(); // Exit finch connection
		// Let the user know the program is now terminating...
		JOptionPane.showMessageDialog(null, "The program is now terminating...", "Finish", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0); // Terminates the program
	}

}
