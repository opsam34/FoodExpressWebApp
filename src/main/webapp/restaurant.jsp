<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.opsam.DaoImpl.RestaurantDaoImpl" %>
<%@ page import="com.opsam.models.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FoodExpress - Explore Restaurants</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" href="css/restaurant.css">
    
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
    
    <h1>Explore Restaurants</h1>
    
    <!-- Search Section -->
    <form class="search-container" method="GET" action="searchRestaurants">
        <div class="search-wrapper">
            <span class="search-icon">🔍</span>
            <input type="text" class="search-input" name="searchQuery" placeholder="Search restaurants, cuisine, or location...">
        </div>
        <button class="search-btn" type="submit">
            Search
        </button>
    </form>
    
    <div class="grid-container" id="restaurantGrid">
        <%
            List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restList");
            
            // Debug: Check if attribute exists and list size
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant restaurant : restaurants) {
        %>
            <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="restaurant-link">
                <div class="restaurant-card">
                    <img src="<%= restaurant.getImagePath() %>" alt="<%= restaurant.getName() %> Restaurant" class="restaurant-image">
                    <div class="restaurant-details">
                        <div class="restaurant-name"><%= restaurant.getName() %></div>
                        <div class="restaurant-location"><%= restaurant.getAddress() %></div>
                        <div class="restaurant-badges">
                            <div class="restaurant-rating"><%= restaurant.getRating() %> / 5</div>
                            <div class="restaurant-cuisine-type"><%= restaurant.getCusineType() %></div>
                        </div>
                    </div>
                </div>
            </a>
       <%
    			}
           }else {
		%>
    <div class="no-restaurants-message">
        <h2>🍽️ No Restaurants Available</h2>
        <p>We're currently updating our restaurant listings. Please check back shortly!</p>
    </div>
<%
    }
%>
      
    </div>


</body>
</html>