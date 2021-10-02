<html>
<head>
    <jsp:include page="nav.jsp" />
    <link rel="stylesheet" href="css/enquiry.css">
    <title>New Enquiry</title> 
</head>
<body>

    <h1>New Enquiry</h1>

    <form method="post" action="userEnquiry.jsp">
        <table class="center">
            <tr>
                <th>Enquiry Details: </th>
            </tr>
            <tr>
                <td><input class="textbox" type="text" placeholder="Enter details" name="question" required="true"></td>
            </tr>
            <div class="submit">
                <tr>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </div>
        </table>
    </form>

    <div class="button">
        <a href="userEnquiry.jsp">Return</a>
    </div>
    
</body>
</html>