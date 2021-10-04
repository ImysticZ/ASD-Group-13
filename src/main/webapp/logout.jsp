<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout Page</title>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>
        <div>
            <h1><a href="index.jsp">Four Seasons Hotel</a></h1>
            <a href="login.jsp">login</a>
            <a href="register.jsp">register</a>
        </div>
        <br>
        <div>
            <p>Successfully logged out.</p>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>