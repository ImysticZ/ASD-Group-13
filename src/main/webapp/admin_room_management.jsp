
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page import="java.util.*"%>
<%@page import="uts.asd.model.dao.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <jsp:include page="nav.jsp"/> 
    </head>

    <%-- ADMIN VIBE CHECK --%>
    <%
        // Get the type of the user in the session
        User currentUser = (User) session.getAttribute("user");
        String userType = (currentUser == null) ? "" : currentUser.getType();

        if(userType == null || !userType.equals("a")) {
            // Error, user is not an admin
            Random rng = new Random();                  // RNG to select the funnies
            int i = rng.nextInt(Funny.funnies.length);  // Random integer for the funnies
            %>
            <body>
                <h1>ERROR: Access Denied</h1>
                <h3>User does not have access to this page.</h3>
                <p style="text-align:center;">
                    <img src="<%=Funny.funnies[i]%>" alt="sad :(" >
                </p>
            </body>
            <%
        }
        else {
            // Load page
    %>

    <body>

        <%
            String submitted = request.getParameter("submitted");
            AdminDBManager db = (AdminDBManager)session.getAttribute("adminmngr");
            if(db == null) {
                db = new AdminDBManager(new DBConnector().openConnection());
                session.setAttribute("adminmngr", db);
            }
            
            String msg = (String) session.getAttribute("roommsg");
            if(msg!=null) {
                %>
                    <div class="alert alert-primary" role="alert">
                        <%=msg%>
                    </div>
                <%
            }

            ArrayList<Room> roomList = (ArrayList<Room>)session.getAttribute("roomList");
            ArrayList<RoomType> roomTypes = (ArrayList<RoomType>)session.getAttribute("roomType");
            
            if(roomList == null)
                roomList = db.fetchAllRooms();
            if(roomTypes == null) {
                roomTypes = db.getRoomTypes();
                session.setAttribute("roomType", roomTypes);
            }
        %>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table>
                <form action="AdminRoomManagementServlet" method="POST">
                    <th>Search Room</th>
                    <tr>
                        <td>Room Number</td>
                        <td><input type="number" placeholder="123" name="roomnumber"></td>
                    </tr>
                    <tr>
                        <td>Room Type</td>
                        <td>
                            <select name="type">
                                <option value="none123123">Select Type</option>
                                <%
                                    for(RoomType rt : roomTypes) {
                                %>
                                <option value="<%=rt.getSuite()%>"><%=rt.getSuite()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                        <input type="hidden" value="yes" name="submitted">
                        <td><input type="submit" value="submit" class="button"></td>
                    </tr>
                </form>
                <tr>
                    <form action="admin_create_room.jsp" method="POST">
                        <td><input type="submit" value="Add Range" class="button"></td>
                    </form>
                    <form action="admin_delete_rooms.jsp" method="POST">
                        <td><input type="submit" value="Delete Range" class="button"></td>
                    </form>
                    <form action="admin_update_rooms.jsp" method="POST">
                        <td><input type="submit" value="Update Range" class="button"></td>
                    </form>
                    <form action="admin_roomtype_management.jsp" method="POST">
                        <td><input type="submit" value="Manage Room Types" class="button"></td>
                    </form>
                </tr>
            </table>
        </div>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table class='table'>
                    <tr>
                        <td>ID</td>
                        <td>Room Type</td>
                        <td>Availability</td>
                    </tr>
                    <%
                        for(Room room : roomList) {
                    %>
                    <tr>
                        <td><%= room.getRoomId() %></td>
                        <td><%= db.getSuite(room.getRoomTypeId()) %></td>
                        <td><%= room.getAvailability() %></td>
                        <td>
                            <%--EDIT USER BUTTON--%>
                            <form action="admin_update_room.jsp" method="post">
                                <input type="hidden" name="id" value="<%=room.getRoomId()%>">
                                <input type="submit" value="Edit" class="button">
                            </form>
                        </td>
                        <td>
                            <%--DELETE ROOM BUTTON--%>
                            <form action="AdminDeleteRoomServlet" method="post">
                                <input type="hidden" name="id" value="<%=room.getRoomId()%>">
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
    <%}%>
</html>