package com.opsam.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opsam.Dao.restaurantDao;
import com.opsam.models.Restaurant;
import com.opsam.util.DBConnection;

public class RestaurantDaoImpl implements restaurantDao{
	Connection con=DBConnection.DBconnect();
	public Restaurant restaurant;
	private int res;
	private String INSERT_RESTAURANT="INSERT into `restaurant`(`name`,`address`,`phone`,`rating`,"
			+ "`cusineType`,`isActive`,`eta`,`adminUserId`,`imagePath`) values(?,?,?,?,?,?,?,?,?)";
	private String GET_RESTAURANT="SELECT * FROM `restaurant` WHERE `restaurantId`=?";
	private String GET_ALL_RESTAURANT="SELECT * FROM `restaurant`";
	private String DELETE_RESTAURANT="DELETE FROM `restaurant` WHERE `restaurantId`=?";
	private String UPDATE_RESTAURANT="UPDATE `restaurant` SET `name`=?,`address`=?,`phone`=?,`rating`=?,"
			+ "`cusineType`=?,`isActive`=?,`eta`=?,`adminUserId`=?,`imagePath`=? WHERE `restaurantId`=? ";

	@Override
	public void addRestaurant(Restaurant restaurant) {
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT_RESTAURANT);
//			pstmt.setInt(1, restaurant.getRestaurantId());
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getAddress());
			pstmt.setString(3, restaurant.getPhone());
			pstmt.setInt(4, restaurant.getRating());
			pstmt.setString(5, restaurant.getCusineType());
			pstmt.setInt(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getEta());
			pstmt.setInt(8, restaurant.getAdminUserId());
			pstmt.setString(9, restaurant.getImagePath());

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int updateRestaurant(Restaurant restaurant) {
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_RESTAURANT);
			pstmt.setString(1, restaurant.getName());
			pstmt.setString(2, restaurant.getAddress());
			pstmt.setString(3, restaurant.getPhone());
			pstmt.setInt(4, restaurant.getRating());
			pstmt.setString(5, restaurant.getCusineType());
			pstmt.setInt(6, restaurant.getIsActive());
			pstmt.setString(7, restaurant.getEta());
			pstmt.setInt(8, restaurant.getAdminUserId());
			pstmt.setString(9, restaurant.getImagePath());
			pstmt.setInt(10, restaurant.getRestaurantId());
			
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteRestaurant(int id) {
		try {

			PreparedStatement pstmt = con.prepareStatement(DELETE_RESTAURANT);
			pstmt.setInt(1, id);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Restaurant getRestaurant(int id) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_RESTAURANT);
			pstmt.setInt(1, id);

			ResultSet res = pstmt.executeQuery();
			while(res.next()) {

				int restaurantId=res.getInt("restaurantId");
				String name=res.getString("name");
				String address=res.getString("address");
				String phone=res.getString("phone");
				int rating=res.getInt("rating");
				String cusineType=res.getString("cusineType");
				int isActive=res.getInt("isActive");
				String eta=res.getString("eta");
				int adminUserId=res.getInt("adminUserId");
				String imagePath=res.getString("imagePath");
				restaurant = new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
			}



		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public List<Restaurant> getAllRestaurants() {

		ArrayList<Restaurant> list=new ArrayList<Restaurant>();

		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_RESTAURANT);
			ResultSet res = pstmt.executeQuery();

			while(res.next()) {

				int restaurantId=res.getInt("restaurantId");
				String name=res.getString("name");
				String address=res.getString("address");
				String phone=res.getString("phone");
				int rating=res.getInt("rating");
				String cusineType=res.getString("cusineType");
				int isActive=res.getInt("isActive");
				String eta=res.getString("eta");
				int adminUserId=res.getInt("adminUserId");
				String imagePath=res.getString("imagePath");
				restaurant = new Restaurant(restaurantId, name, address, phone, rating, cusineType, isActive, eta, adminUserId, imagePath);
				list.add(restaurant);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
