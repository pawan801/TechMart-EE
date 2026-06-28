<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Checkout - TechMart</title></head>
<body>
<h2>Checkout</h2>
<c:if test="${not empty error}"><p style="color:red;">${error}</p></c:if>

<form action="${pageContext.request.contextPath}/checkout" method="post">
    <label>Full Name: <input type="text" name="customerName" required></label><br><br>
    <label>Email: <input type="email" name="customerEmail" required></label><br><br>

    <h3>Your Order Summary</h3>

    <button type="submit">Place Order - Cash on Delivery</button>
</form>
<a href="${pageContext.request.contextPath}/cart">Back to Cart</a>
</body>
</html>