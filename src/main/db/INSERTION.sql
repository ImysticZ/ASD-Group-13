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

INSERT INTO Room(RoomTypeID)
VALUES
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17);