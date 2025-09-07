package com.opsam.Dao;

import java.util.List;

import com.opsam.models.OrdersItem;

public interface OrdersItemDao {
	
	void addOrdersItem(OrdersItem ordersItem);
	int updateOrdersItem(OrdersItem ordersItem);
	int deleteOrdersItem(int id);
	OrdersItem getOrdersItem(int id);
	List<OrdersItem> getAllOrdersItem();

}
