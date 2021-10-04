<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page import="java.util.*"%>
<%@page import="uts.asd.model.dao.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>

        <%
            AdminDBManager db = (AdminDBManager)session.getAttribute("adminmngr");
            if(db == null) {
                db = new AdminDBManager(new DBConnector().openConnection());
                session.setAttribute("adminmngr", db);
            }
            String msg = (String) session.getAttribute("editusermsg");
            if(msg!=null) {
                %>
                    <div class="alert alert-primary" role="alert">
                        <%=msg%>
                    </div>
                <%
            }

            String idStr =request.getParameter("id");
            int id = Integer.parseInt(idStr);
            User user = db.getUser(id);
        %>

        <h1>Update user</h1>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <form action="AdminUpdateUserServlet" method="POST">
            <input type="hidden" name="id" value="<%=user.getId()%>">
                <table class='table'>
                    <tr>
                        <td>First Name: </td>
                        <td><input type="text" value="<%=user.getFirstName()%>" name="firstname"></td>
                    </tr>
                    <tr>
                        <td>Last Name: </td>
                        <td><input type="text" value="<%=user.getLastName()%>" name="lastname"></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" value="<%=user.getEmail()%>" name="email"></td>
                    </tr>
                    <tr>
                        <td>Phone Number: </td>
                        <td><input type="text" value="<%=user.getPhoneNum()%>" name="phone"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" value="<%=user.getPassword()%>" name = password></td>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td><input type="text" value="<%=user.getAddress()%>" name="address"></td>
                    </tr>
                    <tr>
                        <td>Type: </td>
                        <td>
                            <select name="type">
                                <option value="c">Customer</option>
                                <option value="s">Staff</option>
                                <option value="a">Admin</option>
                              </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="submit" class="button"></td>
                        <td><a href="admin_user_management.jsp" class="button">Back</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>