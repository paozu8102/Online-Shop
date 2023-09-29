/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nhat Anh
 */
public class CommentDAO extends DBContext {

    //get comment of Users: ThanhNX
    public String getFisrtCommentOfUser(int userID) {
        String command = "SELECT CommentContent\n"
                + "FROM [User] u JOIN Comment c\n"
                + "ON u.UserID = c.UserID\n"
                + "WHERE u.UserID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("CommentContent");
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new CommentDAO().getFisrtCommentOfUser(3));
    }
}
