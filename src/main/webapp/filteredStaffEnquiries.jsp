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
            ArrayList<Enquiry> enquiries = (ArrayList) request.getAttribute("filteredEnquiries");
        %>
        
        <div class="main group">
            <h1>Enquiries</h1>

            <%--Clear filter--%>
            <table class="filter">
                <tr>
                    <td><a href="staffEnquiry.jsp">Clear Filter</a></td>
                </tr>
            </table>
            
            <%--List of enquiries from all customers--%>
            <table class="enquiriesTable">
                <tr>
                    <th>ID</th>
                    <th class="tableColumn">Enquiry</th>
                    <th>Status</th>
                    <th class="tableColumn">Reply</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                <% for(Enquiry e : enquiries) {%>
                    <tr>
                        <td><%=e.getUserID()%></td>
                        <td><%=e.getQuestion()%></td>
                        <td>
                            <%if(e.getResolved()){%>
                                Resolved
                            <%}else{%>
                                Pending
                            <%}%>
                        </td>
                        <td><%=e.getReply()%></td>
                        <td>
                            <a href="enquiryReply.jsp?enquiryID=<%=e.getEnquiryID()%>">
                            <%if(!e.getResolved()){%>
                                Reply
                            <%}else{%>
                                Edit<%}%></a>
                        </td>
                        <td><a href="DeleteEnquiryServlet?enquiryID=<%=e.getEnquiryID()%>">Delete</a></td>
                    </tr>
                <%}%>
            </table>
        </div>
        
    </body>
    <%}%>
</html>