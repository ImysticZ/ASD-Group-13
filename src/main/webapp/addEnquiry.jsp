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
        
        <%
            User user = (User) session.getAttribute("user");
            String emptyErr = (String) session.getAttribute("enquiryEmptyErr");
        %>

        <h1>Submit Enquiry</h1>

        <%--Add enquiry details--%>
        <p class="emptyErr"><%= emptyErr != null ? emptyErr : ""%></p>
        <form method="post" action="AddEnquiryServlet">
            <table class="enquiryForm">
                <input type="hidden" value="<%=user.getId()%>" name="id">
                <tr>
                    <th>Enquiry Details: </th>
                </tr>
                <tr>
                    <td><textarea class="textbox" type="text" placeholder="Enter details" name="question"></textarea></td>
                </tr>
                <tr>
                    <td><input class="submit" type="submit" value="Submit"></td>
                </tr>
            </table>
        </form>

        <%--Return to enquiries page--%>
        <div class="button">
            <a href="userEnquiry.jsp">Return</a>
        </div>
        
    </body>
</html>