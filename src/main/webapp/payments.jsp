<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--React CDN Links-->
    <script src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
    <script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
    <script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>

    <!-- Font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!-- bootstrap-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <title>Booking | Pay Now</title>
    <jsp:include page="nav.jsp" />

</head>

<body>
    <script type="text/babel">
        const Total = () => <center><h4>The Total Cost is <b>$100.00</b></h4></center>

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
                    cardValue: null,
                    cvcValue: null,
                    dateValue: new Date()
                }
            }
            handleSubmit = (e) => {
                if (this.state.cardValue== null && this.state.cvcValue==null) {
                    e.preventDefault();
                } 
            }
            ValidateCard = (e) => {
                if (e.target.value.match(/[0-9]{16}/) == null) {
                    e.preventDefault();
                    this.setState({
                    card: `*The Card Number provided is incorrect, it must contain 16 digits*`
                });
                return false;
                }
                else {
                    this.setState({
                        card: '',
                        cardValue: e.target.value
                    });
                }
                return true;
            }
            ValidateCVC = (e) => {
                if (e.target.value.match(/[0-9]{3}/) == null) {
                    e.preventDefault();
                    this.setState({
                    cvc: `*The CVC provided is incorrect, it must contain 3 digits*`
                    });
                return false;
                }
                else {
                    this.setState({
                        cvc: '',
                        cvcValue: e.target.value
                    });
                    return true;
                }
            }
            render() {
                return (
                    <form className="container" method="POST" action="success.jsp" onSubmit={this.handleSubmit}>
                        <Total />
                        <DisplayCard />
                        <div class="form-group">
                            <label for="card">Card Number</label>
                            <p style={{ color: "red" }}>{this.state.card}</p>
                            <input type="text" class="form-control" id="card" name="card" placeholder="Enter card number" maxlength="16" onBlur={(e) => this.ValidateCard(e)} required />
                        </div>
                        <div class="form-group">
                            <label for="cvc">CVC</label>
                            <p style={{ color: "red" }}>{this.state.cvc}</p>
                            <input type="text" class="form-control" id="cvc" name="cvc" placeholder="Enter CVC" maxlength="3" onBlur={(e)=> this.ValidateCVC(e)} required />
                        </div>
                        <div class="form-group">
                            <label for="cvc">Expiry Date</label>
                            <p style={{ color: "red" }}>{this.state.date}</p>
                            <p>{this.state.dateValue.toString()}</p>
                            <input type="date" class="form-control" id="date" name="date" placeholder="Enter the Expiry Date" onBlur={(e) => this.setState({dateValue: e.target.value})} />
                        </div>
                        <button type="submit" class="btn btn-primary">Book Now</button>
                    </form>
                );
            }
        }
        ReactDOM.render(<Payment />, document.getElementById('home'))
    </script>
    <div id="home"></div>
</body>

</html>