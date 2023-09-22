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
    //mehtod to test: ThanhNX
    public static void main(String[] args) {
        ArrayList<User> test = new UserDAO().getTopUser();
        for (int i = 0; i < test.size(); i++) {
            System.out.println(test.get(i).toString());
        }
        ArrayList<String> test1 = new UserDAO().getRoleList(test);
        for (int i = 0; i < test1.size(); i++) {
            System.out.println(test1.get(i));
        }
    }
}
