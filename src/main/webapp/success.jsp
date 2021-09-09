<!DOCTYPE html>
<html lang="en">

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
            <h4>Thank you for Booking </h4>
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
                        <th>Room</th>
                        <th>Arrival Date</th>
                        <th>Departure Date</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>
                            <p>Room 308</p>
                        </td>
                        <td>
                            <p>10/09/2021 11:00 AM</p>
                        </td>
                        <td>
                            <p>18/09/2021 11:00 AM</p>
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
                    <a href="index.jsp"><button class="btn btn-info">Return ></button></a>
                </div>
    </section>
</body>

</html>