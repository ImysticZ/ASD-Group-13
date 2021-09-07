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
        <li><a href="#">About us</a></li>

        <%--Conditions: ONLY LOGGED IN USERS--%>
        
        <%--Condition end--%>

        <%--Conditions: ONLY STAFF--%>
        
        <%--Condition end--%>

        <%--Conditions: ONLY ADMIN--%>
        <li><a href="admin_user_management.jsp">Manage Users</a></li>
        <li><a href="admin_room_management.jsp">Manage Rooms</a></li>
        <%--Condition end--%>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
</nav>