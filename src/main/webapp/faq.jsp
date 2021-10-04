<%@page import="java.util.*"%>
<%@page import="uts.asd.model.*"%>
<%@page import="uts.asd.model.dao.*"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="nav.jsp"/>
        <link rel="stylesheet" href="css/enquiry.css">
        <title>FAQ</title> 
    </head>
    <body>

        <h1>FAQ</h1>

        <%--Questions--%>
        <h2>How do I find directions to the hotel?</h2>
        <p>We recommend searching for our hotel on Google Maps, our address can be found on the "About Us" page.</p>

        <h2>Can I make more than one booking at a time?</h2>
        <p>Yes, you can make multiple bookings at the same time.</p>

        <h2>Where can I find contact details for the hotel?</h2>
        <p>Our contact details are available on the "About Us" page. You can also send us an enquiry through the link below.</p>

        <h2>Can I save multiple credit cards on my account?</h2>
        <p>No. Currently, only 1 credit card is allowed on an account.</p>

        <h2>When will I be charged for a booking?</h2>
        <p>Payment will be taken after the booking is made.</p>

        <h2>I don't have a credit card, can I still make a booking?</h2>
        <p>Unfortunely, bookings on our website will require a credit card.</p>
        
        <br>

        <%--To Enquiries page--%>
        <h3>Couldn't find an answer to your question? Send us an enquiry:</h3>
        <a class="enquiriesButton" href="userEnquiry.jsp">Enquiries</a>
        
    </body>
</html>