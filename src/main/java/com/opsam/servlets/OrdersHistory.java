package com.opsam.servlets;

import java.io.IOException;
import java.util.List;

import com.opsam.DaoImpl.OrdersDaoImpl;
import com.opsam.DaoImpl.OrdersItemDaoImpl;
import com.opsam.models.Orders;
import com.opsam.models.OrdersItem;
import com.opsam.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/OrdersHistory")
public class OrdersHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int userId=user.getUserId();
		
		OrdersDaoImpl odi = new OrdersDaoImpl();
		 List<Orders> odList = odi.getAllOrdersByUserId(userId);
		 		
		OrdersItemDaoImpl oidi = new OrdersItemDaoImpl();
		 List<OrdersItem> odiList = oidi.getAllOrdersItemByUserId(userId);
		 
		 request.setAttribute("odList", odList);
		 request.setAttribute("odiList", odiList);
		 

	
		RequestDispatcher rd = request.getRequestDispatcher("ordersHistroy.jsp");
		
		rd.forward(request, response);
	}

}
