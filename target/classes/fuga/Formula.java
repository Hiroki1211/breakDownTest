package fuga;

public class Formula {

	private int a;
	private int b;
	private String ope;
	
	public Formula(int x, int y, String s) {
		a = x;
		b = y;
		ope = s;
	}
	
	public int getA() {
		return a;
	}
	
	public int getb() {
		return b;
	}
	
	public String getOpe() {
		return ope;
	}
}
