package com.opsam.models;
import java.util.HashMap;
import java.util.Map;


public class Cart {
	private Map<Integer, CartItem> items;
	// Constructor
	public Cart() {
		this.items = new HashMap<>();
	}
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}
	public void addItemToCart(CartItem item) {
		int itemId = item.getId();
		if(items.containsKey(itemId)) {
			CartItem existingItem = items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}else {
			items.put(itemId, item);
		}
	}
	public void updateItemToCart(int itemId, int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity<=0) {
				items.remove(itemId);
			}
			else {
				CartItem existingItem = items.get(itemId);
				existingItem.setQuantity(quantity);
			}
		}
	}


	public void deleteCartItem(int itemId) {
		items.remove(itemId);
	}

	public void claer() {
		items.clear();
	}
	
	public double getTotalPrice() {
		return items.values().stream().mapToDouble(item ->item.getPrice() *item.getQuantity()).sum();
	}
}