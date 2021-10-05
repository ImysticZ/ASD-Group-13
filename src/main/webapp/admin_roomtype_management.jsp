
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
            }
            
            String msg = (String) session.getAttribute("roomtypemsg");
            if(msg!=null) {
                %>
                    <div class="alert alert-primary" role="alert">
                        <%=msg%>
                    </div>
                <%
            }

            ArrayList<RoomType> roomTypeList = (ArrayList<RoomType>)session.getAttribute("roomTypeList");
            
            if(roomTypeList == null)
                roomTypeList = db.getRoomTypes();
            
        %>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table>
                <form action="AdminCreateRoomTypeServlet" method="POST">
                    <th>Add Room Type</th>
                    <tr>
                        <td>Suite</td>
                        <td><input type="text" placeholder="Single" name="suite"></td>
                    </tr>
                    <tr>
                        <td>Cost</td>
                        <td><input type="number" placeholder="100.00" step=".01" name="cost"></td>
                        <td>Number of beds</td>
                        <td><input type="number" placeholder="1"  name="numberofbeds"></td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td><input type="text" placeholder="insert funny here" name="desc"></td>
                    </tr>
                    <tr>
                        <input type="hidden" value="yes" name="submitted">
                        <td><input type="submit" value="submit" class="button"></td>
                        <td><a href="admin_room_management.jsp">Back</a></td>
                    </tr>
                </form>
            </table>
        </div>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table class='table'>
                    <tr>
                        <td>ID</td>
                        <td>Suite</td>
                        <td>Cost</td>
                        <td>Number of beds</td>
                        <td>Description</td>
                        <td></td>
                        <td></td>
                    </tr>
                    <%
                        for(RoomType roomType : roomTypeList) {
                    %>
                    <tr>
                        <form action="AdminUpdateRoomTypeServlet" method="POST">
                            <td><%=roomType.getRoomTypeId()%></td>
                            <td><input type="text" placeholder="Single" name="suite" value="<%=roomType.getSuite()%>"></td>
                            <td><input type="number" placeholder="100.00" step=".01" name="cost" value="<%=roomType.getCost()%>"></td>
                            <td><input type="number" placeholder="1"  name="numberofbeds" value="<%=roomType.getNumBeds()%>"></td>
                            <td><input type="text" placeholder="insert the funny here"  name="desc" value="<%=roomType.getDescription()%>"></td>
                            <td>
                                <input type="hidden" name="id" value="<%=roomType.getRoomTypeId()%>">
                                <input type="submit" value="Update" class="button">
                            </td>
                        </form>
                        <td>
                            <%--DELETE ROOMTYPE BUTTON--%>
                            <form action="AdminDeleteRoomTypeServlet" method="post">
                                <input type="hidden" name="id" value="<%=roomType.getRoomTypeId()%>">
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