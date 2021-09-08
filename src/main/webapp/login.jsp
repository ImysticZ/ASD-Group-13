<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>
        <%
            String existErr = (String) session.getAttribute("existErr");
            String emailErr = (String) session.getAttribute("emailErr");
            String passErr = (String) session.getAttribute("passErr");
        %>
        <%-- <div>
            <h1><a href="index.jsp">Four Seasons Hotel</a></h1>
            <a href="login.jsp">Login</a>
            <a href="register.jsp">Register</a>
        </div> --%>
        <br>
        <div>
            <form action ="LoginServlet" method ="post">
                <table>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" placeholder="<%= emailErr != null ? emailErr : "Enter Email"%>" name="email" required></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" placeholder="<%= passErr != null ? passErr : "Enter Password"%>" name="password" required></td>
                    </tr>
                </table>
                <input type="submit" value="login">
            </form>
            <p><%= existErr != null ? existErr : ""%></p>
        </div>

    </body>
</html>
