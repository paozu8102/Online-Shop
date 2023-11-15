/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Comment;
import Model.Post;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.ParseException;

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
                + "WHERE b.StatusID = 2\n"
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
                + "WHERE b.PostID = ?\n"
                + "AND b.StatusID = 2";
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

    //add one view to a post: ThanhNX
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

    

    //get all root comment: ThanhNX
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
                + "JOIN Post p\n"
                + "ON p.PostID = pc.PostID\n"
                + "WHERE ob.TypeName = 'Post' AND p.StatusID = 2\n"
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
                + "WHERE b.PostID != ? AND b.StatusID = 2\n"
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
    public ArrayList<Map<String, String>> getAllPosts(String searchKey, String dateinput1, String dateinput2, String sortElement, String sortOrder) {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String key = "%" + searchKey + "%";
        if (!dateinput1.isEmpty()) {
            dateinput1 = " AND b.[Date] > '" + dateinput1 + "' ";
        }
        if (!dateinput2.isEmpty()) {
            dateinput2 = " AND b.[Date] < '" + dateinput2 + "' ";
        }

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
                + "WHERE (LOWER(b.Title) LIKE ?\n"
                + "	  OR LOWER(b.Content) LIKE ?)\n"
                + dateinput1
                + dateinput2
                + "AND b.StatusID = 2 \n"
                + "ORDER BY " + sortElement + " " + sortOrder + ";";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, key);
            ps.setString(2, key);
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

    //retrieves a list of posts based on search criteria, pagination, and sorting order: BaoMV
    public ArrayList<Post> getAllPost(String searchID, String index, String sql2, String order) {
        ArrayList<Post> listPost = new ArrayList<>();
        UserDAO udao = new UserDAO();
        String sql = "SELECT * FROM [dbo].[Post] p, [User] u where p.UserID = u.UserID and "
                + "("
                + "PostID like '%" + searchID + "%' "
                + "or UserName like '%" + searchID + "%' "
                + "or Title like '%" + searchID + "%' "
                + ")" + sql2 + " order by " + order + "PostID offset ? rows fetch next 9 rows only";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            int page = Integer.parseInt(index);
            ps.setInt(1, (page - 1) * 9);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String postid = rs.getString(1);
                String title = rs.getString(2);
                String Content = rs.getString(3);
                String Date = rs.getString(4);
                String UserID = udao.getUser(Integer.parseInt(rs.getString(5))).getUserName();
                String StatusID = getPostStatusName(rs.getString(6));
                String PostType = rs.getString(7);
                String View = rs.getString(8);
                listPost.add(new Post(postid, title, Content, Date, UserID, StatusID, PostType, View));
            }
        } catch (Exception e) {
            System.out.println("getAllPost: " + e.getMessage());
        }
        return listPost;
    }

    //get post sattus: BaoMV
    public String getPostStatusName(String statusID) {
        switch (statusID) {
            case "1":
                return "Waiting";
            case "2":
                return "Approved";
        }
        return "Denied";
    }

    //calculates the total number of posts based on search criteria and sorting order: BaoMV
    public int getTotalPost(String searchID, String sql2, String order) {
        String sql = "SELECT * FROM [dbo].[Post] p, [User] u where p.UserID = u.UserID and "
                + "("
                + "PostID like '%" + searchID + "%'"
                + "or UserName like '%" + searchID + "%'"
                + "or Title like '%" + searchID + "%'"
                + ")" + sql2 + " order by " + order + "PostID";
        int count = 0;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            System.out.println("getTotalPost: " + e.getMessage());
        }
        return count;
    }

    //delete a post: BaoMV
    public void deletePost(String id) {
        String sql = "delete from [dbo].[Post] where PostID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deletePost: " + e.getMessage());
        }
    }

    //get post by id:BaoMV
    public Post getPostById(String id) {
        String sql = "select * from [dbo].[Post] where PostID = ?";
        UserDAO udao = new UserDAO();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String postid = rs.getString(1);
                String title = rs.getString(2);
                String Content = rs.getString(3);
                String Date = rs.getString(4);
                String UserID = udao.getUser(Integer.parseInt(rs.getString(5))).getUserName();
                String StatusID = getPostStatusName(rs.getString(6));
                String PostType = rs.getString(7);
                String View = rs.getString(8);
                return new Post(postid, title, Content, Date, UserID, StatusID, PostType, View);
            }
        } catch (Exception e) {
            System.out.println("getPostById: " + e.getMessage());
        }
        return new Post();
    }

    //update a post: BaoMV
    public void updatePost(String id, String title, String content, String statusId, String postType) {
        String sql = "update [dbo].[Post] set title=?, content=?, statusId=?, postType = ? where PostID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(5, id);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, statusId);
            ps.setString(4, postType);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("updatePost: " + e.getMessage());
        }
    }

    // get all post type: BaoMV
    public ArrayList<Integer> getAllPostType() {
        String sql = "select distinct posttype from post";
        ArrayList<Integer> listPostType = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listPostType.add(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println("getAllPostType: " + e.getMessage());
        }
        return listPostType;
    }

    //get total number of posts in each status category: BaoMV
    public ArrayList<String> getTotalPostByStatus() {
        String sql = "select ps.statusid, count(p.PostID) as 'total' \n"
                + "  from Post p right join PostStatus ps \n"
                + "  on p.StatusID = ps.StatusID\n"
                + "  group by ps.StatusID \n"
                + "  order by statusid";
        ArrayList<String> listStatus = new ArrayList<>();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(2);
                listStatus.add(count + "");
            }
        } catch (Exception e) {
            System.out.println("getTotalPostByStatus: " + e.getMessage());
        }
        return listStatus;
    }

    //get all postID by category: ThanhNX
    public ArrayList<Integer> getPostIDByCategory(int category) {
        ArrayList<Integer> idList = new ArrayList<>();
        String command = "SELECT p.PostID\n"
                + "FROM PostCategory p\n"
                + "WHERE p.CategoryID = ?\n"
                + "";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setInt(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                idList.add(rs.getInt("PostID"));
            }
        } catch (Exception e) {
        }
        return idList;
    }

    //get all category of post: ThanhNX
    public ArrayList<Map<String, String>> getPostCategory() {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String command = "SELECT c.CategoryID\n"
                + "	   , c.CategoryName\n"
                + "	   , c.[Description]\n"
                + "FROM Category c\n"
                + "WHERE c.ObjectTypeID = 2\n"
                + "	  AND c.Status = 1";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("CategoryID", rs.getString("CategoryID"));
                dataMap.put("CategoryName", rs.getString("CategoryName"));
                dataMap.put("Description", rs.getString("Description"));
                dataList.add(dataMap);
            }
        } catch (Exception e) {
        }
        return dataList;
    }

    //add a post into post table and get its ID: ThanhNX
    public String addPostAndGetID(String content, String userID, String Title) {
        String postID = "";
        String command = "INSERT INTO [dbo].[Post]\n"
                + "           ([Title]\n"
                + "           ,[Content]\n"
                + "           ,[Date]\n"
                + "           ,[UserID]\n"
                + "           ,[StatusID]\n"
                + "           ,[View])\n"
                + "VALUES (?, ?, GETDATE(), ?, 1, 0);\n"
                + "SELECT SCOPE_IDENTITY() AS PostID;";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, Title);
            ps.setString(2, content);
            ps.setString(3, userID);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                postID = generatedKeys.getString("PostID");
            }
        } catch (Exception e) {
        }
        return postID;
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

        ArrayList<Map<String, String>> dataList2 = new PostDAO().getPostCategory();
        for (int i = 0; i < dataList2.size(); i++) {
            System.out.println(dataList2.get(i).toString());
        }

        ArrayList<Integer> test3 = new PostDAO().getPostIDByCategory(13);
        for (int i = 0; i < test3.size(); i++) {
            System.out.println(test3.get(i));
        }

        System.out.println(new ProductDAO().getCommentNumberByProductID("5"));
    }
}
