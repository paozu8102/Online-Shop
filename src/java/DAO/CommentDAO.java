/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Anh
 */
public class CommentDAO extends DBContext {

    //function to add a comment: ThanhNX
    public int addComment(int userID, int postID, String content) {
        int rowAffected = 0;
        String command = "INSERT INTO [dbo].[Comment]\n"
                + "           ([UserID]\n"
                + "           ,[TypeID]\n"
                + "           ,[ObjectID]\n"
                + "           ,[CommentContent]\n"
                + "           ,[CommentDate])\n"
                + "     VALUES (?, 2, ?, ?, GETDATE());";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setInt(1, userID);
            st.setInt(2, postID);
            st.setString(3, content);
            rowAffected = st.executeUpdate();
        } catch (Exception e) {
        }
        return rowAffected;
    }

    //function to add a reply comment: ThanhNX
    public int addRepComment(int userID, int postID, String content, int commentRepID) {
        int rowAffected = 0;
        String command = "INSERT INTO [dbo].[Comment]\n"
                + "           ([UserID]\n"
                + "           ,[TypeID]\n"
                + "           ,[ObjectID]\n"
                + "           ,[CommentContent]\n"
                + "           ,[CommentDate]"
                + "           ,CommentRepID)\n"
                + "     VALUES (?, 2, ?, ?, GETDATE(), ?);";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setInt(1, userID);
            st.setInt(2, postID);
            st.setString(3, content);
            st.setInt(4, commentRepID);
            rowAffected = st.executeUpdate();
        } catch (Exception e) {
        }
        return rowAffected;
    }

    public List<Comment> getAllCommentPrd() {
        List<Comment> list = new ArrayList<>();
        String sql = "SELECT [User].UserName, Avatar, CommentContent "
                + "FROM [User] INNER JOIN Comment ON Comment.UserID = [User].UserID "
                + "WHERE TypeID = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setInt(1, 1);
            ResultSet rs = st.executeQuery();
             while (rs.next()) {
                Comment p = new Comment();
                p.setUserName(rs.getString("UserName"));
                p.setAvatar(rs.getString("Avatar"));
                p.setCommentContent(rs.getString("CommentContent"));
                list.add(p);

            }
        } catch (SQLException e) {
            // Handle SQL exception
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "SQL Exception", e);
        } catch (Exception e) {
            // Handle other exceptions
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
        }
        return list;
    }

    //function to delete a comment: ThanhNX
    public int deleteComment(int commentID) {
        int rowAffected = 0;
        String command = "DELETE FROM Comment \n"
                + "WHERE CommentID = ? \n"
                + "OR CommentRepID = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setInt(1, commentID);
            st.setInt(2, commentID);
            rowAffected = st.executeUpdate();
        } catch (Exception e) {
        }
        return rowAffected;
    }
}
