<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.opsam.models.Cart" %>
<%@ page import="com.opsam.models.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
  	<link rel="stylesheet" href="css/cart.css">
</head>
<body>
	<nav>
        <a href="GetAllRestaurants" class="Logo">FoodExpress</a>
        <div class="nav-Links">
            <a href="GetAllRestaurants">Home</a>
            <a href="OrdersHistory">Orders</a>
            <a href="profile.jsp">Profile</a>
        </div>
    </nav>
    <div class="container">
        <h1>Your Shopping Cart</h1>
        <%
            // Get cart and restaurant ID from session
            Cart cart = (Cart) session.getAttribute("cart");
            Integer restaurantId = (Integer) session.getAttribute("restaurantId");
            
            // Check if cart exists and is not empty
            if (cart != null && !cart.getItems().isEmpty()) {
        %>
            <!-- Display cart items -->
            <% for (CartItem item : cart.getItems().values()) { %>
                <div class="cart-item">
                    <div class="cart-item-details">
                        <h3><%= item.getName() %></h3>
                        <p>Price: ₹<%= item.getPrice() %></p>
                        <p>Total: ₹<%= item.getPrice()*item.getQuantity() %></p>
                    </div>
                    
                    <div class="quantity-controls">
                        <!-- Increase quantity form -->
                        <form action="cart" method="post" style="display: inline;">
                            <input type="hidden" name="itemId" value="<%= item.getId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                            <button class="quantity-btn">+</button>
                        </form>
                        
                        <p class="quantity-display"><%= item.getQuantity() %></p>
                        
                        <!-- Decrease quantity form -->
                        <form action="cart" method="post" style="display: inline;">
                            <input type="hidden" name="itemId" value="<%= item.getId() %>">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                            <button class="quantity-btn" <% if(item.getQuantity() == 1) { %> disabled <% } %> >-</button>
                        </form>
                    </div>
                    
                    <!-- Remove item form -->
                    <form action="cart" method="post" style="margin-top: 10px;">
                        <input type="hidden" name="itemId" value="<%= item.getId() %>">
                        <input type="hidden" name="action" value="remove">
                        <button class="remove-btn">Remove</button>
                    </form>
                </div>
            <%
            }
            
            %>
            
            <!-- Display grand total -->
            <div class="total">
                Grand Total: ₹<%= cart.getTotalPrice()%>
            </div>
            
            <!-- Add more items link -->
            <div class="add-more-items">
                <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add">
                    Add More Items
                </a>
            </div>
            
            <!-- Proceed to checkout -->
            <% if (session.getAttribute("cart") != null) { %>
                <form action="checkout.jsp" method="post">
                    <input type="submit" value="Proceed to Checkout" class="proceed-to-checkout-btn">
                </form>
            <% } %>
            
        <% } else { %>
            <!-- Empty cart message -->
            <p class="empty-cart">Your cart is empty.</p>
            <div class="add-more-items">
                <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn add">
                    Add Items
                </a>
            </div>
        <% } %>
        
        <!-- Additional checkout form (if cart is not null) -->
        <% if (session.getAttribute("cart") != null) { %>
            <!-- This section can be used for additional checkout options or information -->
        <% } %>
    </div>

</body>
</html>