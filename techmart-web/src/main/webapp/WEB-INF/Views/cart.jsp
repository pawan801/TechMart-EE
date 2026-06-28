<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.jiat.techmart.core.model.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<html>
<head>
    <title>Your Cart - TechMart</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px auto; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .total { text-align: right; font-size: 1.2em; font-weight: bold; margin-right: 10%; }
        .empty { text-align: center; padding: 40px; font-size: 1.1em; }
    </style>
</head>
<body>
<h2 style="text-align:center;">🛒 Your Shopping Cart</h2>

<%
    List<CartItem> cart = (List<CartItem>) request.getAttribute("cart");
    BigDecimal total = (BigDecimal) request.getAttribute("cartTotal");

    if (cart == null || cart.isEmpty()) {
%>
<div class="empty">Your cart is empty. <a href="${pageContext.request.contextPath}/products">Continue Shopping</a></div>
<%
} else {
%>
<table>
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th>Qty</th>
        <th>Subtotal</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for (CartItem item : cart) { %>
    <tr>
        <td><%= item.getProduct().getName() %></td>
        <td>Rs. <%= item.getProduct().getPrice() %></td>
        <td><%= item.getQuantity() %></td>
        <td>Rs. <%= item.getSubtotal() %></td>
        <td>
            <a href="${pageContext.request.contextPath}/cart?action=remove&id=<%= item.getProduct().getId() %>"
               onclick="return confirm('Remove <%= item.getProduct().getName() %>?');">Remove</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
<div class="total">Grand Total: Rs. <%= total %></div>
<div style="text-align:center; margin-top:20px;">
    <a href="${pageContext.request.contextPath}/products">Continue Shopping</a> |
    <a href="${pageContext.request.contextPath}/checkout">Checkout</a>
</div>
<%
    }
%>
</body>
</html>