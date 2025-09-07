package com.opsam.Dao;

import java.util.List;

import com.opsam.models.Menu;

public interface MenuDao {

	void addMenu(Menu menu);
	int UpdateMenu(Menu menu);
	int deleteMenu(int id);
	Menu getMenu(int id);
	List<Menu> getAllMenus();
	List<Menu> getAllMenuByRestaurantId(int id);
}
