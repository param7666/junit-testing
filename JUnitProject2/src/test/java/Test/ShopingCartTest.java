package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.nt.Item;
import com.nt.ShopingCart;

class ShopingCartTest {

	private static ShopingCart cart;
	
	@BeforeEach
	@DisplayName("Init method to create object")
	public void init() {
		System.out.println("ShopingCartTest.init()");
		cart=new ShopingCart();
	}
	
	// --------------------- addItem() Tests -------------------------
	
	@Test
	@DisplayName("Add Method With valid Item ")
	public void addValidItem() {
		Item item=new Item("a1", "laptop", 50000, 1);
		cart.addItem(item);
		assertEquals(1, cart.getItemCount());
	}

	@Test
	@DisplayName(" Add Method with invalid details")
	public void addItemInvalidItem() {
		System.out.println("ShopingCartTest.addItemInvalidItem()");
		Item item=new Item("a2","Mobile",4000,-2);
		assertThrows(IllegalArgumentException.class, ()->cart.addItem(item));
	}
	
	@Test
	@DisplayName("Add Item with 0 Price")
	public void addItemWithZeroPrice() {
		System.out.println("ShopingCartTest.addItemWithZeroPrice()");
		Item item=new Item("a3","Charger",0,6);
		assertThrows(IllegalArgumentException.class, ()->cart.addItem(item));
	}
	
	@Test
	@DisplayName("add Null Item")
	public void addNullItem() {
		System.out.println("ShopingCartTest.addNullItem()");
		assertThrows(IllegalArgumentException.class,()-> cart.addItem(null));
	}
	
	
	
	 // --------------------- getTotalAmount() Tests -------------------------
	
	@Test
	@DisplayName("Method to test total amount")
	public void testTotalAmmount() {
		System.out.println("ShopingCartTest.testTotalAmmount()");
		Item item=new Item("a4","Pen",20,5);
		cart.addItem(item);
		assertEquals(100, cart.getTotalAmount());
	}
	
	@Test
	@DisplayName("Method to test total amount of multiple item")
	public void testTotalAmountOnMultipleItem() {
		System.out.println("ShopingCartTest.testTotalAmountOnMultipleItem()");
		cart.addItem(new Item("aa1","Book",500,4)); //500 *4=2k
		cart.addItem(new Item("aa2","Bag",1000,5));  //1000*5=5k ->7k
		assertEquals(7000, cart.getTotalAmount());
	}
	 
	@Test
	@DisplayName("Total Amount on Empty cart")
	public void testTotalAmmountOnEmptyCart() {
		System.out.println("ShopingCartTest.testTotalAmmountOnEmptyCart()");
		assertEquals(0, cart.getTotalAmount());
	}
	
	
    // --------------------- applyDiscount() Tests -------------------------
	
	@Test
	public void testValidDiscount() {
		System.out.println("ShopingCartTest.testValidDiscount()");
		 cart.addItem(new Item("1", "Shoes", 1000, 1));
		 double totalAfterDiscount = cart.calculateDiscount(10); // 10% discount
	        assertEquals(900, totalAfterDiscount);
	}
	
	
	@Test
	public void testZeroPercentageDiscount() {
		System.out.println("ShopingCartTest.testZeroPercentageDiscount()");
		cart.addItem(new Item("1", "Wallet", 500, 1));
		assertEquals(500, cart.calculateDiscount(0));
	}
	
	@Test
	public void testDiscountPerMoreThanFifty() {
		System.out.println("ShopingCartTest.testDiscountPerMoreThanFifty()");
		cart.addItem(new Item("2","Purse",500,1));
		assertThrows(IllegalArgumentException.class, ()->cart.calculateDiscount(55));
		
	}
	
	@Test
	public void testNegetiveDiscount() {
		System.out.println("ShopingCartTest.testNegetiveDiscount()");
		cart.addItem(new Item("3","Fan",6000,2)); // 12k
		assertThrows(IllegalArgumentException.class, ()-> cart.calculateDiscount(-25));
	}
	
	
	 // --------------------- removeItem() Tests -------------------------
	
	@Test
	public void removeExistingItem() {
		cart.addItem(new Item("1", "Tablet", 15000, 1));
		boolean removed=cart.removeItem("1");
		assertTrue(removed);
		assertEquals(0, cart.getItemCount());
	}
	
	@Test
	public void removeNonExistingItem() {
		cart.addItem(new Item("1", "Fan", 800, 1));
		boolean removed=cart.removeItem("5");
		assertFalse(removed);
	}
	
	
	@AfterAll
	@DisplayName("Clean up Method")
	public static void cleanUp() {
		System.out.println("All Test Cases Executed.........");
	}
}
