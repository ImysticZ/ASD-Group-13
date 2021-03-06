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
            AdminDBManager db = (AdminDBManager)session.getAttribute("adminmngr");
            if(db == null) {
                db = new AdminDBManager(new DBConnector().openConnection());
                session.setAttribute("adminmngr", db);
            }
            String msg = (String) session.getAttribute("createroommsg");
            if(msg!=null) {
                %>
                    <div class="alert alert-primary" role="alert">
                        <%=msg%>
                    </div>
                <%
            }

            ArrayList<RoomType> roomTypes = (ArrayList<RoomType>)session.getAttribute("roomType");
            
                if(roomTypes == null) {
                    roomTypes = db.getRoomTypes();
                    session.setAttribute("roomType", roomTypes);
                }
        %>

        <h1>Create Room</h1>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <form action="AdminCreateRoomServlet" method="POST">
                <table class='table'>
                    <tr>
                        <td>Number Range: </td>
                        <td>Lower bound (inclusive): <input type="number" placeholder="0" name="lowerbound"></td>
                        <td>Upper bound (inclusive): <input type="number" placeholder="999" name="upperbound"></td>
                    </tr>
                    <tr>
                        <td>Type: </td>
                        <td>
                            <select name="roomtype">
                                <%
                                    for(RoomType rt : roomTypes) {
                                %>
                                <option value="<%=rt.getRoomTypeId()%>"><%=rt.getSuite()%></option>
                                <%
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="submit" class="button"></td>
                        </form>
                        <form action="admin_room_management.jsp" method="POST">
                            <td><input type="submit" value="Back" class="button"></td>
                        </form>
                    </tr>
                </table>     
        </div>
    </body>
    <%}%>
</html>