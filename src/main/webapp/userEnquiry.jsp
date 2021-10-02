<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="nav.jsp"/>
        <link rel="stylesheet" href="css/enquiry.css"> 
    </head>
    <body>

        <%
            ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
            enquiries.add(new Enquiry(1,"How do I seach for hotels by price?", "Use the search filter", true));
            enquiries.add(new Enquiry(1,"Where can I find the contact details for the hotel?", "", false));
            enquiries.add(new Enquiry(1,"How do I find directions to the hotel?", "", false));
            String question = request.getParameter("question");
            if(question != null && enquiries != null){
                Enquiry enquiry = new Enquiry(1, question, "", false);
                enquiries.add(enquiry);
            }
        %>
        
        <h1>Enquiries</h1>

        <table>
            <tr>
                <th>Enquiry</th>
                <th>Status</th>
                <th>Reply</th>
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
        
        <div class="button">
            <a href="addEnquiry.jsp">New Enquiry</a>
        </div>
        
    </body>
</html>