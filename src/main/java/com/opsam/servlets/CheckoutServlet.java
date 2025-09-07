package com.opsam.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.opsam.DaoImpl.OrdersDaoImpl;
import com.opsam.DaoImpl.OrdersItemDaoImpl;
import com.opsam.models.Cart;
import com.opsam.models.CartItem;
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

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Orders orders;
	private OrdersDaoImpl odi;
	private OrdersItem ordersItem;
	private OrdersItemDaoImpl oidi; 
	
	@Override
	public void init() throws ServletException {
		try {
			odi = new OrdersDaoImpl();
			orders= new Orders();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user = (User) session.getAttribute("user");

		if(user==null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

		if(user!=null && cart != null && !cart.getItems().isEmpty()) {

			//ORDERS TABLE
			String paymentMode = request.getParameter("paymentMode");
			String address = request.getParameter("address");

			orders.setUserId(user.getUserId());
			orders.setRestaurantId((int) session.getAttribute("restaurantId"));
			orders.setOrderDate(new Timestamp(new Date().getTime()).toString());
			orders.setStatus("preparing");

			int totalAmount=0;
			for(CartItem item: cart.getItems().values()) {
				totalAmount+=item.getQuantity()*item.getPrice();
			}

			orders.setTotalAmount(totalAmount);
			orders.setPaymentMode(paymentMode);
			orders.setAddress(address);

			int orderId = odi.addOrders(orders);

			//ORDERSITEM TABLE
			for(CartItem item: cart.getItems().values()) {

				int itemId = item.getId();
				int quantity = item.getQuantity();
				int totalPrice = (item.getQuantity()*item.getPrice());


				ordersItem = new OrdersItem(orderId, itemId, quantity, totalPrice);

				oidi = new OrdersItemDaoImpl();
				oidi.addOrdersItem(ordersItem);


			}
			
			/* int od = orders.getOrderId(); */

			/* session.removeAttribute("cart"); */
			session.setAttribute("orders", orders);
			session.setAttribute("ordersItem", ordersItem);
			/* session.setAttribute("od", od); */

//			response.sendRedirect("orders.jsp");
//			response.sendRedirect("PaymentProcessServlet");
			
			RequestDispatcher rd = request.getRequestDispatcher("PaymentProcessServlet");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect("cart.jsp");
		}

	}

}
