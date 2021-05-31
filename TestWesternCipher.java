
public class TestWesternCipher {
	
	public static void main (String[] args) {

		// --------------- Test 1 --------------- [Encode AB & Test Constructor]
		boolean test1Success = false;
		WesternCipher wc = new WesternCipher(200);
		WesternCipher wc2 = new WesternCipher();
		
		String input = "AB";
		String output = wc.encode(input);
		
		if(output.equals("1G")) {
			test1Success=true;
		}
		
		if(test1Success) {
			System.out.println("Test 1 Passed");
		}
		else {
			System.out.println("Received " + output + " and expected 1G");
		}
		

		

		// --------------- Test 2 --------------- [Encode BBBBBBBBBB]

		boolean test2Success = false;
		
		input = "BBBBBBBBBB";
		output = wc.encode(input);
		
		if(output.equals("GIKMOQSUWY")) {
			test2Success=true;
		}
		
		if(test2Success) {
			System.out.println("Test 2 Passed");
		}
		else {
			System.out.println("Received " + output + " and expected GIKMOQSUWY");
		}
		
		

		// --------------- Test 3 --------------- [Decode 1G]

		boolean test3Success = false;
		
		input = "1G";
		output = wc.decode(input);
		
		if(output.equals("AB")) {
			test3Success=true;
		}
		
		if(test3Success) {
			System.out.println("Test 2 Passed");
		}
		else {
			System.out.println("Received " + output + " and expected AB");
		}
		
		

		// --------------- Test 4 --------------- [Decode B24N2YU]

		boolean test4Success = false;
		
		input = "B24N2YU";
		output = wc.decode(input);
		
		if(output.equals("WEEKEND")) {
			test4Success=true;
		}
		
		if(test4Success) {
			System.out.println("Test 4 Passed");
		}
		else {
			System.out.println("Received " + output + " and expected WEEKEND");
		}
		

		// --------------- Test 5 --------------- [Encode and Decode APPLES AND ORANGES]
		
		boolean test5Success = false;
		
		input = "APPLES AND ORANGES";
		output = wc.encode(input);
		
		if(output.equals("1UYW2D 1GA 4M1SP2B")) {
		
			if(wc.decode(output).equals("APPLES AND ORANGES")) {
				test5Success=true;
			}
			
		}
		
		if(test5Success) {
			System.out.println("Test 5 Passed");
		}
		else {
			System.out.println("Received " + output + " and expected 1UYW2D 1GA 4M1SP2B");
			System.out.println("OR, Received " + wc.decode(output) + " and expected APPLES AND ORANGES");
		}
		
		
		

	}
	

}
