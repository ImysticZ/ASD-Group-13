<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<html>
    <head>
        <jsp:include page="nav.jsp" />
        <link rel="stylesheet" href="css/enquiry.css">
        <title>New Enquiry</title> 
    </head>
    <body>
        
        <h1>Submit Enquiry</h1>

        <%--Add enquiry details--%>
        <form method="post" action="AddEnquiryServlet">
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

        <%--Return to enquiries page--%>
        <div class="button">
            <a href="userEnquiry.jsp">Return</a>
        </div>
    </body>
</html>