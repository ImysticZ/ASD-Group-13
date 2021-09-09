
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>
<%@page import="java.util.*"%>
<%@page import="uts.asd.model.dao.DBConnector"%>
<%@page import="uts.asd.model.dao.DBManager"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <jsp:include page="nav.jsp"/> 
    </head>
    <body>

        <%
            String submitted = request.getParameter("submitted");
            
            ArrayList<Room> roomList = new ArrayList<Room>();
            for(int i = 0; i < 100; i++) {
                roomList.add(new Room(i, ((i^2)%3==0)?0:1, (i%2==0)?true:false));
            }

            if(submitted != null) {
                int submittedType = Integer.parseInt(request.getParameter("type"));

                ArrayList<Room> temp = new ArrayList<Room>();
                for(Room room : roomList) {
                    if(room.getRoomTypeId() == submittedType) {
                        temp.add(room);
                    }
                }
                roomList = temp;
            }
        %>

        <div class='col-xl-8 mx-auto card p-5 mt-5 bg-light'>
            <table>
                <form action="admin_room_management.jsp" method="POST">
                    <th>Search Room</th>
                    <tr>
                        <td>Room Type</td>
                        <td><input type="number" placeholder="Regular" name="type"></td>
                        <input type="hidden" value="yes" name="submitted">
                        <td><input type="submit" value="submit" class="button"></td>
                    </tr>
                </form>
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
                        <td><%= room.getRoomTypeId() %></td>
                        <td><%= room.getAvailability() %></td>
                        <td>
                            <%--EDIT USER BUTTON--%>
                            <form action="#" method="post">
                                <input type="hidden" name="id" value="<%=room.getRoomId()%>">
                                <input type="submit" value="Edit" class="button">
                            </form>
                        </td>
                        <td>
                            <%--DELETE USER BUTTON--%>
                            <form action="#" method="post">
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
</html>