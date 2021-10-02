<%@page import="uts.asd.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Page</title>
        <jsp:include page="navCustomer.jsp"/> 
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            String updated = (String) session.getAttribute("updated");
        %>

        <br>
        <div>
            <p>Your current account details are:</p>
            <form action="UpdateServlet" method="post">
                <input type="hidden" name="ID" value="${user.id}">
                <table>
                    <tr>
                        <td>First Name</td><td><input type="text" name="fname" value="${user.firstName}" required></td>
                    </tr>
                    <tr>
                        <td>Last Name</td><td><input type="text" name="lname" value="${user.lastName}" required></td>
                    </tr>
                    <tr>
                        <td>Email</td><td><input type="text" name="email" value="${user.email}" required></td>
                    </tr>
                    <tr>
                        <td>Phone</td><td><input type="text" name="phone" value="${user.phoneNum}"></td>
                    </tr>
                    <tr>
                        <td>Password</td><td><input type="text" name="password" value="${user.password}" required></td>
                    </tr>
                    <tr>
                        <td>Address</td><td><input type="text" name="address" value="${user.address}"></td>
                    </tr>
                    <tr>
                        <td>Account Type</td><td><input type="text" name="type" value="${user.type}" required></td>
                    </tr>
                </table>
                <p>Please edit the fields above to update any information.</p>
                <p><input type="submit" value="update"></p>
            </form>
            <p><%= updated != null ? updated : ""%></p>
        </div>
    </body>
</html>
