package ac.uk.brunel.cs17pml;

public class Converter {
	
	/**
	 * Hexadecimal to Decimal converter
	 * @param hexadecimal value to be converted
	 * @return decimal value
	 */
	public static int hexToDec(String hex) {
		
		int decOut; // variable storing the value to return
		// hexArray[i] -> hexadecimal
		// i, as index -> hexadecimal's decimal equivalent
		// A to F have all the indexes equal to their decimal
		String hexArray = "0123456789ABCDEF";
		hex = hex.toUpperCase();
		decOut = hexArray.indexOf(hex.charAt(1)); // get the decimal(i, as index) by matching the second char from hexArray
		decOut += hexArray.indexOf(hex.charAt(0))*16; // sum up the decimal(i, as index) by matching the first char from hexArray
		return decOut;
		
	}
	
	/**
	 * Decimal to Binary converter
	 * @param decimal value to be converted
	 * @return String of 1 and 0
	 */
	public static String decToBinary(int dec) {
		
		String bin = "";
		while(dec != 0) {
			bin += String.valueOf(dec%2);
			dec /= 2;
		}
		return new StringBuilder(bin).reverse().toString(); // reverse string on return
	}
}
