<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<html>
    <head>
        <jsp:include page="nav.jsp" />
        <link rel="stylesheet" href="css/enquiry.css">
        <title>New Enquiry</title> 
    </head>
    <%--Check user account--%>
    <%
        User user = (User) session.getAttribute("user");

        if(user == null || !user.getType().equals("c")) {%>
            <body>
                <div class="main group">
                    <h1>Access Denied</h1>
                    <p class="text">Your account does not have access to this page.</p>
            
                    <%--Return to main page--%>
                    <%if (user == null){%>
                        <a class="button" href="index.jsp">Ok</a>
                    <%}else{%>
                        <a class="button" href="main.jsp">Ok</a>
                    <%}%>
                </div>   
            </body>
        <%}else{
    %>
    <body>

        <%
            String emptyErr = (String) session.getAttribute("enquiryEmptyErr");
        %>

        <div class="main group">
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
            <a href="userEnquiry.jsp" class="button">Return</a>
        </div>
        
    </body>
    <%}%>
</html>