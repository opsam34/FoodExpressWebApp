<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.opsam.models.Orders" %>
<%@ page import="com.opsam.models.User" %>
<%@ page import="com.opsam.models.Cart" %>
<%@ page import="com.opsam.models.CartItem" %>
<%@ page import="com.opsam.models.Restaurant" %>
<%@ page import="com.opsam.DaoImpl.RestaurantDaoImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Confirmed - FoodExpress</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/orders.css">
    
</head>
<body>

<nav>
        <a href="GetAllRestaurants" class="Logo">FoodExpress</a>
        <div class="nav-Links">
            <a href="GetAllRestaurants">Home</a>
            <a href="OrdersHistory">Orders</a>
	<!-- 		<a href="cart?action=show">Cart</a> -->
            <a href="profile.jsp">Profile</a>
        </div>
    </nav>

    <%
        // Get session objects
        Orders orders = (Orders) session.getAttribute("orders");
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");
        int restaurantId = (int) session.getAttribute("restaurantId");
        
		RestaurantDaoImpl rdi = new RestaurantDaoImpl();
		Restaurant restaurant=rdi.getRestaurant(restaurantId);

    %>
    
    
        <% if (orders != null) { %>
        <div class="container">
        <!-- Header Section -->
        <div class="header">
            <div class="success-icon">
                <i class="fas fa-check-circle"></i>
            </div>
            <h1>Order Confirmed!</h1>
            <p>Thank you for your order. We're preparing your delicious meal!</p>
        </div>
        
            <!-- Content Section -->
            <div class="content">
                <!-- Order Information -->
                <div class="order-info">
                    <div class="info-card">
                        <h3><i class="fas fa-receipt"></i> Order Details</h3>
                       <%--  <p><strong>Order ID:</strong> #<%= orders.getOrderId() %></p> --%>
                        <p><strong>Order Date:</strong> <%= orders.getOrderDate() %></p>
                        <p><strong>Payment Mode:</strong> <%= orders.getPaymentMode() %></p>
                        <p><strong>Order Status:</strong> 
                            <span style="color: #4CAF50; font-weight: 600; text-transform: capitalize;">
                                <%= orders.getStatus() %>
                            </span>
                        </p>
                        <p><strong>Estimated Delivery:</strong> 30-45 minutes</p>
                    </div>

                    <div class="info-card">
                        <h3><i class="fas fa-map-marker-alt"></i> Delivery Address</h3>
                        <p><strong>Delivery Address:</strong></p>
                        <p><%= orders.getAddress() %></p>
                        <% if (user != null) { %>
                            <p><strong>User ID:</strong> <%= user.getUserId() %></p>
                            <p><strong>User Name:</strong> <%= user.getName() %></p>
                            <p><strong>User Email:</strong> <%= user.getEmail() %></p>
                            <p><strong>User Phone:</strong> <%= user.getPhone() %></p>
                            
                        <% } %>
                    </div>
                </div>
                
                <!-- Order Items -->
                <div class="order-items">
                    <h3>Your Order</h3>
                    
                    <% if (cart != null) { %>
                        <!-- Display cart items if available -->
                        <!-- You'll need to iterate through cart items here based on your Cart model -->
                         <% for (CartItem item : cart.getItems().values()) { %>
                        <div class="item">
                            <div class="item-details">
                                <div class="item-name">Item: <%= item.getName() %></div>
                    
                            </div>
                            <div class="item-quantity">Quantity: <%= item.getQuantity() %></div>
                            <div class="item-price">Total: ₹<%= item.getPrice()*item.getQuantity() %></div>
                        </div>
                    <% } 
                         }
                    %>
                </div>

                <!-- Order Summary -->
                <div class="order-summary">
                    <div class="summary-row">
                        <span>Subtotal:</span>
                        <span>₹<%= orders.getTotalAmount() %></span>
                    </div>
                    <div class="summary-row">
                        <span>Delivery Fee:</span>
                        <span>₹40</span>
                    </div>
                    <div class="summary-row total">
                        <span>Total Amount:</span>
                        <span>₹<%= orders.getTotalAmount() + 40 %></span>
                    </div>
                </div>

                <!-- Order Tracking -->
                <div class="tracking-section">
                    <h3 style="margin-bottom: 15px; color: #333;">
                        <i class="fas fa-truck"></i> Order Tracking
                    </h3>
                    <p style="color: #666; margin-bottom: 20px;">
                        Your order is being prepared. Track the progress below:
                    </p>
                    
                    <div class="tracking-steps">
                        <div class="step completed">
                            <div class="step-icon">
                                <i class="fas fa-check"></i>
                            </div>
                            <div class="step-text">Order Placed</div>
                        </div>
                        <div class="step <%= orders.getStatus().equals("preparing") ? "active" : "" %>">
                            <div class="step-icon">
                                <i class="fas fa-utensils"></i>
                            </div>
                            <div class="step-text">Preparing</div>
                        </div>
                        <div class="step <%= orders.getStatus().equals("out_for_delivery") ? "active" : "" %>">
                            <div class="step-icon">
                                <i class="fas fa-motorcycle"></i>
                            </div>
                            <div class="step-text">Out for Delivery</div>
                        </div>
                        <div class="step <%= orders.getStatus().equals("delivered") ? "active" : "" %>">
                            <div class="step-icon">
                                <i class="fas fa-home"></i>
                            </div>
                            <div class="step-text">Delivered</div>
                        </div>
                    </div>
                </div>

                <!-- Restaurant Info -->
                <div class="info-card" style="margin-bottom: 30px;">
                    <h3><i class="fas fa-store"></i> Restaurant Details</h3>
                    <p><strong>Restaurant ID:</strong> <%= orders.getRestaurantId() %></p>
                    <p><strong>Restaurant:</strong> <%= restaurant.getName() %> </p>
                    <p><strong>Address:</strong>  <%= restaurant.getAddress() %></p>
                    <p><strong>Contact:</strong>  <%= restaurant.getPhone() %></p>
                </div>

                <!-- Action Buttons -->
                <div class="actions">
                   
                    <a href="OrdersHistory" class="btn btn-secondary">
                        <i class="fas fa-history"></i> Order History
                    </a>
                </div>

                <!-- Footer Note -->
                <div class="footer-note">
                    <p><i class="fas fa-info-circle"></i> 
                    You will receive SMS and email updates about your order status. 
                    For any issues, contact our support team at +91 1800 123 4567</p>
                </div>
            </div>
            
            <%
                // Clean up session after displaying order confirmation
                session.removeAttribute("cart"); 
                session.removeAttribute("orders"); 
            %>
            
        <% } else { %>
            <!-- No order found in session -->
            <div class="content">
                <div class="error-message">
                    <h2>No Order Found</h2>
                    <p>Sorry, we couldn't find your order details. Please try placing your order again.</p>
                    <div class="actions" style="margin-top: 30px;">
                        <a href="menu.jsp" class="btn btn-primary">
                            <i class="fas fa-utensils"></i> Back to Menu
                        </a>
                    </div>
                </div>
            </div>
        <% } %>
    </div>
</body>
</html>