<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="index.jsp">Four seasons</a>
      </div>
      <ul class="nav navbar-nav">
        <li><a href="view_room.jsp">Rooms</a></li>
        <li><a href="#">Booking</a></li>
        <li><a href="userEnquiry.jsp">Enquiries</a></li>
        <li><a href="#">About us</a></li>

        <%--Conditions: ONLY LOGGED IN USERS--%>
        
        <%--Condition end--%>

        <%--Conditions: ONLY STAFF--%>
        <li><a href="bookings.jsp">Bookings</a></li>
        <%--Condition end--%>

        <%--Conditions: ONLY ADMIN--%>
        
        <%--Condition end--%>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="register.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
</nav>