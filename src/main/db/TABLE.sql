CREATE TABLE Payment (
    PaymentID int NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (BookingID) REFERENCES Booking(BookingID),
    FOREIGN KEY (CardID) REFERENCES CreditCard(CardID)
);
CREATE TABLE CreditCard (
    CardID int NOT NULL AUTO_INCREMENT,
    Card_Number int NOT NULL,
    CVC int NOT NULL,
    Expiry date
);