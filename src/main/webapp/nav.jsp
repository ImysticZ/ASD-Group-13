<%-- NAVBAR FOR NEW USERS --%>
<%@page import="uts.asd.model.*"%>
<% 
  Card card = (Card) session.getAttribute("card");
%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">
<% if (session.getAttribute("user") == null) {%>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">Four Semester Hotel</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="RoomServlet">Rooms</a></li>
        <li><a href="#">Booking</a></li>
        <li><a href="faq.jsp">FAQ</a></li>
        <li><a href="#">About us</a></li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
</nav>
<%}
else if (((User)session.getAttribute("user")).getType().equals("c")) { %>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="main.jsp">Four Semester Hotel</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="RoomServlet">Rooms</a></li>
        <li><a href="#">Booking</a></li>
        <li><a href="#">About us</a></li>

      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="main.jsp"><span class="glyphicon glyphicon-user"></span> Main</a></li>
        <li><a href="manage_account.jsp"><span class="glyphicon glyphicon-wrench"></span> Manage Account</a></li>
        <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
</nav>
  <%}
  else if (((User)session.getAttribute("user")).getType().equals("s")) {%>
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="main.jsp">Four Semester Hotel</a>
        </div>
        <ul class="nav navbar-nav">
          <li><a href="RoomServlet">Rooms</a></li>
          <li><a href="#">Booking</a></li>
          <li><a href="userEnquiry.jsp">Enquiries</a></li>
          <li><a href="#">About us</a></li>
  
          <%--Conditions: ONLY STAFF--%>
          <li><a href="bookings.jsp">Bookings</a></li>
          <%--Condition end--%>
  
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="main.jsp"><span class="glyphicon glyphicon-user"></span> Main</a></li>
          <li><a href="manage_staffAccount.jsp"><span class="glyphicon glyphicon-wrench"></span> Manage Account</a></li>
          <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
        </ul>
      </div>
  </nav>
<%}
else {%>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="main.jsp">Four Semester Hotel</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="RoomServlet">Rooms</a></li>
        <li><a href="#">Booking</a></li>
        <li><a href="#">About us</a></li>

        <%--Conditions: ONLY ADMIN--%>
        <li><a href="admin_user_management.jsp">Manage Users</a></li>
        <li><a href="admin_room_management.jsp">Manage Rooms</a></li>
        <%--Condition end--%>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="main.jsp"><span class="glyphicon glyphicon-user"></span> Main</a></li>
        <li><a href="manage_staffAccount.jsp"><span class="glyphicon glyphicon-wrench"></span> Manage Account</a></li>
        <li><a href="LogoutServlet"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
</nav>
  <%}%>