/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Category;
import Model.Product;
import Model.Setting;
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

    //get 8 latest product in DB: ThanhNX
    public ArrayList<Product> getLatestProduct() {
        ArrayList<Product> homeProduct = new ArrayList<>();
        String command = "SELECT TOP 8 * FROM Product";
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
    //get total product DucLV

    public int getTotalProduct() {
        String sql = "select count(*) from Product";
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

    public Product getProductByID(String id) {

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
                + "    AND PC.ProductID = ?\n"
                + "ORDER BY P.ProductID";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                return new Product(
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

    public List<Category> getProductCate() {
        List<Category> list = new ArrayList<>();
        String sql = "Select * from Category where ObjectTypeID =1;";

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
        String sql = "Select * from Category where ObjectTypeID =3;";

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
