CREATE DATABASE SWP391_Group3
GO
USE SWP391_Group3;
GO
CREATE TABLE [Role](
RoleID INT NOT NULL PRIMARY KEY,
RoleName VARCHAR(50) NOT NULL

)

INSERT INTO [Role] (RoleID, RoleName)
VALUES (1,'Admin'),
(2,'Artist'),
(3,'Customer');

CREATE TABLE PostStatus(
StatusID INT NOT NULL PRIMARY KEY,
StatusName VARCHAR(20) NOT NULL

)

CREATE TABLE Category(
CategoryID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
CategoryName VARCHAR(50) NOT NULL,
Status BIT NOT NULL,
Description VARCHAR(Max) NOT NULL
)
SET IDENTITY_INSERT Category ON;
INSERT INTO Category (CategoryID, CategoryName, Status, Description)
VALUES
    (1, 'Oil Painting', 1, 'Oil painting is a traditional and widely practiced medium in the world of visual arts. It involves using pigments mixed with a binder, typically linseed oil, to create vivid and durable artworks on a canvas or other suitable surfaces' ),
    (2, 'Watercolor Painting', 1, 'Watercolor painting is a versatile and captivating medium in the world of visual arts. It involves using water-based pigments to create artworks on paper or other absorbent surfaces'),
    (3, 'Pastel Painting', 1, 'Pastel painting is a captivating and expressive medium in the world of visual arts. It involves using soft pastels, which are sticks made of pure pigment held together with a minimal amount of binder, to create artworks on various surfaces like paper, cardboard, or specially designed pastel paper.'),
    (4, 'Lacquer Painting', 1 , 'Lacquer painting is a traditional and intricate art form that involves the use of lacquer, a natural resin obtained from the sap of certain trees, to create highly detailed and decorative artworks'),
	(5, 'Silk painting', 1, 'Silk painting is a unique and exquisite form of art that involves using silk fabric as a canvas and applying dyes or paints to create colorful and intricate designs. This art form is known for its vibrant colors, flowing lines, and the shimmering quality of silk that adds an extra dimension to the artwork'),
	(6, 'Acrylic painting', 1, 'Puzzle painting, also known as jigsaw puzzle painting, is a creative and innovative art form that combines traditional painting techniques with the interactivity of jigsaw puzzles. In puzzle paintings, an artwork is painted on multiple wooden or cardboard pieces, each of which is a small section of the larger picture.');
	SET IDENTITY_INSERT [Category] OFF;
CREATE TABLE Account(

Email VARCHAR(50) NOT NULL PRIMARY KEY,
Password VARCHAR(50) NOT NULL,
Status BIT NOT NULL,
RoleID INT NOT NULL

FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
)
INSERT INTO Account (Email, Password, Status, RoleID)
VALUES ('ducle2002@gmail.com','ducle123',1 ,1),
('thien02@gmail.com','thien123',1 ,2),
('long03@gmail.com','long1234',1, 2),
('son04@gmail.com','son12345',1, 3),
('quan05@gmail.com','quan1234',1, 2),
('hoang06@gmail.com','hoang123',1, 3), 
('minh07@gmail.com','minh1234',1, 2),
('kkkk08@gmail.com','kkkk1234',1, 3),
('bao09@gmail.com','bao12345',1, 2),
('thanh10@gmail.com','thanh123',1, 3);


CREATE TABLE [User](
UserID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
UserName VARCHAR(50) NOT NULL,
Gender BIT ,
PhoneNumber VARCHAR(20) NOT NULL,
Address NVARCHAR(MAX),
Avatar VARCHAR(MAX),
Email VARCHAR(50) NOT NULL

FOREIGN KEY (Email) REFERENCES Account(Email)
)
INSERT INTO [User] (UserName, Gender, PhoneNumber, Address, Avatar, Email)
VALUES ('Ducle', 1, '0903020202', N'123 Đường Lê Lợi, Phường Hải Châu, Quận Hải Châu, Đà Nẵng', 'https://bizweb.dktcdn.net/100/438/408/files/anh-dep-3d-yodyvn4.jpg?v=1683534900596', 'ducle2002@gmail.com'),
('Thien', 1, '0912345678', N'456 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1, Thành phố Hồ Chí Minh', 'https://genzrelax.com/wp-content/uploads/2022/03/anh-dai-dien-dep-1.jpg', 'thien02@gmail.com'),
('Long', 1, '0923456789', N'789 Đường Lý Thường Kiệt, Phường Trần Hưng Đạo, Quận Hoàn Kiếm, Hà Nội', 'https://khoinguonsangtao.vn/wp-content/uploads/2022/05/anh-avatar-dep-ngau-hinh-dai-dien-510x560.jpg', 'long03@gmail.com'),
('Son', 1, '0934567890', N'321 Đường Lê Thanh Tông, Phường Ngô Quyền, Quận Hồng Bàng, Hải Phòng', 'https://pgddttieucan.edu.vn/wp-content/uploads/2022/08/1660864210_Anh-Avatar-Dep-Cute-Chat-Ngau-Lam-Hinh-Dai-Dien-696x619.jpg', 'son04@gmail.com'),
('Quan', 1, '0945678912', N'567 Đường Phan Đình Phùng, Phường Bùi Hạnh, Quận Hồng Bàng, Hải Phòng', 'https://top10kontum.com/wp-content/uploads/2022/10/anh-dai-dien-zalo-y-nghia-2.jpg', 'quan05@gmail.com'),
('Hoang', 1, '0956789123', N'890 Đường Hùng Vương, Thành phố Vinh, Tỉnh Nghệ An', 'https://digitalfuture.vn/wp-content/uploads/2022/03/Top-555-Anh-dai-dien-dep-den-xieu-long-cho.jpg', 'hoang06@gmail.com'),
('Minh', 1, '0967891234', N'234 Đường Hồ Xuân Huong, Phường Trường Thi, Thành phố Huế, Tỉnh Thừa Thiên Huế', 'https://antimatter.vn/wp-content/uploads/2022/04/avatar-trang-zalo.jpg', 'minh07@gmail.com'),
('Nam', 1, '0978912345', N'678 Đường Trần Phú, Phường Hải Tân, Quận Hải An, Hải Phòng', 'https://freenice.net/wp-content/uploads/2021/08/anh-dai-dien-avatar-dep.jpg', 'kkkk08@gmail.com'),
('Bao', 1, '0989123456', N'135 Đường Lê Duẩn, Phường Thanh Bình, Thành phố Rạch Giá, Tỉnh Kiên Giang', 'https://phunugioi.com/wp-content/uploads/2020/01/anh-dai-dien-co-gai-quay-lung.jpg', 'bao09@gmail.com'),
('Thanh', 1, '0991234567', N'987 Đường Nguyễn Văn Cử, Phường An Hòa, Thành phố Rạch Giá, Tỉnh Kiên Giang', 'https://i.pinimg.com/736x/94/9b/8d/949b8d8d9229693ba9d53b054b738e2a.jpg', 'thanh10@gmail.com');

CREATE TABLE Post(
PostID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
Title VARCHAR(MAX) NOT NULL,
Content VARCHAR(20) NOT NULL,
[Date] DATETIME NOT NULL,
UserID INT NOT NULL,
StatusID INT NOT NULL,
PostType int,
FOREIGN KEY (UserID) REFERENCES [User](UserID),
FOREIGN KEY (StatusID) REFERENCES PostStatus(StatusID)
)

------change in the Post table 
----ALTER TABLE Post
----ADD PostType INT DEFAULT 0;


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
[Status] INT,
FOREIGN KEY (UserID) REFERENCES [User](UserID)
)
--insert for product
INSERT INTO [dbo].[Product]
           ([ProductName]
           ,[Price]
           ,[Description]
           ,[Height]
           ,[Width]
           ,[Quantity]
           ,[View]
           ,[Discount]
           ,[UserID]
		   ,[Status])
VALUES ('Beautiful Painting', 100.00,	'A stunning painting that brings freshness and brightness with vibrant colors and exquisite details. It reflects artistic talent and meticulous investment in every brushstroke, providing a sense of comfort and relaxation when looked upon',	50.0,	45.0,	99,	0,	0,	2, 1),
	   ('Landscape Artwork', 75.00,	'A breathtaking landscape painting that captures the serenity of nature. The vibrant colors and intricate brushstrokes bring to life a picturesque scene of rolling hills, a tranquil lake, and a colorful sunset. This masterpiece is an invitation to immerse yourself in the beauty of the outdoors, creating a sense of peace and wonder with every gaze. Perfect for those seeking a connection with nature within the comfort of their own space',	50.0,	25.0,	99,	0,	20,	3, 1),
	   ('Artistic Expression', 120.00,	'An awe-inspiring artwork that delves into the depths of artistic expression. With a mesmerizing blend of vibrant and subtle colors, meticulously crafted brushstrokes, and a harmonious composition, this painting invites the viewer into a world where creativity knows no bounds. The intricate details and thoughtful use of light and shadow create a visual narrative that captivates the imagination, allowing one to explore the depths of their own creativity. Each glance at this masterpiece reveals new layers of meaning and emotions, making it a timeless piece for art enthusiasts and connoisseurs.',	36.0,	24.0,	1,	0,	15.0,	7, 1),
	   ('Enchanted Forest', 200.00,	'Step into a world of enchantment with this captivating painting, "Enchanted Forest." The canvas comes alive with a symphony of colors, portraying a mystical forest where every brushstroke seems to whisper secrets of ancient trees and hidden wonders. The interplay of light and shadow creates an illusion of depth, allowing one to get lost in the mysterious depths of the woods. The artists skillful use of texture and depth brings the foliage and flora to life, making it feel as if you could almost step into this magical realm. Perfect for those seeking a piece that transports them to a realm of magic and wonder.',	42.0,	30.0,	1,	0,	0,	5, 1),
	   ('Galactic Dreamscape', 180.00,	'Transport yourself to the cosmos with the mesmerizing "Galactic Dreamscape" painting. This celestial masterpiece embodies the vastness and beauty of the universe, featuring swirling nebulae, distant stars, and a cosmic dance of colors. The play of light and dark creates a sense of depth, allowing you to peer into the depths of space. Each brushstroke is a journey through the cosmos, inviting contemplation and wonder. Perfect for stargazers and dreamers alike.',	48.0,	36.0,	99,	0,	0,	3, 1),
	   ('Serene Landscape', 250.00,	'Immerse yourself in the beauty of nature with the "Serene Landscape" painting. This artwork encapsulates the tranquility and harmony of a breathtaking landscape. Rolling hills, a gentle stream, and a radiant sunset are expertly captured, evoking a sense of peace and calmness. The artists use of color and texture brings the scene to life, inviting you to escape into this idyllic setting. A perfect addition to any space seeking a touch of serenity and natural beauty.',	36.0,	48.0,	99,	0,	0,	2, 1),
	   ('Abstract Harmony', 180.00,	'"Abstract Harmony" is a mesmerizing abstract painting that effortlessly combines colors and shapes to create a symphony for the eyes. The artists bold and expressive brushstrokes evoke a sense of movement and energy. The vibrant hues dance across the canvas, blending and contrasting in perfect harmony. This piece sparks imagination and invites viewers to find their own meaning within its abstract depths, making it a perfect addition to any contemporary art collection.',	30.0,	30.0,	99,	0,	30,	7, 1),
	   ('Elegant Portrait', 300.00,	'The "Elegant Portrait" is a masterpiece that brings a timeless elegance to life. It captures the essence and grace of the subject, presenting a beautiful portrait with exquisite details and lifelike features. The play of light and shadow accentuates the depth and character, making it a truly captivating piece. This artwork is a homage to the art of portraiture, appealing to those who appreciate the beauty and grace of the human form.',	40.0,	30.0,	99,	0,	50,	9, 1),
	   ('Contemporary Fusion'     ,280.00      ,'"Contemporary Fusion" is a dynamic piece that seamlessly blends various artistic elements. Bold, geometric shapes intertwine with vibrant splashes of color, creating a visually captivating experience. This artwork challenges traditional boundaries, provoking thought and evoking emotions. The juxtaposition of modern aesthetics and classic artistry makes it a conversation starter and a symbol of the ever-evolving nature of art.'     ,36.0      ,36.0       ,99      ,0      ,0   ,3, 1),
	   ('Tranquil Waters'       ,190.00       ,'"Tranquil Waters" transports you to the serene beauty of nature. This artwork captures the essence of calmness and purity through its depiction of a tranquil lake surrounded by lush greenery and majestic mountains. The play of light on the waters surface and the reflections create a soothing and harmonious atmosphere. Every brushstroke in this painting is a testament to the artists deep connection with the natural world, making it a perfect choice for those seeking a piece that brings the outdoors inside.'       ,30.0       ,40.0       ,99       ,0       ,0       ,7, 1),
	   ('Red Sunset', 292.00,	'Painting is an art form of painting on a surface by aesthetically applying colored fluids. Painters represent a very personal expression on supports such as paper, rock, canvas, wood, bark, glass, concrete and many other substrates. Work of representation or invention, painting can be naturalistic and figurative, or abstract. It can have narrative, descriptive, symbolic, spiritual, or philosophical content.',	30,	20,	99,	0,	0,	2, 1),
	   ('INFINITÉ', 132.00,	'A little composition game around the golden ratio and the Fibonacci sequence, and the opportunity to test pastel painting on tissue paper.',	29,	21,	99,	0,	0,	2, 1),
	   ('Ocean Sunset', 499.00,	'A vibrant sunset painting with pastels to brighten up any décor!',	24,	18,	1,	0,	0,	2, 1),
	   ('Still life Pastel Print', 123.00,	'Original pastel painting on paper. Best choice for your interior. This art work brings luck to its owner.',	30,	20,	99,	0,	0,	2, 1),
	   ('COLONIAL style HOUSE', 254.00,	'the size of the sheet with fields for Passepartout: 32 x 24 cm. The size of the drawing itself: approx. 27 x 20 cm',	32,	24,	99,	0,	0,	3, 1),
	    ('Peaceful Street',150.00,	'A "Peaceful Street" painting captures a serene and tranquil urban or suburban scene, inviting viewers into a world of calm and harmony.',	47,	31,	99,	0,	0,	7, 1),
		('Summer Noon', 150.00,	'A "Summer Noon" painting captures the essence of a bright and vibrant midday scene during the summer season.',	47,	31,	99,	0,	0,	7, 1),
		('Spring Sunlight', 160.00,	'A "Spring Sunlight" painting captures the enchanting and rejuvenating qualities of sunlight in the early days of the spring season.',	47,	31,	99,	0,	0,	7, 1),
		('Camellia', 67.00,	'"Camellia" is a captivating artwork that captures the essence of beauty, delicacy, and grace. The painting focal point is a stunning camellia flower, rendered with meticulous detail at the center of the canvas. The camellia petals are a rich, velvety shade of pink, their soft curves and layers depicted with great precision.',	19,	19,	99,	0,	0,	2, 1),
        ('Lotus Lady VIII', 170.00,	'"Lotus Lady VIII" is a captivating and symbolic artwork that features a woman in a serene and contemplative pose amidst a backdrop of lotus flowers.',	29,	37,	99,	0,	0,	3, 1),
		 ('Lotus Lady VI', 170.00,	'"Lotus Lady VI" is a captivating and symbolic artwork that features a woman in a serene and contemplative pose amidst a backdrop of lotus flowers.',	29,	37,	99,	0,	0,	9, 1),
		 ('Lotus Lady IV', 170.00,	'"Lotus Lady IV" is a captivating and symbolic artwork that features a woman in a serene and contemplative pose amidst a backdrop of lotus flowers.',	29,	37,	99,	0,	0,	9, 1),
		 ('Fairy Tail II', 170.00,	'If "Fairy Tail II" is a painting featuring a girl showering, it would typically fall under the category of figurative or contemporary art. The artist approach to the subject matter would greatly influence the style and tone of the painting, whether it focuses on realism, abstraction, symbolism, or other artistic techniques.',	24,	31,	99,	0,	0,	9, 1),
		 ('The Faces', 67.00,	'"The Faces" is a series of captivating abstract artworks that explore the human face as a source of inspiration and expression. In each piece, the artist deconstructs and reimagines facial features in a unique and non-traditional manner, pushing the boundaries of conventional representation.',	43,	39,	99,	0,	0,	9, 1),
		  ('Abstract 10', 97.00,	'"Abstract 10" is a captivating abstract artwork that engages viewers with its expressive use of color, shape, and form. The artist employs a bold and dynamic approach, creating a composition that is both visually stimulating and emotionally evocative.',	49,	31,	99,	0,	0,	9, 1),
		  ('Such a Kiss', 86.00,	'"This Painting is a vibrant and passionate work of art that captures the essence of a kiss through abstract forms and expressive use of color. The canvas comes alive with a fusion of bold reds, pinks, and purples, creating an atmosphere of intensity and warmth. ',	39,	31,	99,	0,	0,	9, 1),
		  ('Couple at night', 175.00,	'This evocative artwork captures a serene and intimate moment between two individuals under the enchanting veil of the night sky. The painting is suffused with a deep, velvety darkness that serves as the backdrop for the protagonists of the scene.',	40,	20,	99,	0,	0,	9, 1),
		  ('Night seascape', 134.00,	'This mesmerizing painting transports viewers to the tranquil and enchanting world of the nighttime seascape. The canvas is a vast expanse of deep, velvety blues and purples, representing the dark, calm waters of the sea.',	40,	27,	99,	0,	0,	9, 1),
		  ('Toad folk painting', 56.00,	'This enchanting painting is a celebration of folk art and the quirky world of toads. The canvas bursts with vibrant colors, intricate patterns, and a sense of joyful storytelling. The toads, anthropomorphized and full of character, take center stage in this whimsical composition.',	40,	30,	99,	0,	0,	2, 1),
		   ('SPRING IN THE AIR', 677.00,	'Spring in the air I adore spring with its warm wind, lots of sun and hope it brings.',	73,	100,	99,	0,	0,	3, 1);

		  --create objecttype table
CREATE TABLE ObjectType(
	TypeID INT PRIMARY KEY IDENTITY,
	TypeName VARCHAR(30)
);
--insert to objecttype table
INSERT INTO [dbo].[ObjectType]
           ([TypeName])
     VALUES ('Product Material'),
		    ('Post'),
			('Product Topic'),
			('Slider'),
			('Product');

CREATE TABLE ProductCategory(
ProductID INT NOT NULL ,
CategoryID INT NOT NULL,
PRIMARY KEY (ProductID, CategoryID),
FOREIGN KEY (ProductID) REFERENCES  [Product](ProductID),
FOREIGN KEY (CategoryID) REFERENCES  [Category](CategoryID)
)
--add column for category table
ALTER TABLE Category
ADD ObjectTypeID INT;
ALTER TABLE Category 
ADD CONSTRAINT Fk_Category_ObjectType_ObjectTypeID
FOREIGN KEY (ObjectTypeID) REFERENCES ObjectType(TypeID);
SET IDENTITY_INSERT [Category] ON;
--update data for category table
UPDATE Category
SET ObjectTypeID = 1;
SET IDENTITY_INSERT [Category] OFF;
INSERT INTO Category(CategoryName, Status, Description,ObjectTypeID )
VALUES
    ('Landscape Painting', 1, 'Landscape painting is a timeless and beloved genre of visual art that focuses on depicting natural scenery, often showcasing the beauty and grandeur of the natural world', 3),
    ('Still Life Painting', 1, 'Still life painting is a genre of visual art that focuses on depicting inanimate objects, typically arranged in a composed and deliberate manner', 3),
	('Portrait Painting', 1, 'Portrait painting is a revered genre of visual art that focuses on the depiction of a person or a group of people, capturing their likeness, personality, and often their emotions', 3),
	('Political Painting', 1, 'Political painting is a genre of visual art that addresses political themes, issues, and commentary through the use of visual imagery and symbolism', 3),
	('Humorous Painting', 1, 'Humorous painting is a delightful genre of visual art that aims to evoke laughter, amusement, or a sense of lightheartedness in viewers through clever and comical imagery', 3),
	('Abstract Painting', 1, 'Abstract painting is a dynamic and expressive genre of visual art that emphasizes the use of shapes, colors, lines, and forms to convey emotions, ideas, and concepts, rather than a direct representation of recognizable objects or scenes', 3);
	SET IDENTITY_INSERT [Category] OFF;

CREATE TABLE ProductImage(
ProductID INT NOT NULL,
ImageUrl VARCHAR(255) UNIQUE,
PRIMARY KEY(ProductID, ImageUrl),
FOREIGN KEY (ProductID) REFERENCES  [Product](ProductID)
)
--insert for ProductImage
INSERT INTO [dbo].[ProductImage]
           ([ProductID]
           ,[ImageUrl])
     VALUES (8,	'https://cdn11.bigcommerce.com/s-rh8oo/images/stencil/original/products/9284/46854/484861_640_320__36276.1631022081.jpg?c=2'),
		    (2,	'https://cdna.artstation.com/p/assets/images/images/013/078/628/large/bogdan-lisovetsky-.jpg?1537956986'),
			(1,	'https://charkoledesigns.com/cdn/shop/products/Warmth-of-Community-1000-x-800-size_c93a1768-9a3c-4dde-8296-5f8d1466ac3b_1000x.jpg?v=1570768477'),
            (3,	'https://images.squarespace-cdn.com/content/v1/5c2f7f8e2971143d628136a8/1606845407887-BH5WPLXFEK5O7AOOHJ6O/photo+5.jpg?format=1000w'),
			(4,	'https://images.squarespace-cdn.com/content/v1/5cee03f752ab760001a4764b/1674838836674-X52EF0VHHY32XBCPDBD8/iowagradient8x10sm.png?format=1000w'),
			(5,	'https://www.chartingnature.com/cdn/shop/products/Plate-32-Black-billed-Cuckoo-final.jpg?v=1535306417'),
			(6,	'https://www.chicagoskylineart.com/cdn/shop/products/abstract_art_painting_DARKER_DSCN0931.jpg?v=1648873269'),
			(7,	'https://www.evergreenartcafe.co.uk/cdn/shop/products/EnchantedLand.jpg?v=1643283516')

CREATE TABLE Orders(
OrderID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
UserID INT NOT NULL,
OrderDate DATETIME NOT NULL,
TotalPrice DECIMAL NOT NULL,
CustomerName NVARCHAR(MAX) NOT NULL,
PhoneNumber VARCHAR(20) NOT NULL,
Address NVARCHAR(MAX) NOT NULL,
Payment VARCHAR(MAX) NOT NULL

FOREIGN KEY (UserID) REFERENCES  [User](UserID)
)
CREATE TABLE OrderDetail(
DetailID INT IDENTITY(1,1) NOT NULL,
OrderID INT NOT NULL,
ProductID INT NOT NULL,
Quantity INT NOT NULL,
Price DECIMAL,
PRIMARY KEY(DetailID, ProductID),
FOREIGN KEY (OrderID) REFERENCES [Orders](OrderID),
FOREIGN KEY (ProductID) REFERENCES [Product](ProductID),
Status VARCHAR(MAX) NOT NULL,
ExpDate DATETIME,
DelDate DATETIME
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

--create table comment
CREATE TABLE Comment(
	CommentID INT PRIMARY KEY IDENTITY,
	UserID INT REFERENCES [User](UserID),
	PostID INT REFERENCES Post(PostID),
	CommentContent VARCHAR(MAX),
	CommentDate DATETIME
);



--delete table product comment
DROP TABLE ProductComment;
DROP TABLE ProductImage;
DROP TABLE Comment;

--re-create Comment table
CREATE TABLE Comment(
	CommentID INT PRIMARY KEY IDENTITY,
	UserID INT REFERENCES [User](UserID),
	TypeID INT REFERENCES ObjectType(TypeID),--type of the object own the image
	ObjectID INT,--id of the object
	CommentContent VARCHAR(MAX),
	CommentDate DATETIME
);

--re-create image table
CREATE TABLE [Image](
	ImageID INT IDENTITY PRIMARY KEY,
	TypeID INT REFERENCES ObjectType(TypeID),
	ObjectID INT,--id of the object
	ImageUrl VARCHAR(MAX)
);


--re-insert for Image table
INSERT INTO [dbo].[Image]
			([TypeID]
           ,[ObjectID]
           ,[ImageUrl])
     VALUES 
			(1, 1,	'product.jpg'),
			(1, 1,	'product_1.jpg'),
			(1, 1,	'product_2.jpg'),
			(1, 2,	'product2.jpg'),
			(1, 2,	'product2_1.jpg'),
			(1, 2,	'product2_2.jpg'),
            (1, 3,	'product3.jpg'),
			(1, 3,	'product3_1.jpg'),
			(1, 3,	'product3_2.jpg'),
			(1, 4,	'product4.jpg'),
			(1, 4,	'product4_1.jpg'),
			(1, 4,	'product4_2.jpg'),
			(1, 4,	'product4_3.jpg'),
			(1, 5,	'product5.jpg'),
			(1, 5,	'product5_1.jpg'),
			(1, 5,	'product5_2.jpg'),
			(1, 5,	'product5_3.jpg'),
			(1, 6,	'product6.jpg'),
			(1, 6,	'product6_1.jpg'),
			(1, 6,	'product6_2.jpg'),
			(1, 6,	'product6_3.jpg'),
			(1, 7,	'product7.jpg'),
			(1, 7,	'product7_1.jpg'),
			(1, 7,	'product7_2.jpg'),
			(1, 7,	'product7_3.jpg'),
			(1, 8,	'product8.jpg'),
			(1, 8,	'product8_1.jpg'),
			(1, 8,	'product8_2.jpg'),
			(1, 8,	'product8_3.jpg'),
			(1, 9,	'product9.jpg'),
			(1, 9,	'product9_1.jpg'),
			(1, 9,	'product9_2.jpg'),
			(1, 9,	'product9_3.jpg'),
			(1, 10,	'product10.jpg'),
			(1, 10,	'product10_1.jpg'),
			(1, 10,	'product10_2.jpg'),
			(1, 10,	'product10_3.jpg'),
			(1, 11,	'product11.jpg'),
			(1, 11,	'product11_1.jpg'),
			(1, 11,	'product11_2.jpg'),
			(1, 11,	'product11_3.jpg'),
			(1, 12,	'product12.jpg'),
			(1, 12,	'product12_1.jpg'),
			(1, 12,	'product12_2.jpg'),
			(1, 12,	'product12_3.jpg'),
			(1, 13,	'product13.jpg'),
			(1, 13,	'product13_1.jpg'),
			(1, 13,	'product13_2.jpg'),
			(1, 13,	'product13_3.jpg'),
			(1, 14,	'product14.jpg'),
			(1, 14,	'product14_1.jpg'),
			(1, 14,	'product14_2.jpg'),
			(1, 14,	'product14_3.jpg'),
			(1, 15,	'product15.jpg'),
			(1, 15,	'product15_1.jpg'),
			(1, 15,	'product15_2.jpg'),
			(1, 15,	'product15_3.jpg'),
			(1, 16,	'product16.jpg'),
			(1, 16,	'product16_1.jpg'),
			(1, 16,	'product16_2.jpg'),
			(1, 16,	'product16_3.jpg'),
			(1, 17,	'product17.jpg'),
			(1, 17,	'product17_1.jpg'),
			(1, 17,	'product17_2.jpg'),
			(1, 17,	'product17_3.jpg'),
			(1, 18,	'product18.jpg'),
			(1, 18,	'product18_1.jpg'),
			(1, 18,	'product18_2.jpg'),
			(1, 18,	'product18_3.jpg'),
			(1, 19,	'product19.jpg'),
			(1, 19,	'product19_1.jpg'),
			(1, 19,	'product19_2.jpg'),
			(1, 19,	'product19_3.jpg'),
			(1, 20,	'product20.jpg'),
			(1, 20,	'product20_1.jpg'),
			(1, 20,	'product20_2.jpg'),
			(1, 20,	'product20_3.jpg'),
			(1, 21,	'product21.jpg'),
			(1, 21,	'product21_1.jpg'),
			(1, 21,	'product21_2.jpg'),
			(1, 21,	'product21_3.jpg'),
			(1, 22,	'product22.jpg'),
			(1, 22,	'product22_1.jpg'),
			(1, 22,	'product22_2.jpg'),
			(1, 22,	'product22_3.jpg'),
			(1, 23,	'product23.jpg'),
			(1, 23,	'product23_1.jpg'),
			(1, 23,	'product23_2.jpg'),
			(1, 23,	'product23_3.jpg'),
			(1, 24,	'product24.jpg'),
			(1, 24,	'product24_1.jpg'),
			(1, 24,	'product24_2.jpg'),
			(1, 24,	'product24_3.jpg'),
			(1, 25,	'product25.jpg'),
			(1, 25,	'product25_1.jpg'),
			(1, 25,	'product25_2.jpg'),
			(1, 25,	'product25_3.jpg'),
			(1, 26,	'product26.jpg'),
			(1, 26,	'product26_1.jpg'),
			(1, 26,	'product26_2.jpg'),
			(1, 26,	'product26_3.jpg'),
			(1, 27,	'product27.jpg'),
			(1, 27,	'product27_1.jpg'),
			(1, 27,	'product27_2.jpg'),
			(1, 27,	'product27_3.jpg'),
			(1, 28,	'product28.jpg'),
			(1, 28,	'product28_1.jpg'),
			(1, 28,	'product28_2.jpg'),
			(1, 28,	'product28_3.jpg'),
			(1, 29,	'product29.jpg'),
			(1, 29,	'product29_1.jpg'),
			(1, 29,	'product29_2.jpg'),
			(1, 29,	'product29_3.jpg'),
			(1, 30,	'product30.jpg'),
			(1, 30,	'product30_1.jpg'),
			(1, 30,	'product30_2.jpg'),
			(1, 30,	'product30_3.jpg');
			
			

--add data for comment table
INSERT INTO [dbo].[Comment]
           ([UserID]
           ,[TypeID]
           ,[ObjectID]
           ,[CommentContent]
           ,[CommentDate])
     VALUES (1, 1, 1, N'Amazing, i have finding this kind of art for so long. Now i dont have to running around to get this kind of art', '2023-09-20'),
			(2, 1, 2, N'Haizza, this pic is exactly what im looking for for life', '2023-09-10'),
			(3, 1, 3, N'I love style of this pic, where have they been the whole time?', '2023-03-10'),
			(4, 1, 4, N'Wow, this pic is amazing, look at this, you cant convince me this is truly art', '2023-04-14'),
			(5, 1, 5, N'Thank you customer for supporting us the whole time. Your support is our motivation to be creative', '2023-05-05'),
			(10, 5, 5, N'This picture is amazing, i need the address', '2023-04-14'), 
			(4, 5, 5, N'Where can i get one of this? It fit perfectly with my room', '2023-05-15'),
			(3, 5, 5, N'What a masterpiece, i mean how can you make the color like that?', '2023-06-16'),
			(2, 5, 5, N'Wow, aint no way this is from a amateur artist, is there an auction for this?', '2023-07-17'),
			(5, 5, 5, N'PM me please, i want pictures like this, a lot, please create more', '2023-08-18')

--change datatype of comment table
ALTER TABLE Comment
ALTER COLUMN CommentContent NVARCHAR(MAX) COLLATE Vietnamese_CI_AS

--add column View for Post table 
ALTER TABLE Post
ADD [View] INT DEFAULT 0;

--adjustment to Post table
ALTER TABLE Post
ALTER COLUMN Title VARCHAR(MAX);
ALTER TABLE Post
ALTER COLUMN Content VARCHAR(MAX);
ALTER TABLE Post
ADD CONSTRAINT Fk_Post_PostStatus_StatusID
FOREIGN KEY (StatusID) REFERENCES PostStatus(StatusID);

--add data for Post Status table
INSERT INTO [dbo].[PostStatus]
           ([StatusID]
           ,[StatusName])
VALUES (1, 'Waiting'),
	   (2, 'Approved'),
	   (3, 'Denied');

--add data for Post Table 
INSERT INTO [dbo].[Post]
           ([Title]
           ,[Content]
           ,[Date]
           ,[UserID]
           ,[StatusID]
		   ,PostType
           ,[View])
VALUES ('History of art', 
		'The history of art focuses on objects made by humans for any number of spiritual, narrative, philosophical, symbolic, conceptual, documentary, decorative, and even functional and other purposes, but with a primary emphasis on its aesthetic visual form. Visual art can be classified in diverse ways, such as separating fine arts from applied arts; inclusively focusing on human creativity; or focusing on different media such as architecture, sculpture, painting, film, photography, and graphic arts. In recent years, technological advances have led to video art, computer art, performance art, animation, television, and videogames.
		 The history of art is often told as a chronology of masterpieces created during each civilization. It can thus be framed as a story of high culture, epitomized by the Wonders of the World. On the other hand, vernacular art expressions can also be integrated into art historical narratives, referred to as folk arts or craft. The more closely that an art historian engages with these latter forms of low culture, the more likely it is that they will identify their work as examining visual culture or material culture, or as contributing to fields related to art history, such as anthropology or archaeology. In the latter cases, art objects may be referred to as archeological artifacts.', 
		'2023-04-14',
		1, 2, 2, 0),
		('Finding a picture for coffe shop',
		'I want to find some picture to decorate my coffe store, i want it to has bright color, main theme is coffe or the feeling of chill in a coffe shop.',
		'2023-05-15',
		2, 2, 2, 0),
		('Finding decor pic for new house',
		'I want to find a picture for our new house. I want 5 pics that has dark color, IT theme for me and 5 pics has brighter color and a bit for children for my wife. Feel free to leave contact', 
		'2023-08-09',
		3, 2, 2, 0),
		('Presenting new picture for your home',
		'Today i will present to all customer these pictures of mine, they are color full, beautiful and have all size for you to choose. Please have a look and leave a comment to let me know which one is your favorite.',
		'2023-06-06',
		4, 2, 2, 0);

--add data for image table
INSERT INTO [dbo].[Image]
           ([TypeID]
           ,[ObjectID]
           ,[ImageUrl])
VALUES (2, 1, 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg/800px-Mona_Lisa%2C_by_Leonardo_da_Vinci%2C_from_C2RMF_retouched.jpg'),
	   (2, 1, 'https://upload.wikimedia.org/wikipedia/commons/0/07/Lascaux2.jpg'),
	   (2, 1, 'https://upload.wikimedia.org/wikipedia/en/thumb/4/4c/Les_Demoiselles_d%27Avignon.jpg/1024px-Les_Demoiselles_d%27Avignon.jpg'),
	   (2, 4, 'https://thehousethatlarsbuilt.com/wp-content/uploads/2017/09/layered-fan-wall-piece-diy-9.jpg'),
	   (2, 4, 'https://cdn.sanity.io/images/cctd4ker/production/7a66ca4beaee92042c71f884be97561d0576116f-1080x1080.jpg?w=3840&q=75&fit=clip&auto=format'),
	   (2, 4, 'https://hoanghamobile.com/tin-tuc/wp-content/uploads/2023/08/ve-tranh-phong-canh-don-gian-2-2.jpg')

--add data for Product Category table
INSERT INTO [dbo].[ProductCategory]
           ([ProductID]
           ,[CategoryID])
VALUES (1, 2),
	   (1, 7),
	   (2, 2),
	   (2, 7),
	   (3, 6),
	   (3, 2),
	   (3, 12),
	   (3, 11),
	   (4, 6),
	   (4, 7),
	   (5, 6),
	    (5, 2),
	   (5, 7),
	   (6, 2),
	   (6, 7),
	   (7, 1),
	   (7, 2),
	   (7, 12),
	   (8, 6),
	   (8, 9),
	   (9, 2),
	    (9, 11),
	   (10, 3),
	   (10, 1),
	   (10, 7),
	   (11, 1),
	   (11, 3),
	   (11, 7),
	   (12, 1),
	   (12, 3),
	   (12, 12),
	    (13, 1),
	   (13, 3),
	   (13, 7),
	   (14, 3),
	   (14, 8),
	   (15, 3),
	   (15, 2),
	   (15, 8),
	   (16, 4),
	   (16, 7),
	    (17, 4),
	   (17, 7),
	   (18, 4),
	   (18, 7),
	   (19, 4),
	   (19, 9),
	   (20, 5),
	   (20, 9),
	   (21, 5),
	   (21, 9),
	    (22, 5),
	   (22, 9),
	   (23, 5),
	   (23, 9),
	   (24, 6),
	   (24, 11),
	   (24, 12),
	   (25, 1),
	   (25, 12),
	     (26, 6),
	   (26, 11),
	   (26, 12),
	   (27, 3),
	   (27, 7),
	    (28, 3),
	   (28, 7),
	   (29, 3),
	   (29, 5),
	   (29, 11),
	   (30, 4),
	   (30, 2),
	   (30, 9),
	   (30, 12);

--NEW VERSION






--add data for category table: ThanhNX
INSERT INTO [dbo].[Category]
           ([CategoryName]
           ,[Status]
		   ,[Description]
           ,[ObjectTypeID])
     VALUES ( 'Life and art', 1,'Post about life and art', 2),
			( 'Q&A', 1,'Post about question and answer', 2),
			( 'Introduce product', 1,'post to introduce products', 2),
			( 'Review product', 1,'post to review products', 2),
			( 'Sharing', 1,'post to sharing information', 2);
--create table PostCategory: ThanhNX
CREATE TABLE PostCategory(
	PostID INT REFERENCES Post(PostID),
	CategoryID INT REFERENCES Category(CategoryID)
	PRIMARY KEY(PostID, CategoryID)
);

--add data for PostCategory Table: ThanhNX
INSERT INTO [dbo].[PostCategory]
           ([PostID]
           ,[CategoryID])
     VALUES(1, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Life and art')),
		   (1, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Sharing')),
		   (2, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Q&A')),
		   (3, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Q&A')),
		   (4, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Life and art')),
		   (4, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Introduce product')),
		   (4, (SELECT c.CategoryID FROM Category c WHERE c.CategoryName = 'Sharing'))
--DATA UNTILL NOW IS SYNCHRONIZED

--add column for comment table
ALTER TABLE Comment
ADD CommentRepID INT DEFAULT NULL;
ALTER TABLE Comment
ADD CONSTRAINT Fk_Comment_CommentID_CommentRepID
FOREIGN KEY (CommentRepID) REFERENCES Comment(CommentID);

--add data for comment table
INSERT INTO [dbo].[Comment]
           ([UserID]
           ,[TypeID]
           ,[ObjectID]
           ,[CommentContent]
           ,[CommentDate]
		   ,CommentRepID)
     VALUES(1, (SELECT o.TypeID FROM ObjectType o WHERE o.TypeName = 'Post'), 
		   1, 'So nice to know more bout history', '2023-04-14', NULL),
		   (2, (SELECT o.TypeID FROM ObjectType o WHERE o.TypeName = 'Post'),
		   1, 'Man i love history, specialy bout art', '2023-04-14', 6),
		   (3, (SELECT o.TypeID FROM ObjectType o WHERE o.TypeName = 'Post'),
		   1, 'Look at the pic in a cave, isnt it beautiful?', '2023-04-14', 6),
		   (4, (SELECT o.TypeID FROM ObjectType o WHERE o.TypeName = 'Post'),
		   1, 'Ooh, so much helpful information', '2023-04-15', NULL),
		   (5, (SELECT o.TypeID FROM ObjectType o WHERE o.TypeName = 'Post'),
		   1, 'I want more post like this', '2023-04-16', NULL),
		   (1, 5, 5, 'Indeed this pic is amazing', '2023-04-24', 6),
		   (2, 5, 5, 'hmm, i doubt about that, its not good enough for me', '2023-04-24', 6),
		   (1, 5, 5, 'i think for an amateur artist, it is good', '2023-04-25', 6),
		   (10, 5, 5, 'nah, dont agree', '2023-05-01', 6),
		   (4, 5, 5, 'i like ur opinion', '2023-05-20', 7),
		   (3, 5, 5, 'hmm, this could be better but its ok', '2023-05-21', 7),
		   (10, 5, 5, 'i love it too, wanna share it? :>', '2023-12-12', 9)



