/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Category;
import Model.Comment;
import Model.Product;
import Model.Setting;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Anh
 */
public class ProductDAO extends DBContext {
    
    //get all replied comment by a list of root comment: ThanhNX
    public ArrayList<ArrayList<Comment>> getAllRepFeedback(ArrayList<Comment> rootCommentList) {
        ArrayList<ArrayList<Comment>> repCommentList = new ArrayList<>();
        String command = "SELECT u.UserID,\n"
                + "	   u.Avatar,\n"
                + "	   u.UserName,\n"
                + "	   c.CommentID,\n"
                + "	   CONVERT(varchar, c.CommentDate, 103) + ' ' + CONVERT(varchar, c.CommentDate, 108) CommentDate,\n"
                + "	   c.CommentContent\n"
                + "FROM Comment c JOIN [User] u\n"
                + "ON c.UserID = u.UserID\n"
                + "JOIN ObjectType o\n"
                + "ON c.TypeID = o.TypeID\n"
                + "WHERE c.CommentRepID = ?\n"
                + "	  AND o.TypeName = 'Product'\n"
                + "ORDER BY c.CommentDate";
        for (int i = 0; i < rootCommentList.size(); i++) {
            try {
                ArrayList<Comment> repComments = new ArrayList<>();
                PreparedStatement st = getConnection().prepareStatement(command);
                st.setString(1, String.valueOf(rootCommentList.get(i).getCommentID()));
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setUserID(rs.getInt("UserID"));
                    comment.setAvatar(rs.getString("Avatar"));
                    comment.setUserName(rs.getString("UserName"));
                    comment.setCommentID(rs.getInt("CommentID"));
                    comment.setCommentDate(rs.getString("CommentDate"));
                    comment.setCommentContent(rs.getString("CommentContent"));
                    repComments.add(comment);
                }
                repCommentList.add(repComments);
            } catch (Exception e) {
            }
        }
        return repCommentList;
    }
    
    //get all root feedback: ThanhNX
    public ArrayList<Comment> getAllRootCommentByProductID(String id) {
        ArrayList<Comment> rootCommentList = new ArrayList<>();
        String command = "SELECT u.UserID,\n"
                + "	   u.Avatar,\n"
                + "	   u.UserName,\n"
                + "	   c.CommentID,\n"
                + "	   CONVERT(varchar, c.CommentDate, 103) + ' ' + CONVERT(varchar, c.CommentDate, 108) CommentDate,\n"
                + "	   c.CommentContent\n"
                + "FROM Comment c JOIN [User] u\n"
                + "ON c.UserID = u.UserID\n"
                + "JOIN ObjectType o\n"
                + "ON c.TypeID = o.TypeID\n"
                + "WHERE c.CommentRepID IS NULL \n"
                + "	  AND o.TypeName = 'Product'\n"
                + "	  AND c.ObjectID = ?\n"
                + "ORDER BY c.CommentDate";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setUserID(rs.getInt("UserID"));
                comment.setAvatar(rs.getString("Avatar"));
                comment.setUserName(rs.getString("UserName"));
                comment.setCommentID(rs.getInt("CommentID"));
                comment.setCommentDate(rs.getString("CommentDate"));
                comment.setCommentContent(rs.getString("CommentContent"));
                rootCommentList.add(comment);
            }
        } catch (Exception e) {
        }
        return rootCommentList;
    }

    //get number comment of a product: ThanhNX
    public int getCommentNumberByProductID(String id) {
        int CommentNumber = 0;
        String command = "SELECT COUNT(*) AS CommentNumber\n"
                + "FROM Comment c JOIN ObjectType o\n"
                + "ON c.TypeID = o.TypeID JOIN Product p\n"
                + "ON p.ProductID = c.ObjectID\n"
                + "WHERE o.TypeName = 'Product' AND p.ProductID = ?;";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CommentNumber = rs.getInt("CommentNumber");
            }
        } catch (Exception e) {
        }
        return CommentNumber;
    }
    
    //get 8 latest product in DB: ThanhNX
    public ArrayList<Product> getLatestProduct() {
        ArrayList<Product> homeProduct = new ArrayList<>();
        String command = "SELECT TOP 8 * FROM Product WHERE Status = 1";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                double Price = rs.getDouble("Price");
                String Description = rs.getString("Description");
                double Height = rs.getDouble("Height");
                double Width = rs.getDouble("Width");
                int Quantity = rs.getInt("Quantity");
                int View = rs.getInt("View");
                double Discount = rs.getDouble("Discount");
                int UserID = rs.getInt("UserID");
                homeProduct.add(new Product(ProductID, ProductName, Price, Description,
                        Height, Width, Quantity, View, Discount, UserID));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return homeProduct;
    }

    //get each product one picture: ThanhNX
    public ArrayList<String> getOnePicPerProduct(ArrayList<Product> products) {
        ArrayList<String> picList = new ArrayList<>();
        String command = "SELECT ImageUrl\n"
                + "FROM Product p JOIN [Image] i\n"
                + "ON p.ProductID = i.ObjectID\n"
                + "WHERE p.ProductID = ? AND i.TypeID = 1";
        for (int i = 0; i < products.size(); i++) {
            try {
                PreparedStatement st = getConnection().prepareStatement(command);
                st.setInt(1, products.get(i).getProductID());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    picList.add(rs.getString("ImageUrl"));
                }
            } catch (SQLException e) {
                // Handle SQL exception
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
            } catch (Exception e) {
                // Handle other exceptions
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
            }
        }

        return picList;
    }

    //HoangNH
    public List<Product> getProductBlockIndex(int index, int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n" // Added a comma here
                + "    P.[Status]\n" // Added a comma here
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "    AND P.Status = 0 " // Add condition to filter by UserID
                + "ORDER BY P.ProductID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID); // Set the UserID parameter
            st.setInt(2, (index - 1) * 6); // Set the offset parameter
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

// Get all product active HoangNH
    public List<Product> getProductActiveIndex(int index, int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n" // Added a comma here
                + "    P.[Status]\n" // Added a comma here
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "    AND P.Status = 1 " // Add condition to filter by UserID
                + "ORDER BY P.ProductID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID); // Set the UserID parameter
            st.setInt(2, (index - 1) * 6); // Set the offset parameter
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getManageProduct(int index, int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n" // Added a comma here
                + "    P.[Status]\n" // Added a comma here
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n" // Add condition to filter by UserID
                + "ORDER BY P.ProductID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID); // Set the UserID parameter
            st.setInt(2, (index - 1) * 6); // Set the offset parameter
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public void addProduct(String name, String price, String description, String height, String width, String quantity, int userid) {
        try {
            String sql = "INSERT INTO product (ProductName, Price, [Description], Height, Width, Quantity, [View], Discount, UserID, [Status]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, 0, 0, ?, 1);";
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setString(1, name);
            st.setBigDecimal(2, new BigDecimal(price));
            st.setString(3, description);
            st.setBigDecimal(4, new BigDecimal(height));
            st.setBigDecimal(5, new BigDecimal(width));
            st.setInt(6, Integer.parseInt(quantity));
            st.setInt(7, userid);
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public int getNewestProductID() {
        String sql = "select top 1 ProductID from [Product] order by ProductID desc";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return 0;
    }

    public void addCategory(int id, String category) {

        try {

            String sql = "INSERT INTO ProductCategory (ProductID , CategoryID )\n"
                    + "VALUES (?, ?);";

            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setInt(1, id);
            st.setInt(2, Integer.parseInt(category));

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public void addImage(int id, String image) {

        try {

            String sql = "INSERT INTO [dbo].[Image]\n"
                    + "			([TypeID]\n"
                    + "           ,[ObjectID]\n"
                    + "           ,[ImageUrl])\n"
                    + "     VALUES \n"
                    + "			(1, ?,?)";

            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setInt(1, id);
            st.setString(2, image);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

    }

    //Get all product HoangNH
    public List<Product> getAllProductNoIndex(int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT TOP 5\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.[Status]\n"
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1"
                + "AND P.UserID = ? "
                + "order by ProductID DESC ";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
// Get all product active HoangNH

    public List<Product> getAllProductActive(int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.[Status]\n"
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1"
                + "AND P.UserID = ? "
                + "AND P.Status = 1 ";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
    //Get all product block HoangNH

    public List<Product> getAllProductBlock(int userID) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.[Status]\n"
                + " FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1"
                + "AND P.UserID = ? "
                + "AND P.Status = 0 ";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> searchProductByName2(String txtSearch, int userid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "    AND P.ProductName LIKE ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);
            st.setString(2, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setDescription(rs.getString("ProductDescription"));
                product.setHeight(rs.getDouble("Height"));
                product.setWidth(rs.getDouble("Width"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setView(rs.getInt("View"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setUserID(rs.getInt("UserID"));
                product.setImage(rs.getString("ProductImage"));
                product.setCateID(rs.getInt("FirstCategoryID"));
                product.setStatus(rs.getInt("Status"));
                list.add(product);
            }

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductsByPriceAsc(int userid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "ORDER BY P.Price ASC";  // Sort by Price in ascending order

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);  // Set the userid parameter

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("ProductDescription"));
                p.setHeight(rs.getDouble("Height"));
                p.setWidth(rs.getDouble("Width"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setView(rs.getInt("View"));
                p.setDiscount(rs.getDouble("Discount"));
                p.setUserID(rs.getInt("UserID"));
                p.setImage(rs.getString("ProductImage"));
                p.setCateID(rs.getInt("FirstCategoryID"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductsByPriceDesc(int userid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "ORDER BY P.Price DESC";  // Sort by Price in descending order

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);  // Set the userid parameter

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("ProductDescription"),
                        rs.getDouble("Height"),
                        rs.getDouble("Width"),
                        rs.getInt("Quantity"),
                        rs.getInt("View"),
                        rs.getDouble("Discount"),
                        rs.getInt("UserID"),
                        rs.getString("ProductImage"),
                        rs.getInt("FirstCategoryID"),
                        rs.getInt("Status")
                ));
            }

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductsByNameAsc(int userid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "ORDER BY P.ProductName ASC";  // Sort by ProductName in ascending order

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);  // Set the userid parameter

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getDouble("Price"),
                        rs.getString("ProductDescription"),
                        rs.getDouble("Height"),
                        rs.getDouble("Width"),
                        rs.getInt("Quantity"),
                        rs.getInt("View"),
                        rs.getDouble("Discount"),
                        rs.getInt("UserID"),
                        rs.getString("ProductImage"),
                        rs.getInt("FirstCategoryID"),
                        rs.getInt("Status")
                ));
            }

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductsByNameDesc(int userid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.UserID = ?\n"
                + "ORDER BY P.ProductName DESC";  // Sort by ProductName in descending order

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);  // Set the userid parameter

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setPrice(rs.getDouble("Price"));
                product.setDescription(rs.getString("ProductDescription"));
                product.setHeight(rs.getDouble("Height"));
                product.setWidth(rs.getDouble("Width"));
                product.setQuantity(rs.getInt("Quantity"));
                product.setView(rs.getInt("View"));
                product.setDiscount(rs.getDouble("Discount"));
                product.setUserID(rs.getInt("UserID"));
                product.setImage(rs.getString("ProductImage"));
                product.setCateID(rs.getInt("FirstCategoryID"));
                product.setStatus(rs.getInt("Status"));
                list.add(product);
            }

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public int getTotalProductBlockCountByUserId(int userId) {
        int totalProductCount = 0;
        String sql = "SELECT COUNT(*) as TotalProduct FROM Product WHERE [Status] = 0 AND UserID = ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalProductCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalProductCount;
    }

    public int getTotalProductActiveCountByUserId(int userId) {
        int totalProductCount = 0;
        String sql = "SELECT COUNT(*) as TotalProduct FROM Product WHERE [Status] = 1 AND UserID = ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalProductCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalProductCount;
    }

    public int getTotalProductCountByUserId(int userId) {
        int totalProductCount = 0;
        String sql = "SELECT COUNT(*) as TotalProduct FROM Product WHERE UserID = ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalProductCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalProductCount;
    }

    //HoangNH
    public int getTotalOrderProductCountByUserId(int userId) {
        int totalOrderCount = 0;
        String sql = "select COUNT(*) as TotalOrderProduct from ((OrderDetail inner join Product on OrderDetail.ProductID = Product.ProductID) inner join Orders on OrderDetail.OrderID = Orders.OrderID)\n"
                + "where Product.UserID = ? ";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalOrderCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalOrderCount;
    }

    //HoangNH
    public int getTotalPriceByUserId(int userId) {
        int totalPrice = 0;
        String sql = "SELECT\n" +
"    SUM(Orders.TotalPrice) as TotalOrderProduct\n" +
"FROM\n" +
"    OrderDetail\n" +
"INNER JOIN\n" +
"    Product ON OrderDetail.ProductID = Product.ProductID\n" +
"INNER JOIN\n" +
"    Orders ON OrderDetail.OrderID = Orders.OrderID\n" +
"WHERE\n" +
"    Product.UserID = ?\n" +
"    AND OrderDetail.Status = 'completed';";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalPrice = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalPrice;
    }

    //HoangNH
    //get total product DucLV
    public int getTotalProduct() {
        String sql = "select count(*) from Product where status =1";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return 0;
    }
    //Get all product DucLV

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    //Get all product DucLV
    public List<Product> getAllProduct(int index) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "AND P.Status = 1\n"
                + "ORDER BY P.ProductID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 15 ROWS ONLY";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, (index - 1) * 15);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
    //Get all product DucLV

    public List<Product> getRelatedProduct(String id, String cateid) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT TOP 4\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "INNER JOIN\n"
                + "    ProductCategory AS PC ON P.ProductID = PC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND PC.CategoryID = ?\n"
                + "    AND P.ProductID <> ?\n"
                + "ORDER BY NEWID();";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, cateid);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
  public Product getProduct(String id) {

        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT Top 1\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName,\n"
                + "    P.Status AS Status\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "INNER JOIN\n"
                + "    ProductCategory AS PC ON P.ProductID = PC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND PC.ProductID = ?\n"
                + "ORDER BY P.ProductID";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setString(1, id);
            ResultSet rs = st.executeQuery();

          while (rs.next()) {
    return new Product(
        rs.getInt("ProductID"),
        rs.getString("ProductName"),
        rs.getDouble("Price"),
        rs.getString("ProductDescription"),
        rs.getDouble("Height"),
        rs.getDouble("Width"),
        rs.getInt("Quantity"),
        rs.getInt("View"),
        rs.getDouble("Discount"),
        rs.getInt("UserID"),
        rs.getString("ProductImage"),
        rs.getInt("FirstCategoryID"),
        rs.getInt("Status")
    );
}

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return null;
    }
  
  
    public void updateProduct(String id, String name, String price, String description, String height, String width, String quantity, String discount, String status) {
    try {
        // Get your database connection
        String sql = "UPDATE Product SET ProductName=?, Price=?, Description=?, Height=?, Width=?, Quantity=?, Discount=?, Status=? WHERE ProductID=?";
      PreparedStatement statement = getConnection().prepareStatement(sql);

        statement.setString(1, name);
        statement.setString(2, price);
        statement.setString(3, description);
        statement.setString(4, height);
        statement.setString(5, width);
        statement.setString(6, quantity);
        statement.setString(7, discount);
        statement.setString(8, status);
        statement.setString(9, id);

        statement.executeUpdate();

        // Close resources

    } catch (SQLException e) {
        // Handle SQL exception
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
    } catch (Exception e) {
        // Handle other exceptions
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
    }
}

    public Product getProductByID(String id) {
    String sql = 
        "WITH RankedImages AS (" +
        "    SELECT" +
        "        I.ObjectID AS ProductID," +
        "        I.ImageUrl," +
        "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank" +
        "    FROM" +
        "        [dbo].[Image] AS I" +
        "    WHERE" +
        "        I.TypeID = 1" +  // Assuming TypeID 1 corresponds to products
        ")," +
        "RankedCategories AS (" +
        "    SELECT" +
        "        PC.ProductID," +
        "        C.CategoryID," +
        "        C.CategoryName," +
        "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank" +
        "    FROM" +
        "        ProductCategory AS PC" +
        "    JOIN" +
        "        Category AS C ON PC.CategoryID = C.CategoryID" +
        ")" +
        "SELECT TOP 1" +
        "    P.ProductID," +
        "    P.ProductName," +
        "    P.Price," +
        "    P.[Description] AS ProductDescription," +
        "    P.Height," +
        "    P.Width," +
        "    P.Quantity," +
        "    P.[View]," +
        "    P.Discount," +
        "    P.UserID," +
        "    R.ImageUrl AS ProductImage," +
        "    RC.CategoryID AS FirstCategoryID," +
        "    RC.CategoryName AS FirstCategoryName" +
        " FROM" +
        "    Product AS P" +
        " LEFT JOIN" +
        "    RankedImages AS R ON P.ProductID = R.ProductID" +
        " LEFT JOIN" +
        "    RankedCategories AS RC ON P.ProductID = RC.ProductID" +
        " INNER JOIN" +
        "    ProductCategory AS PC ON P.ProductID = PC.ProductID" +
        " WHERE" +
        "    R.ImageRank = 1" +
        "    AND RC.CategoryRank = 1" +
        "    AND PC.ProductID = ?" +
        " ORDER BY P.ProductID";

    try (Connection conn = getConnection();
         PreparedStatement st = conn.prepareStatement(sql)) {
        st.setString(1, id);
        try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return new Product(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getDouble("Price"),
                    rs.getString("ProductDescription"),
                    rs.getDouble("Height"),
                    rs.getDouble("Width"),
                    rs.getInt("Quantity"),
                    rs.getInt("View"),
                    rs.getDouble("Discount"),
                    rs.getInt("UserID"),
                    rs.getString("ProductImage"),
                    rs.getInt("FirstCategoryID")
                );
            }
        }
    } catch (SQLException e) {
        // Handle SQL exception
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
    } catch (Exception e) {
        // Handle other exceptions
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
    }

    return null;
}


    //Search setting by name MinhHC
    public List<Product> searchProductByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                 + "AND P.Status = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                + "    AND P.ProductName LIKE ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getpriceAsc() {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                 + "AND P.Status = 1\n"
                + "ORDER BY P.Price ASC  -- Sort by Price in ascending order";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getpriceDesc() {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                 + "AND P.Status = 1\n"
                + "ORDER BY P.Price Desc  -- Sort by Price in ascending order";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getNameAsc() {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                 + "AND P.Status = 1\n"
                + "ORDER BY P.ProductName ASC  -- Sort by ProductName in ascending order;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getNameDesc() {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n"
                 + "AND P.Status = 1\n"
                + "ORDER BY P.ProductName Desc  -- Sort by ProductName in ascending order;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Category> getProductCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE (ObjectTypeID = 1 OR ObjectTypeID = 3) AND Status = 1;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Category> getProductCate() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM Category WHERE ObjectTypeID = 1 AND Status = 1;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Category> getProductTopic() {
        List<Category> list = new ArrayList<>();
        String sql = "Select * from Category where ObjectTypeID =3 and status =1";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getBoolean(3)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductByMatID(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                 + "AND P.Status = 1\n"
                + "	AND RC.CategoryID = ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> getProductByTopic(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                 + "AND P.Status = 1\n"
                + "	AND RC.CategoryID = ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> FilterDiscountProduct(String min, String max) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                 + "AND P.Status = 1\n"
                + "    AND RC.CategoryRank = 1\n AND P.Discount > ?\n"
                + "    AND P.Discount <= ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, min);
            st.setString(2, max);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> FilterPriceProduct(String min, String max) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                 + "AND P.Status = 1\n"
                + "    AND RC.CategoryRank = 1\n AND P.Price >= ?\n"
                + "    AND P.Price <= ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, min);
            st.setString(2, max);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<Product> FilterSizeProduct(String minwidth, String maxwidth, String minheight, String maxheight) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1\n"
                + "    AND RC.CategoryRank = 1\n AND P.Width >= ?\n"
                 + "AND P.Status = 1\n"
                + "    AND P.Width <= ?  AND P.Height >= ?\n"
                + "    AND P.Height <= ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, minwidth);
            st.setString(2, maxwidth);
            st.setString(3, minheight);
            st.setString(4, maxheight);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    //get product by material and artist id: Baomv
    public List<Product> getProductByMatIDAndUserID(String id, int userId) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1 AND P.UserID = ? \n"
                + "	AND RC.CategoryID = ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setInt(1, userId);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
    
    //get product by topic and artist id: Baomv
    public List<Product> getProductByTopicAndUserId(String id, int userId) {
        List<Product> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n"
                + "),\n"
                + "RankedCategories AS (\n"
                + "    SELECT\n"
                + "        PC.ProductID,\n"
                + "        C.CategoryID,\n"
                + "        C.CategoryName,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n"
                + "    FROM\n"
                + "        ProductCategory AS PC\n"
                + "    JOIN\n"
                + "        Category AS C ON PC.CategoryID = C.CategoryID\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    P.ProductID,\n"
                + "    P.ProductName,\n"
                + "    P.Price,\n"
                + "    P.[Description] AS ProductDescription,\n"
                + "    P.Height,\n"
                + "    P.Width,\n"
                + "    P.Quantity,\n"
                + "    P.[View],\n"
                + "    P.Discount,\n"
                + "    P.UserID,\n"
                + "    R.ImageUrl AS ProductImage,\n"
                + "    RC.CategoryID AS FirstCategoryID,\n"
                + "    RC.CategoryName AS FirstCategoryName\n"
                + "FROM\n"
                + "    Product AS P\n"
                + "LEFT JOIN\n"
                + "    RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "LEFT JOIN\n"
                + "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n"
                + "WHERE\n"
                + "    R.ImageRank = 1 AND P.UserID = ?\n"
                + "	AND RC.CategoryID = ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userId);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }
    //get number of sale product: Baomv
    public int getNumberOfSales(int userId) {
        int numberOfSales = 0;
        String sqlQuery = "SELECT COUNT(*) as numberOfSale " +
                "FROM OrderDetail od " +
                "INNER JOIN Product p ON p.ProductID = od.ProductID " +
                "WHERE p.UserID = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfSales = resultSet.getInt("numberOfSale");
                }
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, "Exception", e);
        }

        return numberOfSales;
    }

    //filter and paginate for product in shop detail page: Baomv
    public List<Product> filterAndPaginateProducts(
            String promat,
            String protopic,
            String sortprice,
            String sortname,
            String minprice,
            String maxprice,
            String minwidth,
            String maxwidth,
            String minheight,
            String maxheight,
            String discountmin,
            String discountmax,
            String txtSearch,
            String artistId,
            int pageNumber,
            int pageSize
    ) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "WITH RankedImages AS (\n" +
                    "    SELECT\n" +
                    "        I.ObjectID AS ProductID,\n" +
                    "        I.ImageUrl,\n" +
                    "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n" +
                    "    FROM\n" +
                    "        [dbo].[Image] AS I\n" +
                    "    WHERE\n" +
                    "        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\n" +
                    "),\n" +
                    "RankedCategories AS (\n" +
                    "    SELECT\n" +
                    "        PC.ProductID,\n" +
                    "        C.CategoryID,\n" +
                    "        C.CategoryName,\n" +
                    "        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\n" +
                    "    FROM\n" +
                    "        ProductCategory AS PC\n" +
                    "    JOIN\n" +
                    "        Category AS C ON PC.CategoryID = C.CategoryID\n" +
                    ")\n" +
                    "\n" +
                    "SELECT\n" +
                    "    P.ProductID,\n" +
                    "    P.ProductName,\n" +
                    "    P.Price,\n" +
                    "    P.[Description] AS ProductDescription,\n" +
                    "    P.Height,\n" +
                    "    P.Width,\n" +
                    "    P.Quantity,\n" +
                    "    P.[View],\n" +
                    "    P.Discount,\n" +
                    "    P.UserID,\n" +
                    "    R.ImageUrl AS ProductImage,\n" +
                    "    RC.CategoryID AS FirstCategoryID,\n" +
                    "    RC.CategoryName AS FirstCategoryName\n" +
                    "FROM\n" +
                    "    Product AS P\n" +
                    "LEFT JOIN\n" +
                    "    RankedImages AS R ON P.ProductID = R.ProductID\n" +
                    "LEFT JOIN\n" +
                    "    RankedCategories AS RC ON P.ProductID = RC.ProductID\n" +
                    "LEFT JOIN (\n" +
                    "    SELECT\n" +
                    "        od.ProductID,\n" +
                    "        SUM(od.quantity) AS total_quantity_sold\n" +
                    "    FROM\n" +
                    "        OrderDetail AS od\n" +
                    "    GROUP BY\n" +
                    "        od.ProductID\n" +
                    ") AS od ON P.ProductID = od.ProductID\n" +
                    "WHERE\n" +
                    "    R.ImageRank = 1\n" +
                      "AND P.Status = 1\n" +
                    "    AND RC.CategoryRank = 1\n";

            // Add other filtering conditions based on your parameters
            if (promat != null && !promat.isEmpty()) {
                sql += " AND RC.CategoryID =" + promat + "\n";
            }

            if (protopic != null && !protopic.isEmpty()) {
                sql += " AND ProductTopic = '" + protopic + "'";
            }

            if (minprice != null && !minprice.isEmpty()) {
                sql += " AND Price >= " + minprice;
            }

            if (maxprice != null && !maxprice.isEmpty()) {
                sql += " AND Price <= " + maxprice;
            }

            if (minwidth != null && !minwidth.isEmpty()) {
                sql += " AND Width >= " + minwidth;
            }

            if (maxwidth != null && !maxwidth.isEmpty()) {
                sql += " AND Width <= " + maxwidth;
            }

            if (minheight != null && !minheight.isEmpty()) {
                sql += " AND Height >= " + minheight;
            }

            if (maxheight != null && !maxheight.isEmpty()) {
                sql += " AND Height <= " + maxheight;
            }

            if (discountmin != null && !discountmin.isEmpty()) {
                sql += " AND Discount >= " + discountmin;
            }

            if (discountmax != null && !discountmax.isEmpty()) {
                sql += " AND Discount <= " + discountmax;
            }

            if (txtSearch != null && !txtSearch.isEmpty()) {
                sql += " AND (ProductName LIKE '%" + txtSearch + "%' OR Description LIKE '%" + txtSearch + "%')";
            }

            if (artistId != null && !artistId.isEmpty()) {
                sql += " AND UserID = " + artistId;
            }
            sql += " ORDER BY ";
            if (sortprice != null && sortprice.equals("asc")) {
                sql += "Price ASC";
            } else if (sortprice != null && sortprice.equals("desc")) {
                sql += "Price DESC";
            } else if (sortprice != null && sortprice.equals("best")) {
                sql += "total_quantity_sold DESC";
            } else if (sortname != null && sortname.equals("asc")) {
                sql += "ProductName ASC";
            } else if (sortname != null && sortname.equals("desc")) {
                sql += "ProductName DESC";
            }else {
                sql += "P.ProductID ASC";
            }


            // Calculate the offset for pagination
            int offset = (pageNumber - 1) * pageSize;

            // Add LIMIT and OFFSET for pagination
            sql += "\n OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

            System.out.println(sql);

            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);

            int parameterIndex = 1;

            preparedStatement.setInt(parameterIndex++, offset);
            preparedStatement.setInt(parameterIndex++, pageSize);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1), // ProductID
                        rs.getString(2), // ProductName
                        rs.getDouble(3), // Price
                        rs.getString(4), // Description
                        rs.getDouble(5), // Height
                        rs.getDouble(6), // Width
                        rs.getInt(7), // Quantity
                        rs.getInt(8), // View
                        rs.getDouble(9), // Discount
                        rs.getInt(10), // UserID
                        rs.getString(11), // Image
                        rs.getInt(12)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions appropriately
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    //get best seller product
    public List<Product> sortBestSellerProduct(List<Integer> productIds) throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "   String sql = \"WITH RankedImages AS (\\n\"\n" +
                "                    + \"    SELECT\\n\"\n" +
                "                    + \"        I.ObjectID AS ProductID,\\n\"\n" +
                "                    + \"        I.ImageUrl,\\n\"\n" +
                "                    + \"        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\\n\"\n" +
                "                    + \"    FROM\\n\"\n" +
                "                    + \"        [dbo].[Image] AS I\\n\"\n" +
                "                    + \"    WHERE\\n\"\n" +
                "                    + \"        I.TypeID = 1  -- Assuming TypeID 1 corresponds to products\\n\"\n" +
                "                    + \"),\\n\"\n" +
                "                    + \"RankedCategories AS (\\n\"\n" +
                "                    + \"    SELECT\\n\"\n" +
                "                    + \"        PC.ProductID,\\n\"\n" +
                "                    + \"        C.CategoryID,\\n\"\n" +
                "                    + \"        C.CategoryName,\\n\"\n" +
                "                    + \"        ROW_NUMBER() OVER (PARTITION BY PC.ProductID ORDER BY C.CategoryID) AS CategoryRank\\n\"\n" +
                "                    + \"    FROM\\n\"\n" +
                "                    + \"        ProductCategory AS PC\\n\"\n" +
                "                    + \"    JOIN\\n\"\n" +
                "                    + \"        Category AS C ON PC.CategoryID = C.CategoryID\\n\"\n" +
                "                    + \")\\n\"\n" +
                "                    + \"\\n\"\n" +
                "                    + \"SELECT\\n\"\n" +
                "                    + \"    P.ProductID,\\n\"\n" +
                "                    + \"    P.ProductName,\\n\"\n" +
                "                    + \"    P.Price,\\n\"\n" +
                "                    + \"    P.[Description] AS ProductDescription,\\n\"\n" +
                "                    + \"    P.Height,\\n\"\n" +
                "                    + \"    P.Width,\\n\"\n" +
                "                    + \"    P.Quantity,\\n\"\n" +
                "                    + \"    P.[View],\\n\"\n" +
                "                    + \"    P.Discount,\\n\"\n" +
                "                    + \"    P.UserID,\\n\"\n" +
                "                    + \"    R.ImageUrl AS ProductImage,\\n\"\n" +
                "                    + \"    RC.CategoryID AS FirstCategoryID,\\n\"\n" +
                "                    + \"    RC.CategoryName AS FirstCategoryName\\n\"\n" +
                "                    + \"FROM\\n\"\n" +
                "                    + \"    Product AS P\\n\"\n" +
                "                    + \"LEFT JOIN\\n\"\n" +
                "                    + \"    RankedImages AS R ON P.ProductID = R.ProductID\\n\"\n" +
                "                    + \"LEFT JOIN\\n\"\n" +
                "                    + \"    RankedCategories AS RC ON P.ProductID = RC.ProductID\\n\"\n" +
                "                    + \"WHERE\\n\"\n" +
                "                    + \"    R.ImageRank = 1\\n\"\n" +
                "                    + \"    AND RC.CategoryRank = 1\\n\";";


        String query = "SELECT p.ProductID, p.ProductName, SUM(od.quantity) AS total_quantity_sold " +
                "FROM Product AS p " +
                "LEFT JOIN OrderDetail AS od ON p.ProductID = od.ProductID " +
                "WHERE p.ProductID IN (";

        for (int i = 0; i < productIds.size(); i++) {
            query += "?";
            if (i < productIds.size() - 1) {
                query += ", ";
            }
        }
        query += ") " +
                "GROUP BY p.ProductID, p.ProductName " +
                "ORDER BY total_quantity_sold DESC";


        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            for (int i = 0; i < productIds.size(); i++) {
                preparedStatement.setInt(i + 1, productIds.get(i));
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(
                            rs.getInt(1), // ProductID
                            rs.getString(2), // ProductName
                            rs.getDouble(3), // Price
                            rs.getString(4), // Description
                            rs.getDouble(5), // Height
                            rs.getDouble(6), // Width
                            rs.getInt(7), // Quantity
                            rs.getInt(8), // View
                            rs.getDouble(9), // Discount
                            rs.getInt(10), // UserID
                            rs.getString(11), // Image
                            rs.getInt(12)
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;

    }
    //method to test: ThanhNX
    public static void main(String[] args) {
        ArrayList<Product> test = new ProductDAO().getLatestProduct();
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).toString());
        }
        ArrayList<String> testImage = new ProductDAO().getOnePicPerProduct(test);
        for (int i = 0; i < testImage.size(); i++) {
            System.out.println(testImage.get(i));
        }
    }
}
