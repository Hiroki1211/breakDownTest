package forExample;

public class Executer {

	public static void main(String args[]) {
		int a = 1;
		int b = 100;
		int result = new Executer().run(a, b);
		System.out.println(result);
	}
	
	public int run(int a, int b) {
		int result;
		if(a < b) {
			result = sum(a, b);
		}else {
			result = 0;
		}
		return result;
	}
	
	public int sum(int a, int b) {
		int result = 0;
		for(int i = a; i < b + 1; i++) {
			result = new Calculator().adder(result, i);
		}
		
		return result;
	}
}
