package calculatorIntArgument;

public class Executer {

	public static void main(String args[]) {
		int result = new Executer().run(1, '+', 2);
		System.out.println(result);
	}
	
	public int run(int a, char c, int b) {
		int result = 0;
		switch(c) {
			case '+':
				result = new Calculator().add(a, b);
				break;
			case '-':
				result = new Calculator().sub(a, b);
				break;
			case '*':
				result = new Calculator().mul(a, b);
				break;
			case '/':
				result = new Calculator().div(a, b);
				break;
			default:
				result = 0;
				break;
		}
		
		return result;
	}
}
