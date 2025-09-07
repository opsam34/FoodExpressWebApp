package com.opsam.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

import org.json.JSONObject;

import com.opsam.models.Orders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PaymentProcessServlet")
public class PaymentProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String KEY_ID = System.getenv("KEY_ID");
	private static final String KEY_SECRET = System.getenv("KEY_SECRET");

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Orders orders = (Orders)session.getAttribute("orders");
		String amount=Integer.toString(orders.getTotalAmount()+40) ;
		String orderIdR = createRazorpayOrder(amount);

		session.setAttribute("amount", amount);
		session.setAttribute("KEY_ID", KEY_ID);
		session.setAttribute("KEY_SECRET", KEY_SECRET);
		session.setAttribute("orderIdR", orderIdR);




		if (orderIdR != null ) {
			request.getRequestDispatcher("payment.jsp").forward(request, response);
		} else {
			response.sendRedirect("checkout.jsp?error=Unable to create order. Please try again.");
		}

	}





	private String createRazorpayOrder(String amount) {
		try {
			// Razorpay API endpoint
			URL url = new URL("https://api.razorpay.com/v1/orders");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// Set request method and headers
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			// Set authentication (Basic Auth)
			String auth = KEY_ID + ":" + KEY_SECRET;
			String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
			conn.setRequestProperty("Authorization", "Basic " + encodedAuth);

			// Create order JSON
			JSONObject orderData = new JSONObject();
			orderData.put("amount", Integer.parseInt(amount) * 100); // Convert to paise
			orderData.put("currency", "INR");
			orderData.put("receipt", "order_" + System.currentTimeMillis());

			// Send request
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = orderData.toString().getBytes(StandardCharsets.UTF_8);
				os.write(input, 0, input.length);
			}

			// Read response
			int responseCode = conn.getResponseCode();
			InputStream inputStream;

			if (responseCode == 200) {
				inputStream = conn.getInputStream();
			} else {
				inputStream = conn.getErrorStream();
				System.out.println("Error Response Code: " + responseCode);
			}

			Scanner scanner = new Scanner(inputStream);
			StringBuilder response = new StringBuilder();
			while (scanner.hasNext()) {
				response.append(scanner.nextLine());
			}
			scanner.close();

			if (responseCode == 200) {
				JSONObject responseJson = new JSONObject(response.toString());
				return responseJson.getString("id");
			} else {
				System.out.println("Error Response: " + response.toString());
				return null;
			}

		} catch (Exception e) {
			System.out.println("Error creating Razorpay order: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

}
