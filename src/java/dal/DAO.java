/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import DBcontext.DBContext;

import entity.Setting;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DAO extends DBContext {

    

    public List<Setting> getAllSetting() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status FROM Category\n"
                + "UNION\n"
                + "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status FROM Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {

        }

        return list;
    }
  public List<Setting> getAllSettingCat() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT CategoryID AS ID, CategoryName AS Name, 'Product Category' AS Type ,Status FROM Category";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {

        }

        return list;
    }
  
    public List<Setting> getAllSettingTyp() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT TypeID AS ID, TypeName AS Name, 'Product Type' AS Type, Status FROM Type";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                list.add(new Setting(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
        } catch (SQLException e) {

        }

        return list;
    }
  
  
  
  
}
