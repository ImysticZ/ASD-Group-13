<%@page import="uts.asd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
</head>
<%
        String existErr = (String) session.getAttribute("existErr");
        String emailErr = (String) session.getAttribute("emailErr");
        String passErr = (String) session.getAttribute("passErr");
        String fnameErr = (String) session.getAttribute("fnameErr");
        String lnameErr = (String) session.getAttribute("lnameErr");
        String phoneErr = (String) session.getAttribute("phoneErr");
        String addressErr = (String) session.getAttribute("addressErr");
        String typeErr = (String) session.getAttribute("typeErr");
%>
<body>
        <div>
            <h1><a href="index.jsp">Four Seasons Hotel</a></h1>
            <a href="login.jsp">login</a>
            <a href="register.jsp">register</a>
        </div>
        <br>
        <div>
            <form action=RegisterServlet method="post">
                <table>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" placeholder="<%= fnameErr != null ? fnameErr : "Enter First Name"%>" name="first_name" required></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><input type="text" placeholder="<%= lnameErr != null ? lnameErr : "Enter Last Name"%>" name="last_name" required></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><input type="text" placeholder="<%= emailErr != null ? emailErr : "Enter Email"%>" name="email" required></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" placeholder="<%= phoneErr != null ? phoneErr : "Enter Phone"%>" name="phone"></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="text" placeholder="<%= passErr != null ? passErr : "Enter Password"%>" name="password" required></td>
                    </tr>
                    <tr>
                        <td>Address</td>
                        <td><input type="text" placeholder="<%= addressErr != null ? addressErr : "Enter First Name"%>" name="address" required></td>
                    </tr>
                    <tr>
                        <td>User type</td>
                        <td><input type="text" placeholder="<%= typeErr != null ? typeErr : "Enter Type"%>" name="type"></td>
                    </tr>
                </table>
                <input type="submit" value="sign up">
            </form>
            <p><%= existErr != null ? existErr : ""%></p>
        </div>
    </body>
</html>