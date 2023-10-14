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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nhat Anh
 */
public class PostDAO extends DBContext {

    //get the top 4 viewed posts in DB: ThanhNX
    public ArrayList<Map<String, String>> getTop4Posts() {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String command = "SELECT TOP 4\n"
                + "b.PostID,\n"
                + "b.Title,\n"
                + "CONVERT(varchar, b.[Date], 103) + ' ' + CONVERT(varchar, b.[Date], 108) AS DateTime,\n"
                + "CONCAT(SUBSTRING(b.Content, 1, 100), '...') AS Content,\n"
                + "i.ImageUrl,\n"
                + "u.UserName,\n"
                + "(\n"
                + "SELECT COUNT(*)\n"
                + "FROM Comment AS c\n"
                + "WHERE c.TypeID = 2\n"
                + "AND c.ObjectID = b.PostID\n"
                + ") AS CommentNumber\n"
                + "FROM Post AS b\n"
                + "LEFT JOIN\n"
                + "(\n"
                + "SELECT ObjectID, MIN(ImageUrl) AS ImageUrl\n"
                + "FROM [Image]\n"
                + "GROUP BY ObjectID, TypeID\n"
                + "HAVING TypeID = 2\n"
                + ") AS i\n"
                + "ON b.PostID = i.ObjectID\n"
                + "JOIN [User] AS u\n"
                + "ON u.UserID = b.UserID\n"
                + "ORDER BY [View] DESC;";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("BlogID", rs.getString("PostID"));
                dataMap.put("Title", rs.getString("Title"));
                dataMap.put("DateTime", rs.getString("DateTime"));
                dataMap.put("Content", rs.getString("Content"));
                dataMap.put("ImageUrl", rs.getString("ImageUrl"));
                dataMap.put("UserName", rs.getString("UserName"));
                dataMap.put("CommentNumber", rs.getString("CommentNumber"));
                dataList.add(dataMap);
            }
        } catch (Exception e) {
        }
        return dataList;
    }

    //get post by id: ThanhNX
    public Map<String, String> getPostInfo(String id) {
        Map<String, String> dataMap = new HashMap<>();
        String command = "SELECT b.PostID, \n"
                + "	   b.Title, \n"
                + "	   b.Content\n"
                + "FROM Post b\n"
                + "WHERE b.PostID = ?";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                dataMap.put("BlogID", rs.getString("PostID"));
                dataMap.put("Title", rs.getString("Title"));
                dataMap.put("Content", rs.getString("Content"));
            } else {
                return null;
            }

        } catch (Exception e) {
        }
        return dataMap;
    }

    //add one view to a blog: ThanhNX
    public void addViewToPost(String id) {
        String command = "UPDATE Post\n"
                + "SET [View] = [View] + 1\n"
                + "WHERE PostID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //get number comment of a post: ThanhNX
    public int getCommentNumberByPostID(String id) {
        int CommentNumber = 0;
        String command = "SELECT COUNT(*) CommentNumber\n"
                + "FROM Comment c JOIN Post p\n"
                + "ON c.ObjectID = p.PostID\n"
                + "JOIN ObjectType o\n"
                + "ON o.TypeID = c.TypeID\n"
                + "WHERE o.TypeName = 'Post' AND p.PostID = ?;";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CommentNumber = rs.getInt("CommentNumber");
            }
        } catch (Exception e) {
        }
        return CommentNumber;
    }

    //get all no-reply comment: ThanhNX
    public ArrayList<Comment> getAllRootCommentByPostID(String id) {
        ArrayList<Comment> rootCommentList = new ArrayList<>();
        String command = "SELECT u.UserID,\n"
                + "	   u.Avatar,\n"
                + "	   u.UserName,\n"
                + "	   c.CommentID,\n"
                + "	   CONVERT(varchar, c.CommentDate, 103) + ' ' + CONVERT(varchar, c.CommentDate, 108) CommentDate,\n"
                + "	   c.CommentContent\n"
                + "FROM Comment c JOIN [User] u\n"
                + "ON c.UserID = u.UserID\n"
                + "JOIN ObjectType o\n"
                + "ON c.TypeID = o.TypeID\n"
                + "WHERE c.CommentRepID IS NULL \n"
                + "	  AND o.TypeName = 'Post'\n"
                + "	  AND c.ObjectID = ?\n"
                + "ORDER BY c.CommentDate";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setUserID(rs.getInt("UserID"));
                comment.setAvatar(rs.getString("Avatar"));
                comment.setUserName(rs.getString("UserName"));
                comment.setCommentID(rs.getInt("CommentID"));
                comment.setCommentDate(rs.getString("CommentDate"));
                comment.setCommentContent(rs.getString("CommentContent"));
                rootCommentList.add(comment);
            }
        } catch (Exception e) {
        }
        return rootCommentList;
    }

    //get all replied comment by a list of root comment: ThanhNX
    public ArrayList<ArrayList<Comment>> getAllRepComment(ArrayList<Comment> rootCommentList) {
        ArrayList<ArrayList<Comment>> repCommentList = new ArrayList<>();
        String command = "SELECT u.UserID,\n"
                + "	   u.Avatar,\n"
                + "	   u.UserName,\n"
                + "	   c.CommentID,\n"
                + "	   CONVERT(varchar, c.CommentDate, 103) + ' ' + CONVERT(varchar, c.CommentDate, 108) CommentDate,\n"
                + "	   c.CommentContent\n"
                + "FROM Comment c JOIN [User] u\n"
                + "ON c.UserID = u.UserID\n"
                + "JOIN ObjectType o\n"
                + "ON c.TypeID = o.TypeID\n"
                + "WHERE c.CommentRepID = ?\n"
                + "	  AND o.TypeName = 'Post'\n"
                + "ORDER BY c.CommentDate";
        for (int i = 0; i < rootCommentList.size(); i++) {
            try {
                ArrayList<Comment> repComments = new ArrayList<>();
                PreparedStatement st = getConnection().prepareStatement(command);
                st.setString(1, String.valueOf(rootCommentList.get(i).getCommentID()));
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Comment comment = new Comment();
                    comment.setUserID(rs.getInt("UserID"));
                    comment.setAvatar(rs.getString("Avatar"));
                    comment.setUserName(rs.getString("UserName"));
                    comment.setCommentID(rs.getInt("CommentID"));
                    comment.setCommentDate(rs.getString("CommentDate"));
                    comment.setCommentContent(rs.getString("CommentContent"));
                    repComments.add(comment);
                }
                repCommentList.add(repComments);
            } catch (Exception e) {
            }
        }
        return repCommentList;
    }

    //get category and number of posts each category: ThanhNX
    public ArrayList<Map<String, String>> getPostNumberPerCategory() {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String command = "SELECT c.CategoryID, \n"
                + "	   c.CategoryName, \n"
                + "	   COUNT(pc.PostID) AS NumberOfPosts\n"
                + "FROM Category c\n"
                + "LEFT JOIN PostCategory pc \n"
                + "ON c.CategoryID = pc.CategoryID\n"
                + "JOIN ObjectType ob \n"
                + "ON c.ObjectTypeID = ob.TypeID\n"
                + "WHERE ob.TypeName = 'Post'\n"
                + "GROUP BY c.CategoryID, c.CategoryName\n"
                + "ORDER BY c.CategoryID;";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("CategoryID", rs.getString("CategoryID"));
                dataMap.put("CategoryName", rs.getString("CategoryName"));
                dataMap.put("NumberOfPosts", rs.getString("NumberOfPosts"));
                dataList.add(dataMap);
            }
        } catch (Exception e) {
        }
        return dataList;
    }

    //get 4 recent post except current watching post: ThanhNX
    public ArrayList<Map<String, String>> getRecentPost(String currentPostID) {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String command = "SELECT TOP 4\n"
                + "b.PostID,\n"
                + "b.Title,\n"
                + "CONVERT(varchar, b.[Date], 103) + ' ' + CONVERT(varchar, b.[Date], 108) AS DateTime,\n"
                + "CONCAT(SUBSTRING(b.Content, 1, 100), '...') AS Content,\n"
                + "i.ImageUrl,\n"
                + "u.UserName,\n"
                + "(\n"
                + "SELECT COUNT(*)\n"
                + "FROM Comment AS c\n"
                + "WHERE c.TypeID = 2\n"
                + "AND c.ObjectID = b.PostID\n"
                + ") AS CommentNumber\n"
                + "FROM Post AS b\n"
                + "LEFT JOIN\n"
                + "(\n"
                + "SELECT ObjectID, MIN(ImageUrl) AS ImageUrl\n"
                + "FROM [Image]\n"
                + "GROUP BY ObjectID, TypeID\n"
                + "HAVING TypeID = 2\n"
                + ") AS i\n"
                + "ON b.PostID = i.ObjectID\n"
                + "JOIN [User] AS u\n"
                + "ON u.UserID = b.UserID\n"
                + "WHERE b.PostID != ?\n"
                + "ORDER BY b.[Date] DESC;";
        try {
            PreparedStatement st = getConnection().prepareStatement(command);
            st.setString(1, currentPostID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("BlogID", rs.getString("PostID"));
                dataMap.put("Title", rs.getString("Title"));
                dataMap.put("DateTime", rs.getString("DateTime"));
                dataMap.put("Content", rs.getString("Content"));
                dataMap.put("ImageUrl", rs.getString("ImageUrl"));
                dataMap.put("UserName", rs.getString("UserName"));
                dataMap.put("CommentNumber", rs.getString("CommentNumber"));
                dataList.add(dataMap);
            }
        } catch (Exception e) {
        }
        return dataList;
    }

    //get all post: ThanhNX
    public ArrayList<Map<String, String>> getAllPosts(String searchKey) {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String parameter = "%" + searchKey + "%";
        String command = "SELECT b.PostID,\n"
                + "b.Title,\n"
                + "CONVERT(varchar, b.[Date], 103) + ' ' + CONVERT(varchar, b.[Date], 108) AS DateTime,\n"
                + "CONCAT(SUBSTRING(b.Content, 1, 100), '...') AS Content,\n"
                + "i.ImageUrl,\n"
                + "u.UserName,\n"
                + "(\n"
                + "SELECT COUNT(*)\n"
                + "FROM Comment AS c\n"
                + "WHERE c.TypeID = 2\n"
                + "AND c.ObjectID = b.PostID\n"
                + ") AS CommentNumber\n"
                + "FROM Post AS b\n"
                + "LEFT JOIN\n"
                + "(\n"
                + "SELECT ObjectID, MIN(ImageUrl) AS ImageUrl\n"
                + "FROM [Image]\n"
                + "GROUP BY ObjectID, TypeID\n"
                + "HAVING TypeID = 2\n"
                + ") AS i\n"
                + "ON b.PostID = i.ObjectID\n"
                + "JOIN [User] AS u\n"
                + "ON u.UserID = b.UserID\n"
                + "WHERE LOWER(b.Title) LIKE ?\n"
                + "	  OR LOWER(b.Content) LIKE ?\n"
                + "ORDER BY [View] DESC;";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, parameter);
            ps.setString(2, parameter);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("BlogID", rs.getString("PostID"));
                dataMap.put("Title", rs.getString("Title"));
                dataMap.put("DateTime", rs.getString("DateTime"));
                dataMap.put("Content", rs.getString("Content"));
                dataMap.put("ImageUrl", rs.getString("ImageUrl"));
                dataMap.put("UserName", rs.getString("UserName"));
                dataMap.put("CommentNumber", rs.getString("CommentNumber"));
                dataList.add(dataMap);
            }
        } catch (Exception e) {
        }
        return dataList;
    }

    public static void main(String[] args) {
        ArrayList<Comment> dataList = new PostDAO().getAllRootCommentByPostID("1");
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i).toString());
        }
        ArrayList<ArrayList<Comment>> repcomment = new PostDAO().getAllRepComment(dataList);
        for (int i = 0; i < repcomment.size(); i++) {
            System.out.println(repcomment.get(i).size());
        }

        ArrayList<Map<String, String>> dataList2 = new PostDAO().getPostNumberPerCategory();
        for (int i = 0; i < dataList2.size(); i++) {
            System.out.println(dataList2.get(i).toString());
        }
    }
}
