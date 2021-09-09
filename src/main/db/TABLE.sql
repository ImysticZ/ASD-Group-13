CREATE TABLE Payment (
    PaymentID int NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (BookingID) REFERENCES Booking(BookingID),
    FOREIGN KEY (CardID) REFERENCES CreditCard(CardID)
);
CREATE TABLE Card (
    CardID int NOT NULL AUTO_INCREMENT,
    Card_Number int NOT NULL,
    CVC int NOT NULL,
    Expiry date
);

CREATE TABLE Enquiry (
    EnquiryID INT NOT NULL PRIMARY KEY 
    GENERATED ALWAYS AS IDENTITY 
    (START WITH 1, INCREMENT BY 1),
    Question VARCHAR (150),
    Reply VARCHAR (150),
    Resolved BOOLEAN
    FOREIGN KEY (UserID) REFERENCES User
)