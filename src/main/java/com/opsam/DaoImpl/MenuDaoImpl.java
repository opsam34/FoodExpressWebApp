package com.opsam.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opsam.Dao.MenuDao;
import com.opsam.models.Menu;
import com.opsam.util.DBConnection;

public class MenuDaoImpl implements MenuDao
{
	Connection con=DBConnection.DBconnect();
	int res;
	private String INSERT_MENU="INSERT into `menu` (`restaurantId`,`itemName`,`description`,"
			+ "`price`,`rating`,`isAvailable`,`imagePath`) values(?,?,?,?,?,?,?)";
	private String UPDATE_MENU="UPDATE `menu` SET restaurantId=?,itemName=?,description=?,price=?,rating=?,isAvailable=?,imagePath=? where menuId=? ";
	private String GET_MENU="SELECT * FROM `menu` WHERE menuId=?";
	private String GET_ALL_MENUS="SELECT * FROM `menu`";
	private String DELETE_MENU="DELETE FROM `menu` where `menuId`=?";
	private String GET_ALL_MENUS_BY_REST_ID="SELECT * FROM `menu` WHERE restaurantId=? ";

	@Override
	public void addMenu(Menu menu) {
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(INSERT_MENU);
//			pstmt.setInt(1, menu.getMenuId());
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setInt(5, menu.getRating());
			pstmt.setInt(6, menu.getIsAvailable());
			pstmt.setString(7, menu.getImagePath());

			res = pstmt.executeUpdate();
			System.out.println(res);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int UpdateMenu(Menu menu) {
		try {
			PreparedStatement pstmt = con.prepareStatement(UPDATE_MENU);
			pstmt.setInt(1, menu.getRestaurantId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setString(3, menu.getDescription());
			pstmt.setInt(4, menu.getPrice());
			pstmt.setInt(5, menu.getRating());
			pstmt.setInt(6, menu.getIsAvailable());
			pstmt.setString(7, menu.getImagePath());
			pstmt.setInt(8, menu.getMenuId());

			res = pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteMenu(int id) {
		try {
			PreparedStatement pstmt = con.prepareStatement(DELETE_MENU);
			pstmt.setInt(1, id);

			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Menu getMenu(int id) {
		Menu menu=null;
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(GET_MENU);
			pstmt.setInt(1, id);

			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId=res.getInt("menuId");
				int restaurantId=res.getInt("restaurantId");
				String itemName=res.getString("itemName");
				String description=res.getString("description");
				int price=res.getInt("price");
				int rating=res.getInt("rating");
				int isAvailable=res.getInt("isAvailable");
				String imagePath=res.getString("imagePath");
				menu=new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public List<Menu> getAllMenus() {

		ArrayList<Menu> list=new ArrayList<Menu>();
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_MENUS);


			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId=res.getInt("menuId");
				int restaurantId=res.getInt("restaurantId");
				String itemName=res.getString("itemName");
				String description=res.getString("description");
				int price=res.getInt("price");
				int rating=res.getInt("rating");
				int isAvailable=res.getInt("isAvailable");
				String imagePath=res.getString("imagePath");
				Menu menu=new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Menu> getAllMenuByRestaurantId(int id) {
		ArrayList<Menu> list=new ArrayList<Menu>();
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_MENUS_BY_REST_ID);
			pstmt.setInt(1, id);

			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId=res.getInt("menuId");
				int restaurantId=res.getInt("restaurantId");
				String itemName=res.getString("itemName");
				String description=res.getString("description");
				int price=res.getInt("price");
				int rating=res.getInt("rating");
				int isAvailable=res.getInt("isAvailable");
				String imagePath=res.getString("imagePath");
				Menu menu=new Menu(menuId, restaurantId, itemName, description, price, rating, isAvailable, imagePath);
				list.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
