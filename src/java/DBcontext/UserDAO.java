/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBcontext;

import Model.Setting;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nhat Anh
 */
public class UserDAO extends DBContext {

    //select top 5 user: ThanhNX
    public ArrayList<User> getTopUser() {
        ArrayList<User> topUser = new ArrayList<>();
        String command = "select top 5 * from [User]";
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

    public List<Setting> getAllSetting() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status, Description FROM Category\n"
                + "UNION\n"
                + "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status, Description FROM Type";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return list;
    }

    //get all Category Setting MinhHC
    public List<Setting> getAllSettingCat() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status, Description FROM Category";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return list;
    }

    //get all Type Setting MinhHC
    public List<Setting> getAllSettingTyp() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status, Description FROM Type";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception ex) {

        }

        return list;
    }

    //get all active setting MinhHC
    public List<Setting> getSettingActive() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status, Description FROM Category Where status =1\n"
                + "UNION\n"
                + "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status, Description FROM Type Where status =1;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return list;
    }

    //get all inactive setting MinhHC
    public List<Setting> getSettingInactive() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status, Description FROM Category Where status =0\n"
                + "UNION\n"
                + "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status,Description FROM Type Where status =0;";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return list;
    }

    //Search setting by name MinhHC
    public List<Setting> searchSettingByName(String txtSearch) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM (\n"
                + "    SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type, Status, Description FROM Category \n"
                + "    UNION\n"
                + "    SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status, Description FROM Type\n"
                + ") AS setting \n"
                + "WHERE Name LIKE ?;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return list;
    }

    //Add Product Category MinhHC
    public void addsettingCategory(String name, String description) {

        String sql = "INSERT INTO Category (CategoryName, Status, Description) VALUES (?, ?, ?)";
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {

            st.setString(1, name);
            st.setInt(2, 1);
            st.setString(3, description);
            st.executeUpdate();
        } catch (SQLException e) {

        } catch (Exception e) {

        }
    }

    //Add Product Type MinhHC
    public void addsettingType(String name, String description) {

        String sql = "INSERT INTO Type (TypeName, Status, Description) VALUES (?, ?, ?)";
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {

            st.setString(1, name);
            st.setInt(2, 1);
            st.setString(3, description);
            st.executeUpdate();
        } catch (SQLException e) {

        } catch (Exception e) {

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

        } catch (Exception e) {

        }
    }

    //Change status in table Type MinhHC
    public void ChangeSettingStatusTyp(int id, int newStatus) {

        String sql = "UPDATE Type SET Status = ? WHERE TypeID = ?";

        // Thay categoryId bằng giá trị thích hợp
        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, newStatus);
            st.setInt(2, id);

            st.executeUpdate();

        } catch (SQLException e) {

        } catch (Exception e) {

        }
    }

    //Edit setting Type MinhHC
    public void EditSettingTyp(int id, int status, String description) {

        String sql = "UPDATE Type SET Status = ?, description = ? WHERE TypeID = ?";

        try ( PreparedStatement st = getConnection().prepareStatement(sql)) {
            st.setInt(1, status);
            st.setString(2, description);
            st.setInt(3, id);

            st.executeUpdate();

        } catch (SQLException e) {

        } catch (Exception e) {

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

        } catch (Exception e) {

        }
    }

    public List<User> getAllUser() {
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
                + "    Account A ON U.Email = A.Email;";
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
        } catch (Exception e) {
            // Handle other exceptions
        }

        return list;
    }

    //mehtod to test: ThanhNX
//    public static void main(String[] args) {
//        ArrayList<User> test = new UserDAO().getTopUser();
//        for (int i = 0; i < test.size(); i++) {
//            System.out.println(test.get(i).toString());
//        }
//        ArrayList<String> test1 = new UserDAO().getRoleList(test);
//        for (int i = 0; i < test1.size(); i++) {
//            System.out.println(test1.get(i));
//        }
//
//        UserDAO c = new UserDAO();
//        List<User> listC = c.getAllUser();
//
//        for (User o : listC) {
//            System.out.println(o);
//        }
//
//    }
    public User getUser() {
        String sql = "select * from [user] where UserID = 1";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userID = rs.getInt(1);
                String username = rs.getString(2);
                int gender = rs.getBoolean(3)?1:0;
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
    public static void main(String[] args) {
        System.out.println(new UserDAO().getUser().toString());
    }
    
    public void updateUser(User user) {
        String sql = "update [User] SET [UserName] = ?\n" +
"      ,[Gender] = ?\n" +
"      ,[PhoneNumber] = ?\n" +
"      ,[Address] = ?\n" +
"      ,[Avatar] = ?\n" +
"      where userid = ?";
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
}
