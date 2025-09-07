package com.opsam.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.opsam.DaoImpl.MenuDaoImpl;
import com.opsam.DaoImpl.RestaurantDaoImpl;
import com.opsam.models.Menu;
import com.opsam.models.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet("/menu")
public class GetAllMenuByRestId extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		    
		
		
		String restaurantIdParam = req.getParameter("restaurantId");
		if (restaurantIdParam != null  && !restaurantIdParam.trim().isEmpty() && !restaurantIdParam.equals("null")) {
		    int restaurantId = Integer.parseInt(restaurantIdParam);
		    // use restaurantId
		    
		    MenuDaoImpl mdi = new MenuDaoImpl();
		    
		    List<Menu> menuList = mdi.getAllMenuByRestaurantId(restaurantId);
		    
		    req.setAttribute("menuList", menuList);
		    
		    
		    
		    //		testing worked
		    
		    RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		    Restaurant restaurant = rdi.getRestaurant(restaurantId);
		    req.setAttribute("restList" , restaurant);
		    
		    RequestDispatcher rd = req.getRequestDispatcher("menu.jsp");
		    rd.forward(req, resp);
		    
		    
		    
		    
		} else {
		    // handle missing or empty parameter
		    // maybe set a default value or return an error
			PrintWriter out = resp.getWriter();
			out.print("Please first add item from menu to cart");
		}
		
		
		
		}
	

}
