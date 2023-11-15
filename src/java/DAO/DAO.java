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
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }

    public Account getAccount(String email, String pass) {
        String sql = "SELECT [Email]\n"
                + "      ,[Password]\n"
                + "      ,[RoleID], Status\n"
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
                        rs.getInt("Status"),
                        rs.getInt("RoleID"));
                
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

   

    public void insertAccount(Account c, User u) {
        try {
            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], Status , [RoleID]) VALUES (?, ?, ?, ?)";

            // Thực thi câu truy vấn cho bảng Account
            PreparedStatement stAccount = getConnection().prepareStatement(sql);
            stAccount.setString(1, c.getEmail());
            stAccount.setString(2, c.getPassword());
            stAccount.setInt(3, 1);
            stAccount.setInt(4, 3);
            stAccount.executeUpdate();


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
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
       public void insertAccountArtist(Account c, User u) {
        try {
            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], Status , [RoleID]) VALUES (?, ?, ?, ?)";

            // Thực thi câu truy vấn cho bảng Account
            PreparedStatement stAccount = getConnection().prepareStatement(sql);
            stAccount.setString(1, c.getEmail());
            stAccount.setString(2, c.getPassword());
            stAccount.setInt(3, 2);
            stAccount.setInt(4, 3);
            stAccount.executeUpdate();


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
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
 public void AddUser(Account c, User u) {
        try {
            String sql = "INSERT INTO [dbo].[Account] ([Email], [Password], Status , [RoleID]) VALUES (?, ?, ?, ?)";

            // Thực thi câu truy vấn cho bảng Account
            PreparedStatement stAccount = getConnection().prepareStatement(sql);
            stAccount.setString(1, c.getEmail());
            stAccount.setString(2, c.getPassword());
            stAccount.setInt(3, 1);
            stAccount.setInt(4, c.getRoleID());
            stAccount.executeUpdate();


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
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
    }
 
 
    public void insertUser(User u) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([UserName]\n"
                + "           ,[PhoneNumber]\n"
                + "           ,[Email])\n"
                + "     VALUES\n"
                + "           ( ?, ?, ?)";
        try {
            // Thực thi câu truy vấn cho bảng Account
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, u.getUserName());

            st.setString(2, u.getPhoneNumber());

            st.setString(3, u.getEmail());
            st.executeUpdate();
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
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
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return c;
    }public boolean getAccountByUsername(String email) throws SQLException, Exception {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            if (getConnection() != null) {
                String sql = "SELECT [Email]\n"
                        + "      ,[Password]\n"
                        + "      ,[RoleID]\n"
                        + "  FROM [dbo].[Account]\n"
                        + "  where Email =?";
                //3. Create Statement
                stm = getConnection().prepareStatement(sql);
                stm.setString(1, email);
                rs = stm.executeQuery();
                if (!rs.next()) {
                    return true;
                }
                if (rs.next()) {
                    return false;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            if (getConnection() != null) {
                getConnection().close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public boolean createAccount(Account a) throws SQLException, Exception {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            if (getConnection() != null) {
                String sql = "INSERT INTO [dbo].[Account]\n"
                        + "           ([Email]\n"
                        + "           ,[Password]\n"
                        + "           ,[Status]\n"
                        + "           ,[RoleID])\n"
                        + "     VALUES\n"
                        + "           (?,?,1,1)";
                //3. Create Statement
                stm = getConnection().prepareStatement(sql);
                stm.setString(1, a.getEmail());
                stm.setString(2, a.getPassword());
                return stm.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            if (getConnection() != null) {
                getConnection().close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Account a = new Account("hoangcute@gmail.com", "11111111", 1, 1);
        DAO d = new DAO();
        try {
            System.out.println(d.createAccount(a));
        } catch (Exception ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


