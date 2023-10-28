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
import java.util.ArrayList;

public class accountDAO extends DBContext{

    
    
    public Account getAcount(String user,String pass){
            String sql="select * from Account"
                    + "where email = ? and password=?";
            try{
                PreparedStatement st=getConnection().prepareStatement(sql);
                st.setString(1, user);
                st.setString(2, pass);
                ResultSet rs=st.executeQuery();
                if(rs.next()){
                    return new Account(rs.getString(1),
                                      rs.getString(2),
                                      rs.getInt(3),
                                      rs.getInt(4));
                                      
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
    //get total account by status: BaoMV
    public ArrayList<String> getTotalAccountByStatus() {
        String sql = "SELECT Status, COUNT(Status)\n"
                + "  FROM [SWP391_Group3].[dbo].[Account]\n"
                + "  group by Status\n"
                + "  order by Status";
        ArrayList<String> result = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                result.add(rs.getInt(2) + "");
            }
        } catch (Exception e) {
            System.out.println("getTotalAccountByStatus: " + e.getMessage());
        }
        if(result.isEmpty()) {
            result.add("0");
            result.add("0");
        }
        else if(result.size() == 1) {
            result.add("0");
        }
        return result;
    }
    //HoangNH
   public ArrayList<String> getTotalProductByStatus(int userID){
    String sql = "SELECT Status, COUNT(Status) AS StatusCount\n"
            + "FROM [Product]\n"
            + "WHERE UserID = ?\n"
            + "GROUP BY Status\n";
    ArrayList<String> result = new ArrayList<>();
    try {
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ps.setInt(1, userID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            result.add(rs.getInt(2) + "");
        }
    }   catch (Exception ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    if(result.isEmpty()) {
            result.add("0");
            result.add("0");
        }
        else if(result.size() == 1) {
            result.add("0");
        }
        return result;
}
    public static void main(String[] args) {
        accountDAO d = new accountDAO();
        System.out.println(d.getTotalProductByStatus(9));
    }
}
