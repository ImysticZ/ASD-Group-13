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
            String id = request.getParameter("enquiryID");
            Enquiry enquiry = manager.findEnquiryByID(Integer.parseInt(id));
        %>
        
        <h1>Reply</h1>
        <form method="post" action="EnquiryReplyServlet">
            <table>
                <input type="hidden" value="<%=enquiry.getEnquiryID()%>" name="id">
                <tr><th>Enquiry: </th><td><input type="text" value="<%=enquiry.getQuestion()%>" name="question" readonly></td></tr>
                <tr><th>Reply: </th><td><input type="text" value="<%=enquiry.getReply()%>" name="reply"></td></tr>
                <tr>
                    <td></td><td><input class="button" type="submit" value="Edit"></td>
                </tr>               
            </table>                   
        </form>

        <div class="button">
            <a href="staffEnquiry.jsp">Return</a>
        </div>

        

    </body>
</html>