
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
            String submitted = request.getParameter("submitted");
            AdminDBManager db = (AdminDBManager)session.getAttribute("adminmngr");
            if(db == null) {
                db = new AdminDBManager(new DBConnector().openConnection());
                session.setAttribute("adminmngr", db);
                out.println("Admin manager does not exist, creating one");
            }
            
            ArrayList<User> userList = (ArrayList<User>)session.getAttribute("userList");
            
            if(userList == null)
                userList = db.fetchAllUsers();
            
        %>

        <%--
            ArrayList<User> userList = (ArrayList<User>)session.getAttribute("userList");
            
            if(userList == null) {
                DBManager db = (DBManager) session.getAttribute("manager");
                userList = db.fetchAllUsers();
            }
        --%>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table>
                <form action="AdminUserManagementServlet" method="POST">
                    <th>Search User</th>
                    <tr>
                        <td>First Name</td>
                        <td><input type="text" placeholder="Joe" name="firstname"></td>
                        <input type="hidden" value="yes" name="submitted">
                        <td><input type="submit" value="submit" class="button"></td>
                        <td><a href="admin_create_user.jsp" class="button">Create user</a></td>
                    </tr>
                </form>
            </table>
        </div>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table class='table'>
                    <tr>
                        <td>ID</td>
                        <td>Full name</td>
                        <td>Email</td>
                        <td>Phone Number</td>
                    </tr>
                    <%
                        for(User user : userList) {
                    %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= (user.getFirstName() + " " + user.getLastName()) %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getPhoneNum() %></td>
                        <td>
                            <%--EDIT USER BUTTON--%>
                            <form action="admin_update_user.jsp" method="post">
                                <input type="hidden" name="id" value="<%=user.getId()%>">
                                <input type="submit" value="Edit" class="button">
                            </form>
                        </td>
                        <td>
                            <%--DELETE USER BUTTON--%>
                            <form action="AdminDeleteUserServlet" method="post">
                                <input type="hidden" name="id" value="<%=user.getId()%>">
                                <input type="submit" value="Delete" class="button">
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
            </table>
        </div>
    </body>
</html>