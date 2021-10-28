<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="nav.jsp"/>
        <link rel="stylesheet" href="css/enquiry.css">
        <title>Enquiries</title> 
    </head>
    <body>
        <div class="main group">
        <%
            EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");
            User user = (User) session.getAttribute("user");
            ArrayList<Enquiry> enquiries = manager.findEnquiriesByID(user.getId());
            String filterEmptyErr = (String) session.getAttribute("filterEmptyErr");
        %>
        
        <h1>Enquiries</h1>

        <%--Filter enquiries--%>
        <p class="emptyErr"><%= filterEmptyErr != null ? filterEmptyErr : ""%></p>
        <form method="post" action="FilterEnquiriesServlet">
            <table class="filter">
                <tr>
                    <input type="hidden" value="<%=user.getId()%>" name="UserID">
                    <td><label for="status">Filter by status: </label></td>
                    <td>
                        <select id="status" name="status">
                            <option value="">Select Status</option>
                            <option value="pending">Pending</option>
                            <option value="resolved">Resolved</option>
                        </select>
                    </td>
                    <td><input type="submit" value="Submit"></td>
                </tr>
            </table>

        </form>
        
        <%--List of enquiries from the customer that is logged in--%>
        <table class="enquiriesTable">
            <tr>
                <th class="tableColumn">Enquiry</th>
                <th>Status</th>
                <th class="tableColumn">Reply</th>
            </tr>
            <% for(Enquiry e : enquiries) {%>
                <tr>
                    <td><%=e.getQuestion()%></td>
                    <td>
                        <%if(e.getResolved()){%>
                            Resolved
                        <%}else{%>
                            Pending
                        <%}%>
                    </td>
                    <td><%=e.getReply()%></td>
                </tr>
            <%}%>
        </table>
        
        <%--Add new enquiry--%>
        <a class="newEnquiryButton" href="addEnquiry.jsp">New Enquiry</a>
        
        <%--Return to FAQ page--%>
        <a class="button" href="faq.jsp">Return</a>
    </div>
    </body>
</html>