package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.*;

import com.nt.entity.Product;
import com.nt.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks  // inject dummy object
	private ProductService service;
	
	@BeforeAll
	@DisplayName("Init Method Run once at starting")
	public static void init() {
		System.out.println("ProductServiceTest.init()");
	}
	
	@Test
	public void testGetProduct() {
		System.out.println("ProductServiceTest.testGetProduct()");
		Product p=service.getProduct(1);
		assertEquals("laptop", p.getName());
		assertDoesNotThrow(()->service.getProduct(1));
	}

	@Test
	public void testInvalidGetProduct() {
		System.out.println("ProductServiceTest.testInvalidGetProduct()");
		assertThrows(IllegalArgumentException.class, ()->service.getProduct(-5));
	}
	
	@Test
	public void testApplyDiscount() {
		double price = service.applyDiscount(10000, 20);
        assertEquals(8000, price);
	}
	
	@Test
	public void testApplyDiscountInvalid() {
		assertThrows(IllegalArgumentException.class, ()->service.applyDiscount(5000, 60));
	}
	
	@Test
	public void testCategoriesList() {
		List<String> expected=List.of("Electronics", "Home", "Fashion");
		List<String> actual=service.getCategories();
		assertIterableEquals(expected, actual); // used with collection
	}
	
	@RepeatedTest(3) // repeat test 3 times
	public void testGetProductRepeat() {
		assertDoesNotThrow(()-> service.getProduct(1));
	}
	
	@Test
	@Timeout(2)
	public void testTimeOut() {
		assertTimeout(Duration.ofMillis(600), ()->Thread.sleep(1000));
	}
	
	 @AfterEach
	 @Disabled
	 void tearDown() {
	    System.out.println(">>> After EACH test");
    }
	 
	 @AfterAll
	 public static void endAll() {
	    System.out.println(">>> After ALL tests");
	  }
	
}

