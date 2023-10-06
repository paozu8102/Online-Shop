/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
}
