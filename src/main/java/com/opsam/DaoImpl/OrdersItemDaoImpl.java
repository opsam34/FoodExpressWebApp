package com.opsam.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opsam.Dao.OrdersItemDao;
import com.opsam.models.OrdersItem;
import com.opsam.util.DBConnection;

public class OrdersItemDaoImpl implements OrdersItemDao {
	Connection con=DBConnection.DBconnect();
	private OrdersItem ordersItem;
	int res=0;
	private String INSERT_ORDERS_ITEM="INSERT into `ordersitem`(`orderId`,`menuId`,"
			+ "`quantity`,`totalPrice`) values(?,?,?,?)";
	private String DELETE_ORDERS_ITEM="DELETE FROM `ordersitem` WHERE `orderItemId`=?";
	private String GET_ORDERS_ITEM="SELECT * FROM `ordersitem` WHERE `orderItemId`=?";
	private String GET_ALL_ORDERS_ITEM="SELECT * FROM `ordersitem`";
	private String UPDATE_ORDERS_ITEM="UPDATE `ordersitem` SET `orderId`=?,`menuId`=?,`quantity`=?,"
			+ "`totalPrice`=? WHERE `orderItemId`=?";
	private String GET_ALL_ORDERSITEM_BY_USER_ID="SELECT oi.* FROM `ordersitem` oi INNER JOIN `orders` o ON oi.orderId = o.orderId WHERE o.userId = ?\r\n"
			+ "	        ORDER BY o.orderDate DESC";
	
	
	
	@Override
	public void addOrdersItem(OrdersItem ordersItem) {
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT_ORDERS_ITEM);
//			pstmt.setInt(1, ordersItem.getOrderItemId());
			pstmt.setInt(1, ordersItem.getOrderId() );
			pstmt.setInt(2, ordersItem.getMenuId());
			pstmt.setInt(3, ordersItem.getQuantity());
			pstmt.setInt(4, ordersItem.getTotalPrice());

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateOrdersItem(OrdersItem ordersItem) {
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_ORDERS_ITEM);
			pstmt.setInt(1, ordersItem.getOrderId());
			pstmt.setInt(2, ordersItem.getMenuId() );
			pstmt.setInt(3, ordersItem.getQuantity());
			pstmt.setInt(4, ordersItem.getTotalPrice() );
			pstmt.setInt(5, ordersItem.getOrderItemId());

			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteOrdersItem(int id) {
		try {
			PreparedStatement pstmt = con.prepareStatement(DELETE_ORDERS_ITEM);
			pstmt.setInt(1, id);

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public OrdersItem getOrdersItem(int id) {

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ORDERS_ITEM);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderItemId=res.getInt("orderItemId");
				int orderId=res.getInt("orderId");
				int menuId=res.getInt("menuId");
				int quantity=res.getInt("quantity");
				int totalPrice=res.getInt("totalPrice");
				ordersItem = new OrdersItem(orderItemId, orderId, menuId, quantity, totalPrice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ordersItem;
	}

	@Override
	public List<OrdersItem> getAllOrdersItem() {
		// TODO Auto-generated method stub
		ArrayList<OrdersItem> list=new ArrayList<OrdersItem>();

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_ORDERS_ITEM);
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {
				int orderItemId=res.getInt("orderItemId");
				int orderId=res.getInt("orderId");
				int menuId=res.getInt("menuId");
				int quantity=res.getInt("quantity");
				int totalPrice=res.getInt("totalPrice");
				ordersItem = new OrdersItem(orderItemId, orderId, menuId, quantity, totalPrice);
				list.add(ordersItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public List<OrdersItem> getAllOrdersItemByUserId(int id) {
	    List<OrdersItem> orderItems = new ArrayList<>();
	    
	         PreparedStatement pstmt;
			try {
				pstmt = con.prepareStatement(GET_ALL_ORDERSITEM_BY_USER_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					OrdersItem orderItem = new OrdersItem();
					orderItem.setOrderItemId(rs.getInt("orderItemId"));
					orderItem.setOrderId(rs.getInt("orderId"));
					orderItem.setMenuId(rs.getInt("menuId"));
					orderItem.setQuantity(rs.getInt("quantity"));
					orderItem.setTotalPrice(rs.getInt("totalPrice"));
					orderItems.add(orderItem);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    
	    return orderItems;
	}

}
