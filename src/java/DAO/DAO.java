/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
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

 public void insertAccount(Account c, User u) {
        try {
            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], [RoleID]) VALUES (?, ?, ?)";

            // Thực thi câu truy vấn cho bảng Account
            PreparedStatement stAccount = getConnection().prepareStatement(sql);
            stAccount.setString(1, c.getEmail());
            stAccount.setString(2, c.getPassword());
            stAccount.setInt(3, 3);
            stAccount.executeUpdate();

//            String sql1 = "select top 1 Email from Account  order by Email desc";
//            PreparedStatement st1 = getConnection().prepareStatement(sql1);
//            ResultSet rs = st1.executeQuery();
          
             

                String sql2 = "INSERT INTO [dbo].[User]\n"
                        + "           ([UserName]\n"
                        + "           ,[PhoneNumber]\n"
                        + "           ,[Email])\n"
                        + "     VALUES\n"
                        + "           ( ?, ?, ?)";
                PreparedStatement st2 = getConnection().prepareStatement(sql2);
                st2.setString(1, u.getUserName());

                st2.setString(2, u.getPhoneNumber());

                st2.setString(3, u.getEmail());

                st2.executeUpdate();
            

        } catch (SQLException e) {
        } catch (Exception e) {

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
