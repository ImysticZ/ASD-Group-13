<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>
    <%@page import="uts.asd.model.dao.*"%>
    <%@page import="java.time.LocalDate"%>
    <%@page import="java.time.format.DateTimeFormatter"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room</title>
    <jsp:include page="ConnServlet"/>
    <jsp:include page="nav.jsp"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
</head>
<%
    RoomType room = (RoomType)session.getAttribute("theroom");
    ArrayList<Room> all = (ArrayList<Room>)session.getAttribute("allrooms");
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    String time = LocalDate.now().format(myFormatObj);
%>

<body>
    <div class="container">
        <h1><%= room.getSuite()%></h1>
        <h5><%= room.getNumBeds()%> bed</h5>
        <p><%= room.getDescription()%></p>
        <p>$<%= room.getCost() %> per night</p>
        <form method="POST" action="viewBooking.jsp">
            <input type="text" name="daterange"/>
            <select name="choice">
                <%for (Room a : all) {%>
                    <option value="<%=a.getRoomId()%>">Room <%= a.getRoomId() %></option>
                <%}%>
            </select>
            <%if (session.getAttribute("user") == null) {%>
            <button type="submit" disabled>View Booking Details</button>
            <h3>You need to be signed in to book!</h3>
            <%}
            else {%>
            <button type="submit">View Booking Details</button>
            <%}%>
        </form>
    </div>
</body>
</html>
<script>
    $(function() {
      $('input[name="daterange"]').daterangepicker({
        opens: 'left',
        minDate: '<%=time%>',
        locale: {
            format: "YYYY/MM/DD"
        }
      });
    });
    </script>