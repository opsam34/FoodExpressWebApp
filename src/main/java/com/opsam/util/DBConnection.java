package com.opsam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = System.getenv("URL");
	private static final String USERNAME = System.getenv("USERNAME");
	private static final String PASSWORD = System.getenv("PASSWORD");


	private static Connection con;
	
	
	public static Connection DBconnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
