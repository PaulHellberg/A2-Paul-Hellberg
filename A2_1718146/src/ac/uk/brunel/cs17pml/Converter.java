package ac.uk.brunel.cs17pml;

public class Converter {
	
	/**
	 * Hexadecimal to Decimal converter
	 * @param hex
	 * @return an integer as an equivalent for the given hex value
	 */
	public static int hexToDec(String hex) {
		
		int decOut; // variable to store the value to be returned
		// hexArray[i] -> hex value to be converted
		// i, as index -> hexArray[i]'s decimal value
		// A to F has all the indexes equal to their decimal value
		// These names' will be used in further comments
		String hexArray = "0123456789ABCDEF";
		hex = hex.toUpperCase();
		decOut = hexArray.indexOf(hex.charAt(1)); // get the "i" value by matching the second(1) char from hex variable with a "hexArray[i]" value
		decOut += hexArray.indexOf(hex.charAt(0))*16; // get the "i" value by matching the first(0) char from hex variable with a "hexArray[i]" value
		return decOut;
		
	}
	
	/**
	 * Decimal to Binary converter
	 * @param dec
	 * @return a string of 1 and 0 representing the binary conversion from the given integer
	 */
	public static String decToBinary(int dec) {
		
		String bin = "";
		while(dec != 0) {
			bin += String.valueOf(dec%2);
			dec /= 2;
		}
		return new StringBuilder(bin).reverse().toString();
	}
}
