use master
go
drop database PRJ301_Assignment
go
create database PRJ301_Assignment
go
use PRJ301_Assignment
go

CREATE TABLE Users
(
	userID varchar(15) primary key,
	fullName nvarchar(30),
	imageURL varchar(200),
	email varchar(30),
	password varchar(15),
	roleID varchar(2)
)
GO


INSERT INTO Users (userID, fullName, imageURL, email, password, roleID) VALUES ('hdnqt', N'Hoàng Đình Nhật', 'avatar/default.png', 'nhathdqt@gmail.com', '12345', 'AD')
INSERT INTO Users (userID, fullName, imageURL,  email, password, roleID) VALUES ('thainh', N'Nguyễn Hồng Thái', 'avatar/default.png', 'jayceee296@gmail.com', '12345', 'US')
INSERT INTO Users (userID, fullName, imageURL,  email, password, roleID) VALUES ('duonghc', N'Hoàng Chí Dương', 'avatar/default.png', 'duonghc@gmail.com', '12345', 'US')
Go

CREATE TABLE PhoneSeries
(
	seriesID varchar(15) primary key,
	seriesName nvarchar(30),
	imageURL varchar(200),
	minPrice float
)
GO

INSERT INTO PhoneSeries VALUES ('iPhone14', 'iPhone 14', 'images/iphone14series.webp', 20590000)
INSERT INTO PhoneSeries VALUES ('iPhone13', 'iPhone 13', 'images/iphone13series.webp', 18390000)
INSERT INTO PhoneSeries VALUES ('iPhone12', 'iPhone 12', 'images/iphone12series.webp', 15599000)
GO

CREATE TABLE Phones
(
	phoneID varchar(15) primary key,
	phoneName nvarchar(30),
	imageURL varchar(200),
	seriesID varchar(15),
	price float,
	quantity int CHECK (quantity >= 0),
	phoneCPU varchar(15),
	screenSize varchar(15),
    Foreign key (seriesID) references PhoneSeries(seriesID)
)
GO


INSERT INTO Phones VALUES ('iphone14promax', 'iPhone 14 Pro Max', 'images/iphone14promax.webp' , 'iphone14', 27990000, 10, 'A16 Binonic', '6.7 inch')
INSERT INTO Phones VALUES ('iphone14pro', 'iPhone 14 Pro', 'images/iphone14pro.webp' , 'iphone14', 25490000, 10, 'A16 Binonic', '6.1 inch')
INSERT INTO Phones VALUES ('iphone14plus', 'iPhone 14 Plus', 'images/iphone14plus.webp' , 'iphone14', 22990000, 10, 'A15 Binonic', '6.7 inch')
INSERT INTO Phones VALUES ('iphone14normal', 'iPhone 14', 'images/iphone14normal.webp' , 'iphone14', 19990000, 10, 'A15 Binonic', '6.1 inch')

INSERT INTO Phones VALUES ('iphone13promax', 'iPhone 13 Pro Max', 'images/iphone13promax.webp' , 'iphone13', 28490000, 10, 'A15 Binonic', '6.7 inch')
INSERT INTO Phones VALUES ('iphone13normal', 'iPhone 13', 'images/iphone13normal.webp' , 'iphone13', 17990000, 10, 'A15 Binonic', '6.1 inch')

INSERT INTO Phones VALUES ('iphone12normal', 'iPhone 12', 'images/iphone12normal.webp' , 'iphone12', 15599000, 10, 'A14 Binonic', '6.1 inch')
GO

CREATE TABLE Orders
(
	orderID varchar(15) primary key,
	userID varchar(15),
	foreign key (userID) references Users(userID)
)
GO

INSERT INTO Orders VALUES ('OD154678', 'hdnqt')
GO

CREATE TABLE OrderDetails
(
	orderID varchar(15) primary key,
	orderDate Date,
	expectedDeliveryDate Date,
	paymentMethod varchar(10),
	shippingUnit varchar(10),
	foreign key (orderID) references Orders(orderID)
)
GO

INSERT INTO OrderDetails VALUES('OD154678', '2023-03-18', '20230325', 'COD', 'GHN')
GO

CREATE TABLE OrderPhone
(
	orderID varchar(15),
	phoneID varchar(15),
	primary key (orderID, phoneID),
	foreign key (orderID) references Orders(orderID),
	foreign key (phoneID) references Phones(phoneID)
)
GO

INSERT INTO OrderPhone VALUES('OD154678', 'iphone12normal')
INSERT INTO OrderPhone VALUES('OD154678', 'iphone13normal')
INSERT INTO OrderPhone VALUES('OD154678', 'iphone13promax')
GO