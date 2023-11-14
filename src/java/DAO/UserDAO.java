/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Role;

import DBcontext.DBContext;

import Model.Setting;
import Model.User;
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
public class UserDAO extends DBContext {

    //select top 5 user: ThanhNX
    public ArrayList<User> getTopUser() {
        ArrayList<User> topUser = new ArrayList<>();
        String command = "SELECT TOP 5 * FROM [User]";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                int Gender = rs.getInt("Gender");
                String PhoneNumber = rs.getString("PhoneNumber");
                String Address = rs.getString("Address");
                String Avatar = rs.getString("Avatar");
                String Email = rs.getString("Email");
                topUser.add(new User(UserID, UserName, Gender, PhoneNumber, Address, Avatar, Email));
            }
        } catch (Exception e) {
        }
        return topUser;
    }

    //Change status in Product HoangNH
    public void ChangeSettingStatusProduct(int id, int newStatus) {

        String sql = "UPDATE Product SET Status = ? WHERE ProductID = ?";

        // Thay categoryId bằng giá trị thích hợp
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, newStatus);
            st.setInt(2, id);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
//Hoang NH

    public void updateSaler(User user) {
        String sql = "update [User] SET [UserName] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[PhoneNumber] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Avatar] = ?\n"
                + "      where userid = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getGender());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getAvatar());
            ps.setString(6, user.getUserID() + "");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateImage: " + e.getMessage());
        }
    }

    //method to get role list from a user list: ThanhNX
    public ArrayList<String> getRoleList(ArrayList<User> users) {
        ArrayList<String> roleList = new ArrayList<>();
        String command = "SELECT RoleName \n"
                + "FROM Account a JOIN Role r\n"
                + "on a.RoleID = r.RoleID\n"
                + "WHERE Email = ?";
        for (int i = 0; i < users.size(); i++) {
            try {
                PreparedStatement st = getConnection().prepareStatement(command);
                st.setString(1, users.get(i).getEmail());
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    roleList.add(rs.getString("RoleName"));
                }
            } catch (Exception e) {
            }
        }
        return roleList;
    }

    //get all Setting MinhHC
    public List<Setting> getAllSetting() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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
//get all Setting MinhHC

    public List<Setting> getAllSetting(int index) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName "
                + "FROM Category "
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID "
                + "ORDER BY Category.CategoryID "
                + "OFFSET ? ROWS "
                + "FETCH NEXT 9 ROWS ONLY";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, (index - 1) * 9);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    public List<Setting> getSettingNameSort(String sort) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName "
                + "FROM Category "
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID "
                + "ORDER BY Category.CategoryName " + sort;

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    public List<User> getUserNameSort(String sort) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "ORDER BY U.UserName " + sort; // Sort by UserName

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    //get all Category Setting MinhHC
    public List<Setting> getAllSettingCat() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID\n"
                + "WHERE ObjectType.TypeID = 1;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //get all Blog Setting MinhHC
    public List<Setting> getAllSettingPost() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID\n"
                + "WHERE ObjectType.TypeID = 2;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //get all Type Setting MinhHC
    public List<Setting> getAllSettingTyp() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID\n"
                + "WHERE ObjectType.TypeID = 3;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //get all active setting MinhHC
    public List<Setting> getSettingActive() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID\n"
                + "WHERE Status= 1;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //get all inactive setting MinhHC
    public List<Setting> getSettingInactive() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName\n"
                + "FROM Category\n"
                + "INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID\n"
                + "WHERE Status= 0;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //Search setting by name MinhHC
    public List<Setting> searchSettingByName(String txtSearch) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM ( "
                + "    SELECT Category.CategoryID, Category.CategoryName, Category.Status, Category.Description, ObjectType.TypeName "
                + "    FROM Category "
                + "    INNER JOIN ObjectType ON Category.ObjectTypeID = ObjectType.TypeID "
                + ") AS setting "
                + "WHERE CategoryName LIKE ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(5),
                        rs.getInt(3),
                        rs.getString(4)
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

    //Add Product Category MinhHC
    public void addsetting(String name, String description, int type) {

        String sql = "INSERT INTO Category (CategoryName, Status, Description, ObjectTypeID) VALUES (?, ?, ?, ?)";
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {

            st.setString(1, name);
            st.setInt(2, 1);
            st.setString(3, description);
            st.setInt(4, type);
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    //Change status in table Category MinhHC
    public void ChangeSettingStatusCat(int id, int newStatus) {

        String sql = "UPDATE Category SET Status = ? WHERE CategoryID = ?";

        // Thay categoryId bằng giá trị thích hợp
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, newStatus);
            st.setInt(2, id);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    //Edit setting category MinhHC
    public void EditSettingCat(int id, int status, String description) {

        String sql = "UPDATE Category SET Status = ?, description = ? WHERE CategoryID = ?";

        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, status);
            st.setString(2, description);
            st.setInt(3, id);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    //get total user MinhHC
    public int getTotalUser() {
        String sql = "select count(*) from [User]";
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

    public int getTotalCustomer(int id) {
       String sql = "SELECT COUNT(DISTINCT u.UserID) AS CustomerCount\n"
            + "FROM [User] u\n"
            + "JOIN Orders o ON u.UserID = o.UserID\n"
            + "JOIN OrderDetail od ON o.OrderID = od.OrderID\n"
            + "JOIN Product p ON od.ProductID = p.ProductID\n"
            + "WHERE p.UserID = ?";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
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

    //get total setting minhHC
    public int getTotalSetting() {
        String sql = "SELECT COUNT(*) from category";
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

    //Get all User MinhHC
    public List<User> getAllUser(int index) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status ,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "ORDER BY UserID OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);

            st.setInt(1, (index - 1) * 9);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    //Get all User DucLV
    public List<User> getAllCustomer(int id, int index) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT  DISTINCT [User].UserID AS ID, [User].UserName AS CustomerName,\n"
                + "       [User].Email,\n"
                + "       [User].PhoneNumber AS Mobile,\n"
                + "       Account.Status\n"
                + "FROM [User]\n"
                + "INNER JOIN Orders ON [User].UserID = Orders.UserID\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID\n"
                + "INNER JOIN Account ON [User].Email = Account.Email\n"
                + "WHERE Product.UserID = ?\n"
                + "ORDER BY [User].UserID\n"
                + "OFFSET ? ROWS FETCH NEXT 9 ROWS ONLY";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, (index - 1) * 9);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("ID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
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

    public User getCustomerByID(String id) {

        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email\n"
                + "\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    U.UserID = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                return new User(
                rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
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

   

    public List<User> SearchCustomer(int id, String txtSearch) {
        List<User> list = new ArrayList<>();

        String sql = "SELECT DISTINCT [User].UserID AS ID, [User].UserName AS CustomerName, "
                + "[User].Email, [User].PhoneNumber AS Mobile, Account.Status "
                + "FROM [User] "
                + "INNER JOIN Orders ON [User].UserID = Orders.UserID "
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID "
                + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID "
                + "INNER JOIN Account ON [User].Email = Account.Email "
                + "WHERE Product.UserID = ? "
                + "AND ([User].UserName LIKE ? OR [User].Email LIKE ? OR [User].PhoneNumber LIKE ?) "
                + "ORDER BY [User].UserID";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, "%" + txtSearch + "%");
            st.setString(3, "%" + txtSearch + "%");
            st.setString(4, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("ID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
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

    public List<User> getAllCustomerCity(int id, String city) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT Distinct [User].UserName AS CustomerName,\n"
                + "       [User].UserID AS ID,\n"
                + "       [User].Email,\n"
                + "       [User].PhoneNumber AS Mobile,\n"
                + "       Account.Status\n"
                + "FROM [User]\n"
                + "INNER JOIN Orders ON [User].UserID = Orders.UserID\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID\n"
                + "INNER JOIN Account ON [User].Email = Account.Email\n"
                + "WHERE Product.UserID = ? AND [User].Address Like ?\n"
                + "ORDER BY [User].UserID";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, "%" + city + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("ID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
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

//Get all Customer active DucLV
    public List<User> getAllCustomerActive(int id) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT Distinct [User].UserName AS CustomerName, [User].UserID AS ID,\n"
                + "       [User].Email,\n"
                + "       [User].PhoneNumber AS Mobile,\n"
                + "       Account.Status\n"
                + "FROM [User]\n"
                + "INNER JOIN Orders ON [User].UserID = Orders.UserID\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID\n"
                + "INNER JOIN Account ON [User].Email = Account.Email\n"
                + "WHERE Product.UserID = ? AND Account.Status = 1 \n"
                + "ORDER BY [User].UserID;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("ID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
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

    //Get all customer blocked DucLV
    public List<User> getAllCustomerBlock(int id) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT Distinct [User].UserName AS CustomerName, [User].UserID AS ID,\n"
                + "       [User].Email,\n"
                + "       [User].PhoneNumber AS Mobile,\n"
                + "       Account.Status\n"
                + "FROM [User]\n"
                + "INNER JOIN Orders ON [User].UserID = Orders.UserID\n"
                + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID\n"
                + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID\n"
                + "INNER JOIN Account ON [User].Email = Account.Email\n"
                + "WHERE Product.UserID = ? AND Account.Status = 0 \n"
                + "ORDER BY [User].UserID";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt("ID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Mobile"),
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

 public List<User> getCustomerNameSort(int id, String sort) {
    List<User> list = new ArrayList<>();
    String sql = "SELECT Distinct [User].UserName AS CustomerName, [User].UserID AS ID, "
            + "[User].Email, [User].PhoneNumber AS Mobile, Account.Status "
            + "FROM [User] "
            + "INNER JOIN Orders ON [User].UserID = Orders.UserID "
            + "INNER JOIN OrderDetail ON Orders.OrderID = OrderDetail.OrderID "
            + "INNER JOIN Product ON OrderDetail.ProductID = Product.ProductID "
            + "INNER JOIN Account ON [User].Email = Account.Email "
            + "WHERE Product.UserID = ? "
            + "ORDER BY [User].UserName " + sort; // Sort by UserName

    try {
         PreparedStatement st = getConnection().prepareStatement(sql);

        
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(new User(
                    rs.getInt("ID"),
                    rs.getString("CustomerName"),
                    rs.getString("Email"),
                    rs.getString("Mobile"),
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


    //Get all Admin MinhHC
    public List<User> getAllAdmin() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.RoleID = 1;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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
//Get all Artist MinhHC

    public List<User> getAllArtist() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.RoleID = 2;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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
//Get all Customer MinhHC

    public List<User> getAllCustomer() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.RoleID = 3;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    //Get all Customer MinhHC
    public List<User> getAllUserActive() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.Status = 1;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    //Get all Customer MinhHC
    public List<User> getAllUserBlocked() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.Status = 0;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    //Get all Customer MinhHC
    public List<User> SearchUser(String txtSearch) {
        List<User> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE U.UserName LIKE ?\n"
                + "   OR U.Email LIKE ?\n"
                + "   OR U.PhoneNumber LIKE ?;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setString(2, "%" + txtSearch + "%");
            st.setString(3, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9)
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

    public List<Role> getRole() {
        List<Role> list = new ArrayList<>();
        String sql = "Select * from [Role]";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Role(
                        rs.getInt(1),
                        rs.getString(2)
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

//Change status in table Type MinhHC
    public void ChangeUserStatus(String email, int newStatus) {

        String sql = "UPDATE Account SET Status = ? WHERE email = ?";

        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, newStatus);
            st.setString(2, email);

            st.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    // get user base on userID: Baomv
    public User getUser(int id) {
        String sql = "select * from [user] where UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt(1);
                String username = rs.getString(2);
                int gender = rs.getBoolean(3) ? 1 : 0;
                String PhoneNumber = rs.getString(4);
                String Address = rs.getString(5);
                String Avatar = rs.getString(6);
                String Email = rs.getString(7);
                User user = new User(userID, username, gender, PhoneNumber, Address, Avatar, Email);
                return user;
            }
        } catch (Exception e) {
            System.out.println("getUser: " + e.getMessage());
        }
        return new User();
    }

    //get userID base on email and pass: Baomv
    public int cusAccountExist(String email, String p) {
        String sql = "SELECT u.UserID "
                + "FROM [User] AS u "
                + "INNER JOIN Account AS a ON u.Email = a.Email "
                + "WHERE a.Email = ? AND a.Password = ?";
        int userID = 0;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }
        } catch (Exception e) {
            System.out.println("cusAccountExist: " + e.getMessage());
        }
        return userID;
    }

    //get user base on userID using string: Baomv
    public User getUser(String id) {
        String sql = "select * from [user] where UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt(1);
                String username = rs.getString(2);
                int gender = rs.getBoolean(3) ? 1 : 0;
                String PhoneNumber = rs.getString(4);
                String Address = rs.getString(5);
                String Avatar = rs.getString(6);
                String Email = rs.getString(7);
                User user = new User(userID, username, gender, PhoneNumber, Address, Avatar, Email);
                return user;
            }
        } catch (Exception e) {
            System.out.println("getUser: " + e.getMessage());
        }
        return new User();
    }

    //update user information: Baomv
    public void updateUser(User user) {
        String sql = "update [User] SET [UserName] = ?\n"
                + "      ,[Gender] = ?\n"
                + "      ,[PhoneNumber] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Avatar] = ?\n"
                + "      where userid = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setInt(2, user.getGender());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getAvatar());
            ps.setString(6, user.getUserID() + "");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateImage: " + e.getMessage());
        }
    }

    //get user info by postID: ThanhNX
    public User getUserByPostID(String postID) {
        String command = "SELECT u.UserID,\n"
                + "	   u.UserName,\n"
                + "	   u.Gender,\n"
                + "	   u.PhoneNumber,\n"
                + "	   u.[Address],\n"
                + "	   u.Avatar,\n"
                + "	   u.Email\n"
                + "FROM Post p JOIN [User] u\n"
                + "ON p.UserID = u.UserID\n"
                + "WHERE p.PostID = ?";
        User user = new User();
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, postID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setGender(rs.getInt("Gender"));
                user.setPhoneNumber(rs.getString("PhoneNumber"));
                user.setAddress(rs.getString("Address"));
                user.setAvatar(rs.getString("Avatar"));
                user.setEmail(rs.getString("Email"));
            }
        } catch (Exception e) {
        }
        return user;
    }
    //get artist by id: Baomv
    public User getArtistById(int artistId) {
        User artist = null;
        String sql = "SELECT\n"
                + "    U.UserID,\n"
                + "    U.UserName,\n"
                + "    U.Gender,\n"
                + "    U.PhoneNumber,\n"
                + "    U.Address,\n"
                + "    U.Avatar,\n"
                + "    U.Email,\n"
                + "    A.Status,\n"
                + "    A.RoleID\n"
                + "FROM\n"
                + "    [User] U\n"
                + "INNER JOIN\n"
                + "    Account A ON U.Email = A.Email\n"
                + "WHERE\n"
                + "    A.RoleID = 2 AND U.UserID = ?";  // Add a condition to select a specific artist by their ID
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, artistId);  // Set the artistId parameter
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                artist = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getInt(8),
                    rs.getInt(9)
                );
            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }

        return artist;
    }

//    method to test: ThanhNX
    public static void main(String[] args) {
        ArrayList<User> test = new UserDAO().getTopUser();
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).toString());
        }
        ArrayList<String> test1 = new UserDAO().getRoleList(test);
        for (int i = 0; i < test1.size(); i++) {
            System.out.println(test1.get(i));
        }

        UserDAO c = new UserDAO();
        List<User> listC = c.getAllCustomer(2, 1);

        for (User o : listC) {
            System.out.println(o);
        }
        System.out.println(new UserDAO().getUserByPostID("1").toString());
    }
}
