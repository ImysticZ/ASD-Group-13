<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>
    <%@page import="uts.asd.model.dao.*"%>
    <%@page import="java.time.*"%>
    <%@page import="java.time.format.DateTimeFormatter"%>
    <%@page import="java.text.SimpleDateFormat"%>
    <%@page import="java.util.Date"%>
    <%@page import="java.time.temporal.ChronoUnit"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Booking</title>
    <jsp:include page="ConnServlet"/>
    <jsp:include page="nav.jsp"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
</head>
<%
    RoomType room = (RoomType)session.getAttribute("theroom");
    String[] date = ((String)request.getParameter("daterange")).split(" - ");
    String choice = (String)request.getParameter("choice");
    String start = date[0];
    String end = date[1];
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    long totalDays = ChronoUnit.DAYS.between(LocalDate.parse(start, formatter), LocalDate.parse(end, formatter));
    double cost = totalDays * room.getCost();
%>

<body>
    <div class="container">
        <form method="POST" action="ConfirmBookingServlet">
            <h1><%= room.getSuite()%></h1>
            <h5>Room <%= choice%></h2>
            <h5>Starting date: <%=start%></h5>
            <h5>Ending date: <%=end%></h5>
            <h5 name="totalDays" value=<%= totalDays%>>Amount of days: <%=totalDays%></h5>
            <h5>Total Cost: $<%=cost%></h4>
            <input type="hidden" name="roomID" value=<%= choice%>>
            <input type="hidden" name="startingDate" value=<%= start%>>
            <input type="hidden" name="endingDate" value=<%= end%>>
            <input type="hidden" name="totalCost" value=<%= cost%>>

            <button type="submit">Confirm Booking</a>
        </form>
    </div>
</body>
</html>