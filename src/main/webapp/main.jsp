<%@page import="uts.asd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Main Page</title>
        <jsp:include page="navMain.jsp"/> 
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
        %>
        <div>
            <h2>Welcome,&nbsp;<%= user.getType().equals("s") ? "Staff Member " + user.getFirstName() : user.getType().equals("a") ? "System Admin" + user.getFirstName() : user.getFirstName() %></h2>
        </div>
    </body>
</html>