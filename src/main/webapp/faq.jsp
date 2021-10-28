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

        <div class="main group">
            <h1>FAQ</h1>

            <%--Questions--%>
            <button type="button" class="collapsible">How do I find directions to the hotel?</button>
            <div class="content">
            <p>We recommend searching for our hotel on Google Maps, our address can be found on the "About Us" page.</p>
            </div>

            <button type="button" class="collapsible">Can I make more than one booking at a time?</button>
            <div class="content">
            <p>Yes, you can make multiple bookings at the same time.</p>
            </div>
            
            <button type="button" class="collapsible">Where can I find contact details for the hotel?</button>
            <div class="content">
            <p>Our contact details are available on the "About Us" page. You can also send us an enquiry through the link below.</p>
            </div>

            <button type="button" class="collapsible">Can I save multiple credit cards on my account?</button>
            <div class="content">
            <p>No. Currently, only 1 credit card is allowed on an account.</p>
            </div>

            <button type="button" class="collapsible">When will I be charged for a booking?</button>
            <div class="content">
            <p>Payment will be taken after the booking is made.</p>
            </div>

            <button type="button" class="collapsible">I don't have a credit card, can I still make a booking?</button>
            <div class="content">
            <p>Unfortunely, bookings on our website will require a credit card.</p>
            </div>
            <br>

            <%--To Enquiries page--%>
            <h3>Couldn't find an answer to your question? Send us an enquiry:</h3>
            <img src="css/envelope.png" width="15%" class="center" alt="Enquiry">
                <%if(session.getAttribute("user") == null){%>
                    <a class="enquiriesButton" href="login.jsp">Enquiries</a>
                <%}else{%>
                    <a class="enquiriesButton" href="userEnquiry.jsp">Enquiries</a>
                <%}%>
            <br>
        </div>
        
        <%--JavaScript--%>    
        <script>
            var coll = document.getElementsByClassName("collapsible");
            var i;
    
            for (i = 0; i < coll.length; i++) {
                coll[i].addEventListener("click", function() {
                    this.classList.toggle("active");
                    var content = this.nextElementSibling;
                    if (content.style.maxHeight){
                        content.style.maxHeight = null;
                    } else {
                        content.style.maxHeight = content.scrollHeight + "px";
                    }
                });
            }
        </script> 
        
    </body>
</html>