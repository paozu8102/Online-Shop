/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class accountDAO extends DBContext{

    public Account findUserByEmail(String emailAddress) throws Exception {
        try {
            String sql = "SELECT * FROM [Account] where Email = ?";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, emailAddress);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account user = new Account();
                user.setEmail(rs.getString(1));
                user.setPassword(rs.getString(2));
                user.setRoleID(rs.getInt(3));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePassword(String newPassword, String emailAddress) throws Exception {
        try {
            String sql = "UPDATE [Account]\n"
                    + "   SET [Password] = ?\n"
                    + " WHERE  Email = ?";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, emailAddress);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //check account exist: Baomv
    public Account checkAccountExistByUserPass(String email, String password) throws Exception {
        try {
            String sql = "SELECT * FROM Account WHERE Email = ? AND Password = ?";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                // Account with the provided email and password exists
                Account account = new Account();
                account.setEmail(rs.getString("Email"));
                account.setPassword(rs.getString("Password"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    //update account pass: Baomv
    public void UpDatePassWord(String pass, String user) throws Exception {
        try {
            String sql = "UPDATE [Account]\n"
                    + "   SET [Password] = ?\n"
                    + " WHERE [Email] = ?";
            PreparedStatement stm = getConnection().prepareStatement(sql);
            stm.setString(1, pass);
            stm.setString(2, user);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
