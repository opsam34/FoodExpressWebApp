package com.opsam.servlets;

import java.io.IOException;
import java.util.List;

import com.opsam.DaoImpl.RestaurantDaoImpl;
import com.opsam.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GetAllRestaurants")
public class GetAllRestaurants extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		List<Restaurant> restList = rdi.getAllRestaurants();
		
		
		request.setAttribute("restList", restList);
		RequestDispatcher rd = request.getRequestDispatcher("restaurant.jsp");
		
		rd.forward(request, response);
		
	}

}
