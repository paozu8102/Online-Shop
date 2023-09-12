
CREATE TABLE [Role](
RoleID int Not Null PRIMARY KEY,
RoleName varchar(50) Not NULL

)

INSERT INTO [Role] (RoleID,RoleName) 
VALUES (1, 'Admin' ),
       (2, 'Saler')

Create Table BlogStatus(
StatusID int Not Null PRIMARY KEY,
StatusName varchar(20) Not NULL

)

Create Table Category(
CategoryID int Not Null PRIMARY KEY,
CategoryName varchar(50) Not NULL

)


Create Table Account(
AccountID int IDENTITY(1,1) Not Null PRIMARY KEY,
Email varchar(50) Not NULL,
Password varchar(50) Not NULL,
RoleID int 
FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
)

Create Table [User](
UserID int IDENTITY(1,1) Not Null PRIMARY KEY,
UserName varchar(50) Not NULL,
Gender bit Not NULL,
PhoneNumber varchar(20) Not NULL,
Address varchar(max) Not NULL,
Avatar varchar(max) Not NULL,
AccountID int Not NULL
FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
)

Create Table Blog(
BlogID int IDENTITY(1,1) Not Null PRIMARY KEY,
Title varchar(max) Not NULL,
Content varchar(20) Not NULL,
[Date] date Not NULL,
UserID int Not NULL,
StatusID int Not Null,
FOREIGN KEY (UserID) REFERENCES [User](UserID),
FOREIGN KEY (StatusID) REFERENCES BlogStatus(StatusID)
)

Create Table Product(
ProductID int IDENTITY(1,1) Not Null PRIMARY KEY,
ProductName varchar(50) Not NULL,
Price decimal Not NULL,
[Description] varchar(max) Not NULL,
Height decimal Not NULL,
Width decimal Not NULL,
Quantity int Not NULL,
[View] int Not NULL,
Discount decimal Not NULL,
UserID int Not NULL,
FOREIGN KEY (UserID) REFERENCES [User](UserID)

)

Create Table ProductCategory(
ProductID int Not Null ,
CategoryID int Not Null,
primary key (ProductID, CategoryID),
foreign key (ProductID) references [Product](ProductID),
foreign key (CategoryID) references [Category](CategoryID)
)
Create Table ProductImage(
ProductID int not null,
ImageUrl varchar(max) UNIQUE,
PRIMARY KEY(ProductID, ImageUrl),
foreign key (ProductID) references [Product](ProductID),

)
create table Orders(
OrderID int not null primary key,
UserID int not null,
OrderDate datetime,
foreign key (UserID) references [User](UserID)
)

create table OrderDetail(
OrderID int not null,
ProductID int not null,
Quantity int not null,
Price decimal,
primary key(OrderID, ProductID),
foreign key (OrderID) references [Orders](OrderID),
foreign key (ProductID) references [Product](ProductID),
)
create table Cart(
CartID int not null primary key,
UserID int not null,
foreign key (UserID) references [User](UserID)
)
create table CartDetail(
ProductID int not null,
CartID int not null,
Quantity int not null,
primary key(ProductID, CartID, Quantity),
foreign key (ProductID) references [Product](ProductID),
foreign key (CartID) references [Cart](CartID)
)
create table ProductComment(
CommentID int IDENTITY(1,1) not null primary key,
ProductID int not null,
CommentDate datetime,
Content varchar(max),
UserID int not null,
Rate int not null,
foreign key (ProductID) references [Product](ProductID),
foreign key (UserID) references [User](UserID)
)