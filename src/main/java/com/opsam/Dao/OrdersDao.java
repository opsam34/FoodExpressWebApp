package com.opsam.Dao;

import java.util.List;

import com.opsam.models.Orders;

public interface OrdersDao {
	
	int addOrders(Orders orders); //int for generating orderId
	int updateOrders(Orders orders);
	int deleteOrders(int id);
	Orders getOrders(int id);
	List<Orders> getAllOrders();
	
}
