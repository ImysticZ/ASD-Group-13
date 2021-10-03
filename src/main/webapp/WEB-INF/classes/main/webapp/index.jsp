<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.controller.*"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <%--Navigation bar and css imports moved to another page--%>
    <jsp:include page="nav.jsp"/> 
</head>
<body>
    <img src="https://cdn.britannica.com/86/156586-050-EFB37140/Harbour-Bridge-Sydney.jpg" width="100%"></img>
    <jsp:include page="/ConnServlet" flush="true" />
</body>
</html>