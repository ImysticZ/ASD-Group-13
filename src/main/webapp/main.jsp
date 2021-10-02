<%@page import="uts.asd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            if(user.getType().equals("s")){ 
            %>
                <%@ include file="navStaff.jsp" %>
            <% 
            } else if(user.getType().equals("a")){ 
            %>
                <%@ include file="navAdmin.jsp" %>
            <%
            } else {
            %>
                <%@ include file="navCustomer.jsp" %>
            <% 
            } 
        %>
        <div>
            <h2>Welcome,&nbsp;<%= user.getType().equals("s") ? "Staff Member " + user.getFirstName() : user.getType().equals("a") ? "System Admin" + user.getFirstName() : user.getFirstName() %></h2>
        </div>
    </body>
</html>