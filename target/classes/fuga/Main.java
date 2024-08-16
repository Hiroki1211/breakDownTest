package fuga;

public class Main {

	public static void main(String[] argv) {
		int result = new Main().run("1 + 2");
		System.out.println(result);
	}
	
	public int run(String s) {
		String[] lexer = new Lexer().run(s);
		Formula parser = new Parser().run(lexer);
		int result = new Executer().run(parser);
		return result;
	}
	
}
