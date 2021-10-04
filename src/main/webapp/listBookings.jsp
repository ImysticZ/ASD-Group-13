<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>
    <%@page import="uts.asd.model.dao.*"%>
    <%@page import="java.text.*"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings</title>
    <jsp:include page="ConnServlet"/>
    <jsp:include page="nav.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%
    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
%>
<body>
    <h1>My Bookings</h1>
        <table>
            <tr>
                <th>Booking ID</th>
                <th>Room ID</th>
                <th>Starting Date</th>
                <th>Ending Date</th>
                <th>Status</th>
                <th>Paid</th>
                <th>Cost</th>
            </tr>
            <%for (Booking booking : (ArrayList<Booking>)session.getAttribute("bookings")) {%>
            <tr>
                <td><%=booking.getBookingID()%></td>
                <td><%=booking.getRoomID()%></td>
                <td><%=formatter.format(booking.getStartingDate())%></td>
                <td><%=formatter.format(booking.getEndingDate())%></td>
                <td><%=booking.getStatus()%></td>
                <td><%=booking.getPaid()%></td>
                <td>$<%=booking.getTotalCost()%></td>
                <td>
                    <form method="POST" action="CancelBookingServlet">
                        <button type="submit">Cancel Booking</button>
                        <input type="hidden" name="bookingID" value="<%=booking.getBookingID()%>">
                    </form>
                    
                </td>
            </tr>
            <%}%>
        </table>
    
        
    
</body>
</html>