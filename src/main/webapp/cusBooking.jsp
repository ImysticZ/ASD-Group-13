<!DOCTYPE html>
<html lang="en">

<head>
    <%@page import="uts.asd.model.*" %>
        <%@page import="java.util.*" %>
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

                            td, th {
                                border-top: 1px solid #dddddd;
                                text-align: left;
                                padding: 8px;
                                width: 10%;
                            }
                            .overlay {
                                display: none;
                                position:fixed;
                                width: 100%;
                                height:100vh;
                                background-color: rgba(0,0,0,0.5);
                                top: 0;
                                left:0;
                                right:0;
                                z-index: 2;
                            }
                            .booking-container {
                                margin: 5% auto;
                                width:75vw;
                                border-radius:5px;
                                background-color: rgb(54, 54, 53);
                            }
                        </style>
                        <!-- bootstrap-->
                        <link rel="stylesheet"
                            href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

                        <!--jQuery-->
                        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                        <script>
                            $(document).ready(function () {
                                $("#search").on("keyup", function () {
                                    var value = $(this).val().toLowerCase();
                                    $("#my-table tr").filter(function () {
                                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                                    });
                                });
                                $(".overlay").click(()=> {
                                    $(".overlay").toggle();
                                })
                            });
                        </script>
</head>

<body>
    <section class="container">
        <div class="form-group">
            <input type="text" class="form-control" id="search" placeholder="Search for booking" maxlength="1" />
        </div>
        <table>
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Total Cost</th>
                    <th>Booking Status</th>
                    <th>Open</th>
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
                            <p>$<%= b.getTotalCost() %>
                            </p>
                        </td>
                        <td>
                            <p>
                                <%= b.getStatus() %>
                            </p>
                        </td>
                        <td>
                            <button id="<%= b.getBookingID() %>" class="btn btn-success" onclick="$('.overlay').toggle();">Open Booking ></button>
                        </td>
                    </tr>
                    <% } %>
            </tbody>
        </table>
        <div class="overlay">
            <div class="booking-container">
                <% for (Booking b : allBookings) { %>
                    out.println("this shit works lol");
                <% } %>
            </div>
        </div>
    </section>
</body>
</html>