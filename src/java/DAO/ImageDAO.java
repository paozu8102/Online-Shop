/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBcontext.DBContext;
import Model.Image;
import Model.Product;
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
        String sql = "SELECT ImageID, TypeID, ObjectID, ImageUrl\n" +
"FROM [dbo].[Image]\n" +
"WHERE ObjectID = ? AND TypeID = 1;";

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

}
