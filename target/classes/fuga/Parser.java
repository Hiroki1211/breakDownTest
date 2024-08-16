package fuga;

public class Parser {

	public Formula run(String[] input) {
		
		int a = Integer.valueOf(input[0]);
		String ope = input[1];
		int b = Integer.valueOf(input[2]);
		
		Formula f = new Formula(a, b, ope);
		return f;
		
	}
	
}
