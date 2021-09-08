<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking | Success</title>
    <!-- bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <jsp:include page="nav.jsp" />
</head>
<body>
    <% String card= request.getParameter("card");
    out.println("the card number is " + card); %>
    The answer to 5 + 4 is <%= 5+4 %>
    <h4>Thank you for Booking </h4>
</body>
</html>