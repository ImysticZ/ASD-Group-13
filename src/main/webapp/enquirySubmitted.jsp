<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<html>
    <head>
        <jsp:include page="nav.jsp" />
        <link rel="stylesheet" href="css/enquiry.css">
        <title>Enquiry Submitted</title> 
    </head>
    <body>
        <div class="main group">
        <h1>Enquiry submitted successfully!</h1>
        <p class="text">A member of staff will get back to you within 1-2 business days, thank you for your enquiry.</p>

        <%--Return to enquiries page--%>
        <div class="button">
            <a href="userEnquiry.jsp">Ok</a>
        </div>

    </div>
        
    </body>
</html>