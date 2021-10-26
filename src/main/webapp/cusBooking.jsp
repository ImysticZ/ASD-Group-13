<!DOCTYPE html>
<html lang="en">

<head>
    <%@page import="uts.asd.model.*" %>
        <%@page import="java.util.*" %>
            <%@page import="java.text.SimpleDateFormat" %>
                <% User user=(session.getAttribute("user")!=null) ? (User) session.getAttribute("user") : null; %>
                    <% ArrayList<Booking> allBookings =(session.getAttribute("bookings")!=null) ? (ArrayList<Booking>)
                            session.getAttribute("bookings") : null; %>
                            <meta charset="UTF-8">
                            <meta http-equiv="X-UA-Compatible" content="IE=edge">
                            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                            <title>Customer Bookings</title>
                            <jsp:include page="nav.jsp" />
                            <style>
                                #search {
                                    width: 70vw;
                                    margin: 1% auto;
                                }
                            </style>
                            <link rel="stylesheet"
                                href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

                            <script>
                                $(document).ready(function () {
                                    $('[data-toggle="tooltip"]').tooltip();
                                    $("#search").on("keyup", function () {
                                        var value = $(this).val().toLowerCase();
                                        $("#my-table tr").filter(function () {
                                            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                                        });
                                    });
                                });
                            </script>
</head>

<body>
    <section class="container">
        <% if (user!=null && user.getType().equals("s")) { %>
            <div class="form-group">
                <input type="text" class="form-control" id="search" style="display: inline-block;" placeholder="Search for booking"/>
                <a data-toggle="tooltip" data-placement="right"
                    title="Enter booking ID to filter" style="display: inline-block;"><span style="font-size:24px;"class="glyphicon glyphicon-info-sign"></span></a>
            </div>
            <article class="row">
                <table>
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Room</th>
                            <th>Total Cost</th>
                            <th>Arrival</th>
                            <th>Departure</th>
                            <th>Booking Status</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody id="my-table">
                        <% for (Booking b : allBookings) { %>
                            <tr>
                                <td>
                                    <p>
                                        <%= b.getBookingID() %>
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <%= b.getRoomID() %>
                                    </p>
                                </td>
                                <td>
                                    <p>$<%= b.getTotalCost() %>
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <%= new SimpleDateFormat("dd-MM-yyyy").format(b.getStartingDate()) %>
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <%= new SimpleDateFormat("dd-MM-yyyy").format(b.getEndingDate()) %>
                                    </p>
                                </td>
                                <td>
                                    <form action="UpdateBooking" method="POST">
                                        <select class="form-control" id="bookingStatus" name="status">
                                            <option value="Booked" <%=b.getStatus().equals("Booked") ? "selected" : ""
                                                %>
                                                >Booked</option>
                                            <option value="Checked In" <%=b.getStatus().equals("Checked In")
                                                ? "selected" : "" %>>Checked In</option>
                                            <option value="Checked Out" <%=b.getStatus().equals("Checked Out")
                                                ? "selected" : "" %>>Checked Out</option>
                                            <option value="Cancelled" <%=b.getStatus().equals("Cancelled") ? "selected"
                                                : "" %>>Cancelled</option>
                                        </select>
                                </td>
                                <td>
                                    <input type="hidden" name="bookingID" value="<%= b.getBookingID()%>" />
                                    <input type="submit" class="btn btn-success" value="Update Booking >"
                                        style="float:right;width:80%;" />
                                    </form>
                                </td>
                                <!--Instead of doing a drop down why dont you just make the booking status a drop down field and the button send a request that updates it-->
                            </tr>
                            <% } %>
                    </tbody>
                </table>
            </article>
            <% } else { %>
                <center>
                    <h4>You do not have access, You must be a staff privileges</h4>
                </center>
                <% } %>
    </section>
</body>
</html>