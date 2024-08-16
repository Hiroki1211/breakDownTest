package fuga;

public class Executer {

	public int run(Formula f) {
		
		int a = f.getA();
		int b = f.getb();
		int result = 0;
		
		switch( f.getOpe() ){
			case "+":
				result = a + b;
				break;
			case "-":
				result = a - b;
				break;
			case "*":
				result = a * b;
				break;
			case "/":
				result = a / b;
				break;
		}
		
		return result;
	}
	
}
