package com.nt;

import java.util.ArrayList;
import java.util.List;

public class ShopingCart {

	private List<Item> items=new ArrayList<>();
	
	
	// add item into list if valid
	public void addItem(Item item) {
		if(item==null) {
			throw new IllegalArgumentException("Item can not be null");
		}
		
		if(item.getPrice()<=0) {
			throw new IllegalArgumentException("Item price should be > 0");
		}
		
		if(item.getQuantity()<0) {
			throw new IllegalArgumentException("Item Quentity must be > 0");
		}
		items.add(item);
	}
	
	// remove item based on id
	
	public boolean removeItem(String itemId) {
		if(itemId.isBlank() || itemId.isBlank() || itemId==null) {
			throw new IllegalArgumentException("Item Id Can not be null");
		}
		
		return items.removeIf((i)->i.getId().equals(itemId));
	}
	
	// calculate the total ammount of all item
	
	public double getTotalAmount() {
		double total=0;
		for(Item item:items) {
			total+=item.getPrice()*item.getQuantity();
		}
		return total;
	}
	
	public double calculateDiscount(double percentage) {
		
		if(percentage<0 || percentage>50) throw new IllegalArgumentException("Discount must be between 0 to 50");
		
		double total=getTotalAmount();
		 return total-((percentage/100)*total);
	}
	
	public int getItemCount() {
		return items.size();
	}
}
