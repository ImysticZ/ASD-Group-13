CREATE TABLE USER (
    ID INT NOT NULL AUTO_INCREMENT,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME VARCHAR(255) NOT NULL,
    EMAIL VARCHAR(50) NOT NULL,
    PHONE VARCHAR(15),
    PASSWORD VARCHAR(255) NOT NULL,
    ADDRESS VARCHAR(50) NOT NULL,
    TYPE CHAR(1),
    PRIMARY KEY(ID)
);
-- ALTER TABLE USER AUTO_INCREMENT = 1000;
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
);

CREATE TABLE RoomType (
    RoomTypeID INT NOT NULL AUTO_INCREMENT,
    CostPerDay DOUBLE NOT NULL,
    NumBeds INT NOT NULL,
    Suite VARCHAR(150),
    Description VARCHAR(255),
    PRIMARY KEY(RoomTypeID)
);