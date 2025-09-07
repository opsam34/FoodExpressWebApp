package com.opsam.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PaymentSuccessServlet")
public class PaymentSuccessServlet extends HttpServlet {
	private static final String KEY_SECRET = System.getenv("KEY_SECRET");
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			/*
			 * HttpSession session = request.getSession(); String KEY_SECRET = (String)
			 * session.getAttribute("KEY_SECRET");
			 */
			String paymentId = request.getParameter("razorpay_payment_id");
			String orderId = request.getParameter("razorpay_order_id");
			String signature = request.getParameter("razorpay_signature");


			//			System.out.println(paymentId+ orderId +signature);

			// Verify payment signature

			if (verifyPaymentSignature(orderId, paymentId, signature)) { // Payment
				//verified successfully

				response.sendRedirect("orders.jsp");
			} else {
				System.out.println("Payment verification failed!");
				response.sendRedirect("checkout.jsp?error=Payment verification failed"); }


		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("checkout.jsp?error=Payment processing failed");

		}
	}


	private boolean verifyPaymentSignature(String orderId, String paymentId, String signature) {
		try {


			// Add null check for KEY_SECRET
			//			String KEY_SECRET = (String) session.getAttribute("KEY_SECRET");
			if (KEY_SECRET == null || KEY_SECRET.isEmpty()) {
				System.out.println("KEY_SECRET is null or empty!");
				return false;
			}

			// Create the data string
			String data = orderId + "|" + paymentId;


			// Create HMAC SHA256 signature
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(KEY_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			mac.init(secretKey);

			byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

			// Convert to hex string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}

			String calculatedSignature = hexString.toString();

			// Compare signatures
			return calculatedSignature.equals(signature);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}

}
