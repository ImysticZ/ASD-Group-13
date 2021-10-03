INSERT INTO RoomType(CostPerDay, NumBeds, Suite, Description)
VALUES
(99.99, 1, 'Single', 'A small room for a single person'),
(149.99, 2, 'Double', 'A small room for two people'),
(169.99, 2, 'Triple', 'A room that can fit three people'),
(199.99, 2, 'Quad', 'A room that can fit four people'),
(199.99, 2, 'Queen', 'A nice room that comfortably fits two people'),
(219.99, 3, 'King', 'A nice room that comfortably fits three people'),
(349.99, 6, 'Executive Suite', 'A living room connected to three bedrooms'),
(649.99, 10, 'President Suite', 'A deluxe and comfortable room connected to several bedrooms');

INSERT INTO Enquiry (Question, Reply, Resolved, UserID)
VALUES
('How do I search for available rooms?', 'Rooms can be found on the "Rooms" page, the link can be found in the navigation bar.', true, 1011),
('How do I find directions to the hotel?', 'We suggest looking up our hotel on services like Google Maps.', true, 1011),
('Do you offer any discounts?', 'No, there are currently no discounts being provided.', true, 1011),
('I would like to cancel a booking, will I receive a refund?', 'Yes, cancelled bookings will be refunded.', true, 1011),
('Do you charge cancellation fees?', '', false, 1011),
('What happens if I arrive late?', '', false, 1011),
('Can I save more than one credit card to my account?', '', false, 1011),
('My payment for a booking is not going through', '', false, 1011),
('How do I view past bookings?', '', false, 1011),
('What is the maximum number of people for one room?', '', false, 1011);
