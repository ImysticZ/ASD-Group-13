<html lang="en">
    <%@page import="java.util.*"%>
    <%@page import="uts.asd.model.*"%>
    <%@page import="uts.asd.model.dao.*"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Rooms</title>
    <jsp:include page="ConnServlet"/>
    <%if (session.getAttribute("user") != null) { %>
    <jsp:include page="navMain.jsp"/>
    <%}
    else {%>
    <jsp:include page="nav.jsp"/>
    <%}%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%
    RoomType room = (RoomType)session.getAttribute("theroom");
%>

<body>
    <div class="container">
        <h1><%= room.getSuite()%></h1>
        <h5><%= room.getNumBeds()%></h5>
        <p><%= room.getDescription()%></p>
        <p><%= room.getCost() %></p>
    </div>
</body>
</html>