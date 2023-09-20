/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mai Vu Bao
 */
public class UserProfileDAO extends DAO{

    public UserProfileDAO() {
        super();
    }
    
    public void updateAccount() {
//        try {
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UserProfileDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    public Account findUserByEmail(String emailAddress) throws Exception {
//        try {
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UserProfileDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }

    public Account checkAccountExistByEmailPass(String email, String pass) {
//        try {
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(UserProfileDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return null;
    }
}
