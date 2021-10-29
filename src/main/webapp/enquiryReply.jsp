<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="nav.jsp"/>
        <link rel="stylesheet" href="css/enquiry.css">
        <title>Reply</title> 
    </head>
    <%--Check user account--%>
    <%
        User user = (User) session.getAttribute("user");

        if(user == null || !user.getType().equals("s")) {%>
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
            EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");
            String id = request.getParameter("enquiryID");
            if(id == null){
                id = (String) session.getAttribute("enquiryID");
            }
            Enquiry enquiry = manager.findEnquiryByID(Integer.parseInt(id));
            String emptyErr = (String) session.getAttribute("enquiryEmptyErr");
        %>

        <div class="main group">
            <h1>Reply</h1>

            <%--Add reply--%>
            <p class="emptyErr"><%= emptyErr != null ? emptyErr : ""%></p>
            <form method="post" action="EnquiryReplyServlet">
                <table class="enquiryForm">
                    <input type="hidden" value="<%=enquiry.getEnquiryID()%>" name="id">
                    <input type="hidden" value="<%=enquiry.getUserID()%>" name="UserID">
                    <tr>
                        <th>Enquiry: </th>
                    </tr>
                    <tr>
                        <td><textarea class="textbox" type="text" name="question" readonly><%=enquiry.getQuestion()%></textarea></td>
                    </tr>
                    <tr>
                        <th>Reply: </th>
                    </tr>
                    <tr>
                        <td><textarea class="textbox" type="text" value="<%=enquiry.getReply()%>" placeholder="Enter reply" name="reply"><%=enquiry.getReply()%></textarea></td>
                    </tr>
                    <tr>
                        <td><input class="submit" type="submit" value="Submit"></td>
                    </tr>               
                </table>                   
            </form>

            <%--Return to enquiries page--%>
            <div class="button">
                <a href="staffEnquiry.jsp">Return</a>
            </div>
        </div>
        
    </body>
    <%}%>
</html>