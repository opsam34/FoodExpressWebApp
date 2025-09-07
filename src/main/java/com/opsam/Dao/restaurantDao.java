package com.opsam.Dao;

import java.util.List;

import com.opsam.models.Restaurant;

public interface restaurantDao {
	
	void addRestaurant(Restaurant restaurant);
	int updateRestaurant(Restaurant restaurant);
	int deleteRestaurant(int id);
	Restaurant getRestaurant(int id);
	List<Restaurant> getAllRestaurants();

}
