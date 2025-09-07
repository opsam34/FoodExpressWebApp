<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Create New Account</title>
<link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
<link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="registration-container">
        <h2>Create New Account</h2>
        <form action="RegisterServlet" method="post">

            <div class="form-group name">
                <label>Full Name:</label>
                <input type="text" name="name" placeholder="Enter Your Name" required>
            </div>

            <div class="form-group username">
                <label>Username:</label>
                <input type="text" name="username" placeholder="Choose a username" required>
            </div>

            <div class="form-group password">
                <label>Password:</label>
                <input type="password" name="password" placeholder="Create a password" required>
            </div>

            <div class="form-group email">
                <label>Email:</label>
                <input type="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="form-group phone">
                <label>Phone:</label>
                <input type="text" name="phone" placeholder="Enter Phone No " required>
            </div>

            <div class="form-group address">
                <label>Address:</label>
                <textarea name="address" rows="3" placeholder="Enter address" required></textarea>
            </div>

            <div class="form-group role">
                <label>Role:</label>
                <select name="role" required>
                    <option value="">-- Select Role --</option>
                    <option value="customer">Customer</option>
                    <option value="superAdmin">Admin</option>
                    <option value="restaurantAdmin">Restaurant Admin</option>
                    <option value="deliveryAgent">Delivery Agent</option>
                </select>
            </div>

            <button type="submit">Register</button>
        </form>

        <p>Already have an account? <a href="login.jsp">Login Here</a></p>
    </div>
</body>
</html>