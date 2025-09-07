package com.opsam.models;

public class OrdersItem {
	
	private int orderItemId;
	private int orderId;
	private int menuId;
	private int quantity;
	private int totalPrice;
	
	public int getOrderItemId() {
		return orderItemId;
	}
	public OrdersItem(int orderId, int menuId, int quantity, int totalPrice) {
		super();
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public OrdersItem(int orderItemId, int orderId, int menuId, int quantity, int totalPrice) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuId = menuId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public OrdersItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrdersItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + "]";
	}

	
	
}
