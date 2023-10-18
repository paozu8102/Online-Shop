/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Account;
import Model.Order;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext {

    public void addOrder(int userid, double totalPrice ,String customername, String address, String phonenumber) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();

        String sql = "INSERT INTO Orders (UserID, OrderDate,  TotalPrice, CustomerName, PhoneNumber, Address, Status)\n"
                + "VALUES\n"
                + "    (?, ?,?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, userid);
            st.setString(2, date);
            st.setDouble(3, totalPrice);
              st.setString(4, customername);
            st.setString(5, phonenumber);
st.setString(6, address);
st.setString(7, "pending processing");
            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

   public void addOrderDetails(int oid, int pid, double price, int quantity) {
    String sql = "INSERT INTO OrderDetail (OrderID, ProductID, Price, Quantity) VALUES (?,?,?,?)";

    try {
         PreparedStatement st = getConnection().prepareStatement(sql);
        st.setInt(1, oid); 
        st.setInt(2, pid); 
        st.setDouble(3, price); 
        st.setInt(4, quantity);

        st.executeUpdate();
        
    }  catch (SQLException e) {
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
        
    }  catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
}
public Order getOrderLatest() {
       
        String sql = "select Top 1 * from orders";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
              Order o = new Order(rs.getInt(1),
                      rs.getString(2),
                      rs.getInt(3),
                      rs.getFloat(4),
                      rs.getString(5),
                        rs.getString(6),
              rs.getString(7),
              rs.getString(8));

                    
               return o;
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

}
