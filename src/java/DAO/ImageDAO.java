/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static Controller.CreatePostController.generateUniqueFileName;
import DBcontext.DBContext;
import Model.Image;
import Model.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Anh
 */
public class ImageDAO extends DBContext {

    //get all image of a post: ThanhNX
    public ArrayList<Image> getPostImageByID(String id) {
        ArrayList<Image> imageList = new ArrayList<>();
        String command = "SELECT i.ImageID,\n"
                + "	   i.TypeID,\n"
                + "	   i.ObjectID,\n"
                + "	   i.ImageUrl\n"
                + "FROM [Image] i\n"
                + "WHERE TypeID = 2 \n"
                + "AND ObjectID = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int imageID = rs.getInt("ImageID");
                int TypeID = rs.getInt("TypeID");
                int ObjectID = rs.getInt("ObjectID");
                String ImageUrl = rs.getString("ImageUrl");
                imageList.add(new Image(imageID, TypeID, ObjectID, ImageUrl));
            }
        } catch (Exception e) {
        }
        return imageList;
    }

    public List<Image> getProductImage(String id) {
        List<Image> list = new ArrayList<>();
        String sql = "SELECT ImageID, TypeID, ObjectID, ImageUrl\n"
                + "FROM [dbo].[Image]\n"
                + "WHERE ObjectID = ? AND TypeID = 1;";

        try {
            PreparedStatement st = getConnection().prepareStatement(sql);
            st.setString(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                list.add(new Image(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4)
                ));
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

    //retrieve a list of images with a TypeID of 3(slider) from the database: BaoMV
    public ArrayList<Image> getAllImageWithType3(String searchID, String index) {
        ArrayList<Image> imageList = new ArrayList<>();
        String sql = "select * from [dbo].[Image] where [TypeID] = 3 and ImageID like '%" + searchID + "%'  order by ImageID offset ? rows fetch next 9 rows only";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            int page = Integer.parseInt(index);
            ps.setInt(1, (page - 1) * 9);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int typeid = rs.getInt(2);
                int objectid = rs.getInt(3);
                String url = rs.getString(4);
                imageList.add(new Image(id, typeid, objectid, url));
            }
        } catch (Exception e) {
            System.out.println("getAllImageWithType3: " + e.getMessage());
        }
        return imageList;
    }

    //deletes an image: BaoMV
    public void deleteImage(String id) {
        String sql = "delete from [dbo].[Image] where [ImageId] = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteImage: " + e.getMessage());
        }
    }

    //calculates the total number of images with a TypeID of 3(slider): BaoMV
    public int getTotalImageByTypeId3(String searchID) {
        String sql = "select * from [dbo].[Image] where [TypeID] = 3 and ImageID like '%" + searchID + "%'  order by ImageID";
        int count = 0;
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (Exception e) {
            System.out.println("getTotalImageByTypeId3: " + e.getMessage());
        }
        return count;
    }

    //retrieves an image by ID: BaoMV
    public Image getImageById(String id) {
        String sql = "select * from [dbo].[Image] where [TypeID] = 3 and ImageID = ?";
        Image image = new Image();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int typeid = rs.getInt(2);
                int objectid = rs.getInt(3);
                String url = rs.getString(4);
                return new Image(Integer.parseInt(id), typeid, objectid, url);
            }
        } catch (Exception e) {
            System.out.println("getAllImageWithType3: " + e.getMessage());
        }
        return image;
    }

    //update an image: BaoMV
    public void updateImage(String id, String imageURL) {
        String sql = "update [dbo].[Image] set [ImageUrl] = ? where [ImageID] = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(2, id);
            ps.setString(1, imageURL);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateImage: " + e.getMessage());
        }
    }

    //add a new image: BaoMV
    public void addNew(String imageURL) {
        String sql = "insert into [dbo].[Image] values (3,0,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, imageURL);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addNew: " + e.getMessage());
        }
    }

    //add new post image to database: ThanhNX
    public void addNewPostImage(String imageUrl, String postID) {
        String command = "INSERT INTO [dbo].[Image]\n"
                + "           ([TypeID]\n"
                + "           ,[ObjectID]\n"
                + "           ,[ImageUrl])\n"
                + "VALUES(2, ?, ?);";
        try {
            PreparedStatement ps = getConnection().prepareStatement(command);
            ps.setString(1, postID);
            ps.setString(2, imageUrl);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
