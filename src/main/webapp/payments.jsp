<!DOCTYPE html>
<html lang="en">
<%@page import="uts.asd.model.*" %>
<% User user=(session.getAttribute("user")!=null) ? (User) session.getAttribute("user") : null; %>
<% Card creditcard= (session.getAttribute("card")!=null) ? (Card) session.getAttribute("card") : null;%>
<% Booking booking= (session.getAttribute("booking")) != null ? (Booking) session.getAttribute("booking") : null; %>
<% String cardnumber= (creditcard!=null) ? creditcard.getnumber() : "0" ;%>
<% String cvcnumber= (creditcard!=null) ? creditcard.getcvc() : "0" ;%>
            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <!--React CDN Links-->
                <script src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
                <script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
                <script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>

                <!-- Font awesome -->
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

                <!-- bootstrap-->
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <title>Booking | Pay Now</title>
                <jsp:include page="nav.jsp" />

            </head>

            <body>
                <%
                String cardErr = ((String) session.getAttribute("cardErr")!=null) ? (String) session.getAttribute("cardErr") : "";
                %>

                <script>
                </script>
                <script type="text/babel">
                    const DisplayCard = () => {
                        return (<div>
                            <i class="fa fa-cc-visa" style={{ fontSize: 24, padding: '1%' }}></i>
                            <i class="fa fa-cc-amex" style={{ fontSize: 24, padding: '1%' }}></i>
                            <i class="fa fa-cc-mastercard" style={{ fontSize: 24, padding: '1%' }}></i>
                        </div>)
                    }
                    class Payment extends React.Component {
                        constructor(props) {
                            super(props);
                            this.state = {
                                card: '',
                                cvc: '',
                                date: '',
                                cardValue: <%= cardnumber %>,
                                cvcValue: <%= cvcnumber %>,
                                dateValue: ''
                            }
                        }
                        handleSubmit = (e) => {
                            if (this.state.cardValue.match(/[0-9]{16}/) == 0 || this.state.cvcValue.match(/[0-9]{3}/) == null || !this.state.dateValue) {
                                e.preventDefault();
                            }
                        }
                        ValidateCard = (e) => {
                            if (e.target.value.match(/[0-9]{16}/) == null) {
                                e.preventDefault();
                                this.setState({
                                    card: `*The Card Number provided is incorrect, it must contain 16 digits*`,
                                });
                            }
                            else {
                                this.setState({
                                    card: '',
                                });
                            }
                        }
                        ValidateCVC = (e) => {
                            if (e.target.value.match(/[0-9]{3}/) == null) {
                                e.preventDefault();
                                this.setState({
                                    cvc: `*The CVC provided is incorrect, it must contain 3 digits*`,
                                });
                            }
                            else {
                                this.setState({
                                    cvc: '',
                                });
                            }
                        }
                        render() {
                            return (
                                <form className="container" method="POST" action="process" onSubmit={this.handleSubmit}>
                                    <p style={{color: "red" }}><%= cardErr %></p>
                                    <DisplayCard />                        
                                    <hr />
                                    <div class="form-group">
                                        <label for="card">Card Number</label>
                                        <p style={{ color: "red" }}>{this.state.card}</p>
                                        <input type="text" class="form-control" id="card" name="card" value={this.state.cardValue} placeholder="Enter card number" maxlength="16"
                                            onChange={(e) => this.setState({ cardValue: e.target.value })} onBlur={(e) => this.ValidateCard(e)} required />
                                    </div>
                                    <div class="form-group">
                                        <label for="cvc">CVC</label>
                                        <p style={{ color: "red" }}>{this.state.cvc}</p>
                                        <input type="text" class="form-control" id="cvc" name="cvc" value={this.state.cvcValue} placeholder="Enter CVC" maxlength="3"

                                            onChange={(e) => this.setState({ cvcValue: e.target.value })} onBlur={(e) => this.ValidateCVC(e)} required />
                                    </div>
                                    <div class="form-group">
                                        <label for="cvc">Expiry Date</label>
                                        <p style={{ color: "red" }}>{this.state.date}</p>
                                        <input type="date" class="form-control" id="date" name="date" placeholder="Enter the Expiry Date" onChange={(e) => this.setState({ dateValue: e.target.value })} />
                                    </div>
                                    <% if (user!=null && user.getType().equals("c")) { %>

                                    <div class="checkbox" style={{ padding: '1%' }}>
                                        <label>
                                            <input type="checkbox" value="save" name="save" />
                                            Save credit card information?
                                        </label>
                                    </div>
                                    <% } %>

                                    <button type="submit" class="btn btn-primary">Book Now</button><DisplayCard />
                                </form>
                            );
                        }
                    }
                    ReactDOM.render(<Payment />, document.getElementById('home'))
                </script>
                <center>
                    <h4>The Total Cost is <b>$<%= (booking!=null) ? booking.getTotalCost() : 100 %></b></h4>
                </center>
                <div id="home">

                </div>
            </body>

</html>