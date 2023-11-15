/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.ProOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class OrderDAO extends DBContext {

    public void addOrder(int userid, double totalPrice, String customername, String address, String phonenumber, String payment) {
        LocalDateTime curDate = LocalDateTime.now();
        String date = curDate.toString();

        String sql = "INSERT INTO Orders (UserID, OrderDate, TotalPrice, CustomerName, PhoneNumber, Address, Payment)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);
            st.setString(2, date);
            st.setDouble(3, totalPrice);
            st.setString(4, customername);
            st.setString(5, phonenumber);
            st.setString(6, address);
            st.setString(7, payment);

            st.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
    public int getOrderbyMonth(int month, int userID){
    int totalOrders = 0;
    String sql =  "SELECT COUNT(*) AS total_orders FROM OrderDetail " +
                 "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID " +
                 "INNER JOIN Orders ON OrderDetail.OrderID = Orders.OrderID " +
                 "WHERE MONTH(Orders.OrderDate) = ? AND Product.UserID = ? ";
     try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1,month + "");
            st.setInt(2, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                totalOrders = rs.getInt(1);
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return totalOrders;
}
 public void setOrderDelDate(String id) {
        LocalDateTime curDate = LocalDateTime.now();
        String date = curDate.toString();

         String sql = "UPDATE OrderDetail\n"
                + "SET DelDate = ?\n"
                + "WHERE DetailID = ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, date);
            st.setString(2, id);
         

            st.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
//get total my order minhHC
    public int getTotalMyOrder(int cid) {
        String sql = "SELECT COUNT(*) \n"
                + "FROM OrderDetail OD\n"
                + "INNER JOIN Orders O ON OD.OrderID = O.OrderID\n"
                + "where UserID=?;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
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

    public void addOrderDetails(int oid, int pid, double price, int quantity) {
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime expDate = curDate.plus(7, ChronoUnit.DAYS);
        String formattedExpDate = expDate.toString();

        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, Quantity,Price, Status, ExpDate, DelDate) VALUES (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, oid);
            st.setInt(2, pid);
            st.setInt(3, quantity);
            st.setDouble(4, price);
            st.setString(5, "pending");
            st.setString(6, formattedExpDate);
            st.setString(7, null);
            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public void updateProductQuantity(int pid, int quantity) {
        String sql = "update Product set quantity = quantity - ? "
                + "where productID = ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, pid);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public int getOrderIdLatest() {

        String sql = "SELECT TOP 1 OrderID FROM Orders ORDER BY OrderID DESC";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int o = rs.getInt(1);
                return o;
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
    //get all inactive setting MinhHC

    public List<ProOrder> getMyOrder(int index, int id) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    O.OrderDate,\n"
                + "    O.TotalPrice,\n"
                + "    U.UserName AS ArtistName,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.Status,\n"
                + "    OD.ProductID AS OrderedProductID,\n"
                + "    RD.ProductName,\n"
                + "    RD.ProductImage,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "LEFT JOIN\n"
                + "    (\n"
                + "        SELECT\n"
                + "            P.ProductID,\n"
                + "            P.ProductName,\n"
                + "            P.UserID,\n"
                + "            R.ImageUrl AS ProductImage\n"
                + "        FROM\n"
                + "            Product AS P\n"
                + "        LEFT JOIN\n"
                + "            RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "        WHERE\n"
                + "            R.ImageRank = 1\n"
                + "    ) AS RD ON OD.ProductID = RD.ProductID\n"
                + "LEFT JOIN\n"
                + "    [User] AS U ON RD.UserID = U.UserID\n"
                + "WHERE O.UserID = ?\n"
                + "ORDER BY O.OrderID Desc\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 9 ROWS ONLY;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, (index - 1) * 9);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductImage"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("OrderDate"),
                        rs.getString("ArtistName"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"), // Get expDate
                        rs.getString("DelDate"), // Get delDate
                        rs.getString("Payment") // Get payment
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

    public List<ProOrder> getAllOrder(int index, int userID) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    CAST(O.OrderDate AS DATE) AS date,\n"
                + "    O.TotalPrice,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    OD.Price,\n"
                + "    OD.Status,\n"
                + "    P.ProductName,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "WHERE P.UserID = ?\n"
                + "ORDER BY O.OrderID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 6 ROWS ONLY;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            st.setInt(2, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<ProOrder> getAllOrderASC(int userID) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    CAST(O.OrderDate AS DATE) AS date,\n"
                + "    O.TotalPrice,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    OD.Price,\n"
                + "    OD.Status,\n"
                + "    P.ProductName,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "WHERE P.UserID = ?\n"
                + "ORDER BY O.TotalPrice ASC\n";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"), // Get expDate
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<ProOrder> getAllOrderDES(int userID) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    CAST(O.OrderDate AS DATE) AS date,\n"
                + "    O.TotalPrice,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    OD.Price,\n"
                + "    OD.Status,\n"
                + "    P.ProductName,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "WHERE P.UserID = ?\n"
                + "ORDER BY O.TotalPrice DESC\n";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"), // Get expDate
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<ProOrder> filterOrderbyDate(int userID, String from, String to) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    CAST(O.OrderDate AS DATE) AS [date],\n"
                + "    O.TotalPrice,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    OD.Price,\n"
                + "    OD.Status,\n"
                + "    P.ProductName,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "WHERE\n"
                + "    P.UserID = ?\n"
                + "    AND CAST(O.OrderDate AS DATE) > ?\n"
                + "    AND CAST(O.OrderDate AS DATE) < ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            st.setString(2, from);
            st.setString(3, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"), // Get expDate
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return list;
    }

    public List<ProOrder> filterMyOrderByDate(int userID, String from, String to) {
        List<ProOrder> list = new ArrayList<>();
       String sql = "WITH RankedImages AS (\n"
            + "    SELECT\n"
            + "        I.ObjectID AS ProductID,\n"
            + "        I.ImageUrl,\n"
            + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
            + "    FROM\n"
            + "        [dbo].[Image] AS I\n"
            + "    WHERE\n"
            + "        I.TypeID = 1\n"
            + ")\n"
            + "\n"
            + "SELECT\n"
            + "    OD.DetailID,\n"
            + "    O.OrderID,\n"
            + "    O.OrderDate,\n"
            + "    O.TotalPrice,\n"
            + "    U.UserName AS ArtistName,\n"
            + "    O.CustomerName,\n"
            + "    O.PhoneNumber,\n"
            + "    O.Address,\n"
            + "    OD.Status,\n"
            + "    OD.ProductID AS OrderedProductID,\n"
            + "    RD.ProductName,\n"
            + "    RD.ProductImage,\n"
            + "    OD.Quantity,\n"
            + "    OD.Price,\n"
            + "    OD.ExpDate,\n"
            + "    OD.DelDate,\n"
            + "    O.Payment\n"
            + "FROM\n"
            + "    Orders AS O\n"
            + "INNER JOIN\n"
            + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
            + "LEFT JOIN\n"
            + "    (\n"
            + "        SELECT\n"
            + "            P.ProductID,\n"
            + "            P.ProductName,\n"
            + "            P.UserID,\n"
            + "            R.ImageUrl AS ProductImage\n"
            + "        FROM\n"
            + "            Product AS P\n"
            + "        LEFT JOIN\n"
            + "            RankedImages AS R ON P.ProductID = R.ProductID\n"
            + "        WHERE\n"
            + "            R.ImageRank = 1\n"
            + "    ) AS RD ON OD.ProductID = RD.ProductID\n"
            + "LEFT JOIN\n"
            + "    [User] AS U ON RD.UserID = U.UserID\n"
            + "WHERE O.UserID = ? \n"
            + "    AND CAST(O.OrderDate AS DATE) > ?\n"
            + "    AND CAST(O.OrderDate AS DATE) < ?\n" 
            + "ORDER BY O.OrderID DESC";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            st.setString(2, from);
               st.setString(3, to);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductImage"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("OrderDate"),
                        rs.getString("ArtistName"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"), // Get expDate
                        rs.getString("DelDate"), // Get delDate
                        rs.getString("Payment") // Get payment
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

    public List<ProOrder> getAllOrderSearch(String search, int userID) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    CAST(O.OrderDate AS DATE) AS date,\n"
                + "    O.TotalPrice,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    OD.Price,\n"
                + "    OD.Status,\n"
                + "    P.ProductName,\n" // Corrected the column name
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "WHERE P.UserID = ?\n"
                + "AND P.ProductName LIKE ?\n"
                + "ORDER BY O.OrderID Desc\n";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            st.setString(2, "%" + search + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"), // Corrected the column name
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
                ));
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return list;
    }

    public List<ProOrder> getMyOrderSearch(String search, int id) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        [dbo].[Image] AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    O.OrderDate,\n"
                + "    O.TotalPrice,\n"
                + "    U.UserName AS ArtistName,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.Status,\n"
                + "    OD.ProductID AS OrderedProductID,\n"
                + "    RD.ProductName,\n"
                + "    RD.ProductImage,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "LEFT JOIN\n"
                + "    (\n"
                + "        SELECT\n"
                + "            P.ProductID,\n"
                + "            P.ProductName,\n"
                + "            P.UserID,\n"
                + "            R.ImageUrl AS ProductImage\n"
                + "        FROM\n"
                + "            Product AS P\n"
                + "        LEFT JOIN\n"
                + "            RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "        WHERE\n"
                + "            R.ImageRank = 1\n"
                + "    ) AS RD ON OD.ProductID = RD.ProductID\n"
                + "LEFT JOIN\n"
                + "    [User] AS U ON RD.UserID = U.UserID\n"
                + "WHERE O.UserID = ? AND RD.ProductName LIKE ?\n"
                + "ORDER BY O.OrderID Desc";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, "%" + search + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductImage"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("OrderDate"),
                        rs.getString("ArtistName"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"), // Get expDate
                        rs.getString("DelDate"), // Get delDate
                        rs.getString("Payment") // Get payment
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

    public int getTotalCustomerOrder(String cusid, int sellid) {
        String sql = "SELECT COUNT(*) \n"
                + "FROM OrderDetail AS OD\n"
                + "INNER JOIN Orders AS O ON OD.OrderID = O.OrderID\n"
                + "INNER JOIN (\n"
                + "    SELECT\n"
                + "        P.ProductID,\n"
                + "        P.UserID\n"
                + "    FROM Product AS P\n"
                + "    WHERE P.UserID = ?\n"
                + ") AS RD ON OD.ProductID = RD.ProductID\n"
                + "WHERE O.UserID = ?;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, sellid);
            st.setString(2, cusid);
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            // Xử lý ngoại lệ SQL
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return 0;
    }

    public List<ProOrder> getOrdersByDateRange(int userID, String from, String to, int index) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "SELECT  OD.DetailID, O.OrderID, CAST(O.OrderDate AS DATE) AS date, O.TotalPrice, O.CustomerName, O.PhoneNumber, "
                + "O.Address, OD.ExpDate, OD.DelDate, OD.Price, OD.Status, P.ProductName, OD.Quantity, OD.Price, O.Payment "
                + "FROM Orders AS O "
                + "INNER JOIN OrderDetail AS OD ON O.OrderID = OD.OrderID "
                + "INNER JOIN Product AS P ON OD.ProductID = P.ProductID "
                + "WHERE P.UserID = ? AND CAST(O.OrderDate AS DATE) BETWEEN ? AND ? "
                + "ORDER BY O.OrderID "
                + "OFFSET ? ROWS "
                + "FETCH NEXT 6 ROWS ONLY;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userID);
            st.setString(2, from);
            st.setString(3, to);
            st.setInt(4, (index - 1) * 6);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"),
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getDouble("TotalPrice"),
                        rs.getString("date"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"), // Get expDate
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
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

    public int getTotalOrderCountByUserId(int userId) {
        int totalOrderCount = 0;
        String sql = "SELECT\n"
                + "COUNT(*)\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "INNER JOIN\n"
                + "    Product AS P ON OD.ProductID = P.ProductID\n"
                + "\n"
                + "	where P.UserID = ?";

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

    public List<ProOrder> getCustomerOrder(int index, String cusid, int sellid) {
        List<ProOrder> list = new ArrayList<>();
        String sql = "WITH RankedImages AS (\n"
                + "    SELECT\n"
                + "        I.ObjectID AS ProductID,\n"
                + "        I.ImageUrl,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY I.ObjectID ORDER BY I.ImageID) AS ImageRank\n"
                + "    FROM\n"
                + "        dbo.Image AS I\n"
                + "    WHERE\n"
                + "        I.TypeID = 1\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    OD.DetailID,\n"
                + "    O.OrderID,\n"
                + "    O.OrderDate,\n"
                + "    O.TotalPrice,\n"
                + "    U.UserName AS ArtistName,\n"
                + "    O.CustomerName,\n"
                + "    O.PhoneNumber,\n"
                + "    O.Address,\n"
                + "    OD.Status,\n"
                + "    OD.ProductID AS OrderedProductID,\n"
                + "    RD.ProductName,\n"
                + "    RD.ProductImage,\n"
                + "    OD.Quantity,\n"
                + "    OD.Price,\n"
                + "    OD.ExpDate,\n"
                + "    OD.DelDate,\n"
                + "    O.Payment\n"
                + "FROM\n"
                + "    Orders AS O\n"
                + "INNER JOIN\n"
                + "    OrderDetail AS OD ON O.OrderID = OD.OrderID\n"
                + "LEFT JOIN\n"
                + "    (\n"
                + "        SELECT\n"
                + "            P.ProductID,\n"
                + "            P.ProductName,\n"
                + "            P.UserID,\n"
                + "            R.ImageUrl AS ProductImage\n"
                + "        FROM\n"
                + "            Product AS P\n"
                + "        LEFT JOIN\n"
                + "            RankedImages AS R ON P.ProductID = R.ProductID\n"
                + "        WHERE\n"
                + "            R.ImageRank = 1\n"
                + "    ) AS RD ON OD.ProductID = RD.ProductID\n"
                + "LEFT JOIN\n"
                + "    [User] AS U ON RD.UserID = U.UserID\n"
                + "WHERE\n"
                + "    RD.UserID = ? AND O.UserID = ?\n"
                + "ORDER BY O.OrderID\n"
                + "OFFSET ? ROWS \n"
                + "FETCH NEXT 9 ROWS ONLY;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, sellid);
            st.setString(2, cusid);
            st.setInt(3, (index - 1) * 9);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProOrder(rs.getInt("DetailID"), rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductImage"),
                        rs.getInt("Quantity"),
                        rs.getDouble("Price"),
                        rs.getString("OrderDate"),
                        rs.getString("ArtistName"),
                        rs.getString("CustomerName"),
                        rs.getString("Address"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Status"),
                        rs.getString("ExpDate"),
                        rs.getString("DelDate"),
                        rs.getString("Payment")
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

    public void CancelOrder(int id) {

        String sql = "UPDATE OrderDetail\n"
                + "SET Status = 'cancel'\n"
                + "WHERE DetailID = ?;";

        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, id);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public void ChangeOrderStatus(String id, String status) {

        String sql = "UPDATE OrderDetail\n"
                + "SET Status = ?\n"
                + "WHERE DetailID = ?;";

        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setString(1, status);
            st.setString(2, id);
            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    //calculates the total number of orders for each day of the past week: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByWeek() {
        String sql = "WITH DateRange AS (\n"
                + "    SELECT DATEADD(DAY, -7, GETDATE()) AS StartDate, GETDATE() AS EndDate\n"
                + ")\n"
                + "SELECT\n"
                + "    DATENAME(WEEKDAY, OrderDate) AS DayName,\n"
                + "    SUM(CASE WHEN OrderDate BETWEEN DateRange.StartDate AND DateRange.EndDate THEN 1 ELSE 0 END) AS TotalOrders\n"
                + "FROM\n"
                + "    [Orders]\n"
                + "CROSS JOIN\n"
                + "    DateRange\n"
                + "WHERE\n"
                + "    OrderDate BETWEEN DateRange.StartDate AND DateRange.EndDate\n"
                + "GROUP BY\n"
                + "    DATENAME(WEEKDAY, OrderDate)\n"
                + "ORDER BY\n"
                + "    MIN(OrderDate)";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        ArrayList<String> daysOfWeek = getLast7Day();
        for (String day : daysOfWeek) {
            listTotal.put("'" + day + "'", "0");
        }
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1) + "'", rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByWeek: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past month: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByMonth() {
        String sql = "WITH DateSequence AS (\n"
                + "    SELECT top 1\n"
                + "        DATEADD(WEEK, DATEDIFF(WEEK, 0, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)), 0) AS WeekStart\n"
                + "    UNION ALL\n"
                + "    SELECT\n"
                + "        DATEADD(WEEK, 1, WeekStart)\n"
                + "    FROM DateSequence\n"
                + "    WHERE\n"
                + "        DATEADD(WEEK, 1, WeekStart) < DATEADD(MONTH, 1, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0))\n"
                + ")\n"
                + "SELECT\n"
                + "    WeekStart,\n"
                + "    DATEADD(DAY, 6, WeekStart) AS WeekEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    DateSequence ds\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON o.OrderDate >= ds.WeekStart AND o.OrderDate < DATEADD(DAY, 7, ds.WeekStart)\n"
                + "GROUP BY\n"
                + "    WeekStart\n"
                + "ORDER BY\n"
                + "    WeekStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }
    //hoangnh

    public LinkedHashMap<String, String> getTotalPriceByMonth() {
        String sql = "WITH DateSequence AS (\n"
                + "    SELECT top 1\n"
                + "        DATEADD(WEEK, DATEDIFF(WEEK, 0, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0)), 0) AS WeekStart\n"
                + "    UNION ALL\n"
                + "    SELECT\n"
                + "        DATEADD(WEEK, 1, WeekStart)\n"
                + "    FROM DateSequence\n"
                + "    WHERE\n"
                + "        DATEADD(WEEK, 1, WeekStart) < DATEADD(MONTH, 1, DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()), 0))\n"
                + ")\n"
                + "SELECT\n"
                + "    WeekStart,\n"
                + "    DATEADD(DAY, 6, WeekStart) AS WeekEnd,\n"
                + "    COALESCE(SUM(o.TotalPrice), 0) AS TotalPrice\n"
                + "FROM\n"
                + "    DateSequence ds\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON o.OrderDate >= ds.WeekStart AND o.OrderDate < DATEADD(DAY, 7, ds.WeekStart)\n"
                + "GROUP BY\n"
                + "    WeekStart\n"
                + "ORDER BY\n"
                + "    WeekStart;";

        LinkedHashMap<String, String> listTotalRevenue = new LinkedHashMap<>();

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotalRevenue.put(
                        "'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'",
                        rs.getString(3)
                );
            }
        } catch (Exception e) {
            System.out.println("getTotalPriceByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotalRevenue.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotalRevenue.keySet());
        System.out.println(listTotalRevenue.values());

        return listTotalRevenue;
    }

    //calculates the total number of orders for each day of the past 3 months: BaoMV
    public LinkedHashMap<String, String> getTotalOrderBy3Month() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -2\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                   AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //hoangnh
    public LinkedHashMap<String, String> getTotalPriceBy3Months(int userID) {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -6  -- Thay đổi giá trị này để tính toán cho 6 tháng gần đây\n"
                + ")\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(SUM(o.TotalPrice), 0) AS TotalPrice\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                 AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "LEFT JOIN\n"
                + "    [OrderDetail] od ON o.OrderID = od.OrderID\n"
                + "LEFT JOIN\n"
                + "    [Product] p ON od.ProductID = p.ProductID\n"
                + "WHERE\n"
                + "    p.UserID = ? "
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";

        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put(
                        "'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'",
                        rs.getString(3)
                );
            }
        } catch (Exception e) {
            System.out.println("getTotalPriceBy3Months: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past 6 months: BaoMV
    public LinkedHashMap<String, String> getTotalOrderBy6Month() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -5\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                   AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }
    //hoangnh

    public LinkedHashMap<String, String> getTotalPriceBy6Months() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 0 AS MonthOffset\n"
                + "    UNION ALL\n"
                + "    SELECT MonthOffset - 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthOffset > -5\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())) AS MonthStart,\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE()))) AS MonthEnd,\n"
                + "    COALESCE(SUM(o.TotalPrice), 0) AS TotalPrice\n"
                + "FROM\n"
                + "    Months m\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "                   AND MONTH(o.OrderDate) = MONTH(EOMONTH(GETDATE(), m.MonthOffset))\n"
                + "GROUP BY\n"
                + "    DATEADD(MONTH, m.MonthOffset, EOMONTH(GETDATE())),\n"
                + "    DATEADD(DAY, -1, DATEADD(MONTH, m.MonthOffset + 1, EOMONTH(GETDATE())))\n"
                + "ORDER BY\n"
                + "    MonthStart;";

        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put(
                        "'" + rs.getString(1).split(" ")[0] + " / " + rs.getString(2).split(" ")[0] + "'",
                        rs.getString(3)
                );
            }
        } catch (Exception e) {
            System.out.println("getTotalPriceBy6Months: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    //calculates the total number of orders for each day of the past year: BaoMV
    public LinkedHashMap<String, String> getTotalOrderByYear() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 1 AS MonthIndex\n"
                + "    UNION ALL\n"
                + "    SELECT MonthIndex + 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthIndex < 12\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    MonthIndex AS MonthNumber,\n"
                + "    CASE\n"
                + "        WHEN MonthIndex = 1 THEN 'January'\n"
                + "        WHEN MonthIndex = 2 THEN 'February'\n"
                + "        WHEN MonthIndex = 3 THEN 'March'\n"
                + "        WHEN MonthIndex = 4 THEN 'April'\n"
                + "        WHEN MonthIndex = 5 THEN 'May'\n"
                + "        WHEN MonthIndex = 6 THEN 'June'\n"
                + "        WHEN MonthIndex = 7 THEN 'July'\n"
                + "        WHEN MonthIndex = 8 THEN 'August'\n"
                + "        WHEN MonthIndex = 9 THEN 'September'\n"
                + "        WHEN MonthIndex = 10 THEN 'October'\n"
                + "        WHEN MonthIndex = 11 THEN 'November'\n"
                + "        WHEN MonthIndex = 12 THEN 'December'\n"
                + "    END AS MonthName,\n"
                + "    DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1) AS MonthStart,\n"
                + "    EOMONTH(DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1)) AS MonthEnd,\n"
                + "    COALESCE(COUNT(o.OrderID), 0) AS TotalOrders\n"
                + "FROM\n"
                + "    Months\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(GETDATE()) AND MONTH(o.OrderDate) = MonthIndex\n"
                + "GROUP BY\n"
                + "    MonthIndex\n"
                + "ORDER BY\n"
                + "    MonthIndex;";
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(2) + "'", rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("getTotalOrderByMonth: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    public LinkedHashMap<String, String> getTotalPriceByYear() {
        String sql = "WITH Months AS (\n"
                + "    SELECT 1 AS MonthIndex\n"
                + "    UNION ALL\n"
                + "    SELECT MonthIndex + 1\n"
                + "    FROM Months\n"
                + "    WHERE MonthIndex < 12\n"
                + ")\n"
                + "\n"
                + "SELECT\n"
                + "    DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1) AS YearStart,\n"
                + "    EOMONTH(DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1)) AS YearEnd,\n"
                + "    COALESCE(SUM(o.TotalPrice), 0) AS TotalPrice\n"
                + "FROM\n"
                + "    Months\n"
                + "LEFT JOIN\n"
                + "    [Orders] o ON YEAR(o.OrderDate) = YEAR(GETDATE())\n"
                + "GROUP BY\n"
                + "    DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1)\n"
                + "ORDER BY\n"
                + "    DATEFROMPARTS(YEAR(GETDATE()), MonthIndex, 1);";

        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();

        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTotal.put("'" + rs.getString(1) + "'", rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("getTotalPriceByYear: " + e.getMessage());
        }

        for (Map.Entry<String, String> entry : listTotal.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key + " " + val);
        }

        System.out.println(listTotal.keySet());
        System.out.println(listTotal.values());

        return listTotal;
    }

    public ArrayList<String> getLast7Day() {
        LocalDate currentDate = LocalDate.now();
        LocalDate last7thDay = currentDate.minusDays(6);

        ArrayList<String> daysOfWeek = new ArrayList<>();
        while (!currentDate.isBefore(last7thDay)) {
            String dayName = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            daysOfWeek.add(dayName);
            currentDate = currentDate.minusDays(1);
        }
        Collections.reverse(daysOfWeek);
        return daysOfWeek;
    }

//    public List<Order> getManageProduct() {
//    List<Order> list = new ArrayList<>();
//    String sql = "SELECT Orders.OrderID AS id, CAST(Orders.OrderDate AS DATE) AS date, " +
//                 "Orders.UserID AS cusid, Orders.TotalPrice AS totalmoney, " +
//                 "Orders.[Status] AS Status, [User].UserName " +
//                 "FROM Orders " +
//                 "INNER JOIN [User] ON Orders.UserID = [User].UserID";
//
//    try {
//        PreparedStatement st = getConnection().prepareStatement(sql);
//        ResultSet rs = st.executeQuery();
//
//        while (rs.next()) {
//            Order order = new Order();
//            order.setId(rs.getInt("id"));
//            order.setDate(rs.getString("date"));
//            order.setCusid(rs.getInt("cusid"));
//            order.setTotalmoney(rs.getDouble("totalmoney"));
//            order.setStatus(rs.getInt("Status"));
//
//            // Create a User object and set the username
//            User user = new User();
//            user.setUserName(rs.getString("UserName"));
//
//            // Set the User object in the Order
//            order.setUser(user);
//
//            // Add the order to the list
//            list.add(order);
//        }
//    } catch (SQLException e) {
//        // Handle SQL exception
//        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
//    } catch (Exception e) {
//        // Handle other exceptions
//        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
//    }
//
//    return list;
//}
    public static void main(String[] args) {

        OrderDAO o = new OrderDAO();
        List<ProOrder> listO = o.getAllOrder(1, 9);
        System.out.println(o.getAllOrderASC(9));
    }

}
