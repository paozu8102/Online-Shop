/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Nhat Anh
 */
public class PostAndBlogDAO extends DBContext {

    //get the top 4 viewed posts in DB: ThanhNX
    public ArrayList<Map<String, String>> getTop4Posts() {
        ArrayList<Map<String, String>> dataList = new ArrayList<>();
        String command = "SELECT TOP 4\n"
                + "    b.BlogID,\n"
                + "    b.Title,\n"
                + "    CONVERT(varchar, b.[Date], 103) + ' ' + CONVERT(varchar, b.[Date], 108) AS DateTime,\n"
                + "    CONCAT(SUBSTRING(b.Content, 1, 100), '...') AS Content,\n"
                + "    i.ImageUrl,\n"
                + "    u.UserName,\n"
                + "    (\n"
                + "        SELECT COUNT(*)\n"
                + "        FROM Comment AS c\n"
                + "        WHERE c.TypeID = 2\n"
                + "        AND c.ObjectID = b.BlogID\n"
                + "    ) AS CommentNumber\n"
                + "FROM Blog AS b\n"
                + "LEFT JOIN\n"
                + "    (\n"
                + "        SELECT ObjectID, MIN(ImageUrl) AS ImageUrl\n"
                + "        FROM [Image]\n"
                + "        GROUP BY ObjectID, TypeID\n"
                + "        HAVING TypeID = 2\n"
                + "    ) AS i\n"
                + "ON b.BlogID = i.ObjectID\n"
                + "JOIN [User] AS u\n"
                + "ON u.UserID = b.UserID\n"
                + "ORDER BY [View] DESC;";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("BlogID", rs.getString("BlogID"));
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


    //get blog or post by id: ThanhNX
    public void checkPostOrBlogID(String id) {
        
    }

    

    public static void main(String[] args) {
        ArrayList<Map<String, String>> dataList = new PostAndBlogDAO().getTop4Posts();
        for (int i = 0; i < dataList.size(); i++) {
            System.out.println(dataList.get(i));
        }
    }
}
