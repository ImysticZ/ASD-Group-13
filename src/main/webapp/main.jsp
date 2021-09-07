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
        %>
        <div>
            <h1><a href="main.jsp">Four Seasons Hotel</a></h1>
            <a href="LogoutServlet">logout</a>
            <a href="main.jsp">main</a>
        </div>
        <br>
        <div>
            <h2>Welcome</h2>
        </div>
    </body>
</html>