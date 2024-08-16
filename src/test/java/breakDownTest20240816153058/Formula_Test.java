package breakDownTest20240816153058;

import static org.junit.Assert.*;
import org.junit.Test;

public class Formula_Test {

	@Test
	public void test0(){
		// create Instance for Method
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		Formula formula28 = new Formula(1, 2, "+");
		// assertion
		assertEquals(1, formula28.getA);
		assertEquals(2, formula28.getb);
		assertEquals("+", formula28.getOpe);
	}

	@Test
	public void test1(){
		// create Instance for Method
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		Formula formula29 = new Formula(5, 2, "-");
		// assertion
		assertEquals(5, formula29.getA);
		assertEquals(2, formula29.getb);
		assertEquals("-", formula29.getOpe);
	}

	@Test
	public void test2(){
		// create Instance for Method
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		Formula formula30 = new Formula(2, 3, "*");
		// assertion
		assertEquals(2, formula30.getA);
		assertEquals(3, formula30.getb);
		assertEquals("*", formula30.getOpe);
	}

	@Test
	public void test3(){
		// create Instance for Method
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		Formula formula31 = new Formula(12, 4, "/");
		// assertion
		assertEquals(12, formula31.getA);
		assertEquals(4, formula31.getb);
		assertEquals("/", formula31.getOpe);
	}

	@Test
	public void test4(){
		// create Instance for Method
		Formula formula28 = new Formula(1, 2, "+");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula28.getA();
		// assertion
		assertEquals(result, 1);
	}

	@Test
	public void test5(){
		// create Instance for Method
		Formula formula29 = new Formula(5, 2, "-");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula29.getA();
		// assertion
		assertEquals(result, 5);
	}

	@Test
	public void test6(){
		// create Instance for Method
		Formula formula30 = new Formula(2, 3, "*");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula30.getA();
		// assertion
		assertEquals(result, 2);
	}

	@Test
	public void test7(){
		// create Instance for Method
		Formula formula31 = new Formula(12, 4, "/");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula31.getA();
		// assertion
		assertEquals(result, 12);
	}

	@Test
	public void test8(){
		// create Instance for Method
		Formula formula28 = new Formula(1, 2, "+");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula28.getA();
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula28.getb();
		// assertion
		assertEquals(result, 2);
	}

	@Test
	public void test9(){
		// create Instance for Method
		Formula formula29 = new Formula(5, 2, "-");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula29.getA();
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula29.getb();
		// assertion
		assertEquals(result, 2);
	}

	@Test
	public void test10(){
		// create Instance for Method
		Formula formula30 = new Formula(2, 3, "*");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula30.getA();
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula30.getb();
		// assertion
		assertEquals(result, 3);
	}

	@Test
	public void test11(){
		// create Instance for Method
		Formula formula31 = new Formula(12, 4, "/");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula31.getA();
		// execute Method for Instance of Method Argument
		// execute Target Method
		int result = formula31.getb();
		// assertion
		assertEquals(result, 4);
	}

	@Test
	public void test12(){
		// create Instance for Method
		Formula formula28 = new Formula(1, 2, "+");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula28.getA();
		int result = formula28.getb();
		// execute Method for Instance of Method Argument
		// execute Target Method
		String result = formula28.getOpe();
		// assertion
		assertEquals(result, "+");
	}

	@Test
	public void test13(){
		// create Instance for Method
		Formula formula29 = new Formula(5, 2, "-");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula29.getA();
		int result = formula29.getb();
		// execute Method for Instance of Method Argument
		// execute Target Method
		String result = formula29.getOpe();
		// assertion
		assertEquals(result, "-");
	}

	@Test
	public void test14(){
		// create Instance for Method
		Formula formula30 = new Formula(2, 3, "*");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula30.getA();
		int result = formula30.getb();
		// execute Method for Instance of Method Argument
		// execute Target Method
		String result = formula30.getOpe();
		// assertion
		assertEquals(result, "*");
	}

	@Test
	public void test15(){
		// create Instance for Method
		Formula formula31 = new Formula(12, 4, "/");
		// create Instance for Method Argument
		// execute Method for Instance of Method
		int result = formula31.getA();
		int result = formula31.getb();
		// execute Method for Instance of Method Argument
		// execute Target Method
		String result = formula31.getOpe();
		// assertion
		assertEquals(result, "/");
	}

}