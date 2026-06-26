<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.jiat.techmart.core.model.Category, java.util.List" %>
<html><head><title>Categories</title></head><body>
<h2>Categories</h2>

<%
    List<Category> list = (List<Category>) request.getAttribute("categories");
    if(list != null){
        for(Category c : list){
            out.println(c.getId() + " | " + c.getName() + " | " + c.getDescription() + "<br>");
        }
    }
%>

</body></html>