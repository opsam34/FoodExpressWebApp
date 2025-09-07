package com.opsam.Dao;

import java.util.List;

import com.opsam.models.User;

public interface UserDao {
	
	void addUser(User user);
	int updateUser(User user);
	int deleteUser(int id);
	User getUser(int id);
	List<User> getAllUsers();

}
