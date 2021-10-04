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
            String msg = (String) session.getAttribute("deleteroomsmsg");
            if(msg!=null) {
                %>
                    <div class="alert alert-primary" role="alert">
                        <%=msg%>
                    </div>
                <%
            }
        %>

        <h1>Delete Rooms</h1>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <form action="AdminDeleteRoomsServlet" method="POST">
                <table class='table'>
                    <tr>
                        <td>Number Range: </td>
                        <td>Lower bound (inclusive): <input type="number" placeholder="0" name="lowerbound"></td>
                        <td>Upper bound (inclusive): <input type="number" placeholder="999" name="upperbound"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="submit" class="button"></td>
                        <td><a href="admin_room_management.jsp" class="button">Back</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>