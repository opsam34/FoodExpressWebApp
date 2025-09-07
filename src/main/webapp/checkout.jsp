<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
    <link rel="stylesheet" href="css/checkout.css"> 
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
</head>
<body>

    <!-- Navigation -->
    <nav>
        <a href="GetAllRestaurants" class="Logo">FoodExpress</a>
        <div class="nav-Links">
            <a href="GetAllRestaurants">Home</a>
            <a href="#">Orders</a>
			<a href="cart?action=show">Cart</a>
            <a href="profile.jsp">Profile</a>
        </div>
    </nav>
    
    
    <!-- Checkout Container -->
    <div class="checkout-container">
        <h1>Checkout</h1>
        <form action="checkout" method="post">

            <!-- Address -->
            <label for="address">Delivery Address</label>
            <textarea id="address" name="address" rows="2" required></textarea>

            <!-- Payment Mode -->
            <label for="paymentMode">Payment Mode</label>
            <select id="paymentMode" name="paymentMode" required>
                <option value="">-- Select Payment Mode --</option>
                <option value="cod">Cash on Delivery</option>
                <option value="upi">UPI</option>
                <option value="card">Credit / Debit Card</option>
                <option value="netbanking">Net Banking</option>
            </select>
            

            <!-- Place Order Button -->
            <button type="submit" class="place-order-btn">
                Place Order
            </button>
            
            
        </form>
        <br>
        
        
        <h6 style="color:red">NOTE:40 RUPEES DELIVERY CHARGE WILL BE ADDED</h6>
    </div>

</body>
</html>
