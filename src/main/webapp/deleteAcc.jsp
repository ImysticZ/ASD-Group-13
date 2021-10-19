<%@page import="uts.asd.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
        <jsp:include page="nav.jsp"/> 
    </head>
    <%
        String passErr = (String) session.getAttribute("passErr");
    %>
    <body>
        <div>
            <p>Are you sure you want to delete your account?</p>
            <form action="DeleteAccServlet" method="post">
                <input type="submit" value="confirm">
            </form>
        </div>
        <br>
    </body>
</html>
