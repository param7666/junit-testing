package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nt.Multification;

import static org.junit.jupiter.api.Assertions.*;

public class MultificationTest {
	
	private Multification mt;
	
	@BeforeEach
	void init() {
		mt=new Multification();
		System.out.println("Object Created.....");
	}
	
	@AfterEach
	void cleanUp() {
		System.out.println("Test Completed.....");
	}
	
	@Test
	@DisplayName("Test Simple Multification with positive number")
	public void testPositiveNumberMulti() {
		int a=2,b=2,exp=4;
		assertEquals(exp, mt.multi(a, b));
	}
	
	@Test
	@DisplayName("Test Simple Multification with negetive number")
	public void testNegetiveNumberMulti() {
		int a=-2,b=-2;
		int exp=a*b;
		assertEquals(exp, mt.multi(a, b));
	}
	
	

}
