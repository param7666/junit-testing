package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.nt.Arithmatic;

class ArithmaticTestCases {

	@Test
	@Disabled
	void testAdd() {
		int a=10, b=20;
		int expected=a+b;
		Arithmatic ar=new Arithmatic();
		int actual=ar.add(a, b);
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddNegetive() {
		int a=-10;
		int b=30;
		int expected=a+b;
		Arithmatic ar=new Arithmatic();
		assertEquals(expected, ar.add(a, b),"Addition should be corect");
	}
	
	
	@Test
	@Disabled
	public void testAddWithZero() {
		int a=0,b=0, expected=0;
		Arithmatic ar=new Arithmatic();
		assertEquals(expected, ar.add(a, b),"Addtion should be zero");
	}
	
	
	

}
