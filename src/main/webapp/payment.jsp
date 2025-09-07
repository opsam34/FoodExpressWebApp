<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Student Store</title>
    <link href="https://fonts.cdnfonts.com/css/kobo" rel="stylesheet">
    <link rel="stylesheet" href="css/payment.css">
    <script src="https://checkout.razorpay.com/v1/checkout.js"></script>
</head>
<body>
	<%
		if (session.getAttribute("orders") == null) {
    		response.sendRedirect("checkout.jsp");
    		return;
		}

		String amount = (String) session.getAttribute("amount");
		String orderIdR = (String) session.getAttribute("orderIdR");
		String KEY_ID = (String) session.getAttribute("KEY_ID");
	%>

<div class="payment-container">
    <h2>Complete Your Payment</h2>
    
    <div class="amount-display">
        <p><strong>₹<%= amount %></strong></p>
    </div>
    
    <form name="razorpayform" action="PaymentSuccessServlet" method="POST">
        <input type="hidden" name="razorpay_payment_id" id="razorpay_payment_id" />
        <input type="hidden" name="razorpay_order_id" id="razorpay_order_id" />
        <input type="hidden" name="razorpay_signature" id="razorpay_signature" />
    </form>
    
    <button class="pay-button" onclick="openRazorpay()">
        Pay ₹<%= amount %>
    </button>
    
    <div class="security-info">
        <p>Secure payment powered by Razorpay</p>
    </div>
</div>

<script>
function openRazorpay() {
    var options = {
        key: '<%= KEY_ID %>', // Replace with your Key ID
        amount: <%= Integer.parseInt(amount) * 100 %>,
        currency: 'INR',
        name: 'Student Store',
        description: 'Test Payment',
        order_id: '<%= orderIdR %>',
        handler: function (response){
            document.getElementById('razorpay_payment_id').value = response.razorpay_payment_id;
            document.getElementById('razorpay_order_id').value = response.razorpay_order_id;
            document.getElementById('razorpay_signature').value = response.razorpay_signature;
            document.forms['razorpayform'].submit();
        },
        theme: {
            color: '#528ff0'
        }
    };

    var rzp1 = new Razorpay(options);
    rzp1.open();
}
</script>
</body>
</html>