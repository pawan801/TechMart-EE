
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TechMart - All Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">TechMart Admin</a>
        <div class="navbar-nav">
            <a class="nav-link active" href="${pageContext.request.contextPath}/orders">Orders</a>
            <a class="nav-link" href="${pageContext.request.contextPath}/products">Products</a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>📦 All Customer Orders</h2>
        <span class="badge bg-secondary">Dummy Data Mode</span>
    </div>

    <c:choose>
        <c:when test="${empty orders}">
            <div class="alert alert-warning" role="alert">
                තාම Order එකක් නෑ.
            </div>
        </c:when>
        <c:otherwise>
            <div class="card shadow-sm">
                <div class="card-body">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                        <tr>
                            <th scope="col"># Order ID</th>
                            <th scope="col">Customer Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Total Amount</th>
                            <th scope="col">Order Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>${order.id}</td>
                                <td>${order.customerName}</td>
                                <td>${order.customerEmail}</td>
                                <td>
                                    <fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="LKR "/>
                                </td>
                                <td>
                                   ${order.orderDate}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>