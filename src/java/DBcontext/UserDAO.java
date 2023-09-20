/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBcontext;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
