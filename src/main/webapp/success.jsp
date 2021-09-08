<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking | Success</title>
    <!-- bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- stylesheet-->
    <link rel="stylesheet" href="style.css">
    
    <!--navbar-->
    <jsp:include page="nav.jsp" />
</head>
<body>
    <section class="container" style="text-align: center;">
        <h4>Thank you for Booking </h4>
        <hr/>
        <h4>Card Number: <% out.println(request.getParameter("card")); %></h4>
        <h4>CVC: <% out.println(request.getParameter("cvc")); %></h4>        
        <h4>Expiry - Date: <% out.println(request.getParameter("date")); %></h4>        
        <a href="/webapp"><button class="btn btn-info">Return ></button></a>
    </section>   
</body>
</html>