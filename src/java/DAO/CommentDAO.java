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
     //retrieve the 5 recent comments: BaoMV
    public ArrayList<Comment> getRecentComment() {
        ArrayList<Comment> listComment = new ArrayList<>();
        String sql = "select top 5 * from [SWP391_Group3].[dbo].[Comment] order by CommentDate desc";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                int comid = rs.getInt(1);
                int uid = rs.getInt(2);
                int typeid = rs.getInt(3);
                int objectid = rs.getInt(4);
                String content = rs.getString(5);
                String date = rs.getString(6);
                listComment.add(new Comment(comid, uid, typeid, objectid, content, date));
            }
        } catch (Exception e) {
            System.out.println("getRecentComment: " + e.getMessage());
        }
        return listComment;
    }
}
