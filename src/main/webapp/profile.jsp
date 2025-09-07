<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.opsam.models.User" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
        <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>
<body>

	<nav>
        <a href="GetAllRestaurants" class="Logo">FoodExpress</a>
        <div class="nav-Links">
            <a href="GetAllRestaurants">Home</a>
            <a href="login.jsp">logout</a>
        </div>
    </nav>
    <%
        // Get user object from session
        User user = (User) session.getAttribute("user");
        
        // Check if user is logged in
        if (user == null) {
    %>
        <div class="profile-container">
            <div class="login-prompt">
                <h2>Please Log In</h2>
                <p>You need to be logged in to view your profile.</p>
                <a href="login.jsp" class="action-btn">Go to Login</a>
            </div>
        </div>
    <%
        } else {
            // Cast to your User class - replace 'User' with your actual class name
            // User user = (User) userObj;
            
            // For demonstration, we'll use scriptlet to access the object
            // In a real application, you might want to use JSTL/EL instead
    %>
        <div class="profile-container">
            <!-- Profile Header -->
            <div class="profile-header">
                <div class="profile-avatar">
                    <%
                        String name = "";
                        try {
                            name = (String) user.getName();
                        } catch (Exception e) {
                            name = "User";
                        }
                        String avatarLetter = name.length() > 0 ? name.substring(0, 1).toUpperCase() : "U";
                    %>
                    <%= avatarLetter %>
                </div>
                <div class="profile-name">
                    <%= name %>
                </div>
                <div class="profile-username">
                    @<%
                        try {
                            out.print(user.getUsername());
                        } catch (Exception e) {
                            out.print("username");
                        }
                    %>
                </div>
            </div>
            
            <!-- Profile Body -->
            <div class="profile-body">
                <!-- Account Information -->
                <div class="profile-section">
                    <div class="section-title">Account Information</div>
                    
                    <div class="profile-field">
                        <div class="field-label">User ID:</div>
                        <div class="field-value"><%=user.getUserId() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Name:</div>
                        <div class="field-value"><%=user.getName() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">User Name:</div>
                        <div class="field-value"><%=user.getUsername() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Password:</div>
                        <div class="field-value masked">••••••••</div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Email:</div>
                        <div class="field-value"><%=user.getEmail() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Phone Number:</div>
                        <div class="field-value"><%=user.getPhone() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Address:</div>
                        <div class="field-value"><%=user.getAddress() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Role:</div>
                        <div class="field-value"><%=user.getRole() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Created Date:</div>
                        <div class="field-value"><%=user.getCreateDate() %></div>
                    </div>
                    <div class="profile-field">
                        <div class="field-label">Login Date:</div>
                        <div class="field-value"><%=user.getLastLoginDate()%></div>
                    </div>
                </div>
            </div>
        </div>
    <%
        }
    %>
</body>
</html>