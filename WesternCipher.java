import java.io.*;
import java.util.Scanner;

/**
 * This class represents a cipher that can encode and decode information with the algorithm.
 * @author Zicong Qu
 */
public class WesternCipher {
	private CircularArrayQueue<Character> encodingQueue;
	private CircularArrayQueue<Character> decodingQueue;
	
	/**
	 * Constructor t initializes both the encoding and decoding queues with a capacity of 10 and as type Character.
	 */
	public WesternCipher () {
		encodingQueue = new CircularArrayQueue(10);
		decodingQueue = new CircularArrayQueue(10);
	}
	
	/**
	 * Constructor takes an integer as input and initializes both the encoding and decoding queues with the capacity
	 *  provided and as type Character
	 *  
	 *  @param size the capacity of encodingQueue and decodingQueue
	 */
	public WesternCipher (int size) { 
		encodingQueue = new CircularArrayQueue(size);
		decodingQueue = new CircularArrayQueue(size);
	}
	
	/** Takes an character, and return yes if it is one of the value on any of the two tables, return no if not.
	 * 
	 * @param input    the element that we need to check 
     *  @return if the input element is on the table
	*/
	private boolean onTable_char(char input) {
		if (input == 'A' || input == 'E' ||input == 'I' ||input == 'O' ||input == 'U' || input == 'Y') {
			return true;
		}else {
			return false;
		}
	}
		
	/** Takes a number, and return yes if it is on the table, no if not.
	 * 
	 * @param input    the number that we need to check 
     *  @return if the input number is on the table
	*/
	private boolean onTable_num(char input) {
		if (input == '1' || input == '2' ||input == '3' ||input == '4' ||input == '5' || input == '6') {
			return true;
		}else {
			return false;
		}
	}
	
	/** Takes a character, and return the corresponding number in the first table.
	 * 
	 * @param input    the character that is in the first table where the previous character is not converted.
     *  @return the corresponding number according to the first table
	*/
	private char firstTable_encode(char input) {
		if (input == 'A') {
			return '1';
		}else if (input == 'E') {
			return '2';
		}else if (input == 'I') {
			return '3';
		}else if (input == 'O') {
			return '4';
		}else if (input == 'U') {
			return '5';
		}else if (input == 'Y') {
			return '6';
		}else {
			return ' ';
		}
	}
	
	/** Takes a character, and return the corresponding number in the second table.
	 * 
	 * @param input    the character that is in the second table where the previous character is converted.
     *  @return the corresponding number according to the second table
	*/
	private char secondTable_encode(char input) {
		if (input == 'A') {
			return '3';
		}else if (input == 'E') {
			return '4';
		}else if (input == 'I') {
			return '5';
		}else if (input == 'O') {
			return '6';
		}else if (input == 'U') {
			return '1';
		}else if (input == 'Y') {
			return '2';
		}else {
			return ' ';
		}
	}
	
	/** Takes a number, and return the corresponding character in the first table.
	 * 
	 * @param input    the number that is in the first table where the previous character is not converted.
     *  @return the corresponding character according to the first table
	*/
	private char firstTable_decode(char input) {
		if (input == '1') {
			return 'A';
		}else if (input == '2') {
			return 'E';
		}else if (input == '3') {
			return 'I';
		}else if (input == '4') {
			return 'O';
		}else if (input == '5') {
			return 'U';
		}else if (input == '6') {
			return 'Y';
		}else {
			return ' ';
		}
	}
	
	/** Takes a character, and return the corresponding number in the second table.
	 * 
	  * @param input    the number that is in the second table where the previous character is converted.
     *  @return the corresponding character according to the second table
	*/
	private char secondTable_decode(char input) {
		if (input == '3') {
			return 'A';
		}else if (input == '4') {
			return 'E';
		}else if (input == '5') {
			return 'I';
		}else if (input == '6') {
			return 'O';
		}else if (input == '1') {
			return 'U';
		}else if (input == '2') {
			return 'Y';
		}else {
			return ' ';
		}
	}
	
	/** Takes a string input, and return a string that was encoded.
	 * 
	 * @param input    string before being applied the Western Cipher algorithm
     *  @return string after being applied the Western Cipher algorithm
	*/
	public String encode(String input) {
		String output = "";
		// first we create the alphabet as a array of characters
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		// Break the input into an array of characters
		for (int i = 0; i < input.length(); i ++){
			encodingQueue.enqueue(input.charAt(i)); 
		}
		int size = encodingQueue.size();
		
		// we first encode the first character
		char character = encodingQueue.dequeue();
		if (onTable_char(character)) {
			output += firstTable_encode(character);
		}else {
			for (int i = 0; i < 26; i ++) {
				if (character == alphabet[i]) {
					output += alphabet[(i + 5) % 26];
				}
			}
		}
		
		// now we encode the rest of the characters
		for (int i = 1; i < size; i ++) {
			character = encodingQueue.dequeue();
			if (onTable_char(character)) {  
				if (onTable_num(output.charAt(i - 1))) {
					output += secondTable_encode(character);          // rule #5
				}else {
					output += firstTable_encode(character);           // rule #3
				}
			}else if (character == ' ') {
				output += " ";
			}else {
				if (onTable_num(output.charAt(i - 1))) {
					for (int j = 0; j < 26; j ++) {
						if (character == alphabet[j]) {
							int a = Character.getNumericValue(output.charAt(i - 1));
							output += alphabet[(j + 5 + 2 * i - 2 * a) % 26]; // rule #1, #2, and #4
							break;
						}
					}
				}else {
					for (int j = 0; j < 26; j ++) {
						if (character == alphabet[j]) {
							output += alphabet[(j + 5 + 2 * i) % 26];         // rule #1 and #2			
							break;
						}
					}
				}
			}
		}
		return output;
	}
	
	/** Takes a encoded string input, and return the string after decoded.
	 * 
	 * @param input    the encoded string 
     *  @return the string after being decoded
	*/
	public String decode(String input) {
		String output = "";
		// first we create the alphabet as a array of characters
		char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		// Break the input into an array of characters
		for (int i = 0; i < input.length(); i ++){
			decodingQueue.enqueue(input.charAt(i)); 
	    }
		int size = decodingQueue.size();
		char number_element = ' ';
		
		// we decode the first element
		char character = decodingQueue.dequeue();
		if (onTable_num(character)) {
			number_element = character;
			output += firstTable_decode(character);
		}else {
			for (int i = 0; i < 26; i ++) {
				if (character == alphabet[i]) {
					output += alphabet[(i + 26 * size - 5) % 26];
				}
			}
		}
		
		// now we decode the rest of the characters
		for (int i = 1; i < size; i ++) {
			character = decodingQueue.dequeue();
			if (onTable_num(character)) { 
				number_element = character;
				if (onTable_char(output.charAt(i - 1))) {
					output += secondTable_decode(character);          // rule #5
				}else {
					output += firstTable_decode(character);           // rule #3
				}
			}else if (character == ' ') {
				output += " ";
			}else {
				if (onTable_char(output.charAt(i - 1))) {
					for (int j = 0; j < 26; j ++) {
						if (character == alphabet[j]) {
							int a = Character.getNumericValue(number_element);
							output += alphabet[(j + 26 * size - 5 - 2 * i + 2 * a) % 26]; // rule #1, #2, and #4
							break;
						}
					}
				}else {
					for (int j = 0; j < 26; j ++) {
						if (character == alphabet[j]) {
							output += alphabet[(j + 26 * size - 5 - 2 * i) % 26];         // rule #1 and #2
							break;
						}
					}
				}
			}
		}
		return output;					
	}
	
	/**
	 * This class prompt the user about whether they would like to encode or decode a string, 
	 * take the string and encode/decode as appropriate, and then prompt the user if they would like to enter
	 * another string. If no is selected, the program should exit.
	 */
	public static void main(String[] args) {
		WesternCipher wc = new WesternCipher();
		Scanner scan = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Do you want to encode or decode a string? Enter no to exit.");
	    String operation = scan.nextLine();
	    System.out.println(operation);
	    while (!operation.equals("no")) {
	    	if (operation.equals("encode")) {
	    	    System.out.println("Enter the encode string.");
	    	    String input = scan.nextLine().toUpperCase();
	    	    System.out.println(wc.encode(input));
	    	    System.out.println("Operation completed. Do you want to encode or decode a string? Enter no to exit.");
	    	    operation = scan.nextLine();
	    	}else if (operation.equals("decode")) {
	    	    System.out.println("Enter the decode string.");
	    	    String input = scan.nextLine().toUpperCase();
	    	    System.out.println(wc.decode(input));
	    	    System.out.println("Operation completed. Do you want to encode or decode a string? Enter no to exit.");
	    	    operation = scan.nextLine();
	    	}
	    }
	}
}
