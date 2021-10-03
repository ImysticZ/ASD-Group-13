<!DOCTYPE html>
<html lang="en">
<%@page import="uts.asd.model.*" %>
<% User user=(session.getAttribute("user")!=null) ? (User) session.getAttribute("user") : null; %>
<% Booking booking=session.getAttribute("booking") !=null ? (Booking) session.getAttribute("booking") : null; %>

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Booking | Success</title>
                <!-- bootstrap-->
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <!-- stylesheet-->
                <link rel="stylesheet" href="style.css">

            </head>

            <body>
                <section class="container">
                    <center>
                        <h4>Thank you for Booking <%= user.getFirstName()%>
                                <%= user.getLastName() %>
                        </h4>
                    </center>
                    <hr />
                    <article class="row">
                        <div class="col-md-4">
                            <h4>Payment Details</h4>
                            <table>
                                <tr>
                                    <th>Card</th>
                                    <td>
                                        <% out.println(request.getParameter("card")); %>
                                    </td>
                                </tr>
                                <tr>
                                    <th>CVC</th>
                                    <td>
                                        <% out.println(request.getParameter("cvc")); %>
                                    </td>
                                </tr>
                                <tr>
                                    <th>Expiry</th>
                                    <td>
                                        <% out.println(request.getParameter("date")); %>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-8" style="text-align: center;">
                            <center>
                                <h4>Your Booking details</h4>
                            </center>
                            <table>
                                <tr>
                                    <th>BookingID</th>
                                    <th>Total Cost</th>
                                    <th>Room</th>
                                    <th>Arrival Date</th>
                                    <th>Departure Date</th>
                                    <th>Status</th>
                                </tr>
                                <tr>
                                    <td>
                                        <p>
                                            <% if (booking!=null) {out.println(booking.getBookingID());} else {out.println(897);} %>
                                        </p>
                                    </td>
                                    <td>
                                        <p>$
                                            <% if (booking!=null) {out.println(booking.getTotalCost());} else {out.println("875.99");} %>
                                        </p>
                                    </td>
                                    <td>
                                        <p>
                                            <% if (booking!=null) {out.println(booking.getRoomID());} else {out.println(214);} %>
                                        </p>
                                    </td>
                                    <td>
                                        <p>
                                            <% if (booking!=null) {out.println(booking.getStartingDate());} else {out.println("10/09/2021 11:00 AM");} %>
                                        </p>
                                    </td>
                                    <td>
                                        <p>
                                            <% if (booking!=null) {out.println(booking.getEndingDate());} else {out.println("18/09/2021 11:00 AM");} %>
                                        </p>
                                    </td>
                                    <td>
                                        <p>Booked</p>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </article>
                    <hr />
                    <% if (request.getParameter("save")!=null) { %>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" checked disabled />
                            <label class="form-check-label">
                                Credit Card saved
                            </label>
                        </div>
                        <% } %>
                            <div style="float:right">
                                <p>Please check in 30 minutes early upon your arrival</p>
                                <form method="POST" action="success"><button type="submit" class="btn btn-info">Return
                                        ></button></form>
                            </div>
                </section>
            </body>

</html>