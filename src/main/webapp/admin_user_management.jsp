
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>

        <%
            Random random = new Random();

            ArrayList<User> UserList = new ArrayList<User>();
            for(int i = 0; i < 10; i++) {
                UserList.add(new User(random.nextInt(99999), ("FirstName" + i), ("LastName" + i), ("Email" + i), "000000000", "s", ("Address" + i), "a"));
            }

            
        %>

        <div>
            <table>
                <form action="AdminManagementServlet" method="GET">
                    <tr>
                        <td>E-mail address</td>
                        <td><input type="text" placeholder="name@something.com" name="email"></td>
                        <td><input type="submit" value="submit" class="button"></td>
                    </tr>
                </form>
            </table>
        </div>

        <div>
            <table>
                    <tr>
                        <td>ID</td>
                        <td>Full name</td>
                        <td>Email</td>
                        <td>Phone Number</td>
                    </tr>
                    <%
                        for(User user : UserList) {
                    %>
                    <tr>
                        <td><%= user.getId() %></td>
                        <td><%= (user.getFirstName() + " " + user.getLastName()) %></td>
                        <td><%= user.getEmail() %>l</td>
                        <td><%= user.getPhoneNum() %></td>
                    </tr>
                    <%
                        }
                    %>
            </table>
        </div>
    </body>
</html>