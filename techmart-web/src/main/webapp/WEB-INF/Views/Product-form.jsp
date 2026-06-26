<%--
  Created by IntelliJ IDEA.
  User: pawan
  Date: 6/25/2026
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="lk.jiat.techmart.core.model.Category, java.util.List"%>

<html>
<head>
    <title>${product.id == null ? 'Add' : 'Edit'}Product</title>
</head>
<body>

<h2>${product.id == null ? 'Add' : 'Edit'} Product </h2>

<form method="post" action="products">
    <input type="hidden" name="id" value="${product.id}">

    Name: <input type="text" name="name" value="${product.name}" required> <br><br>
    Description: <input type="text" name="description" value="${product.description}" required> <br><br>
    price: <input type="number" steps="0.01" name="price" value="${product.price}" required> <br><br>
    image URL: <input type="text" name="imageUrl" value="${product.imageUrl}" required> <br><br>

    Category:
    <select name="categoryId" required>
        <option value="">-- Select Category --</option>
        <%
            List<Category> cats = (List<Category>) request.getAttribute("categories");
            if(cats != null){
                for(Category c : cats){
                    out.println("<option value='"+c.getId()+"'>"+c.getName()+"</option>");
                }
            }
        %>
    </select><br>


    <input type="submit" value="Save">

</form>

<a href="products" >Cancel</a>



</body>
</html>
