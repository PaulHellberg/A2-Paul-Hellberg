package ac.uk.brunel.cs17pml;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class TaskManager {

	private String hex; // hexadecimal
	private int dec; // decimal
	private String binary; // binary stores a string of 0 and 1
	private Finch finch; // Finch object
	int speedometer; // robot's speed
	int[] rgb; // rgb[0] for red, rgb[1] for green, and rgb[2] for blue

	public TaskManager(String hex) {
		this.hex = hex;
		this.dec = Converter.hexToDec(this.hex);
		this.binary = Converter.decToBinary(this.dec);
		this.speedometer = speedManager();
		this.rgb = RGBManager();
		start();
	}
	
	/**
	 *Getter for the decimal value.
	 * @return an integer as the decimal value
	 */
	private int getDec() {
		return this.dec;
	}
	
	/**
	 *Getter for decimal's binary equivalent
	 * @return a String as the binary format
	 */
	private String getBinary() {
		return this.binary;
	}
	
	/*
	 *SpeedManager returns robot's speed
	 *Decimal +30 - if decimal is less than 80
	 */
	private int speedManager() {
		if(getDec()<80) {
			return getDec()+30;
		}
		return getDec();
	}
	
	/**
	 *RGBManager for each of the RGB values.
	 * @return Red at [0], Green at [1], Blue at [2]
	 */
	private int[] RGBManager() {
		int red, green, blue;
		red = getDec();
		green = (red%80)+60;
		blue = (red+green)/2;
		return new int[] {red, green, blue};
	}
	
	private void start() {
		JOptionPane.showMessageDialog
		(null,
				"Initial Input Is " + this.hex + "\n"
				+ "Input To Decimal Equals " + getDec() + "\n"
				+ "Decimal To Binary Equals " + getBinary() + "\n"
				+ "Wheels Speed Equals " + this.speedometer + "\n"
				+ "LED RGB(" + this.rgb[0] + ", " + this.rgb[1] + ", " + this.rgb[2] + ")",
		"Settings", JOptionPane.INFORMATION_MESSAGE);
		
		finch = new Finch();
		finch.setLED(this.rgb[0], this.rgb[1], this.rgb[2]);
		
		for(int i=0; i<getBinary().length(); i++) {
			if(getBinary().charAt(i) == '0') { //getBinary() returns a string, each character is either 0 or 1
				finch.setWheelVelocities(-speedometer, -speedometer, 2000); // Set finch's speed to negative to move backwards
			} else {
				finch.setWheelVelocities(+speedometer, +speedometer, 2000); // Set the finch's speed to positive to move forward
			}
		}
		
		finch.setLED(0, 0, 0);
		finch.quit();
		JOptionPane.showMessageDialog(null, "The program is now terminating...", "Finish", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0); // The entire program terminates here
	}

}
