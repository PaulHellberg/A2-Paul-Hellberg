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
			if(getBinary().charAt(i) == '0') {
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
