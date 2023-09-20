CREATE DATABASE SWP391_Group3
use [SWP391_Group3]
CREATE TABLE [Role](
RoleID INT NOT NULL PRIMARY KEY,
RoleName VARCHAR(50) NOT NULL

)

INSERT INTO [Role] (RoleID, RoleName)
VALUES (1,'Admin'),
(2,'Saler'),
(3,'Customer');

CREATE TABLE BlogStatus(
StatusID INT NOT NULL PRIMARY KEY,
StatusName VARCHAR(20) NOT NULL

)

CREATE TABLE Category(
CategoryID INT NOT NULL PRIMARY KEY,
CategoryName VARCHAR(50) NOT NULL,
Status BIT NOT NULL,
)

INSERT INTO Category (CategoryID, CategoryName, Status)
VALUES
    (1, 'Oil Painting', 1),
    (2, 'Watercolor Painting', 1),
    (3, 'Pastel Painting', 1),
    (4, 'Lacquer Painting', 1),
	(5, 'Silk painting', 1),
	(6, 'Puzzle painting', 1);

CREATE TABLE Account(

Email VARCHAR(50) NOT NULL PRIMARY KEY,
Password VARCHAR(50) NOT NULL,
RoleID INT NOT NULL
FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
)
INSERT INTO Account (Email, Password, RoleID)
VALUES ('ducle2002@gmail.com','ducle123', 1),
('thien02@gmail.com','thien123', 2),
('long03@gmail.com','long1234', 2),
('son04@gmail.com','son12345', 1),
('quan05@gmail.com','quan1234', 2),
('hoang06@gmail.com','hoang123', 1),
('minh07@gmail.com','minh1234', 2),
('kkkk08@gmail.com','kkkk1234', 3),
('bao09@gmail.com','bao12345', 2),
('thanh10@gmail.com','thanh123', 1);


CREATE TABLE [User](
UserID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
UserName VARCHAR(50) NOT NULL,
Gender BIT NOT NULL,
PhoneNumber VARCHAR(20) NOT NULL,
Address VARCHAR(MAX) NOT NULL,
Avatar VARCHAR(MAX) NOT NULL,
Email VARCHAR(50) NOT NULL
FOREIGN KEY (Email) REFERENCES Account(Email)
)
INSERT INTO [User] (UserName, Gender, PhoneNumber, Address, Avatar, Email)
VALUES ('Ducle', 1, '0903020202', '123 Le Loi Street, Hai Chau Ward, Hai Chau District, Da Nang', 'https://bizweb.dktcdn.net/100/438/408/files/anh-dep-3d-yodyvn4.jpg?v=1683534900596', 'ducle2002@gmail.com'),
('Thien', 1, '0912345678', '456 Nguyen Hue Street, Ben Nghe Ward, District 1, Ho Chi Minh City', 'https://genzrelax.com/wp-content/uploads/2022/03/anh-dai-dien-dep-1.jpg', 'thien02@gmail.com'),
('Long', 1, '0923456789', '789 Ly Thuong Kiet Street, Tran Hung Dao Ward, Hoan Kiem District, Hanoi', 'https://khoinguonsangtao.vn/wp-content/uploads/2022/05/anh-avatar-dep-ngau-hinh-dai-dien-510x560.jpg', 'long03@gmail.com'),
('Son', 1, '0934567890', '321 Le Thanh Tong Street, Ngo Quyen Ward, Hong Bang District, Hai Phong', 'https://pgddttieucan.edu.vn/wp-content/uploads/2022/08/1660864210_Anh-Avatar-Dep-Cute-Chat-Ngau-Lam-Hinh-Dai-Dien-696x619.jpg', 'son04@gmail.com'),
('Quan', 1, '0945678912', '567 Phan Dinh Phung Street, Bui Hanh Ward, Hong Bang District, Hai Phong', 'https://top10kontum.com/wp-content/uploads/2022/10/anh-dai-dien-zalo-y-nghia-2.jpg', 'quan05@gmail.com'),
('Hoang', 1, '0956789123', '890 Hung Vuong Street, Vinh City, Nghe An Province', 'https://digitalfuture.vn/wp-content/uploads/2022/03/Top-555-Anh-dai-dien-dep-den-xieu-long-cho.jpg', 'hoang06@gmail.com'),
('Minh', 1, '0967891234', '234 Ho Xuan Huong Street, Truong Thi Ward, Hue City, Thua Thien Hue Province', 'https://antimatter.vn/wp-content/uploads/2022/04/avatar-trang-zalo.jpg', 'minh07@gmail.com'),
('Nam', 1, '0978912345', '678 Tran Phu Street, Hai Tan Ward, Hai An District, Hai Phong', 'https://freenice.net/wp-content/uploads/2021/08/anh-dai-dien-avatar-dep.jpg', 'kkkk08@gmail.com'),
('Bao', 1, '0989123456', '135 Le Duan Street, Thanh Binh Ward, Rach Gia City, Kien Giang Province', 'https://phunugioi.com/wp-content/uploads/2020/01/anh-dai-dien-co-gai-quay-lung.jpg', 'bao09@gmail.com'),
('Thanh', 1, '0991234567', '987 Nguyen Van Cu Street, An Hoa Ward, Rach Gia City, Kien Giang Province', 'https://i.pinimg.com/736x/94/9b/8d/949b8d8d9229693ba9d53b054b738e2a.jpg', 'thanh10@gmail.com');

CREATE TABLE Blog(
BlogID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
Title VARCHAR(MAX) NOT NULL,
Content VARCHAR(20) NOT NULL,
[Date] DATETIME NOT NULL,
UserID INT NOT NULL,
StatusID INT NOT NULL,
FOREIGN KEY (UserID) REFERENCES [User](UserID),
FOREIGN KEY (StatusID) REFERENCES BlogStatus(StatusID)
)

CREATE TABLE Product(
ProductID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
ProductName VARCHAR(50) NOT NULL,
Price DECIMAL NOT NULL,
[Description] VARCHAR(MAX) NOT NULL,
Height DECIMAL NOT NULL,
Width DECIMAL NOT NULL,
Quantity INT NOT NULL,
[View] INT NOT NULL,
Discount DECIMAL NOT NULL,
UserID INT NOT NULL,
FOREIGN KEY (UserID) REFERENCES [User](UserID)

)

CREATE TABLE ProductCategory(
ProductID INT NOT NULL ,
CategoryID INT NOT NULL,
PRIMARY KEY (ProductID, CategoryID),
FOREIGN KEY (ProductID) REFERENCES  [Product](ProductID),
FOREIGN KEY (CategoryID) REFERENCES  [Category](CategoryID)
)
CREATE TABLE [Type](
TypeID INT NOT NULL PRIMARY KEY,
TypeName VARCHAR(50) NOT NULL,
Status BIT NOT NULL
)

INSERT INTO Type (TypeID, TypeName, Status)
VALUES
    (1, 'Landscape Painting', 1),
    (2, 'Still Life Painting', 1),
	(3, 'Portrait Painting', 1),
	(4, 'Political Painting', 1),
	(5, 'Humorous Painting', 1),
	(6, 'Abstract Painting', 1);

CREATE TABLE ProductType(
ProductID INT NOT NULL ,
TypeID INT NOT NULL,
PRIMARY KEY (ProductID, TypeID),
FOREIGN KEY (ProductID) REFERENCES  [Product](ProductID),
FOREIGN KEY (TypeID) REFERENCES  [Type](TypeID)
)
CREATE TABLE ProductImage(
ProductID INT NOT NULL,
ImageUrl VARCHAR(255) UNIQUE,
PRIMARY KEY(ProductID, ImageUrl),
FOREIGN KEY (ProductID) REFERENCES  [Product](ProductID)
)
CREATE TABLE Orders(
OrderID INT NOT NULL PRIMARY KEY,
UserID INT NOT NULL,
OrderDate DATETIME NOT NULL,
FOREIGN KEY (UserID) REFERENCES  [User](UserID)
)
CREATE TABLE OrderDetail(
OrderID INT NOT NULL,
ProductID INT NOT NULL,
Quantity INT NOT NULL,
Price DECIMAL,
PRIMARY KEY(OrderID, ProductID),
FOREIGN KEY (OrderID) REFERENCES [Orders](OrderID),
FOREIGN KEY (ProductID) REFERENCES [Product](ProductID),
)
CREATE TABLE ProductComment(
CommentID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
ProductID INT NOT NULL,
CommentDate DATETIME,
Content VARCHAR(MAX),
UserID INT NOT NULL,
Rate INT NOT NULL,
FOREIGN KEY (ProductID) REFERENCES [Product](ProductID),
FOREIGN KEY (UserID) REFERENCES [User](UserID)
)

