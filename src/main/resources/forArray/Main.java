package forArray;

public class Main {

	public static void main(String args[]) {
		Main main = new Main();
		int array[] = {100, 200, 300, 400, 500};
		int result1 = main.adder(array);
		array[2] = 400;
		int result2 = main.adder(array);
		
	}
	
	public int adder(int[] a) {
		return a[0] + a[2];
	}
	
}
