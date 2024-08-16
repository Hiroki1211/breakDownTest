package testAnalyzer;

public class Main {

	// boolean
	private boolean privateBoolean = true;
	public boolean publicBolean = true;
	private static boolean privateStaticBoolean = true;
	public static boolean pubilcStaticBoolean = true;
	
	// int
	private int privateInt = 1;
	public int publicInt = 2;
	private static int privateStaticInt = 3;
	public static int publicStaticInt = 4;
	
	// char
	private char privateChar = 'a';
	public char publicChar = 'b';
	private static char privateStaticChar = 'c';
	public static char publicStaticChar = 'd';
	
	// String
	private String privateString = "st1";
	public String publicString = "st2";
	private static String privateStaticString = "st3";
	public static String publicStaticString = "st4";
	
	// boolean array
	private boolean[] privateBooleanArray1 = new boolean[3];
	private boolean privateBooleanArray2[] = {true, false, true, true, true};
	
	// int array
	private int[] privateIntArray1 = new int[3];
	private int privateIntArray[] = {1, 2, 3, 4, 5};
	
	// char array
	private char[] privateCharArray1 = new char[3];
	private char privateCharArray2[] = {'a', 'b', 'c', 'd', 'e'};
	
	// String array
	private String[] privateStringArray1 = new String[3];
	private String privateStringArray2[] = {"a", "b", "c", "d", "e"};
	
	public static void main(String[] argv) {
		int resultNum = new Main().adder();
		System.out.println(resultNum);
	}
	
	public int getPrivateInt() {
		return privateInt;
	}
	
	public void setPrivateInt(int input) {
		privateInt = input;
	}
	
	public int adder() {
		int result = privateInt + publicInt + privateStaticInt + publicStaticInt;
		int tmp = getPrivateInt();
		System.out.println(tmp);
		return result;
	}
}
