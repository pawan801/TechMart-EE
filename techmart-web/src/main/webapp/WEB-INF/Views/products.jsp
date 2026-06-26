<%@ page contentType="text/html;charset-UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="lk.jiat.techmart.core.model.Product, java.util.List" %>
<%@ page import="lk.jiat.techmart.core.model.Product, lk.jiat.techmart.core.model.Category, lk.jiat.techmart.ejb.CategoryService, java.util.List" %>
<! DOCKTYPE html>

<%--02--%>

<html>
   <head>
       <title>Products - TechMart</title>
<%--       <style>--%>
<%--           table {border-collapse: collapse; width : 100%; margin-top: 20px;}--%>
<%--           th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}--%>
<%--           th {background-color: #4CAF50; color : white;}--%>
<%--           img {width: 50px; height: 50px; object-fit: cover;}--%>
<%--           .btn{padding: 5px 10px; text-decoration : none; color:white; border-radius: 4px;}--%>
<%--           .btn-add {background: #219800;}--%>
<%--           .btn-del {background: #f44336;}--%>
<%--       </style>--%>
   </head>
<body>


<%--   01 --%>

<%--  <h2>Product List</h2>--%>
<%--<a href="products?action=new" class="btn btn-add"> +Add New Product</a>--%>
<%--  <table>--%>
<%--    <tr>--%>
<%--        <th>ID</th><th>Image</th><th>Name</th><th>Price</th><th>Description</th><th>Action</th>--%>
<%--    </tr>--%>
<%--      <p>Debug: ${products[0].class.name}</p>--%>
<%--    <c:forEach var="p"  items="&{products}">--%>
<%--        <tr>--%>
<%--            <td>${p.id}</td>--%>
<%--            <td><img src="${p.imageUrl}" alt="${p.name}"</td>--%>
<%--            <td>${p.name}</td>--%>
<%--            <td>${p.price}</td>--%>
<%--            <td>${p.description}</td>--%>
<%--            <td>--%>
<%--                <a href="products?action=edit&id=${p.id}" class="btn btn-edit">Edit</a>--%>
<%--                <a href="products?action=delete&id=${p.id}" class="btn btn-del" onclick="return confirm('Delete ${p.name}?')">Delete</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<%-- 02--%>
<%--<h3>Test</h3>--%>
<%--<%--%>
<%--    List<Product> list = (List<Product>) request.getAttribute("products");--%>
<%--    if(list != null && !list.isEmpty()){--%>
<%--        for(Product p : list){--%>
<%--            out.println(p.getId() + " | " + p.getName() + " | Rs." + p.getPrice() + "<br>");--%>
<%--        }--%>
<%--    } else {--%>
<%--        out.println("No products yet");--%>
<%--    }--%>
<%--%>--%>
<%--<a href="products?action=new">+ Add New Product</a>--%>

<%--</body>--%>
<%--</html>--%>



<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    CategoryService catService = new CategoryService();
%>

<h2>Products</h2>
<a href="${pageContext.request.contextPath}/products?action=new">Add Product</a>
<a href="${pageContext.request.contextPath}/cart">Cart</a>
|
<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
    <%
        if(products != null){
            for(Product p : products){
                Category cat = catService.getCategoryById(p.getCategoryId());
                String catName = (cat != null) ? cat.getName() : "N/A";
    %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getPrice() %></td>
        <td><%= catName %></td>
        <td>
            <a href="${pageContext.request.contextPath}/products?action=edit&id=<%=p.getId()%>">Edit</a> |
            <a href="${pageContext.request.contextPath}/products?action=delete&id=<%=p.getId()%>"
               onclick="return confirm('Delete <%=p.getName()%>?');">Delete</a>
            <a href="<%= request.getContextPath() %>/cart?action=add&id=<%= p.getId() %>">Add to Cart</a>

        </td>
    </tr>
    <%
            }
        }
    %>
</table>
