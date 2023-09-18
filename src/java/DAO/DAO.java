/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class DAO extends DBContext {

    public void AddAccount(Account c) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([Email]\n"
                + "           ,[Password]\n"
                + "           ,[RoleID])\n"
                + "     VALUES\n"
                + "           (?,?,1)";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, c.getEmail());
            st.setString(2, c.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {

        } catch (Exception e) {

        }
    }

    public Account getAccount(String email, String pass) {
        String sql = "SELECT [Email]\n"
                + "      ,[Password]\n"
                + "      ,[RoleID]\n"
                + "  FROM [dbo].[Account]\n"
                + "  where Email = ? and  [Password] = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {

        }
        return null;
    }

public void insertAccountUser(Account c, User u) {
    String sqlAccount = "INSERT INTO [dbo].[Account] ([Email], [Password], [RoleID]) VALUES (?, ?, ?)";
    String sqlUser = "INSERT INTO [dbo].[User] ([UserName], [Gender], [PhoneNumber], [Address], [Avatar], [Email]) VALUES (?, ?, ?, ?, ?, ?)";
    
    try {
        // Thực thi câu truy vấn cho bảng Account
        PreparedStatement stAccount = getConnection().prepareStatement(sqlAccount);
        stAccount.setString(1, c.getEmail());
        stAccount.setString(2, c.getPassword());
        stAccount.setInt(3, 2);  // Giả sử RoleID là 2
        stAccount.executeUpdate();

        // Thực thi câu truy vấn cho bảng User
        PreparedStatement stUser = getConnection().prepareStatement(sqlUser);
        stUser.setString(1, u.getUserName());
        stUser.setInt(2, u.getGender());  // Giả sử getGender() trả về giá trị số nguyên
        stUser.setString(3, u.getPhoneNumber());
        stUser.setString(4, u.getAddress());
        stUser.setString(5, u.getAvatar());
        stUser.setString(6, u.getEmail());
        stUser.executeUpdate();
    } catch (SQLException e) {
        // Xử lý ngoại lệ SQL
        e.printStackTrace();
    } catch (Exception e) {
        // Xử lý các ngoại lệ khác
        e.printStackTrace();
    }
}


    public Account getAccountByEmail(String email) {
        Account c = null;
        String sql = "SELECT [Email]\n"
                + "      ,[Password]\n"
                + "      ,[RoleID]\n"
                + "  FROM [dbo].[Account]\n"
                + "  where Email =?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Account(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3));
            }
        } catch (Exception e) {
        }
        return c;
    }
}
