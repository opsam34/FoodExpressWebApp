<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.opsam.DaoImpl.MenuDaoImpl" %>
<%@ page import="com.opsam.models.Menu" %>
<%@ page import="com.opsam.models.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodExpress - Menu</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" href="css/menu.css">
</head>
<body>
    <nav>
        <a href="GetAllRestaurants" class="Logo">FoodExpress</a>
        <div class="nav-Links">
            <a href="GetAllRestaurants">Home</a>
            <a href="OrdersHistory">Orders</a>
            <a href="cart?action=show">Cart</a>
            <a href="profile.jsp">Profile</a>
        </div>
    </nav>
    
    <a href="GetAllRestaurants" class="back-button">Back to Restaurants</a>
    
    <%
        Restaurant restaurant = (Restaurant) request.getAttribute("restList");
        if (restaurant != null) {
    %>
    <div class="restaurant-header">
        <h1 class="restaurant-title"><%= restaurant.getName() %></h1>
        <div class="restaurant-info">
            <div class="info-item">
                <span class="info-label">Location</span>
                <span class="info-value">📍 <%= restaurant.getAddress() %></span>
            </div>
            <div class="info-item">
                <span class="info-label">Rating</span>
                <span class="info-value">⭐ <%= restaurant.getRating() %> / 5</span>
            </div>
            <div class="info-item">
                <span class="info-label">Cuisine</span>
                <span class="info-value">🍽️ <%= restaurant.getCusineType() %></span>
            </div>
        </div>
    </div>
    <% } %>
    
    <h2 class="section-header">Our Delicious Menu</h2>
    
    <div class="menu-container">
        <%
            List<Menu> menuItems = (List<Menu>) request.getAttribute("menuList");
            
            if (menuItems != null && !menuItems.isEmpty()) {
                for (Menu item : menuItems) {
        %>
            <div class="menu-item-card">
                <div class="menu-image-container">
                    <img src="<%= request.getContextPath() %>/<%= item.getImagePath() != null && !item.getImagePath().isEmpty() ? item.getImagePath() : "images/default-food.jpg" %>" 
                         alt="<%= item.getItemName() %>" 
                         class="menu-image"
                         onerror="this.style.display='none'; this.parentNode.querySelector('.image-placeholder').style.display='flex';">
                    <div class="image-placeholder" style="display: none;">
                        🍽️
                        <div class="image-placeholder-text">Food Image</div>
                    </div>
                </div>
                <div class="menu-details">
                    <div>
                        <div class="menu-name"><%= item.getItemName() %></div>
                        <div class="menu-description"><%= item.getDescription() != null && !item.getDescription().isEmpty() ? item.getDescription() : "Delicious item from our kitchen" %></div>
                    </div>
                    <div>
                        <div class="menu-price"><%= item.getPrice() %></div>
                        <form action="cart" method="post">
                            <input type="hidden" name="itemId" value="<%= item.getMenuId() %>">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="restaurantId" value="<%= item.getRestaurantId() %>">
                            <input type="hidden" name="action" value="add">
                            <input type="submit" value="Add To Cart">
                        </form>
                    </div>
                </div>
            </div>
        <%
                }
            } else {
        %>
            <div class="no-menu-message">
                <h2>🍽️ Menu Coming Soon</h2>
                <p>This restaurant is currently updating their menu. Please check back shortly!</p>
            </div>
        <%
            }
        %>
    </div>

    <footer>
        <p>&copy; 2025 FoodExpress. All rights reserved. | <a href="#">Privacy Policy</a> | <a href="#">Terms of Service</a></p>
    </footer>
</body>
</html>