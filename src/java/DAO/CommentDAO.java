/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Comment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
