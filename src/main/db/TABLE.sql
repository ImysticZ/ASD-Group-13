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
CREATE TABLE CUSTOMER (
    ID INT NOT NULL,
    CardID INT NOT NULL,
    FOREIGN KEY(ID) REFERENCES USER(ID),
    FOREIGN KEY (CardID) REFERENCES Card(CardID)
);

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

CREATE TABLE Room (
    RoomID INT NOT NULL AUTO_INCREMENT,
    RoomTypeID INT NOT NULL,
    PRIMARY KEY(RoomID),
    FOREIGN KEY(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE Booking (
    BookingID INT NOT NULL AUTO_INCREMENT,
    UserID INT NOT NULL,
    RoomID INT NOT NULL,
    Starting_Date DATE NOT NULL,
    Ending_Date DATE NOT NULL,
    Status VARCHAR(20) NOT NULL,
    Paid BOOLEAN NOT NULL,
    Total_Cost FLOAT NOT NULL,
    PRIMARY KEY(BookingID),
    FOREIGN KEY(UserID) REFERENCES USER(ID),
    FOREIGN KEY(RoomID) REFERENCES Room(RoomID)
);