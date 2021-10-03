<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Deleted Page</title>
    </head>
    <body>
        <div>
            <h1><a href="index.jsp">Four Semester Hotel</a></h1>
        </div>
        <br>
        <div>
            <p>Account successfully deleted. Click <a href="index.jsp">here</a> to return to the home page.</p>
        </div>
        <jsp:include page="/ConnServlet" flush="true" />
    </body>
</html>
