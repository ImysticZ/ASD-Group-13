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

        <%
            EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");
            User user = (User) session.getAttribute("user");
            ArrayList<Enquiry> enquiries = (ArrayList)request.getAttribute("filteredEnquiries");
        %>
        
        <h1>Enquiries</h1>

        <%--Clear filter--%>
        <table class="filter">
            <tr>
                <td><a href="userEnquiry.jsp">Clear Filter</a></td>
            </tr>
        </table>
        
        <%--Filtered enquiries--%>
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

    </body>
</html>