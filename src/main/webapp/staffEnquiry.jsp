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

        <div class="main group">
            <%
                EnquiryDBManager manager = (EnquiryDBManager) session.getAttribute("enquiryManager");
                ArrayList<Enquiry> enquiries = manager.fetchAll();
                String filterEmptyErr = (String) session.getAttribute("filterEmptyErr");
            %>
            
            <h1>Enquiries</h1>

            <%--Filter enquiries--%>
            <p class="emptyErr"><%= filterEmptyErr != null ? filterEmptyErr : ""%></p>
            <form method="post" action="FilterEnquiriesServlet">
                <table class="staffFilter">
                    <tr>
                        <td><label for="status">Filter by status: </label></td>
                        <td>
                            <select id="status" name="status">
                                <option value="">Select Status</option>
                                <option value="pending">Pending</option>
                                <option value="resolved">Resolved</option>
                            </select>
                        </td>
                        <td><label for="id">Filter by ID</label></td>
                        <td><input id="id" type="text" name="UserID"></td>
                        <td><input type="submit" value="Submit"></td>
                    </tr>
                </table>
            </form>
            
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